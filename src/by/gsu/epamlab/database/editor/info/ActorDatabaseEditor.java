package by.gsu.epamlab.database.editor.info;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_CONNECTION_ACCESS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import by.gsu.epamlab.action.CheckData;
import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.data.IActorTagDAO;
import by.gsu.epamlab.database.dao.data.ITrackActorDAO;
import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.dao.user.IUserActorDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.editor.data.ActorTagDatabaseEditor;
import by.gsu.epamlab.database.editor.data.TrackActorDatabaseEditor;
import by.gsu.epamlab.database.editor.user.UserActorDatabaseEditor;
import by.gsu.epamlab.database.queries.info.ActorDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.info.ActorColumns;
import by.gsu.epamlab.exception.DatabaseException;

public class ActorDatabaseEditor extends DatabaseEditor implements IActorDAO {

    private final PreparedStatement PS_ADD_FULL_ACTOR;
    private final PreparedStatement PS_ADD_ACTOR;
    private final PreparedStatement PS_INC_RATING;
    private final PreparedStatement PS_DEC_RATING;
    private final PreparedStatement PS_GET_ACTOR;
    private final PreparedStatement PS_GET_ACTORS;
    private final PreparedStatement PS_GET_COUNT_ACTORS;
    private final PreparedStatement PS_GET_ID;
    private final PreparedStatement PS_GET_RATING;
    private final PreparedStatement PS_DELETE_ACTOR;

    {
        try {
            PS_ADD_FULL_ACTOR = con.prepareStatement(ActorDatabaseQueries.addFullActor());
            PS_ADD_ACTOR = con.prepareStatement(ActorDatabaseQueries.addActor());
            PS_INC_RATING = con.prepareStatement(ActorDatabaseQueries.incRating());
            PS_DEC_RATING = con.prepareStatement(ActorDatabaseQueries.decRating());
            PS_GET_ACTOR = con.prepareStatement(ActorDatabaseQueries.getActor());
            PS_GET_ACTORS = con.prepareStatement(ActorDatabaseQueries.getActors());
            PS_GET_COUNT_ACTORS = con.prepareStatement(ActorDatabaseQueries.getCountActors());
            PS_GET_ID = con.prepareStatement(ActorDatabaseQueries.getId());
            PS_GET_RATING = con.prepareStatement(ActorDatabaseQueries.getRating());
            PS_DELETE_ACTOR = con.prepareStatement(ActorDatabaseQueries.deleteActor());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ActorDatabaseEditor() throws DatabaseException {
        super();
    }

    public ActorDatabaseEditor(final Connection connection) throws DatabaseException {
        super(connection);
    }

    @Override
    public void addActor(final String name, final String image) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.addFullActor(PS_ADD_FULL_ACTOR, name, image);

            synchronized (ActorDatabaseEditor.class) {
                PS_ADD_FULL_ACTOR.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void addActor(final String name) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.addActor(PS_ADD_ACTOR, name);

            synchronized (ActorDatabaseEditor.class) {
                PS_ADD_ACTOR.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void incRating(final int actorId) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.changeRating(PS_INC_RATING, actorId);

            synchronized (ActorDatabaseEditor.class) {
                PS_INC_RATING.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void decRating(final int actorId) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.changeRating(PS_DEC_RATING, actorId);

            synchronized (ActorDatabaseEditor.class) {
                PS_DEC_RATING.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public Actor getActor(final int actorId) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.getActor(PS_GET_ACTOR, actorId);
            ResultSet rs = PS_GET_ACTOR.executeQuery();

            CheckData.hasNext(rs);

            return getActorFromRS(rs);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Actor> getActors() throws DatabaseException {
        try {
            final List<Actor> actors = new LinkedList<>();
            ResultSet rs = PS_GET_ACTORS.executeQuery();

            while (rs.next()) {
                actors.add(getActorFromRS(rs));
            }
            return actors;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Actor> getCountActors(final int page) throws DatabaseException {
        try {
            final List<Actor> actors = new ArrayList<>(COUNT_ELEMENTS);
            final int number = (page - 1) * COUNT_ELEMENTS;
            ActorDatabaseQueries.Prepared.getCountActors(PS_GET_COUNT_ACTORS, number);
            ResultSet rs = PS_GET_COUNT_ACTORS.executeQuery();

            while (rs.next()) {
                actors.add(getActorFromRS(rs));
            }
            return actors;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public int getId(final String name) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.getId(PS_GET_ID, name);
            ResultSet rs = PS_GET_ID.executeQuery();

            CheckData.hasNext(rs);

            return rs.getInt(ActorColumns.ID.toString());
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public int getRating(final int actorId) throws DatabaseException {
        try {
            ActorDatabaseQueries.Prepared.getRating(PS_GET_RATING, actorId);
            ResultSet rs = PS_GET_RATING.executeQuery();

            CheckData.hasNext(rs);

            return rs.getInt(ActorColumns.RATING.toString());
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteActor(final String name) throws DatabaseException {
        try {
            try (ITrackActorDAO trackActorDAO = new TrackActorDatabaseEditor()) {
                trackActorDAO.deleteActor(name);
            }

            try (IActorTagDAO actorTagDAO = new ActorTagDatabaseEditor()) {
                actorTagDAO.deleteActor(name);
            }

            try (IUserActorDAO userActorDAO = new UserActorDatabaseEditor()) {
                userActorDAO.deleteActor(name);
            }

            ActorDatabaseQueries.Prepared.deleteActor(PS_DELETE_ACTOR, name);
            PS_DELETE_ACTOR.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        DatabaseManager.closePreparedStatements(PS_ADD_FULL_ACTOR, PS_ADD_ACTOR, PS_INC_RATING,
                PS_DEC_RATING, PS_GET_ACTOR, PS_GET_ACTORS, PS_GET_COUNT_ACTORS, PS_GET_ID,
                PS_GET_RATING, PS_DELETE_ACTOR);
        super.close();
    }

    private Actor getActorFromRS(final ResultSet rs) throws SQLException {
        final int id = rs.getInt(ActorColumns.ID.toString());
        final String track = rs.getString(ActorColumns.NAME.toString());
        final String image = rs.getString(ActorColumns.IMAGE.toString());
        final int rating = rs.getInt(ActorColumns.RATING.toString());

        if (CheckData.checkValue(image)) {
            return new Actor(id, track, image, rating);
        } else {
            return new Actor(id, track, rating);
        }
    }

}
