package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_HOSTNAME = "localhost";
    private static final String DB_NAME = "myfirstdatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOSTNAME + "/" + DB_NAME;
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException e) {
            e.fillInStackTrace();
        }

        return connection;
    }
}
