package by.gsu.epamlab.database.structure.columns.info;

public enum TrackColumns {

    ID("IdTrack"), NAME("Name"), IMAGE("Image"), URL("URL"), RATING("Rating");

    private String table;

    private TrackColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
