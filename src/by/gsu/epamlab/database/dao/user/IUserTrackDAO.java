package by.gsu.epamlab.database.dao.user;

import java.util.List;

import by.gsu.epamlab.bean.Track;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface IUserTrackDAO extends IDAO {

    public int likeUserTrack(int userId, String trackName) throws DatabaseException;

    public List<Track> getTracks(int userId) throws DatabaseException;

    public List<Track> getCountTracks(int userId, int page) throws DatabaseException;

    public void deleteTrack(String trackName) throws DatabaseException;

}
