package by.gsu.epamlab.database.structure.columns.data;

public enum ActorTagColumns {

    ID_ACTOR("ActorId"), ID_TAG("TagId");

    private String table;

    private ActorTagColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
