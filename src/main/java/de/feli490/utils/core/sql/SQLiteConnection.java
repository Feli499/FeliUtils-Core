package de.feli490.utils.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SQLiteConnection extends AbstractSQLConnection {

    private final String path;

    public SQLiteConnection(Logger logger, String path, int maxTries) {
        super(logger, maxTries);
        this.path = path;
    }

    public SQLiteConnection(Logger logger, String path) {
        this(logger, path, 3);
    }

    @Override
    public Connection buildConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + path);
    }
}
