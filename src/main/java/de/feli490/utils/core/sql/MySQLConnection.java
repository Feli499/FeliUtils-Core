package de.feli490.utils.core.sql;

import java.util.logging.Logger;

public class MySQLConnection extends AbstractJdbcSQLConnection {

    static {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MySQLConnection(Logger logger, SQLConfig sqlConfig, int maxTries) {
        super(logger, sqlConfig, maxTries);
    }

    public MySQLConnection(Logger logger, SQLConfig sqlConfig) {
        this(logger, sqlConfig, 3);
    }

    @Override
    protected String getSQLPrefix() {
        return "mysql";
    }
}
