package by.gsu.epamlab.database.editor;

import java.sql.Connection;

import by.gsu.epamlab.database.access.DatabaseManager;
import by.gsu.epamlab.database.dao.IDAO;
import by.gsu.epamlab.exception.DatabaseException;

public abstract class DatabaseEditor implements IDAO {

    protected Connection con;

    public DatabaseEditor() throws DatabaseException {
        con = DatabaseManager.getConnection();
    }

    public DatabaseEditor(final Connection connection) {
        con = connection;
    }

    @Override
    public void close() throws DatabaseException {
        DatabaseManager.returnConnection(con);
    }

}
