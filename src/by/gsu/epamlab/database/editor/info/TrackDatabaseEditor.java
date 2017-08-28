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
import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.data.ITrackActorDAO;
import by.gsu.epamlab.database.dao.data.ITrackTagDAO;
import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.dao.user.IUserTrackDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.editor.data.TrackActorDatabaseEditor;
import by.gsu.epamlab.database.editor.data.TrackTagDatabaseEditor;
import by.gsu.epamlab.database.editor.user.UserTrackDatabaseEditor;
import by.gsu.epamlab.database.queries.info.TrackDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.info.TrackColumns;
import by.gsu.epamlab.exception.DatabaseException;

public class TrackDatabaseEditor extends DatabaseEditor implements ITrackDAO {

    private final PreparedStatement PS_ADD_FULL_TRACK;
    private final PreparedStatement PS_ADD_TRACK;
    private final PreparedStatement PS_INC_RATING;
    private final PreparedStatement PS_DEC_RATING;
    private final PreparedStatement PS_GET_TRACK;
    private final PreparedStatement PS_GET_TRACKS;
    private final PreparedStatement PS_GET_COUNT_TRACKS;
    private final PreparedStatement PS_GET_ID;
    private final PreparedStatement PS_GET_RATING;
    private final PreparedStatement PS_DELETE_TRACK;

    {
        try {
            PS_ADD_FULL_TRACK = con.prepareStatement(TrackDatabaseQueries.addFullTrack());
            PS_ADD_TRACK = con.prepareStatement(TrackDatabaseQueries.addTrack());
            PS_INC_RATING = con.prepareStatement(TrackDatabaseQueries.incRating());
            PS_DEC_RATING = con.prepareStatement(TrackDatabaseQueries.decRating());
            PS_GET_TRACK = con.prepareStatement(TrackDatabaseQueries.getTrack());
            PS_GET_TRACKS = con.prepareStatement(TrackDatabaseQueries.getTracks());
            PS_GET_COUNT_TRACKS = con.prepareStatement(TrackDatabaseQueries.getCountTracks());
            PS_GET_ID = con.prepareStatement(TrackDatabaseQueries.getId());
            PS_GET_RATING = con.prepareStatement(TrackDatabaseQueries.getRating());
            PS_DELETE_TRACK = con.prepareStatement(TrackDatabaseQueries.deleteTrack());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TrackDatabaseEditor() throws DatabaseException {
        super();
    }

    public TrackDatabaseEditor(final Connection connection) throws DatabaseException {
        super(connection);
    }

    @Override
    public void addTrack(final String name, final String image, final String url)
            throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.addFullTrack(PS_ADD_FULL_TRACK, name, image, url);

            synchronized (TrackDatabaseEditor.class) {
                PS_ADD_FULL_TRACK.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void addTrack(final String name, final String url) throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.addTrack(PS_ADD_TRACK, name, url);

            synchronized (TrackDatabaseEditor.class) {
                PS_ADD_TRACK.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void incRating(final int trackId) throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.changeRating(PS_INC_RATING, trackId);

            synchronized (TrackDatabaseEditor.class) {
                PS_INC_RATING.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void decRating(final int trackId) throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.changeRating(PS_DEC_RATING, trackId);

            synchronized (TrackDatabaseEditor.class) {
                PS_DEC_RATING.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public Track getTrack(final int trackId) throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.getTrack(PS_GET_TRACK, trackId);
            ResultSet rs = PS_GET_TRACK.executeQuery();

            CheckData.hasNext(rs);

            return getTrackFromRS(rs);

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getTracks() throws DatabaseException {
        try {
            final List<Track> tracks = new LinkedList<>();
            ResultSet rs = PS_GET_TRACKS.executeQuery();

            while (rs.next()) {
                tracks.add(getTrackFromRS(rs));
            }
            return tracks;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getCountTracks(final int page) throws DatabaseException {
        try {
            final List<Track> tracks = new ArrayList<>(COUNT_ELEMENTS);
            final int number = (page - 1) * COUNT_ELEMENTS;
            TrackDatabaseQueries.Prepared.getCountTracks(PS_GET_COUNT_TRACKS, number);
            ResultSet rs = PS_GET_COUNT_TRACKS.executeQuery();

            while (rs.next()) {
                tracks.add(getTrackFromRS(rs));
            }
            return tracks;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public int getId(final String name) throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.getId(PS_GET_ID, name);
            ResultSet rs = PS_GET_ID.executeQuery();

            CheckData.hasNext(rs);

            return rs.getInt(TrackColumns.ID.toString());
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public int getRating(final int trackId) throws DatabaseException {
        try {
            TrackDatabaseQueries.Prepared.getRating(PS_GET_RATING, trackId);
            ResultSet rs = PS_GET_RATING.executeQuery();

            CheckData.hasNext(rs);

            return rs.getInt(TrackColumns.RATING.toString());
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTrack(final String name) throws DatabaseException {
        try {
            try (ITrackActorDAO trackActorDAO = new TrackActorDatabaseEditor()) {
                trackActorDAO.deleteTrack(name);
            }

            try (ITrackTagDAO trackTagDAO = new TrackTagDatabaseEditor()) {
                trackTagDAO.deleteTrack(name);
            }

            try (IUserTrackDAO userTrackDAO = new UserTrackDatabaseEditor()) {
                userTrackDAO.deleteTrack(name);
            }

            TrackDatabaseQueries.Prepared.deleteTrack(PS_DELETE_TRACK, name);
            PS_DELETE_TRACK.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        DatabaseManager.closePreparedStatements(PS_ADD_FULL_TRACK, PS_ADD_TRACK, PS_INC_RATING,
                PS_DEC_RATING, PS_GET_TRACK, PS_GET_TRACKS, PS_GET_COUNT_TRACKS, PS_GET_ID,
                PS_GET_RATING, PS_DELETE_TRACK);
        super.close();
    }

    private Track getTrackFromRS(final ResultSet rs) throws SQLException {
        final int id = rs.getInt(TrackColumns.ID.toString());
        final String track = rs.getString(TrackColumns.NAME.toString());
        final String image = rs.getString(TrackColumns.IMAGE.toString());
        final String url = rs.getString(TrackColumns.URL.toString());
        final int rating = rs.getInt(TrackColumns.RATING.toString());

        if (CheckData.checkValue(image)) {
            return new Track(id, track, image, url, rating);
        } else {
            return new Track(id, track, url, rating);
        }
    }

}
