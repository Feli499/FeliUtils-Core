package de.feli490.utils.core.sql;

import java.io.Closeable;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLConnection extends Closeable {
    void disconnect();

    void connect() throws SQLException;

    PreparedStatement createPreparedStatement(String sql) throws SQLException;

    @Override
    default void close() throws IOException{
        disconnect();
    }
}
