package org.spbu.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that grants connection to DB
 */

public class SQLConnector {
    private final String url = "jdbc:postgresql://localhost/p_data";
    private final String user = "postgres";
    private final String password = "repmrf";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
