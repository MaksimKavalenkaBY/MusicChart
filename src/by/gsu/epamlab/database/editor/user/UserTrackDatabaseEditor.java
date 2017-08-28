package by.gsu.epamlab.database.editor.user;

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
import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.dao.user.IUserTrackDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.user.UserTrackDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.user.UserTrackColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.TrackFactory;

public class UserTrackDatabaseEditor extends DatabaseEditor implements IUserTrackDAO {

    private final ITrackDAO         TRACK_DAO;

    private final PreparedStatement PS_CHECK_USER_TRACK;
    private final PreparedStatement PS_ADD_USER_TRACK;
    private final PreparedStatement PS_DELETE_USER_TRACK;
    private final PreparedStatement PS_GET_TRACKS;
    private final PreparedStatement PS_GET_COUNT_TRACKS;
    private final PreparedStatement PS_DELETE_TRACK;

    {
        TRACK_DAO = TrackFactory.getEditor(con);

        try {
            PS_CHECK_USER_TRACK = con.prepareStatement(UserTrackDatabaseQueries.checkUserTrack());
            PS_ADD_USER_TRACK = con.prepareStatement(UserTrackDatabaseQueries.addUserTrack());
            PS_DELETE_USER_TRACK = con.prepareStatement(UserTrackDatabaseQueries.deleteUserTrack());
            PS_GET_TRACKS = con.prepareStatement(UserTrackDatabaseQueries.getTracks());
            PS_GET_COUNT_TRACKS = con.prepareStatement(UserTrackDatabaseQueries.getCountTracks());
            PS_DELETE_TRACK = con.prepareStatement(UserTrackDatabaseQueries.deleteTrack());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserTrackDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public int likeUserTrack(final int userId, final String trackName) throws DatabaseException {
        try {
            int trackId = TRACK_DAO.getId(trackName);

            UserTrackDatabaseQueries.Prepared.likeUserTrack(PS_CHECK_USER_TRACK, userId, trackId);
            ResultSet rs = PS_CHECK_USER_TRACK.executeQuery();

            if (rs.next()) {
                UserTrackDatabaseQueries.Prepared.likeUserTrack(PS_DELETE_USER_TRACK, userId,
                        trackId);
                PS_DELETE_USER_TRACK.executeUpdate();
                TRACK_DAO.decRating(trackId);
            } else {
                UserTrackDatabaseQueries.Prepared.likeUserTrack(PS_ADD_USER_TRACK, userId, trackId);
                PS_ADD_USER_TRACK.executeUpdate();
                TRACK_DAO.incRating(trackId);
            }
            return TRACK_DAO.getRating(trackId);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getTracks(final int userId) throws DatabaseException {
        try {
            final List<Track> tracks = new LinkedList<>();
            UserTrackDatabaseQueries.Prepared.getTracks(PS_GET_TRACKS, userId);
            ResultSet rs = PS_GET_TRACKS.executeQuery();

            while (rs.next()) {
                final int trackId = rs.getInt(UserTrackColumns.ID_TRACK.toString());
                final Track track = TRACK_DAO.getTrack(trackId);
                tracks.add(track);
            }
            return tracks;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getCountTracks(final int userId, final int page) throws DatabaseException {
        try {
            final List<Track> tracks = new ArrayList<>(COUNT_ELEMENTS);
            final int number = (page - 1) * COUNT_ELEMENTS;
            UserTrackDatabaseQueries.Prepared.getCountTracks(PS_GET_COUNT_TRACKS, userId, number);
            ResultSet rs = PS_GET_COUNT_TRACKS.executeQuery();

            while (rs.next()) {
                final int trackId = rs.getInt(UserTrackColumns.ID_TRACK.toString());
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
            UserTrackDatabaseQueries.Prepared.deleteTrack(PS_DELETE_TRACK, trackId);
            PS_DELETE_TRACK.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        TRACK_DAO.close();
        DatabaseManager.closePreparedStatements(PS_CHECK_USER_TRACK, PS_ADD_USER_TRACK,
                PS_DELETE_USER_TRACK, PS_GET_TRACKS, PS_GET_COUNT_TRACKS, PS_DELETE_TRACK);
        super.close();
    }

}
