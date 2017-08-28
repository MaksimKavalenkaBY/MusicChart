package by.gsu.epamlab.database.editor.user;

import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_CONNECTION_ACCESS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.gsu.epamlab.bean.Tag;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.dao.user.IUserTagDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.user.UserTagDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.user.UserTagColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.TagFactory;

public class UserTagDatabaseEditor extends DatabaseEditor implements IUserTagDAO {

    private final ITagDAO           TAG_DAO;

    private final PreparedStatement PS_CHECK_USER_TAG;
    private final PreparedStatement PS_ADD_USER_TAG;
    private final PreparedStatement PS_DELETE_USER_TAG;
    private final PreparedStatement PS_GET_TAGS;
    private final PreparedStatement PS_DELETE_TAG;

    {
        TAG_DAO = TagFactory.getEditor(con);

        try {
            PS_CHECK_USER_TAG = con.prepareStatement(UserTagDatabaseQueries.checkUserTag());
            PS_ADD_USER_TAG = con.prepareStatement(UserTagDatabaseQueries.addUserTag());
            PS_DELETE_USER_TAG = con.prepareStatement(UserTagDatabaseQueries.deleteUserTag());
            PS_GET_TAGS = con.prepareStatement(UserTagDatabaseQueries.getTags());
            PS_DELETE_TAG = con.prepareStatement(UserTagDatabaseQueries.deleteTag());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserTagDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public int likeUserTag(final int userId, final String tagName) throws DatabaseException {
        try {
            int tagId = TAG_DAO.getId(tagName);

            UserTagDatabaseQueries.Prepared.likeUserTag(PS_CHECK_USER_TAG, userId, tagId);
            ResultSet rs = PS_CHECK_USER_TAG.executeQuery();

            if (rs.next()) {
                UserTagDatabaseQueries.Prepared.likeUserTag(PS_DELETE_USER_TAG, userId, tagId);
                PS_DELETE_USER_TAG.executeUpdate();
                TAG_DAO.decRating(tagId);
            } else {
                UserTagDatabaseQueries.Prepared.likeUserTag(PS_ADD_USER_TAG, userId, tagId);
                PS_ADD_USER_TAG.executeUpdate();
                TAG_DAO.incRating(tagId);
            }
            return TAG_DAO.getRating(tagId);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Tag> getTags(final int userId) throws DatabaseException {
        try {
            final List<Tag> tags = new LinkedList<>();
            UserTagDatabaseQueries.Prepared.getTags(PS_GET_TAGS, userId);
            ResultSet rs = PS_GET_TAGS.executeQuery();

            while (rs.next()) {
                final int tagId = rs.getInt(UserTagColumns.ID_TAG.toString());
                final Tag tag = TAG_DAO.getTag(tagId);
                tags.add(tag);
            }
            return tags;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTag(final String tagName) throws DatabaseException {
        try {
            final int tagId = TAG_DAO.getId(tagName);
            UserTagDatabaseQueries.Prepared.deleteTag(PS_DELETE_TAG, tagId);
            PS_DELETE_TAG.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        TAG_DAO.close();
        DatabaseManager.closePreparedStatements(PS_CHECK_USER_TAG, PS_ADD_USER_TAG,
                PS_DELETE_USER_TAG, PS_GET_TAGS, PS_DELETE_TAG);
        super.close();
    }

}
