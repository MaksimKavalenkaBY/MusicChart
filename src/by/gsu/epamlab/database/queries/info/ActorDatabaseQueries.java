package by.gsu.epamlab.database.queries.info;

import static by.gsu.epamlab.constants.DatabaseConstants.COUNT_ELEMENTS;
import static by.gsu.epamlab.constants.QueryConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.gsu.epamlab.database.structure.columns.info.ActorColumns;
import by.gsu.epamlab.database.structure.tables.InfoTables;

public class ActorDatabaseQueries {

    public static String addFullActor() {
        return INSERT_INTO + InfoTables.ACTOR + BRACKET_OPEN + ActorColumns.NAME + COMMA
                + ActorColumns.IMAGE + BRACKET_CLOSE + VALUES + BRACKET_OPEN + PREPARED_SIGN + COMMA
                + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String addActor() {
        return INSERT_INTO + InfoTables.ACTOR + BRACKET_OPEN + ActorColumns.NAME + BRACKET_CLOSE
                + VALUES + BRACKET_OPEN + PREPARED_SIGN + BRACKET_CLOSE;
    }

    public static String incRating() {
        return UPDATE + InfoTables.ACTOR + SET + ActorColumns.RATING + EQUALLY + ActorColumns.RATING
                + PLUS + 1 + WHERE + ActorColumns.ID + EQUALLY + PREPARED_SIGN;
    }

    public static String decRating() {
        return UPDATE + InfoTables.ACTOR + SET + ActorColumns.RATING + EQUALLY + ActorColumns.RATING
                + MINUS + 1 + WHERE + ActorColumns.ID + EQUALLY + PREPARED_SIGN;
    }

    public static String getActors() {
        return SELECT + ALL + FROM + InfoTables.ACTOR;
    }

    public static String getCountActors() {
        return SELECT + ALL + FROM + InfoTables.ACTOR + ORDER_BY + ActorColumns.ID + DESC + LIMIT
                + PREPARED_SIGN + COMMA + COUNT_ELEMENTS;
    }

    public static String getActor() {
        return SELECT + ALL + FROM + InfoTables.ACTOR + WHERE + ActorColumns.ID + EQUALLY
                + PREPARED_SIGN;
    }

    public static String getId() {
        return SELECT + ActorColumns.ID + FROM + InfoTables.ACTOR + WHERE + ActorColumns.NAME
                + EQUALLY + PREPARED_SIGN;
    }

    public static String getRating() {
        return SELECT + ActorColumns.RATING + FROM + InfoTables.ACTOR + WHERE + ActorColumns.ID
                + EQUALLY + PREPARED_SIGN;
    }

    public static String deleteActor() {
        return DELETE_FROM + InfoTables.ACTOR + WHERE + ActorColumns.NAME + EQUALLY + PREPARED_SIGN;
    }

    public static class Prepared {

        public static void addFullActor(final PreparedStatement pStatement, final String name,
                final String image) throws SQLException {
            final int NAME = 1;
            final int IMAGE = 2;
            pStatement.setString(NAME, name);
            pStatement.setString(IMAGE, image);
        }

        public static void addActor(final PreparedStatement pStatement, final String name)
                throws SQLException {
            final int NAME = 1;
            pStatement.setString(NAME, name);
        }

        public static void changeRating(final PreparedStatement pStatement, final int actorId)
                throws SQLException {
            final int ACTOR_ID = 1;
            pStatement.setInt(ACTOR_ID, actorId);
        }

        public static void getActor(final PreparedStatement pStatement, final int id)
                throws SQLException {
            final int ID = 1;
            pStatement.setInt(ID, id);
        }

        public static void getCountActors(final PreparedStatement pStatement, final int number)
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

        public static void deleteActor(final PreparedStatement pStatement, final String name)
                throws SQLException {
            final int NAME = 1;
            pStatement.setString(NAME, name);
        }

    }

}
