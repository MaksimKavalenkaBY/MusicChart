package by.gsu.epamlab.database.dao;

import by.gsu.epamlab.exception.DatabaseException;

public interface IDAO extends AutoCloseable {

    @Override
    void close() throws DatabaseException;

}
