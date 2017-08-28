package by.gsu.epamlab.database.dao.user;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface IUserDAO extends IDAO {

    public void addUser(String login, String password) throws DatabaseException;

    public User getUser(String login, String password) throws DatabaseException;

}
