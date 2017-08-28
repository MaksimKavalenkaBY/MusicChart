package by.gsu.epamlab.factory.info;

import java.sql.Connection;

import by.gsu.epamlab.database.dao.info.ITagDAO;
import by.gsu.epamlab.database.editor.info.TagDatabaseEditor;
import by.gsu.epamlab.exception.DatabaseException;

public class TagFactory {

    public static ITagDAO getEditor() throws DatabaseException {
        return new TagDatabaseEditor();
    }

    public static ITagDAO getEditor(final Connection con) throws DatabaseException {
        return new TagDatabaseEditor(con);
    }

}
