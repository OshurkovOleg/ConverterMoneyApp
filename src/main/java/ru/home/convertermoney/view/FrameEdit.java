package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.connection.MethodDB;
import ru.home.convertermoney.util.GetAllCurrencies;
import ru.home.convertermoney.util.UpdateBoxFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameEdit extends JFrame implements ActionListener {

//    private static FrameEdit instance;
    private final JButton ADD_CURRENCY_IN_LIST = new JButton(Settings.ADD);
    private final JButton DELETE_CURRENCY_IN_LIST = new JButton(Settings.DELETE);
    private final JComboBox<String> LIST_ALL_CURRENCIES = new JComboBox<>(GetAllCurrencies.getAllCurrenciesFromFile());
    private String nameCurrencyForAddToTable;

    public FrameEdit() {
        super(Settings.EDIT);
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

/*    public static FrameEdit getInstance() {
        if (instance == null) {
            instance = new FrameEdit();
            return instance;
        }
        return instance;
    }*/


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == LIST_ALL_CURRENCIES) {
            if (LIST_ALL_CURRENCIES.getSelectedItem() != null) {
                nameCurrencyForAddToTable = LIST_ALL_CURRENCIES.getSelectedItem().toString();
            }
        }


        if (e.getSource() == ADD_CURRENCY_IN_LIST) {
            MethodDB.insertRowInTable(nameCurrencyForAddToTable);
            UpdateBoxFrame.mainBoxs();

        }

        if (e.getSource() == DELETE_CURRENCY_IN_LIST) {
            MethodDB.deleteRowFromTable(nameCurrencyForAddToTable);
            UpdateBoxFrame.mainBoxs();
        }
    }

}
