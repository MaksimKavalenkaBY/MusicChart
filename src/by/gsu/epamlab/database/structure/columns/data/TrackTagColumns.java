package by.gsu.epamlab.database.structure.columns.data;

public enum TrackTagColumns {

    ID_TRACK("TrackId"), ID_TAG("TagId");

    private String table;

    private TrackTagColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
