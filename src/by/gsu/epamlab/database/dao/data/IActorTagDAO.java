package by.gsu.epamlab.database.dao.data;

import java.util.List;

import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface IActorTagDAO extends IDAO {

    public void addActorTag(String actorName, String tagName) throws DatabaseException;

    public List<Actor> getActors(String tagName) throws DatabaseException;

    public List<Actor> getCountActors(String tagName, int page) throws DatabaseException;

    public void deleteActor(String actorName) throws DatabaseException;

    public void deleteTag(String tagName) throws DatabaseException;

    public void deleteActorTag(String actorName, String tagName) throws DatabaseException;

}
