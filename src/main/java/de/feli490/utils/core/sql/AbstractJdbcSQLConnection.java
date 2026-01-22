package de.feli490.utils.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class AbstractJdbcSQLConnection extends AbstractSQLConnection {

    private final SQLConfig config;

    public AbstractJdbcSQLConnection(Logger logger, SQLConfig config, int maxTries) {
        super(logger, maxTries);
        this.config = config;
    }

    public String getHost() {
        return config.host();
    }

    public int getPort() {
        return config.port();
    }

    public String getDatabase() {
        return config.database();
    }

    public String getUser() {
        return config.user();
    }

    public String getPassword() {
        return config.password();
    }

    protected abstract String getSQLPrefix();

    @Override
    public Connection buildConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:" + getSQLPrefix() + "://" + getHost() + ":" + getPort() + "/" + getDatabase(),
                                           getUser(),
                                           getPassword());
    }
}
