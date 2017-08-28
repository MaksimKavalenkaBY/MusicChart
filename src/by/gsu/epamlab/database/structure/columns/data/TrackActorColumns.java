package by.gsu.epamlab.database.structure.columns.data;

public enum TrackActorColumns {

    ID_TRACK("TrackId"), ID_ACTOR("ActorId");

    private String table;

    private TrackActorColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
