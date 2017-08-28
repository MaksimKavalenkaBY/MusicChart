package by.gsu.epamlab.database.dao.user;

import java.util.List;

import by.gsu.epamlab.bean.Tag;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public interface IUserTagDAO extends IDAO {

    public int likeUserTag(int userId, String tagName) throws DatabaseException;

    public List<Tag> getTags(int userId) throws DatabaseException;

    public void deleteTag(String tagName) throws DatabaseException;

}
