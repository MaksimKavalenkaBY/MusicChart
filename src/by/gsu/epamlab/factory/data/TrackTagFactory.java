package by.gsu.epamlab.factory.data;

import by.gsu.epamlab.database.dao.data.ITrackTagDAO;
import by.gsu.epamlab.database.editor.data.TrackTagDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class TrackTagFactory {

    public static ITrackTagDAO getEditor() throws DatabaseException {
        return new TrackTagDatabaseEditor();
    }

}
