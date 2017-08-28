package by.gsu.epamlab.database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.dbcp.dbcp.ConnectionFactory;
import org.apache.tomcat.dbcp.dbcp.DriverManagerConnectionFactory;
import org.apache.tomcat.dbcp.dbcp.PoolableConnectionFactory;
import org.apache.tomcat.dbcp.dbcp.PoolingDataSource;
import org.apache.tomcat.dbcp.pool.impl.GenericObjectPool;

import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.exception.DatabaseException;

public class DatabaseManager {

    private static final String      REQUEST = "SELECT 1";

    private static GenericObjectPool connectionPool;

    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
        try {
            Class.forName(DatabaseConstants.DRIVER);

            connectionPool = new GenericObjectPool();

            final ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                    DatabaseConstants.URL + DatabaseConstants.DSN, DatabaseConstants.USER,
                    DatabaseConstants.PASSWORD);

            new PoolableConnectionFactory(connectionFactory, connectionPool, null, REQUEST, false,
                    true);
            new PoolingDataSource(connectionPool);

            connectionPool.setMaxIdle(DatabaseConstants.MAX_IDLE);
            connectionPool.setMaxActive(DatabaseConstants.MAX_ACTIVE);

        } catch (final ClassNotFoundException e) {
            throw new RuntimeException(ExceptionConstants.ERROR_LOAD_DRIVER);
        }
    }

    public static Connection getConnection() throws DatabaseException {
        try {
            if (connectionPool.getMaxActive() <= connectionPool.getNumActive()) {
                throw new DatabaseException(ExceptionConstants.ERROR_CONNECTIONS_LIMIT);
            }
            final Connection con = (Connection) connectionPool.borrowObject();
            return con;
        } catch (final Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_CONNECTION);
        }
    }

    public static void returnConnection(final Connection con) throws DatabaseException {
        if (con != null) {
            try {
                connectionPool.returnObject(con);
            } catch (final Exception e) {
                throw new DatabaseException(ExceptionConstants.ERROR_CONNECTION_RETURN);
            }
        }
    }

    public static void closeConnection(final Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (final SQLException e) {
                System.err.println(ExceptionConstants.ERROR_CLOSE);
            }
        }
    }

    public static void closeStatements(final Statement... statements) {
        for (final Statement st : statements) {
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    System.err.println(ExceptionConstants.ERROR_CLOSE);
                }
            }
        }
    }

    public static void closePreparedStatements(final PreparedStatement... preparedStatements) {
        for (final PreparedStatement ps : preparedStatements) {
            if (ps != null) {
                try {
                    ps.close();
                } catch (final SQLException e) {
                    System.err.println(ExceptionConstants.ERROR_CLOSE);
                }
            }
        }
    }

    public static void closeResultSets(final ResultSet... resultSets) {
        for (final ResultSet rs : resultSets) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException e) {
                    System.err.println(ExceptionConstants.ERROR_CLOSE);
                }
            }
        }
    }

}
