package by.gsu.epamlab.database.structure.columns.user;

public enum UserColumns {

    ID("IdUser"), LOGIN("Login"), PASSWORD("Password"), ROLE("Role");

    private String table;

    private UserColumns(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
