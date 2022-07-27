package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.connection.ConnectionDB;
import ru.home.convertermoney.repository.CurrenciesDB;
import ru.home.convertermoney.working.ProcessingConvert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;


public class FrameMain extends JFrame implements ActionListener {
    protected static final String EDIT = "edit";
    private static final String START = "start";
    private static final String MONEY_CONVERTER = "Money Converter";
    private static final String RESULT = "Result";
    private static final String ERROR = "Error";
    private static final String DATA_NOT_ENOUGH = ">>> not enough data to complete <<<\n";
    private static final String NUMBER_NOT_FOUND = ">>> Invalid value, please try again <<<";
    private static final String AMOUNT_RANGE_VIOLATED = ">>> The amount cannot be less than one or more than ten thousand, please try again <<<\n";

    Connection startConnect = ConnectionDB.getInstance();
    private static FrameMain instance;
    private final JButton BUTTON_START_CONVERSION_PROCESS = new JButton(START);
    private final JButton BUTTON_EDITING_LIST_CURRENCIES = new JButton(EDIT);
    private final JTextField TEXT_FIELD_VALUE = new JTextField(5);
    protected JComboBox<String> listIncomingCurrencyType = new JComboBox<>(CurrenciesDB.selectValueFromDataBase());
    protected JComboBox<String> listExpectedCurrencyType = new JComboBox<>(CurrenciesDB.selectValueFromDataBase());

    private static String startCurrency;
    private static String resultCurrency;
    private static BigDecimal amountToConvert;

    private static String conversionResult;

    private FrameMain() {
        super(MONEY_CONVERTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setBounds(300, 300, 300, 120);
        this.setVisible(true);


        this.add(listIncomingCurrencyType);
        this.add(listExpectedCurrencyType);
        this.add(TEXT_FIELD_VALUE);
        this.add(BUTTON_START_CONVERSION_PROCESS);
        this.add(BUTTON_EDITING_LIST_CURRENCIES);

        listIncomingCurrencyType.addActionListener(this);
        listExpectedCurrencyType.addActionListener(this);
        TEXT_FIELD_VALUE.addActionListener(this);
        BUTTON_START_CONVERSION_PROCESS.addActionListener(this);
        BUTTON_EDITING_LIST_CURRENCIES.addActionListener(this);

    }


    public static FrameMain getInstance() {
        if (instance == null) {
            instance = new FrameMain();

            return instance;
        }
        return instance;
    }

    public JComboBox<String> getListIncomingCurrencyType() {
        return listIncomingCurrencyType;
    }

    public JComboBox<String> getListExpectedCurrencyType() {
        return listExpectedCurrencyType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == listIncomingCurrencyType) {
            if (listIncomingCurrencyType.getSelectedItem() != null) {
                startCurrency = listIncomingCurrencyType.getSelectedItem().toString();
            }
        }

        if (e.getSource() == listExpectedCurrencyType) {
            if (listExpectedCurrencyType.getSelectedItem() != null) {
                resultCurrency = listExpectedCurrencyType.getSelectedItem().toString();
            }
        }


        if (e.getSource() == TEXT_FIELD_VALUE) {
            setAmountForConverted(TEXT_FIELD_VALUE.getText());
        }


        if (e.getSource() == BUTTON_START_CONVERSION_PROCESS) {
            startConverting();
        }

        if (e.getSource() == BUTTON_EDITING_LIST_CURRENCIES) {
            new FrameEdit();
        }

    }

    private void setAmountForConverted(String valueAmount) {
        try {
            amountToConvert = BigDecimal.valueOf(Integer.parseInt(TEXT_FIELD_VALUE.getText()));

            if (isCorrectNumbers()) {
                JOptionPane.showMessageDialog(null, AMOUNT_RANGE_VIOLATED, ERROR, JOptionPane.PLAIN_MESSAGE);
                amountToConvert = null;
            }

        } catch (NullPointerException | NumberFormatException exp) {
            JOptionPane.showMessageDialog(null, NUMBER_NOT_FOUND, ERROR, JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void startConverting() {
        if (isCompleteData()) {
            JOptionPane.showMessageDialog(null, DATA_NOT_ENOUGH, ERROR, JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                conversionResult = ProcessingConvert.startConvert(startCurrency, resultCurrency, amountToConvert);
            } catch (IOException exp) {
                System.out.println("Поймали исключение которое нужно обработать" + exp.getMessage());
            }
            JOptionPane.showMessageDialog(null, conversionResult, RESULT, JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean isCompleteData() {
        return startCurrency == null
                || resultCurrency == null
                || amountToConvert == null;
    }

    private boolean isCorrectNumbers() {
        return amountToConvert.compareTo(BigDecimal.valueOf(Settings.MIN_AMOUNT_CONVERSION)) < 0
                || amountToConvert.compareTo(BigDecimal.valueOf(Settings.MAX_AMOUNT_CONVERSION)) > 0;
    }


}
