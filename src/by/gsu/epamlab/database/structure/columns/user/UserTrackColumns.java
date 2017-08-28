package by.gsu.epamlab.database.structure.columns.user;

public enum UserTrackColumns {

    ID_USER("UserId"), ID_TRACK("TrackId");

    private String table;

    private UserTrackColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
