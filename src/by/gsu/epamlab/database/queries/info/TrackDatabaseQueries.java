package by.gsu.epamlab.database.queries.info;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.info.TrackColumns;
import by.gsu.epamlab.database.structure.tables.InfoTables;

public class TrackDatabaseQueries {

    public static String addFullTrack() {
        return INSERT_INTO + InfoTables.TRACK + BRACKET_OPEN + TrackColumns.NAME + COMMA
                + TrackColumns.IMAGE + COMMA + TrackColumns.URL + BRACKET_CLOSE + VALUES
                + BRACKET_OPEN + PREPARED_SIGN + COMMA + PREPARED_SIGN + COMMA + PREPARED_SIGN
                + BRACKET_CLOSE;
    }

    public static String addTrack() {
        return INSERT_INTO + InfoTables.TRACK + BRACKET_OPEN + TrackColumns.NAME + COMMA
                + TrackColumns.URL + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN + COMMA
                + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String incRating() {
        return UPDATE + InfoTables.TRACK + SET + TrackColumns.RATING + EQUALLY + TrackColumns.RATING
                + PLUS + 1 + WHERE + TrackColumns.ID + EQUALLY + PREPARED_SIGN;
    }

    public static String decRating() {
        return UPDATE + InfoTables.TRACK + SET + TrackColumns.RATING + EQUALLY + TrackColumns.RATING
                + MINUS + 1 + WHERE + TrackColumns.ID + EQUALLY + PREPARED_SIGN;
    }

    public static String getTracks() {
        return SELECT + ALL + FROM + InfoTables.TRACK;
    }

    public static String getCountTracks() {
        return SELECT + ALL + FROM + InfoTables.TRACK + ORDER_BY + TrackColumns.ID + DESC + LIMIT
                + PREPARED_SIGN + COMMA + COUNT_ELEMENTS;
    }

    public static String getTrack() {
        return SELECT + ALL + FROM + InfoTables.TRACK + WHERE + TrackColumns.ID + EQUALLY
                + PREPARED_SIGN;
    }

    public static String getId() {
        return SELECT + TrackColumns.ID + FROM + InfoTables.TRACK + WHERE + TrackColumns.NAME
                + EQUALLY + PREPARED_SIGN;
    }

    public static String getRating() {
        return SELECT + TrackColumns.RATING + FROM + InfoTables.TRACK + WHERE + TrackColumns.ID
                + EQUALLY + PREPARED_SIGN;
    }

    public static String deleteTrack() {
        return DELETE_FROM + InfoTables.TRACK + WHERE + TrackColumns.NAME + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void addFullTrack(final PreparedStatement pStatement, final String name,
                final String image, final String url) throws SQLException {
            final int NAME = 1;
            final int IMAGE = 2;
            final int URL = 3;
            pStatement.setString(NAME, name);
            pStatement.setString(IMAGE, image);
            pStatement.setString(URL, url);
        }

        public static void addTrack(final PreparedStatement pStatement, final String name,
                final String url) throws SQLException {
            final int NAME = 1;
            final int URL = 2;
            pStatement.setString(NAME, name);
            pStatement.setString(URL, url);
        }

        public static void changeRating(final PreparedStatement pStatement, final int trackId)
                throws SQLException {
            final int TRACK_ID = 1;
            pStatement.setInt(TRACK_ID, trackId);
        }

        public static void getTrack(final PreparedStatement pStatement, final int id)
                throws SQLException {
            final int ID = 1;
            pStatement.setInt(ID, id);
        }

        public static void getCountTracks(final PreparedStatement pStatement, final int number)
                throws SQLException {
            final int NUMBER = 1;
            pStatement.setInt(NUMBER, number);
        }

        public static void getId(final PreparedStatement pStatement, final String name)
                throws SQLException {
            final int NAME = 1;
            pStatement.setString(NAME, name);
        }

        public static void getRating(final PreparedStatement pStatement, final int id)
                throws SQLException {
            final int ID = 1;
            pStatement.setInt(ID, id);
        }

        public static void deleteTrack(final PreparedStatement pStatement, final String name)
                throws SQLException {
            final int NAME = 1;
            pStatement.setString(NAME, name);
        }

    }

}
