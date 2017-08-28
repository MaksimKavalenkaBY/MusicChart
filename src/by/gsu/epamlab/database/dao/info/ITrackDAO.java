package by.gsu.epamlab.database.dao.info;

import java.util.List;

import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface ITrackDAO extends IDAO {

    public void addTrack(String name, String image, String url) throws DatabaseException;

    public void addTrack(String name, String url) throws DatabaseException;

    public void incRating(int trackId) throws DatabaseException;

    public void decRating(int trackId) throws DatabaseException;

    public Track getTrack(int trackId) throws DatabaseException;

    public List<Track> getTracks() throws DatabaseException;

    public List<Track> getCountTracks(int page) throws DatabaseException;

    public int getId(String name) throws DatabaseException;

    public int getRating(int trackId) throws DatabaseException;

    public void deleteTrack(String name) throws DatabaseException;

}
