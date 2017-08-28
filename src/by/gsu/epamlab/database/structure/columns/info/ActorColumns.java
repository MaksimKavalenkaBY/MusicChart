package by.gsu.epamlab.database.structure.columns.info;

public enum ActorColumns {

    ID("IdActor"), NAME("Name"), IMAGE("Image"), RATING("Rating");

    private String table;

    private ActorColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
