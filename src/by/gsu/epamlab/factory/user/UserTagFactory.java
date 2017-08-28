package by.gsu.epamlab.factory.user;

import by.gsu.epamlab.database.dao.user.IUserTagDAO;
import by.gsu.epamlab.database.editor.user.UserTagDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class UserTagFactory {

    public static IUserTagDAO getEditor() throws DatabaseException {
        return new UserTagDatabaseEditor();
    }

}
