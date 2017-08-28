package by.gsu.epamlab.factory.user;

import by.gsu.epamlab.database.dao.user.IUserTrackDAO;
import by.gsu.epamlab.database.editor.user.UserTrackDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class UserTrackFactory {

    public static IUserTrackDAO getEditor() throws DatabaseException {
        return new UserTrackDatabaseEditor();
    }

}
