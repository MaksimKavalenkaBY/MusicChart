package by.gsu.epamlab.database.editor.user;

import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_CONNECTION_ACCESS;
import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_DOUBLE_LOGIN;
import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_LOGIN;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.RoleConstants;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.user.IUserDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.user.UserDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.user.UserColumns;
import by.gsu.epamlab.exception.DatabaseException;

public class UserDatabaseEditor extends DatabaseEditor implements IUserDAO {

    private final PreparedStatement PS_CHECK_USER;
    private final PreparedStatement PS_ADD_USER;
    private final PreparedStatement PS_GET_USER;

    {
        try {
            PS_CHECK_USER = con.prepareStatement(UserDatabaseQueries.checkUser());
            PS_ADD_USER = con.prepareStatement(UserDatabaseQueries.addUser());
            PS_GET_USER = con.prepareStatement(UserDatabaseQueries.getUser());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public void addUser(final String login, final String password) throws DatabaseException {
        try {
            UserDatabaseQueries.Prepared.checkUser(PS_CHECK_USER, login);
            UserDatabaseQueries.Prepared.addUser(PS_ADD_USER, login, password);

            synchronized (UserDatabaseEditor.class) {
                ResultSet rs = PS_CHECK_USER.executeQuery();
                if (rs.next()) {
                    throw new DatabaseException(ERROR_DOUBLE_LOGIN);
                }
                PS_ADD_USER.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public User getUser(final String login, final String password) throws DatabaseException {
        try {
            UserDatabaseQueries.Prepared.getUser(PS_GET_USER, login, password);
            ResultSet rs = PS_GET_USER.executeQuery();

            if (!rs.next()) {
                throw new DatabaseException(ERROR_LOGIN);
            }

            final int id = rs.getInt(UserColumns.ID.toString());
            final RoleConstants role = RoleConstants
                    .valueOf(rs.getString(UserColumns.ROLE.toString()).toUpperCase());
            return new User(id, login, role);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        DatabaseManager.closePreparedStatements(PS_CHECK_USER, PS_ADD_USER, PS_GET_USER);
        super.close();
    }

}
