package ru.home.convertermoney;

import ru.home.convertermoney.connection.ConnectionDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection connect = ConnectionDB.connect();

        ConnectionDB.closeConnection(connect);
    }
}