package by.gsu.epamlab.database.structure.tables;

public enum InfoTables {

    TRACK("Track"), ACTOR("Actor"), TAG("Tag");

    private String table;

    private InfoTables(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
