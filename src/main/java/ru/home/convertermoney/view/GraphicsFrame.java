package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.TypeMoney;
import ru.home.convertermoney.working.ProcessingConvert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;


public class GraphicsFrame extends JFrame implements ActionListener {
    JComboBox<TypeMoney> startValue = new JComboBox<>(TypeMoney.values());
    JComboBox<TypeMoney> resultValue = new JComboBox<>(TypeMoney.values());
    JTextField textField = new JTextField(5);
    JButton startButton = new JButton("start");


    public GraphicsFrame() {
        super("Money Converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setBounds(300, 300, 300, 80);
        this.setVisible(true);


        startValue.addActionListener(this);
        resultValue.addActionListener(this);
        textField.addActionListener(this);
        startButton.addActionListener(this);

        this.add(startValue);
        this.add(resultValue);
        this.add(textField);
        this.add(startButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startValue) {
            if (Objects.nonNull(startValue.getSelectedItem())) {  //?
                Settings.startCurrency = startValue.getSelectedItem().toString();

            }
        }

        if (e.getSource() == resultValue) {
            if (resultValue.getSelectedItem() != null) {
                Settings.resultCurrency = resultValue.getSelectedItem().toString();
            }
        }


        if (e.getSource() == textField) {
            setAmountForConverted(textField.getText());
        }


        if (e.getSource() == startButton) {
            startConverting();
        }

    }

    private void setAmountForConverted(String valueAmount) {
        try {
            Settings.amountToConvert = BigDecimal.valueOf(Integer.parseInt(textField.getText())); //?

            if (isCorrectNumbers()) {
                JOptionPane.showMessageDialog(null, Settings.AMOUNT_RANGE_VIOLATED, Settings.ERROR, JOptionPane.PLAIN_MESSAGE);
                Settings.amountToConvert = null;
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
                ProcessingConvert.startConvert(Settings.startCurrency, Settings.resultCurrency, Settings.amountToConvert);
            } catch (IOException exp) {
                System.out.println("Поймали исключение которое нужно обработать" + exp.getMessage());
            }
            JOptionPane.showMessageDialog(null, Settings.conversionResult, Settings.RESULT, JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean isCompleteData() {
        return Settings.startCurrency == null
                || Settings.resultCurrency == null
                || Settings.amountToConvert == null;
    }

    private boolean isCorrectNumbers() {
        return Settings.amountToConvert.compareTo(BigDecimal.valueOf(Settings.MIN_AMOUNT_CONVERSION)) < 0
                || Settings.amountToConvert.compareTo(BigDecimal.valueOf(Settings.MAX_AMOUNT_CONVERSION)) > 0;
    }

}
