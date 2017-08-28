package by.gsu.epamlab.database.queries.data;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.data.TrackTagColumns;
import by.gsu.epamlab.database.structure.tables.DataTables;

public class TrackTagDatabaseQueries {

    public static String addTrackTag() {
        return INSERT_INTO + DataTables.TRACK_TAG + BRACKET_OPEN + TrackTagColumns.ID_TRACK + COMMA
                + TrackTagColumns.ID_TAG + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN
                + COMMA + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String getTracks() {
        return SELECT + TrackTagColumns.ID_TRACK + FROM + DataTables.TRACK_TAG + WHERE
                + TrackTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN;
    }

    public static String getCountTracks() {
        return SELECT + TrackTagColumns.ID_TRACK + FROM + DataTables.TRACK_TAG + WHERE
                + TrackTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN + ORDER_BY
                + TrackTagColumns.ID_TRACK + DESC + LIMIT + PREPARED_SIGN + COMMA + COUNT_ELEMENTS;
    }

    public static String deleteTrack() {
        return DELETE_FROM + DataTables.TRACK_TAG + WHERE + TrackTagColumns.ID_TRACK + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteTag() {
        return DELETE_FROM + DataTables.TRACK_TAG + WHERE + TrackTagColumns.ID_TAG + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteTrackTag() {
        return DELETE_FROM + DataTables.TRACK_TAG + WHERE + TrackTagColumns.ID_TRACK + EQUALLY
                + PREPARED_SIGN + AND + TrackTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void addTrackTag(final PreparedStatement pStatement, final int trackId,
                final int tagId) throws SQLException {
            final int TRACK_ID = 1;
            final int TAG_ID = 2;
            pStatement.setInt(TRACK_ID, trackId);
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void getTracks(final PreparedStatement pStatement, final int tagId)
                throws SQLException {
            final int TAG_ID = 1;
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void getCountTracks(final PreparedStatement pStatement, final int tagId,
                final int number) throws SQLException {
            final int TAG_ID = 1;
            final int NUMBER = 2;
            pStatement.setInt(TAG_ID, tagId);
            pStatement.setInt(NUMBER, number);
        }

        public static void deleteTrack(final PreparedStatement pStatement, final int trackId)
                throws SQLException {
            final int TRACK_ID = 1;
            pStatement.setInt(TRACK_ID, trackId);
        }

        public static void deleteTag(final PreparedStatement pStatement, final int tagId)
                throws SQLException {
            final int TAG_ID = 1;
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void deleteTrackTag(final PreparedStatement pStatement, final int trackId,
                final int tagId) throws SQLException {
            final int TRACK_ID = 1;
            final int TAG_ID = 2;
            pStatement.setInt(TRACK_ID, trackId);
            pStatement.setInt(TAG_ID, tagId);
        }

    }

}
