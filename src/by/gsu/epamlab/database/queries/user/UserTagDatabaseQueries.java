package by.gsu.epamlab.database.queries.user;

import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.user.UserTagColumns;
import by.gsu.epamlab.database.structure.tables.UserTables;

public class UserTagDatabaseQueries {

    public static String checkUserTag() {
        return SELECT + ALL + FROM + UserTables.USER_TAG + WHERE + UserTagColumns.ID_USER + EQUALLY
                + PREPARED_SIGN + AND + UserTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN;
    }

    public static String addUserTag() {
        return INSERT_INTO + UserTables.USER_TAG + BRACKET_OPEN + UserTagColumns.ID_USER + COMMA
                + UserTagColumns.ID_TAG + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN
                + COMMA + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String deleteUserTag() {
        return DELETE_FROM + UserTables.USER_TAG + WHERE + UserTagColumns.ID_USER + EQUALLY
                + PREPARED_SIGN + AND + UserTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN;
    }

    public static String getTags() {
        return SELECT + UserTagColumns.ID_TAG + FROM + UserTables.USER_TAG + WHERE
                + UserTagColumns.ID_USER + EQUALLY + PREPARED_SIGN;
    }

    public static String deleteTag() {
        return DELETE_FROM + UserTables.USER_TAG + WHERE + UserTagColumns.ID_TAG + EQUALLY
                + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void likeUserTag(final PreparedStatement pStatement, final int userId,
                final int tagId) throws SQLException {
            final int USER_ID = 1;
            final int TAG_ID = 2;
            pStatement.setInt(USER_ID, userId);
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void getTags(final PreparedStatement pStatement, final int userId)
                throws SQLException {
            final int USER_ID = 1;
            pStatement.setInt(USER_ID, userId);
        }

        public static void deleteTag(final PreparedStatement pStatement, final int tagId)
                throws SQLException {
            final int TAG_ID = 1;
            pStatement.setInt(TAG_ID, tagId);
        }

    }

}
