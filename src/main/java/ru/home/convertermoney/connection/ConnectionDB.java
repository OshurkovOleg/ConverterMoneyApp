package ru.home.convertermoney.connection;


import ru.home.convertermoney.Settings;
import ru.home.convertermoney.exceptions.ConverterException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection connect() {

        Connection dataBaseConnection = null;

        try {

            installDriverSql();

            dataBaseConnection = DriverManager.getConnection(Settings.SQL_SERVER, Settings.SQL_USER, Settings.SQL_PASSWORD);

            if (dataBaseConnection.isValid(1)) {
                System.out.println(Settings.SUCCESS_DB_CONNECTION);
            }

        } catch (ConverterException | SQLException e) {
            System.err.println(Settings.ERROR_CONNECT_SQL + e.getMessage());
        }

        return dataBaseConnection;
    }


    private static void installDriverSql() throws SQLException {
        try {
            Class<?> driverClass = Class.forName(Settings.POSTGRESQL_DRIVER);
            Driver driverPostgresql = (java.sql.Driver) driverClass.newInstance(); // refactor required
            DriverManager.registerDriver(driverPostgresql);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            throw new ConverterException(Settings.ERROR_INIT_DRIVER_DB + " " + e.getMessage());
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();

            if (!connection.isValid(1)) {
                System.out.println(Settings.DB_CONNECT_CLOSED_SUCCESS);
            }
        } catch (SQLException e) {
            throw new ConverterException(Settings.ERROR_CLOSE_CONNECT_DB);
        }

    }
}
