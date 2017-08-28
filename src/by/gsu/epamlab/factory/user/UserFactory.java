package by.gsu.epamlab.factory.user;

import by.gsu.epamlab.database.dao.user.IUserDAO;
import by.gsu.epamlab.database.editor.user.UserDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class UserFactory {

    public static IUserDAO getEditor() throws DatabaseException {
        return new UserDatabaseEditor();
    }

}
