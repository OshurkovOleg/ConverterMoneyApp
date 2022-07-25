package ru.home.convertermoney.connection;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.exceptions.ConverterException;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MethodDB {

    public static String[] selectValueFromDataBase() {

        try (Statement statement = ConnectionDB.getInstance().createStatement();
             ResultSet resultSet = statement.executeQuery(Settings.SELECT_ALL_TABLE_VALUES)) {

            List<String> listType = new ArrayList<>();

            while (resultSet.next()) {
                listType.add(resultSet.getString(2));
            }

            return listType.toArray(n -> new String[listType.size()]);

        } catch (SQLException e) {
            throw new ConverterException(Settings.ERROR_CONNECT_SQL + e);
        }
    }

    public static void insertRowInTable(String currency) {

        if (isNotBlankAndNullCurrency(currency)) {
            try (PreparedStatement preparedStatement = ConnectionDB.getInstance().prepareStatement(Settings.INSERT_ONE_VALUE_IN_TABLE)) {
                preparedStatement.setString(1, currency);

                if (preparedStatement.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, Settings.CURRENCY_SUCCESSFULLY_ADDED_TO_LIST,
                            Settings.RESULT, JOptionPane.PLAIN_MESSAGE);
                }
            } catch (SQLException exp) {
                throw new ConverterException(Settings.ERROR_ADDING_VALUE_TO_LIST_CURRENCY + exp.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, Settings.DATA_NOT_ENOUGH, Settings.ERROR, JOptionPane.PLAIN_MESSAGE);
        }

    }

    public static void deleteRowFromTable(String currency) {

        if (isNotBlankAndNullCurrency(currency)) {

            try (PreparedStatement preparedStatement = ConnectionDB.getInstance().prepareStatement(Settings.DELETE_VALUE_CURRENCY_FROM_TABLE)) {
                preparedStatement.setString(1, currency);

                if (preparedStatement.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, Settings.CURRENCY_SUCCESSFULLY_REMOVED_FROM_LIST,
                            Settings.RESULT, JOptionPane.PLAIN_MESSAGE);
                }

            } catch (SQLException exp) {
                throw new ConverterException(Settings.ERROR_ADDING_VALUE_TO_LIST_CURRENCY + exp.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, Settings.DATA_NOT_ENOUGH, Settings.ERROR, JOptionPane.PLAIN_MESSAGE);
        }

    }

    private static boolean isNotBlankAndNullCurrency(String textField) {
        return textField != null && !textField.isBlank();
    }

}
