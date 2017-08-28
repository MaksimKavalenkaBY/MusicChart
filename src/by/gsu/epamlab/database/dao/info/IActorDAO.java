package by.gsu.epamlab.database.dao.info;

import java.util.List;

import by.gsu.epamlab.bean.Actor;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface IActorDAO extends IDAO {

    public void addActor(String name, String image) throws DatabaseException;

    public void addActor(String name) throws DatabaseException;

    public void incRating(int trackId) throws DatabaseException;

    public void decRating(int trackId) throws DatabaseException;

    public Actor getActor(int actorId) throws DatabaseException;

    public List<Actor> getActors() throws DatabaseException;

    public List<Actor> getCountActors(int page) throws DatabaseException;

    public int getId(String name) throws DatabaseException;

    public int getRating(int trackId) throws DatabaseException;

    public void deleteActor(String name) throws DatabaseException;

}
