package by.gsu.epamlab.database.dao.data;

import java.util.List;

import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface ITrackTagDAO extends IDAO {

    public void addTrackTag(String trackName, String tagName) throws DatabaseException;

    public List<Track> getTracks(String tagName) throws DatabaseException;

    public List<Track> getCountTracks(String tagName, int page) throws DatabaseException;

    public void deleteTrack(String trackName) throws DatabaseException;

    public void deleteTag(String tagName) throws DatabaseException;

    public void deleteTrackTag(String trackName, String tagName) throws DatabaseException;

}
