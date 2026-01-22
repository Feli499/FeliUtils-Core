package de.feli490.utils.core.sql;

import java.sql.SQLException;

public class MySQLConnection extends AbstractJdbcSQLConnection {

    public MySQLConnection(SQLConfig sqlConfig) throws SQLException {
        super(sqlConfig);
    }

    @Override
    protected String getSQLPrefix() {
        return "mysql";
    }
}
