package by.gsu.epamlab.database.structure.columns.info;

public enum TagColumns {

    ID("IdTag"), NAME("Name"), RATING("Rating");

    private String table;

    private TagColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
