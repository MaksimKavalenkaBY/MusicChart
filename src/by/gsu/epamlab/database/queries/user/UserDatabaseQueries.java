package by.gsu.epamlab.database.queries.user;

import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.constants.RoleConstants;
import by.gsu.epamlab.database.structure.columns.user.UserColumns;
import by.gsu.epamlab.database.structure.tables.UserTables;

public class UserDatabaseQueries {

    public static String getUser() {
        return SELECT + UserTables.USER + POINT + UserColumns.ID + COMMA + UserTables.USER + POINT
                + UserColumns.ROLE + FROM + UserTables.USER + WHERE + UserTables.USER + POINT
                + UserColumns.LOGIN + EQUALLY + PREPARED_SIGN + AND + UserColumns.PASSWORD + EQUALLY
                + PREPARED_SIGN;
    }

    public static String addUser() {
        return INSERT_INTO + UserTables.USER + BRACKET_OPEN + UserColumns.LOGIN + COMMA
                + UserColumns.PASSWORD + COMMA + UserColumns.ROLE + BRACKET_CLOSE + VALUES
                + BRACKET_OPEN + PREPARED_SIGN + COMMA + PREPARED_SIGN + COMMA + QUOTE + RoleConstants.USER
                + QUOTE + BRACKET_CLOSE;
    }

    public static String checkUser() {
        return SELECT + ALL + FROM + UserTables.USER + WHERE + UserTables.USER + POINT
                + UserColumns.LOGIN + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        private static final int LOGIN    = 1;
        private static final int PASSWORD = 2;

        public static void getUser(final PreparedStatement pStatement, final String login,
                final String password) throws SQLException {
            pStatement.setString(LOGIN, login);
            pStatement.setString(PASSWORD, password);
        }

        public static void addUser(final PreparedStatement pStatement, final String login,
                final String password) throws SQLException {
            pStatement.setString(LOGIN, login);
            pStatement.setString(PASSWORD, password);
        }

        public static void checkUser(final PreparedStatement pStatement, final String login)
                throws SQLException {
            pStatement.setString(LOGIN, login);
        }

    }

}
