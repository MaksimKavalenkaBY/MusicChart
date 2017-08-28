package by.gsu.epamlab.database.dao.data;

import java.util.List;

import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface ITrackActorDAO extends IDAO {

    public void addTrackActor(String trackName, String actorName) throws DatabaseException;

    public List<Track> getTracks(String actorName) throws DatabaseException;

    public List<Track> getCountTracks(String actorName, int page) throws DatabaseException;

    public void deleteTrack(String trackName) throws DatabaseException;

    public void deleteActor(String actorName) throws DatabaseException;

    public void deleteTrackActor(String trackName, String actorName) throws DatabaseException;

}
