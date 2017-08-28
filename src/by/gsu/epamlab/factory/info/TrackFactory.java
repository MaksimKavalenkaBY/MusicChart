package by.gsu.epamlab.factory.info;

import java.sql.Connection;

import by.gsu.epamlab.database.dao.info.ITrackDAO;
import by.gsu.epamlab.database.editor.info.TrackDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class TrackFactory {

    public static ITrackDAO getEditor() throws DatabaseException {
        return new TrackDatabaseEditor();
    }

    public static ITrackDAO getEditor(final Connection con) throws DatabaseException {
        return new TrackDatabaseEditor(con);
    }

}
