package ru.home.convertermoney.factory;

import ru.home.convertermoney.TypeMoney;

import javax.swing.*;

public class GetTextField {

    public static JTextField getNew(Integer col) {
        return new JTextField(col);
    }
}