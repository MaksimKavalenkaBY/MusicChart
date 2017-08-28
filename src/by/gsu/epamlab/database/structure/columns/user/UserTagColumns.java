package by.gsu.epamlab.database.structure.columns.user;

public enum UserTagColumns {

    ID_USER("UserId"), ID_TAG("TagId");

    private String table;

    private UserTagColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
