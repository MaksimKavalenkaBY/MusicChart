package by.gsu.epamlab.database.editor.user;

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
import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.dao.user.IUserActorDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.user.UserActorDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.user.UserActorColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.ActorFactory;

public class UserActorDatabaseEditor extends DatabaseEditor implements IUserActorDAO {

    private final IActorDAO         ACTOR_DAO;

    private final PreparedStatement PS_CHECK_USER_ACTOR;
    private final PreparedStatement PS_ADD_USER_ACTOR;
    private final PreparedStatement PS_DELETE_USER_ACTOR;
    private final PreparedStatement PS_GET_ACTORS;
    private final PreparedStatement PS_GET_COUNT_ACTORS;
    private final PreparedStatement PS_DELETE_ACTOR;

    {
        ACTOR_DAO = ActorFactory.getEditor(con);

        try {
            PS_CHECK_USER_ACTOR = con.prepareStatement(UserActorDatabaseQueries.checkUserActor());
            PS_ADD_USER_ACTOR = con.prepareStatement(UserActorDatabaseQueries.addUserActor());
            PS_DELETE_USER_ACTOR = con.prepareStatement(UserActorDatabaseQueries.deleteUserActor());
            PS_GET_ACTORS = con.prepareStatement(UserActorDatabaseQueries.getActors());
            PS_GET_COUNT_ACTORS = con.prepareStatement(UserActorDatabaseQueries.getCountActors());
            PS_DELETE_ACTOR = con.prepareStatement(UserActorDatabaseQueries.deleteActor());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserActorDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public int likeUserActor(final int userId, final String actorName) throws DatabaseException {
        try {
            int actorId = ACTOR_DAO.getId(actorName);

            UserActorDatabaseQueries.Prepared.likeUserActor(PS_CHECK_USER_ACTOR, userId, actorId);
            ResultSet rs = PS_CHECK_USER_ACTOR.executeQuery();

            if (rs.next()) {
                UserActorDatabaseQueries.Prepared.likeUserActor(PS_DELETE_USER_ACTOR, userId,
                        actorId);
                PS_DELETE_USER_ACTOR.executeUpdate();
                ACTOR_DAO.decRating(actorId);
            } else {
                UserActorDatabaseQueries.Prepared.likeUserActor(PS_ADD_USER_ACTOR, userId, actorId);
                PS_ADD_USER_ACTOR.executeUpdate();
                ACTOR_DAO.incRating(actorId);
            }
            return ACTOR_DAO.getRating(actorId);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Actor> getActors(final int userId) throws DatabaseException {
        try {
            final List<Actor> actors = new LinkedList<>();
            UserActorDatabaseQueries.Prepared.getActors(PS_GET_ACTORS, userId);
            ResultSet rs = PS_GET_ACTORS.executeQuery();

            while (rs.next()) {
                final int actorId = rs.getInt(UserActorColumns.ID_ACTOR.toString());
                final Actor actor = ACTOR_DAO.getActor(actorId);
                actors.add(actor);
            }
            return actors;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Actor> getCountActors(final int userId, final int page) throws DatabaseException {
        try {
            final List<Actor> actors = new ArrayList<>(COUNT_ELEMENTS);
            final int number = (page - 1) * COUNT_ELEMENTS;
            UserActorDatabaseQueries.Prepared.getCountActors(PS_GET_COUNT_ACTORS, userId, number);
            ResultSet rs = PS_GET_COUNT_ACTORS.executeQuery();

            while (rs.next()) {
                final int actorId = rs.getInt(UserActorColumns.ID_ACTOR.toString());
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
            UserActorDatabaseQueries.Prepared.deleteActor(PS_DELETE_ACTOR, actorId);
            PS_DELETE_ACTOR.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        ACTOR_DAO.close();
        DatabaseManager.closePreparedStatements(PS_CHECK_USER_ACTOR, PS_ADD_USER_ACTOR,
                PS_DELETE_USER_ACTOR, PS_GET_ACTORS, PS_GET_COUNT_ACTORS, PS_DELETE_ACTOR);
        super.close();
    }

}
