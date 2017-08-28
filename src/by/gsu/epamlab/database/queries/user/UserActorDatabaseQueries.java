package by.gsu.epamlab.database.queries.user;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.user.UserActorColumns;
import by.gsu.epamlab.database.structure.tables.UserTables;

public class UserActorDatabaseQueries {

    public static String checkUserActor() {
        return SELECT + ALL + FROM + UserTables.USER_ACTOR + WHERE + UserActorColumns.ID_USER
                + EQUALLY + PREPARED_SIGN + AND + UserActorColumns.ID_ACTOR + EQUALLY
                + PREPARED_SIGN;
    }

    public static String addUserActor() {
        return INSERT_INTO + UserTables.USER_ACTOR + BRACKET_OPEN + UserActorColumns.ID_USER + COMMA
                + UserActorColumns.ID_ACTOR + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN
                + COMMA + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String deleteUserActor() {
        return DELETE_FROM + UserTables.USER_ACTOR + WHERE + UserActorColumns.ID_USER + EQUALLY
                + PREPARED_SIGN + AND + UserActorColumns.ID_ACTOR + EQUALLY + PREPARED_SIGN;
    }

    public static String getActors() {
        return SELECT + UserActorColumns.ID_ACTOR + FROM + UserTables.USER_ACTOR + WHERE
                + UserActorColumns.ID_USER + EQUALLY + PREPARED_SIGN;
    }

    public static String getCountActors() {
        return SELECT + UserActorColumns.ID_ACTOR + FROM + UserTables.USER_ACTOR + WHERE
                + UserActorColumns.ID_USER + EQUALLY + PREPARED_SIGN + ORDER_BY
                + UserActorColumns.ID_ACTOR + DESC + LIMIT + PREPARED_SIGN + COMMA + COUNT_ELEMENTS;
    }

    public static String deleteActor() {
        return DELETE_FROM + UserTables.USER_ACTOR + WHERE + UserActorColumns.ID_ACTOR + EQUALLY
                + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void likeUserActor(final PreparedStatement pStatement, final int userId,
                final int actorId) throws SQLException {
            final int USER_ID = 1;
            final int ACTOR_ID = 2;
            pStatement.setInt(USER_ID, userId);
            pStatement.setInt(ACTOR_ID, actorId);
        }

        public static void getActors(final PreparedStatement pStatement, final int userId)
                throws SQLException {
            final int USER_ID = 1;
            pStatement.setInt(USER_ID, userId);
        }

        public static void getCountActors(final PreparedStatement pStatement, final int userId,
                final int number) throws SQLException {
            final int USER_ID = 1;
            final int NUMBER = 2;
            pStatement.setInt(USER_ID, userId);
            pStatement.setInt(NUMBER, number);
        }

        public static void deleteActor(final PreparedStatement pStatement, final int actorId)
                throws SQLException {
            final int ACTOR_ID = 1;
            pStatement.setInt(ACTOR_ID, actorId);
        }

    }

}
