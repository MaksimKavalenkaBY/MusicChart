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
import by.gsu.epamlab.database.dao.data.ITrackTagDAO;
import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.editor.DatabaseEditor;
import by.gsu.epamlab.database.queries.data.TrackTagDatabaseQueries;
import by.gsu.epamlab.database.structure.columns.data.TrackTagColumns;
import by.gsu.epamlab.exception.DatabaseException;
import by.gsu.epamlab.factory.info.TagFactory;
import by.gsu.epamlab.factory.info.TrackFactory;

public class TrackTagDatabaseEditor extends DatabaseEditor implements ITrackTagDAO {

    private final ITrackDAO         TRACK_DAO;
    private final ITagDAO           TAG_DAO;

    private final PreparedStatement PS_ADD_TRACK_TAG;
    private final PreparedStatement PS_GET_TRACKS;
    private final PreparedStatement PS_GET_COUNT_TRACKS;
    private final PreparedStatement PS_DELETE_TRACK;
    private final PreparedStatement PS_DELETE_TAG;
    private final PreparedStatement PS_DELETE_TRACK_TAG;

    {
        TRACK_DAO = TrackFactory.getEditor(con);
        TAG_DAO = TagFactory.getEditor(con);

        try {
            PS_ADD_TRACK_TAG = con.prepareStatement(TrackTagDatabaseQueries.addTrackTag());
            PS_GET_TRACKS = con.prepareStatement(TrackTagDatabaseQueries.getTracks());
            PS_GET_COUNT_TRACKS = con.prepareStatement(TrackTagDatabaseQueries.getCountTracks());
            PS_DELETE_TRACK = con.prepareStatement(TrackTagDatabaseQueries.deleteTrack());
            PS_DELETE_TAG = con.prepareStatement(TrackTagDatabaseQueries.deleteTag());
            PS_DELETE_TRACK_TAG = con.prepareStatement(TrackTagDatabaseQueries.deleteTrackTag());
        } catch (final SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TrackTagDatabaseEditor() throws DatabaseException {
        super();
    }

    @Override
    public void addTrackTag(final String trackName, final String tagName) throws DatabaseException {
        try {
            final int trackId = TRACK_DAO.getId(trackName);
            final int tagId = TAG_DAO.getId(tagName);

            TrackTagDatabaseQueries.Prepared.addTrackTag(PS_ADD_TRACK_TAG, trackId, tagId);

            synchronized (TrackTagDatabaseEditor.class) {
                PS_ADD_TRACK_TAG.executeUpdate();
            }

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getTracks(final String tagName) throws DatabaseException {
        try {
            final List<Track> tracks = new LinkedList<>();
            final int tagId = TAG_DAO.getId(tagName);

            TrackTagDatabaseQueries.Prepared.getTracks(PS_GET_TRACKS, tagId);
            ResultSet rs = PS_GET_TRACKS.executeQuery();

            while (rs.next()) {
                final int trackId = rs.getInt(TrackTagColumns.ID_TRACK.toString());
                final Track track = TRACK_DAO.getTrack(trackId);
                tracks.add(track);
            }
            return tracks;

        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public List<Track> getCountTracks(final String tagName, final int page)
            throws DatabaseException {
        try {
            final List<Track> tracks = new ArrayList<>(COUNT_ELEMENTS);
            final int tagId = TAG_DAO.getId(tagName);
            final int number = (page - 1) * COUNT_ELEMENTS;

            TrackTagDatabaseQueries.Prepared.getCountTracks(PS_GET_COUNT_TRACKS, tagId, number);
            ResultSet rs = PS_GET_COUNT_TRACKS.executeQuery();

            while (rs.next()) {
                final int trackId = rs.getInt(TrackTagColumns.ID_TRACK.toString());
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
            TrackTagDatabaseQueries.Prepared.deleteTrack(PS_DELETE_TRACK, trackId);
            PS_DELETE_TRACK.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTag(final String tagName) throws DatabaseException {
        try {
            final int tagId = TAG_DAO.getId(tagName);
            TrackTagDatabaseQueries.Prepared.deleteTag(PS_DELETE_TAG, tagId);
            PS_DELETE_TAG.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void deleteTrackTag(final String trackName, final String tagName)
            throws DatabaseException {
        try {
            final int trackId = TRACK_DAO.getId(trackName);
            final int tagId = TAG_DAO.getId(tagName);
            TrackTagDatabaseQueries.Prepared.deleteTrackTag(PS_DELETE_TRACK_TAG, trackId, tagId);
            PS_DELETE_TRACK_TAG.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(ERROR_CONNECTION_ACCESS);
        }
    }

    @Override
    public void close() throws DatabaseException {
        TRACK_DAO.close();
        TAG_DAO.close();
        DatabaseManager.closePreparedStatements(PS_ADD_TRACK_TAG, PS_GET_TRACKS,
                PS_GET_COUNT_TRACKS, PS_DELETE_TRACK, PS_DELETE_TAG, PS_DELETE_TRACK_TAG);
        super.close();
    }

}
