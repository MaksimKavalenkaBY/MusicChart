package by.gsu.epamlab.database.dao.user;

import java.util.List;

import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface IUserActorDAO extends IDAO {

    public int likeUserActor(int userId, String actorName) throws DatabaseException;

    public List<Actor> getActors(int userId) throws DatabaseException;

    public List<Actor> getCountActors(int userId, int page) throws DatabaseException;

    public void deleteActor(String actorName) throws DatabaseException;

}
