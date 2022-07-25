package ru.home.convertermoney;

import ru.home.convertermoney.connection.MethodDB;
import ru.home.convertermoney.view.FrameEdit;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Scanner;


public class Settings {
    public static final Scanner scanner = new Scanner(System.in);

    public static final Integer MAX_AMOUNT_CONVERSION = 10000;

    public static final Integer MIN_AMOUNT_CONVERSION = 1;


    public static final String USER_GREETING = "\nWelcome to currency converter\n";
    public static final String CHOOSE_TYPE_MONEYS = "\nSpecify the number from the list of currency type to be converted:";
    public static final String CHOOSE_TYPE_MONEYS_RESULT = "\nSelect the type of currency to which you want to convert from the list:";
    public static final String AMOUNT_OF_MONEY = "\nSpecify the amount to start the conversion:";
    public static final String NUMBER_NOT_FOUND = ">>> Invalid value, please try again <<<";
    public static final String AMOUNT_RANGE_VIOLATED = ">>> The amount cannot be less than one or more than ten thousand, please try again <<<\n";
    public static final String DATA_NOT_ENOUGH = ">>> not enough data to complete <<<\n";

    public static final String API_KEY = "3N4M0w7iWDSanpNJHQTzbFmqhI2GIGRe";
    public static final String USER_AGENT = "Chrome/71.1.2222.33";


    public static final String SQL_SERVER = "jdbc:postgresql://localhost:5432/postgres";
    public static final String SQL_USER = "postgres";
    public static final String SQL_PASSWORD = "123";

    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    public static final String REQUEST_FAILED = "\nRequest failed";

    public static final String ERROR = "Error";
    public static final String RESULT = "Result";

    public static final String SUCCESS_DB_CONNECTION = "Successful database connection";

    public static final String DB_CONNECT_CLOSED_SUCCESS = "Database connection closed successfully";

    public static final String ERROR_CLOSE_CONNECT_DB = "Error when trying to close database connection";
    public static final String ERROR_CONNECT_SQL = "Error connecting to sql server";
    public static final String ERROR_INIT_DRIVER_DB = "Error initializing database driver";


    public static final String SELECT_ALL_TABLE_VALUES = "select * from currency";

    public static final String INSERT_ONE_VALUE_IN_TABLE = "insert into currency (name) values (?)";

    public static final String DELETE_VALUE_CURRENCY_FROM_TABLE = "delete from currency where name=?";
    public static final String ERROR_SENDING_REQUEST = "Error while sending GET request";

    public static final String MONEY_CONVERTER = "Money Converter";

    public static final String START = "start";
    public static final String EDIT = "edit";
    public static final String ADD = "add";
    public static final String DELETE = "del";

    public static final String CURRENCY_SUCCESSFULLY_ADDED_TO_LIST = "currency successfully added to the list";

    public static final String CURRENCY_SUCCESSFULLY_REMOVED_FROM_LIST = "currency successfully removed from the list";
    public static final String ERROR_ADDING_VALUE_TO_LIST_CURRENCY = "Error adding value to database";

    public static final String PATH_TO_FILE_WITH_ALL_CURRENCY_TYPE = "src/main/resources/currency.txt";
    public static final String UNABLE_TO_PARSE_FILE = "Unable to parse file";

    public static String conversionResult;


}
