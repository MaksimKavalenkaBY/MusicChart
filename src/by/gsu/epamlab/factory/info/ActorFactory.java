package by.gsu.epamlab.factory.info;

import java.sql.Connection;

import by.gsu.epamlab.database.dao.info.IActorDAO;
import by.gsu.epamlab.database.editor.info.ActorDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class ActorFactory {

    public static IActorDAO getEditor() throws DatabaseException {
        return new ActorDatabaseEditor();
    }

    public static IActorDAO getEditor(final Connection con) throws DatabaseException {
        return new ActorDatabaseEditor(con);
    }

}
