package ru.home.convertermoney.repository;

import ru.home.convertermoney.connection.ConnectionDB;
import ru.home.convertermoney.exceptions.ConverterException;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrenciesDB {
    private static final String ERROR_ADDING_VALUE_TO_LIST_CURRENCY = "Error adding value to database";
    private static final String CURRENCY_SUCCESSFULLY_REMOVED_FROM_LIST = "currency successfully removed from the list";
    private static final String CURRENCY_SUCCESSFULLY_ADDED_TO_LIST = "currency successfully added to the list";
    private static final String ERROR_CONNECT_SQL = "Error connecting to sql server";
    private static final String ERROR = "Error";
    private static final String RESULT = "Result";
    private static final String DATA_NOT_ENOUGH = ">>> not enough data to complete <<<\n";


    public static String SELECT_ALL_CURRENCIES = "select * from currency";
    public static String INSERT_ONE_TYPE_CURRENCY_IN_TABLE = "insert into currency (name) values (?)";
    public static String DELETE_ONE_TYPE_CURRENCY_FROM_TABLE = "delete from currency where name=?";



    public static String[] selectValueFromDataBase() {

        try (Statement statement = ConnectionDB.getInstance().createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CURRENCIES)) {

            List<String> listType = new ArrayList<>();

            while (resultSet.next()) {
                listType.add(resultSet.getString(2));
            }

            return listType.toArray(n -> new String[listType.size()]);

        } catch (SQLException e) {
            throw new ConverterException(ERROR_CONNECT_SQL + e);
        }
    }

    public static void insertRowInTable(String currency) {

        if (isNotBlankAndNullCurrency(currency)) {
            try (PreparedStatement preparedStatement = ConnectionDB.getInstance().prepareStatement(String.valueOf(INSERT_ONE_TYPE_CURRENCY_IN_TABLE))) {
                preparedStatement.setString(1, currency);

//                Settings.INSERT_ONE_VALUE_IN_TABLE
                if (preparedStatement.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, CURRENCY_SUCCESSFULLY_ADDED_TO_LIST,
                            RESULT, JOptionPane.PLAIN_MESSAGE);
                }
            } catch (SQLException exp) {
                throw new ConverterException(ERROR_ADDING_VALUE_TO_LIST_CURRENCY + exp);
            }
        } else {
            JOptionPane.showMessageDialog(null, DATA_NOT_ENOUGH, ERROR, JOptionPane.PLAIN_MESSAGE);
        }

    }

    public static void deleteRowFromTable(String currency) {

        if (isNotBlankAndNullCurrency(currency)) {

            try (PreparedStatement preparedStatement = ConnectionDB.getInstance().prepareStatement(String.valueOf(DELETE_ONE_TYPE_CURRENCY_FROM_TABLE))) {
                preparedStatement.setString(1, currency);

                if (preparedStatement.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, CURRENCY_SUCCESSFULLY_REMOVED_FROM_LIST,
                            RESULT, JOptionPane.PLAIN_MESSAGE);
                }

            } catch (SQLException exp) {
                throw new ConverterException(ERROR_ADDING_VALUE_TO_LIST_CURRENCY + exp.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, DATA_NOT_ENOUGH, ERROR, JOptionPane.PLAIN_MESSAGE);
        }

    }

    private static boolean isNotBlankAndNullCurrency(String textField) {
        return textField != null && !textField.isBlank();
    }




}
