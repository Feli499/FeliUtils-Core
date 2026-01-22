package de.feli490.utils.core.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class AbstractSQLConnection implements SQLConnection {

    private final Logger logger;
    private final int maxTries;
    private Connection connection;

    public AbstractSQLConnection(Logger logger, int maxTries) {
        this.logger = logger;
        this.maxTries = maxTries;
    }

    protected abstract Connection buildConnection() throws SQLException;

    @Override
    public synchronized void connect() throws SQLException {

        for (int tries = 0; tries < maxTries; tries++)
            try {
                connection = buildConnection();
                return;
            } catch (SQLException e) {
                logger.warning("Could not connect to database! Retrying... (" + (tries + 1) + "/" + maxTries + ")");
            }
        throw new SQLException("Could not connect to database!");
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
