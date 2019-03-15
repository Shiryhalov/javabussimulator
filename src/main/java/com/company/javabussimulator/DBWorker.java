package com.company.javabussimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection connection;

    DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено.");
            }
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
