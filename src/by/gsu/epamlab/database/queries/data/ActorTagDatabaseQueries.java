package by.gsu.epamlab.database.queries.data;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.data.ActorTagColumns;
import by.gsu.epamlab.database.structure.tables.DataTables;

public class ActorTagDatabaseQueries {

    public static String addActorTag() {
        return INSERT_INTO + DataTables.ACTOR_TAG + BRACKET_OPEN + ActorTagColumns.ID_ACTOR + COMMA
                + ActorTagColumns.ID_TAG + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN
                + COMMA + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String getActors() {
        return SELECT + ActorTagColumns.ID_ACTOR + FROM + DataTables.ACTOR_TAG + WHERE
                + ActorTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN;
    }

    public static String getCountActors() {
        return SELECT + ActorTagColumns.ID_ACTOR + FROM + DataTables.ACTOR_TAG + WHERE
                + ActorTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN + ORDER_BY
                + ActorTagColumns.ID_ACTOR + DESC + LIMIT + PREPARED_SIGN + COMMA + COUNT_ELEMENTS;
    }

    public static String deleteActor() {
        return DELETE_FROM + DataTables.ACTOR_TAG + WHERE + ActorTagColumns.ID_ACTOR + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteTag() {
        return DELETE_FROM + DataTables.ACTOR_TAG + WHERE + ActorTagColumns.ID_TAG + EQUALLY
                + PREPARED_SIGN;
    }

    public static String deleteActorTag() {
        return DELETE_FROM + DataTables.ACTOR_TAG + WHERE + ActorTagColumns.ID_ACTOR + EQUALLY
                + PREPARED_SIGN + AND + ActorTagColumns.ID_TAG + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void addActorTag(final PreparedStatement pStatement, final int actorId,
                final int tagId) throws SQLException {
            final int ACTOR_ID = 1;
            final int TAG_ID = 2;
            pStatement.setInt(ACTOR_ID, actorId);
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void getActors(final PreparedStatement pStatement, final int tagId)
                throws SQLException {
            final int TAG_ID = 1;
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void getCountActors(final PreparedStatement pStatement, final int tagId,
                final int number) throws SQLException {
            final int TAG_ID = 1;
            final int NUMBER = 2;
            pStatement.setInt(TAG_ID, tagId);
            pStatement.setInt(NUMBER, number);
        }

        public static void deleteActor(final PreparedStatement pStatement, final int actorId)
                throws SQLException {
            final int ACTOR_ID = 1;
            pStatement.setInt(ACTOR_ID, actorId);
        }

        public static void deleteTag(final PreparedStatement pStatement, final int tagId)
                throws SQLException {
            final int TAG_ID = 1;
            pStatement.setInt(TAG_ID, tagId);
        }

        public static void deleteActorTag(final PreparedStatement pStatement, final int actorId,
                final int tagId) throws SQLException {
            final int ACTOR_ID = 1;
            final int TAG_ID = 2;
            pStatement.setInt(ACTOR_ID, actorId);
            pStatement.setInt(TAG_ID, tagId);
        }

    }

}
