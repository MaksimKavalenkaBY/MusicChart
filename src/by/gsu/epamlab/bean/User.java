package by.gsu.epamlab.bean;

import by.gsu.epamlab.constants.RoleConstants;

public class User implements Comparable<User> {

    private int           id;
    private String        login;
    private RoleConstants role;

    public User() {
        super();
    }

    public User(final Integer id, final String login, final RoleConstants role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public RoleConstants getRole() {
        return role;
    }

    public void setRole(final RoleConstants role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final User user) {
        return login.compareTo(user.login);
    }

}
