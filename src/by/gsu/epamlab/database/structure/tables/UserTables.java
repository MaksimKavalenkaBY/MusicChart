package by.gsu.epamlab.database.structure.tables;

public enum UserTables {

    USER("User"), USER_TRACK("UserTrack"), USER_ACTOR("UserActor"), USER_TAG("UserTag");

    private String table;

    private UserTables(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
