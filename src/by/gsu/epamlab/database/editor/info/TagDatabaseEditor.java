package by.gsu.epamlab.database.editor.info;

import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_CONNECTION_ACCESS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.gsu.epamlab.action.CheckData;
import by.gsu.epamlab.bean.Tag;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.data.IActorTagDAO;
import by.gsu.epamlab.database.dao.data.ITrackTagDAO;
import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.dao.user.IUserTagDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.editor.data.ActorTagDatabaseEditor;
import by.gsu.epamlab.database.editor.data.TrackTagDatabaseEditor;
import by.gsu.epamlab.database.editor.user.UserTagDatabaseEditor;
import by.gsu.epamlab.database.queries.info.TagDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.info.TagColumns;
import by.gsu.epamlab.exception.DatabaseException;

public class TagDatabaseEditor extends DatabaseEditor implements ITagDAO {

    private final PreparedStatement PS_ADD_TAG;
    private final PreparedStatement PS_INC_RATING;
    private final PreparedStatement PS_DEC_RATING;
    private final PreparedStatement PS_GET_TAG;
    private final PreparedStatement PS_GET_TAGS;
    private final PreparedStatement PS_GET_ID;
    private final PreparedStatement PS_GET_RATING;
    private final PreparedStatement PS_DELETE_TAG;

    {
        try {
            PS_ADD_TAG = con.prepareStatement(TagDatabaseQueries.addTag());
            PS_INC_RATING = con.prepareStatement(TagDatabaseQueries.incRating());
            PS_DEC_RATING = con.prepareStatement(TagDatabaseQueries.decRating());
            PS_GET_TAG = con.prepareStatement(TagDatabaseQueries.getTag());
            PS_GET_TAGS = con.prepareStatement(TagDatabaseQueries.getTags());
            PS_GET_ID = con.prepareStatement(TagDatabaseQueries.getId());
            PS_GET_RATING = con.prepareStatement(TagDatabaseQueries.getRating());
            PS_DELETE_TAG = con.prepareStatement(TagDatabaseQueries.deleteTag());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TagDatabaseEditor() throws DatabaseException {
        super();
    }

    public TagDatabaseEditor(final Connection connection) throws DatabaseException {
        super(connection);
    }

    @Override
    public void addTag(final String name) throws DatabaseException {
        try {
            TagDatabaseQueries.Prepared.addTag(PS_ADD_TAG, name);

            synchronized (TagDatabaseEditor.class) {
                PS_ADD_TAG.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void incRating(final int tagId) throws DatabaseException {
        try {
            TagDatabaseQueries.Prepared.changeRating(PS_INC_RATING, tagId);

            synchronized (TagDatabaseEditor.class) {
                PS_INC_RATING.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void decRating(final int tagId) throws DatabaseException {
        try {
            TagDatabaseQueries.Prepared.changeRating(PS_DEC_RATING, tagId);

            synchronized (TagDatabaseEditor.class) {
                PS_DEC_RATING.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public Tag getTag(final int tagId) throws DatabaseException {
        try {
            TagDatabaseQueries.Prepared.getTag(PS_GET_TAG, tagId);
            ResultSet rs = PS_GET_TAG.executeQuery();

            CheckData.hasNext(rs);

            final int id = rs.getInt(TagColumns.ID.toString());
            final String tag = rs.getString(TagColumns.NAME.toString());
            final int rating = rs.getInt(TagColumns.RATING.toString());

            return new Tag(id, tag, rating);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Tag> getTags() throws DatabaseException {
        try {
            final List<Tag> tags = new LinkedList<>();
            ResultSet rs = PS_GET_TAGS.executeQuery();

            while (rs.next()) {
                final int id = rs.getInt(TagColumns.ID.toString());
                final String name = rs.getString(TagColumns.NAME.toString());
                final int rating = rs.getInt(TagColumns.RATING.toString());

                tags.add(new Tag(id, name, rating));
            }
            return tags;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public int getId(final String name) throws DatabaseException {
        try {
            TagDatabaseQueries.Prepared.getId(PS_GET_ID, name);
            ResultSet rs = PS_GET_ID.executeQuery();

            CheckData.hasNext(rs);

            return rs.getInt(TagColumns.ID.toString());
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public int getRating(final int tagId) throws DatabaseException {
        try {
            TagDatabaseQueries.Prepared.getRating(PS_GET_RATING, tagId);
            ResultSet rs = PS_GET_RATING.executeQuery();

            CheckData.hasNext(rs);

            return rs.getInt(TagColumns.RATING.toString());
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTag(final String name) throws DatabaseException {
        try {
            try (ITrackTagDAO trackTagDAO = new TrackTagDatabaseEditor()) {
                trackTagDAO.deleteTag(name);
            }

            try (IActorTagDAO actorTagDAO = new ActorTagDatabaseEditor()) {
                actorTagDAO.deleteTag(name);
            }

            try (IUserTagDAO userTagDAO = new UserTagDatabaseEditor()) {
                userTagDAO.deleteTag(name);
            }

            TagDatabaseQueries.Prepared.deleteTag(PS_DELETE_TAG, name);
            PS_DELETE_TAG.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        DatabaseManager.closePreparedStatements(PS_ADD_TAG, PS_INC_RATING, PS_DEC_RATING,
                PS_GET_TAG, PS_GET_TAGS, PS_GET_ID, PS_GET_RATING, PS_DELETE_TAG);
        super.close();
    }

}
