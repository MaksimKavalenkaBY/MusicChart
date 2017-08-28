package by.gsu.epamlab.database.structure.tables;

public enum DataTables {

    TRACK_ACTOR("TrackActor"), ACTOR_TAG("ActorTag"), TRACK_TAG("TrackTag");

    private String table;

    private DataTables(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
