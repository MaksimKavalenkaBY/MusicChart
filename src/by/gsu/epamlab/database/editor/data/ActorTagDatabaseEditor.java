package by.gsu.epamlab.database.editor.data;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_CONNECTION_ACCESS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.data.IActorTagDAO;
import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.data.ActorTagDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.data.ActorTagColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.ActorFactory;
import by.gsu.epamlab.factory.info.TagFactory;

public class ActorTagDatabaseEditor extends DatabaseEditor implements IActorTagDAO {

    private final IActorDAO         ACTOR_DAO;
    private final ITagDAO           TAG_DAO;

    private final PreparedStatement PS_ADD_ACTOR_TAG;
    private final PreparedStatement PS_GET_ACTORS;
    private final PreparedStatement PS_GET_COUNT_ACTORS;
    private final PreparedStatement PS_DELETE_ACTOR;
    private final PreparedStatement PS_DELETE_TAG;
    private final PreparedStatement PS_DELETE_ACTOR_TAG;

    {
        ACTOR_DAO = ActorFactory.getEditor(con);
        TAG_DAO = TagFactory.getEditor(con);

        try {
            PS_ADD_ACTOR_TAG = con.prepareStatement(ActorTagDatabaseQueries.addActorTag());
            PS_GET_ACTORS = con.prepareStatement(ActorTagDatabaseQueries.getActors());
            PS_GET_COUNT_ACTORS = con.prepareStatement(ActorTagDatabaseQueries.getCountActors());
            PS_DELETE_ACTOR = con.prepareStatement(ActorTagDatabaseQueries.deleteActor());
            PS_DELETE_TAG = con.prepareStatement(ActorTagDatabaseQueries.deleteTag());
            PS_DELETE_ACTOR_TAG = con.prepareStatement(ActorTagDatabaseQueries.deleteActorTag());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ActorTagDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public void addActorTag(final String actorName, final String tagName) throws DatabaseException {
        try {
            final int actorId = ACTOR_DAO.getId(actorName);
            final int tagId = TAG_DAO.getId(tagName);

            ActorTagDatabaseQueries.Prepared.addActorTag(PS_ADD_ACTOR_TAG, actorId, tagId);

            synchronized (TrackTagDatabaseEditor.class) {
                PS_ADD_ACTOR_TAG.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Actor> getActors(final String tagName) throws DatabaseException {
        try {
            final List<Actor> actors = new LinkedList<>();
            final int tagId = TAG_DAO.getId(tagName);

            ActorTagDatabaseQueries.Prepared.getActors(PS_GET_ACTORS, tagId);
            ResultSet rs = PS_GET_ACTORS.executeQuery();

            while (rs.next()) {
                final int actorId = rs.getInt(ActorTagColumns.ID_ACTOR.toString());
                final Actor actor = ACTOR_DAO.getActor(actorId);
                actors.add(actor);
            }
            return actors;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Actor> getCountActors(final String tagName, final int page)
            throws DatabaseException {
        try {
            final List<Actor> actors = new ArrayList<>(COUNT_ELEMENTS);
            final int tagId = TAG_DAO.getId(tagName);
            final int number = (page - 1) * COUNT_ELEMENTS;

            ActorTagDatabaseQueries.Prepared.getCountActors(PS_GET_COUNT_ACTORS, tagId, number);
            ResultSet rs = PS_GET_COUNT_ACTORS.executeQuery();

            while (rs.next()) {
                final int actorId = rs.getInt(ActorTagColumns.ID_ACTOR.toString());
                final Actor actor = ACTOR_DAO.getActor(actorId);
                actors.add(actor);
            }
            return actors;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteActor(final String actorName) throws DatabaseException {
        try {
            final int actorId = ACTOR_DAO.getId(actorName);
            ActorTagDatabaseQueries.Prepared.deleteActor(PS_DELETE_ACTOR, actorId);
            PS_DELETE_ACTOR.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTag(final String tagName) throws DatabaseException {
        try {
            final int tagId = TAG_DAO.getId(tagName);
            ActorTagDatabaseQueries.Prepared.deleteTag(PS_DELETE_TAG, tagId);
            PS_DELETE_TAG.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteActorTag(final String actorName, final String tagName)
            throws DatabaseException {
        try {
            final int actorId = ACTOR_DAO.getId(actorName);
            final int tagId = TAG_DAO.getId(tagName);
            ActorTagDatabaseQueries.Prepared.deleteActorTag(PS_DELETE_ACTOR_TAG, actorId, tagId);
            PS_DELETE_ACTOR_TAG.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        ACTOR_DAO.close();
        TAG_DAO.close();
        DatabaseManager.closePreparedStatements(PS_ADD_ACTOR_TAG, PS_GET_ACTORS,
                PS_GET_COUNT_ACTORS, PS_DELETE_ACTOR, PS_DELETE_TAG, PS_DELETE_ACTOR_TAG);
        super.close();
    }

}
