package de.feli490.utils.core.sql;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractSQLConnection implements SQLConnection {

    private final int maxTries;
    private Connection connection;

    public AbstractSQLConnection(int maxTries) throws SQLException {
        this.maxTries = maxTries;
        connect();
    }

    public AbstractSQLConnection() throws SQLException {
        this(3);
    }

    protected abstract Connection buildConnection() throws SQLException;

    @Override
    public void connect() throws SQLException {
        connection = buildConnection();
    }

    @Override
    public Connection getConnection() {
        if (this.connection == null) {
            throw new IllegalStateException("Connection not initialized!");
        }
        return this.connection;
    }

    @Override
    public synchronized void disconnect() {

        if (this.connection == null)
            return;

        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.connection = null;
        }
    }
}
