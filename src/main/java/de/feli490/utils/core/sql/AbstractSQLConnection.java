package de.feli490.utils.core.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class AbstractSQLConnection implements SQLConnection {

    private final Logger logger;
    private final int maxTries;
    private Connection connection;

    private final Map<String, PreparedStatement> preparedStatements = new HashMap<>();

    public AbstractSQLConnection(Logger logger, int maxTries) throws SQLException {
        this.logger = logger;
        this.maxTries = maxTries;
        connect();
    }

    protected abstract Connection buildConnection() throws SQLException;

    @Override
    public synchronized void connect() throws SQLException {

        for (int tries = 0; tries < maxTries; tries++)
            try {
                connection = buildConnection();
                return;
            } catch (SQLException e) {
                if (tries == maxTries - 1) //On final try throw detailed exception
                    throw new SQLException("Could not connect to database!", e);
                logger.warning("Could not connect to database! Retrying... (" + (tries + 1) + "/" + maxTries + ")");
            }
    }

    public PreparedStatement createPreparedStatement(String sql) throws SQLException {

        if (connection == null)
            throw new IllegalStateException("Connection is not initialized!");

        if (preparedStatements.containsKey(sql))
            return preparedStatements.get(sql);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatements.put(sql, preparedStatement);

        return preparedStatement;
    }

    @Override
    public synchronized void disconnect() {

        if (this.connection == null)
            return;


        try {
            for (PreparedStatement preparedStatement : preparedStatements.values()) {
                preparedStatement.close();
            }
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.connection = null;
            preparedStatements.clear();
        }
    }
}
