package de.feli490.utils.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection extends AbstractSQLConnection {

    private final String path;

    public SQLiteConnection(String path) throws SQLException {
        super();
        this.path = path;
    }

    @Override
    public Connection buildConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + path);
    }
}
