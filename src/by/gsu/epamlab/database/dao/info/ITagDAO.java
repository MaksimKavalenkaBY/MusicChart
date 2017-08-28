package by.gsu.epamlab.database.dao.info;

import java.util.List;

import by.gsu.epamlab.bean.Tag;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface ITagDAO extends IDAO {

    public void addTag(String name) throws DatabaseException;

    public void incRating(int trackId) throws DatabaseException;

    public void decRating(int trackId) throws DatabaseException;

    public Tag getTag(int tagId) throws DatabaseException;

    public List<Tag> getTags() throws DatabaseException;

    public int getId(String name) throws DatabaseException;

    public int getRating(int trackId) throws DatabaseException;

    public void deleteTag(String name) throws DatabaseException;

}
