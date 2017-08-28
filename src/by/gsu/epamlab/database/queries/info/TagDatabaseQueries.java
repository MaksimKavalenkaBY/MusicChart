package by.gsu.epamlab.database.queries.info;

import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.info.TagColumns;
import by.gsu.epamlab.database.structure.columns.info.TrackColumns;
import by.gsu.epamlab.database.structure.tables.InfoTables;

public class TagDatabaseQueries {

    public static String addTag() {
        return INSERT_INTO + InfoTables.TAG + BRACKET_OPEN + TrackColumns.NAME + BRACKET_CLOSE
                + VALUES + BRACKET_OPEN + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String incRating() {
        return UPDATE + InfoTables.TAG + SET + TagColumns.RATING + EQUALLY + TagColumns.RATING
                + PLUS + 1 + WHERE + TagColumns.ID + EQUALLY + PREPARED_SIGN;
    }

    public static String decRating() {
        return UPDATE + InfoTables.TAG + SET + TagColumns.RATING + EQUALLY + TagColumns.RATING
                + MINUS + 1 + WHERE + TagColumns.ID + EQUALLY + PREPARED_SIGN;
    }

    public static String getTags() {
        return SELECT + ALL + FROM + InfoTables.TAG;
    }

    public static String getTag() {
        return SELECT + ALL + FROM + InfoTables.TAG + WHERE + TagColumns.ID + EQUALLY
                + PREPARED_SIGN;
    }

    public static String getId() {
        return SELECT + TagColumns.ID + FROM + InfoTables.TAG + WHERE + TagColumns.NAME + EQUALLY
                + PREPARED_SIGN;
    }

    public static String getRating() {
        return SELECT + TagColumns.RATING + FROM + InfoTables.TAG + WHERE + TagColumns.ID + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteTag() {
        return DELETE_FROM + InfoTables.TAG + WHERE + TagColumns.NAME + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void addTag(final PreparedStatement pStatement, final String name)
                throws SQLException {
            final int NAME = 1;
            pStatement.setString(NAME, name);
        }

        public static void changeRating(final PreparedStatement pStatement, final int tagId)
                throws SQLException {
            final int TAG_ID = 1;
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void getTag(final PreparedStatement pStatement, final int id)
                throws SQLException {
            final int ID = 1;
            pStatement.setInt(ID, id);
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

        public static void deleteTag(final PreparedStatement pStatement, final String name)
                throws SQLException {
            final int NAME = 1;
            pStatement.setString(NAME, name);
        }

    }

}
