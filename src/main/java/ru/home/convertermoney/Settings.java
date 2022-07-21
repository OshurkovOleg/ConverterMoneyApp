package ru.home.convertermoney;

import java.math.BigDecimal;
import java.util.Scanner;


public class Settings {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Integer MAX_LENGTH_TYPE = TypeMoney.values().length - 1;

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

    public static final String ERROR_CONNECT_SQL = "Error connecting to sql server";
    public static final String ERROR_INIT_DRIVER_DB = "Error initializing database driver";
    public static final String ERROR_CLOSE_CONNECT_DB = "Error when trying to close database connection";
    public static String startCurrency;
    public static String resultCurrency;
    public static BigDecimal amountToConvert;

    public static String conversionResult;

}
