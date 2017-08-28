package by.gsu.epamlab.factory.data;

import by.gsu.epamlab.database.dao.data.ITrackActorDAO;
import by.gsu.epamlab.database.editor.data.TrackActorDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class TrackActorFactory {

    public static ITrackActorDAO getEditor() throws DatabaseException {
        return new TrackActorDatabaseEditor();
    }

}
