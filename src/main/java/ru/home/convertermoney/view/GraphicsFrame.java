package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.TypeMoney;
import ru.home.convertermoney.factory.GetButton;
import ru.home.convertermoney.factory.GetComboBox;
import ru.home.convertermoney.factory.GetTextField;
import ru.home.convertermoney.working.ProcessingConvert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;


public class GraphicsFrame extends JFrame implements ActionListener {
    JComboBox startValue = GetComboBox.getNew();
    JComboBox resultValue = GetComboBox.getNew();
    JTextField textField = GetTextField.getNew(5);
    JButton startButton = GetButton.getNew();


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
            if(startValue.getSelectedItem() != null){
                Settings.startCurrency = startValue.getSelectedItem().toString();}
        }

        if (e.getSource() == resultValue) {
            if(resultValue.getSelectedItem() != null) {
                Settings.resultCurrency = resultValue.getSelectedItem().toString();
            }
        }


        if (e.getSource() == textField) {

                try {
                    Settings.amountToConvert = new BigDecimal(textField.getText());

                    if (Settings.amountToConvert.compareTo(new BigDecimal(Settings.MIN_AMOUNT_CONVERSION)) < 0
                            || Settings.amountToConvert.compareTo(new BigDecimal(Settings.MAX_AMOUNT_CONVERSION)) > 0) {
                        JOptionPane.showMessageDialog(null, Settings.AMOUNT_RANGE_VIOLATED, "Error", JOptionPane.PLAIN_MESSAGE);
                        Settings.amountToConvert = null;
                    }

                } catch (NullPointerException | NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, Settings.NUMBER_NOT_FOUND, "Error", JOptionPane.PLAIN_MESSAGE);
                }
        }


        if (e.getSource() == startButton) {

            if (Settings.startCurrency == null
                    || Settings.resultCurrency == null
                    || Settings.amountToConvert == null) {
                JOptionPane.showMessageDialog(null, Settings.DATA_NOT_ENOUGH, "Error", JOptionPane.PLAIN_MESSAGE);
            } else {
                try {
                    ProcessingConvert.startConvert(Settings.startCurrency, Settings.resultCurrency, Settings.amountToConvert);
                } catch (IOException exp) {
                    System.out.println("Поймали исключение которое нужно обработать" + exp.getMessage());
                }
                JOptionPane.showMessageDialog(null, Settings.conversionResult, "Result", JOptionPane.PLAIN_MESSAGE);
            }

        }


    }
}

