package by.gsu.epamlab.database.queries.user;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.user.UserTrackColumns;
import by.gsu.epamlab.database.structure.tables.UserTables;

public class UserTrackDatabaseQueries {

    public static String checkUserTrack() {
        return SELECT + ALL + FROM + UserTables.USER_TRACK + WHERE + UserTrackColumns.ID_USER
                + EQUALLY + PREPARED_SIGN + AND + UserTrackColumns.ID_TRACK + EQUALLY
                + PREPARED_SIGN;
    }

    public static String addUserTrack() {
        return INSERT_INTO + UserTables.USER_TRACK + BRACKET_OPEN + UserTrackColumns.ID_USER + COMMA
                + UserTrackColumns.ID_TRACK + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN
                + COMMA + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String deleteUserTrack() {
        return DELETE_FROM + UserTables.USER_TRACK + WHERE + UserTrackColumns.ID_USER + EQUALLY
                + PREPARED_SIGN + AND + UserTrackColumns.ID_TRACK + EQUALLY + PREPARED_SIGN;
    }

    public static String getTracks() {
        return SELECT + UserTrackColumns.ID_TRACK + FROM + UserTables.USER_TRACK + WHERE
                + UserTrackColumns.ID_USER + EQUALLY + PREPARED_SIGN;
    }

    public static String getCountTracks() {
        return SELECT + UserTrackColumns.ID_TRACK + FROM + UserTables.USER_TRACK + WHERE
                + UserTrackColumns.ID_USER + EQUALLY + PREPARED_SIGN + ORDER_BY
                + UserTrackColumns.ID_TRACK + DESC + LIMIT + PREPARED_SIGN + COMMA + COUNT_ELEMENTS;
    }

    public static String deleteTrack() {
        return DELETE_FROM + UserTables.USER_TRACK + WHERE + UserTrackColumns.ID_TRACK + EQUALLY
                + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void likeUserTrack(final PreparedStatement pStatement, final int userId,
                final int trackId) throws SQLException {
            final int USER_ID = 1;
            final int TRACK_ID = 2;
            pStatement.setInt(USER_ID, userId);
            pStatement.setInt(TRACK_ID, trackId);
        }

        public static void getTracks(final PreparedStatement pStatement, final int userId)
                throws SQLException {
            final int USER_ID = 1;
            pStatement.setInt(USER_ID, userId);
        }

        public static void getCountTracks(final PreparedStatement pStatement, final int userId,
                final int number) throws SQLException {
            final int USER_ID = 1;
            final int NUMBER = 2;
            pStatement.setInt(USER_ID, userId);
            pStatement.setInt(NUMBER, number);
        }

        public static void deleteTrack(final PreparedStatement pStatement, final int trackId)
                throws SQLException {
            final int TRACK_ID = 1;
            pStatement.setInt(TRACK_ID, trackId);
        }

    }

}
