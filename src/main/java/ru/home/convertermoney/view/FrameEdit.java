package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.repository.CurrenciesDB;
import ru.home.convertermoney.repository.CurrenciesFiles;
import ru.home.convertermoney.util.ComboBoxFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameEdit extends JFrame implements ActionListener {

    private static final String DELETE = "del";
    private static final String ADD = "add";
    private final JButton ADD_CURRENCY_IN_LIST = new JButton(ADD);
    private final JButton DELETE_CURRENCY_IN_LIST = new JButton(DELETE);
    private final JComboBox<String> LIST_ALL_CURRENCIES = new JComboBox<>(CurrenciesFiles.getAllCurrenciesFromFile());
    private String nameCurrencyForAddToTable;

    public FrameEdit() {
        super(FrameMain.EDIT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setBounds(300, 300, 200, 200);
        this.setVisible(true);

        this.add(ADD_CURRENCY_IN_LIST);
        this.add(DELETE_CURRENCY_IN_LIST);
        this.add(LIST_ALL_CURRENCIES);
        LIST_ALL_CURRENCIES.setEditable(true);


        ADD_CURRENCY_IN_LIST.addActionListener(this);
        DELETE_CURRENCY_IN_LIST.addActionListener(this);
        LIST_ALL_CURRENCIES.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == LIST_ALL_CURRENCIES) {
            if (LIST_ALL_CURRENCIES.getSelectedItem() != null) {
                nameCurrencyForAddToTable = LIST_ALL_CURRENCIES.getSelectedItem().toString();
            }
        }


        if (e.getSource() == ADD_CURRENCY_IN_LIST) {
            CurrenciesDB.insertRowInTable(nameCurrencyForAddToTable);
            ComboBoxFrame.updateComboBoxInFrame();

        }

        if (e.getSource() == DELETE_CURRENCY_IN_LIST) {
            CurrenciesDB.deleteRowFromTable(nameCurrencyForAddToTable);
            ComboBoxFrame.updateComboBoxInFrame();
        }
    }


}
