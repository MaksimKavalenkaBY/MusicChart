package by.gsu.epamlab.factory.data;

import by.gsu.epamlab.database.dao.data.IActorTagDAO;
import by.gsu.epamlab.database.editor.data.ActorTagDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class ActorTagFactory {

    public static IActorTagDAO getEditor() throws DatabaseException {
        return new ActorTagDatabaseEditor();
    }

}
