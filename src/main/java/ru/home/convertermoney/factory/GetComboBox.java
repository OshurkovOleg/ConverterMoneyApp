package ru.home.convertermoney.factory;

import ru.home.convertermoney.TypeMoney;

import javax.swing.*;

public class GetComboBox {
    public static JComboBox getNew() {
        return new JComboBox(TypeMoney.values());
    }
}
