package by.gsu.epamlab.constants;

public enum RoleConstants {

    USER, ADMIN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
