package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.connection.ConnectionDB;
import ru.home.convertermoney.connection.MethodDB;
import ru.home.convertermoney.util.ListenerWindowsFrame;
import ru.home.convertermoney.working.ProcessingConvert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;


public class FrameMain extends JFrame implements ActionListener {

    private static FrameMain instance;

    private final JButton BUTTON_START_CONVERSION_PROCESS = new JButton(Settings.START);
    private final JButton BUTTON_EDITING_LIST_CURRENCIES = new JButton(Settings.EDIT);
    private final JTextField TEXT_FIELD_VALUE = new JTextField(5);
    protected JComboBox<String> listIncomingCurrencyType = new JComboBox<>(MethodDB.selectValueFromDataBase());
    protected JComboBox<String> listExpectedCurrencyType = new JComboBox<>(MethodDB.selectValueFromDataBase());

    private static String startCurrency;
    private static String resultCurrency;
    private static BigDecimal amountToConvert;
    WindowListener windowsListen = new ListenerWindowsFrame();

    private FrameMain() {
        super(Settings.MONEY_CONVERTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setBounds(300, 300, 300, 120);
        this.setVisible(true);


        this.add(listIncomingCurrencyType);
        this.add(listExpectedCurrencyType);
        this.add(TEXT_FIELD_VALUE);
        this.add(BUTTON_START_CONVERSION_PROCESS);
        this.add(BUTTON_EDITING_LIST_CURRENCIES);
        this.addWindowListener(windowsListen);

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
            new FrameEdit(); // TODO не работает корретно в виде синглтона
        }

    }

    private void setAmountForConverted(String valueAmount) {
        try {
            amountToConvert = BigDecimal.valueOf(Integer.parseInt(TEXT_FIELD_VALUE.getText()));

            if (isCorrectNumbers()) {
                JOptionPane.showMessageDialog(null, Settings.AMOUNT_RANGE_VIOLATED, Settings.ERROR, JOptionPane.PLAIN_MESSAGE);
                amountToConvert = null;
            }

        } catch (NullPointerException | NumberFormatException exp) {
            JOptionPane.showMessageDialog(null, Settings.NUMBER_NOT_FOUND, Settings.ERROR, JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void startConverting() {
        if (isCompleteData()) {
            JOptionPane.showMessageDialog(null, Settings.DATA_NOT_ENOUGH, Settings.ERROR, JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                ProcessingConvert.startConvert(startCurrency, resultCurrency, amountToConvert);
            } catch (IOException exp) {
                System.out.println("Поймали исключение которое нужно обработать" + exp.getMessage());
            }
            JOptionPane.showMessageDialog(null, Settings.conversionResult, Settings.RESULT, JOptionPane.PLAIN_MESSAGE);
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
