package ru.home.convertermoney.connection;


import ru.home.convertermoney.Settings;
import ru.home.convertermoney.exceptions.ConverterException;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection instance;

    private ConnectionDB() {
    }

    public static Connection getInstance() { //TODO создает один раз подключение при создании объекта
        if (instance == null) {
            instance = new ConnectionDB().connect();
        }

        return instance;
    }

    private Connection connect() {

        try {
            installDriverSql();

            Connection dataBaseConnection = DriverManager.getConnection(Settings.SQL_SERVER,
                    Settings.SQL_USER,
                    Settings.SQL_PASSWORD);

            if (dataBaseConnection.isValid(1)) {
                System.out.println(Settings.SUCCESS_DB_CONNECTION);

                return dataBaseConnection;
            } else {
                throw new ConverterException(Settings.ERROR_CONNECT_SQL);
            }
        } catch (ConverterException | SQLException e) {
            System.err.println(Settings.ERROR_CONNECT_SQL + e.getMessage());
        }
        return null;
    }

    private void installDriverSql() throws SQLException {

        try {
            Class<?> driverClass = Class.forName(Settings.POSTGRESQL_DRIVER);
            Driver driverPostgresql = (java.sql.Driver) driverClass.getDeclaredConstructor().newInstance();
            DriverManager.registerDriver(driverPostgresql);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new ConverterException(Settings.ERROR_INIT_DRIVER_DB + " " + e.getMessage());
        }
    }

}
