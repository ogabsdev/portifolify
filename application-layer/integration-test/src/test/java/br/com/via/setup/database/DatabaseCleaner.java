package br.com.via.setup.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DatabaseCleaner {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseCleaner.class);

    @Autowired
    private DataSource dataSource;

    public void clean() throws SQLException {
        LOG.info("Sanitizing...");
        Connection connection = dataSource.getConnection();

        try (connection; Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("SET REFERENTIAL_INTEGRITY = 0");
            truncateTables(connection, stmt);
            restartSequences(connection, stmt);
            stmt.executeUpdate("SET REFERENTIAL_INTEGRITY = 1");
        }
    }

    private void truncateTables(final Connection connection, final Statement stmt) throws SQLException {
        try (ResultSet rs = connection.createStatement().executeQuery("SHOW TABLES")) {
            while (rs.next()) {
                stmt.executeUpdate("TRUNCATE TABLE " + rs.getString("TABLE_NAME"));
            }
        }
    }

    private void restartSequences(final Connection connection, final Statement stmt) throws SQLException {
        try (ResultSet rs = connection
                .createStatement()
                .executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES")) {
            while (rs.next()) {
                stmt.executeUpdate("ALTER SEQUENCE " + rs.getString("SEQUENCE_NAME") + " RESTART WITH 1");
            }
        }
    }

}
