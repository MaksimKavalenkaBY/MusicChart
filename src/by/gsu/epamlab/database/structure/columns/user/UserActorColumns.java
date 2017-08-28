package by.gsu.epamlab.database.structure.columns.user;

public enum UserActorColumns {

    ID_USER("UserId"), ID_ACTOR("ActorId");

    private String table;

    private UserActorColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
