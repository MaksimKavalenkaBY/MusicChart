package by.gsu.epamlab.database.queries.data;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.data.TrackActorColumns;
import by.gsu.epamlab.database.structure.tables.DataTables;

public class TrackActorDatabaseQueries {

    public static String addTrackActor() {
        return INSERT_INTO + DataTables.TRACK_ACTOR + BRACKET_OPEN + TrackActorColumns.ID_TRACK
                + COMMA + TrackActorColumns.ID_ACTOR + BRACKET_CLOSE + VALUES + BRACKET_OPEN
                + PREPARED_SIGN + COMMA + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String getTracks() {
        return SELECT + TrackActorColumns.ID_TRACK + FROM + DataTables.TRACK_ACTOR + WHERE
                + TrackActorColumns.ID_ACTOR + EQUALLY + PREPARED_SIGN;
    }

    public static String getCountTracks() {
        return SELECT + TrackActorColumns.ID_TRACK + FROM + DataTables.TRACK_ACTOR + WHERE
                + TrackActorColumns.ID_ACTOR + EQUALLY + PREPARED_SIGN + ORDER_BY
                + TrackActorColumns.ID_TRACK + DESC + LIMIT + PREPARED_SIGN + COMMA
                + COUNT_ELEMENTS;
    }

    public static String deleteTrack() {
        return DELETE_FROM + DataTables.TRACK_ACTOR + WHERE + TrackActorColumns.ID_TRACK + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteActor() {
        return DELETE_FROM + DataTables.TRACK_ACTOR + WHERE + TrackActorColumns.ID_ACTOR + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteTrackActor() {
        return DELETE_FROM + DataTables.TRACK_ACTOR + WHERE + TrackActorColumns.ID_TRACK + EQUALLY
                + PREPARED_SIGN + AND + TrackActorColumns.ID_ACTOR + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void addTrackActor(final PreparedStatement pStatement, final int trackId,
                final int actorId) throws SQLException {
            final int TRACK_ID = 1;
            final int ACTOR_ID = 2;
            pStatement.setInt(TRACK_ID, trackId);
            pStatement.setInt(ACTOR_ID, actorId);
        }

        public static void getTracks(final PreparedStatement pStatement, final int actorId)
                throws SQLException {
            final int ACTOR_ID = 1;
            pStatement.setInt(ACTOR_ID, actorId);
        }

        public static void getCountTracks(final PreparedStatement pStatement, final int actorId,
                final int number) throws SQLException {
            final int ACTOR_ID = 1;
            final int NUMBER = 2;
            pStatement.setInt(ACTOR_ID, actorId);
            pStatement.setInt(NUMBER, number);
        }

        public static void deleteTrack(final PreparedStatement pStatement, final int trackId)
                throws SQLException {
            final int TRACK_ID = 1;
            pStatement.setInt(TRACK_ID, trackId);
        }

        public static void deleteActor(final PreparedStatement pStatement, final int actorId)
                throws SQLException {
            final int ACTOR_ID = 1;
            pStatement.setInt(ACTOR_ID, actorId);
        }

        public static void deleteTrackActor(final PreparedStatement pStatement, final int trackId,
                final int actorId) throws SQLException {
            final int TRACK_ID = 1;
            final int ACTOR_ID = 2;
            pStatement.setInt(TRACK_ID, trackId);
            pStatement.setInt(ACTOR_ID, actorId);
        }

    }

}
