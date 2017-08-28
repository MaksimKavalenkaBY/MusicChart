package by.gsu.epamlab.factory.user;

import by.gsu.epamlab.database.dao.user.IUserActorDAO;
import by.gsu.epamlab.database.editor.user.UserActorDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class UserActorFactory {

    public static IUserActorDAO getEditor() throws DatabaseException {
        return new UserActorDatabaseEditor();
    }

}
