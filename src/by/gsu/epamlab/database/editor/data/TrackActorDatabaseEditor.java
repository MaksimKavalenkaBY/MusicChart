package by.gsu.epamlab.database.editor.data;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.ExceptionConstants.ERROR_CONNECTION_ACCESS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.data.ITrackActorDAO;
import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.data.TrackActorDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.data.TrackActorColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.ActorFactory;
import by.gsu.epamlab.factory.info.TrackFactory;

public class TrackActorDatabaseEditor extends DatabaseEditor implements ITrackActorDAO {

    private final ITrackDAO         TRACK_DAO;
    private final IActorDAO         ACTOR_DAO;

    private final PreparedStatement PS_ADD_TRACK_ACTOR;
    private final PreparedStatement PS_GET_TRACKS;
    private final PreparedStatement PS_GET_COUNT_TRACKS;
    private final PreparedStatement PS_DELETE_TRACK;
    private final PreparedStatement PS_DELETE_ACTOR;
    private final PreparedStatement PS_DELETE_TRACK_ACTOR;

    {
        TRACK_DAO = TrackFactory.getEditor(con);
        ACTOR_DAO = ActorFactory.getEditor(con);

        try {
            PS_ADD_TRACK_ACTOR = con.prepareStatement(TrackActorDatabaseQueries.addTrackActor());
            PS_GET_TRACKS = con.prepareStatement(TrackActorDatabaseQueries.getTracks());
            PS_GET_COUNT_TRACKS = con.prepareStatement(TrackActorDatabaseQueries.getCountTracks());
            PS_DELETE_TRACK = con.prepareStatement(TrackActorDatabaseQueries.deleteTrack());
            PS_DELETE_ACTOR = con.prepareStatement(TrackActorDatabaseQueries.deleteActor());
            PS_DELETE_TRACK_ACTOR = con
                    .prepareStatement(TrackActorDatabaseQueries.deleteTrackActor());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TrackActorDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public void addTrackActor(final String trackName, final String actorName)
            throws DatabaseException {
        try {
            final int trackId = TRACK_DAO.getId(trackName);
            final int actorId = ACTOR_DAO.getId(actorName);

            TrackActorDatabaseQueries.Prepared.addTrackActor(PS_ADD_TRACK_ACTOR, trackId, actorId);

            synchronized (TrackActorDatabaseEditor.class) {
                PS_ADD_TRACK_ACTOR.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getTracks(final String actorName) throws DatabaseException {
        try {
            final List<Track> tracks = new LinkedList<>();
            final int actorId = ACTOR_DAO.getId(actorName);

            TrackActorDatabaseQueries.Prepared.getTracks(PS_GET_TRACKS, actorId);
            ResultSet rs = PS_GET_TRACKS.executeQuery();

            while (rs.next()) {
                final int trackId = rs.getInt(TrackActorColumns.ID_TRACK.toString());
                final Track track = TRACK_DAO.getTrack(trackId);
                tracks.add(track);
            }
            return tracks;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getCountTracks(final String actorName, final int page)
            throws DatabaseException {
        try {
            final List<Track> tracks = new ArrayList<>(COUNT_ELEMENTS);
            final int actorId = ACTOR_DAO.getId(actorName);
            final int number = (page - 1) * COUNT_ELEMENTS;

            TrackActorDatabaseQueries.Prepared.getCountTracks(PS_GET_COUNT_TRACKS, actorId, number);
            ResultSet rs = PS_GET_COUNT_TRACKS.executeQuery();

            while (rs.next()) {
                final int trackId = rs.getInt(TrackActorColumns.ID_TRACK.toString());
                final Track track = TRACK_DAO.getTrack(trackId);
                tracks.add(track);
            }
            return tracks;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTrack(final String trackName) throws DatabaseException {
        try {
            final int trackId = TRACK_DAO.getId(trackName);
            TrackActorDatabaseQueries.Prepared.deleteTrack(PS_DELETE_TRACK, trackId);
            PS_DELETE_TRACK.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteActor(final String actorName) throws DatabaseException {
        try {
            final int actorId = ACTOR_DAO.getId(actorName);
            TrackActorDatabaseQueries.Prepared.deleteActor(PS_DELETE_ACTOR, actorId);
            PS_DELETE_ACTOR.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTrackActor(final String trackName, final String actorName)
            throws DatabaseException {
        try {
            final int trackId = TRACK_DAO.getId(trackName);
            final int actorId = ACTOR_DAO.getId(actorName);
            TrackActorDatabaseQueries.Prepared.deleteTrackActor(PS_DELETE_TRACK_ACTOR, trackId,
                    actorId);
            PS_DELETE_TRACK_ACTOR.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        TRACK_DAO.close();
        ACTOR_DAO.close();
        DatabaseManager.closePreparedStatements(PS_ADD_TRACK_ACTOR, PS_GET_TRACKS,
                PS_GET_COUNT_TRACKS, PS_DELETE_TRACK, PS_DELETE_ACTOR, PS_DELETE_TRACK_ACTOR);
        super.close();
    }

}
