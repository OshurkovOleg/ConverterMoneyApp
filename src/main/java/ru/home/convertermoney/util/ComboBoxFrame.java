package ru.home.convertermoney.util;

import ru.home.convertermoney.repository.CurrenciesDB;
import ru.home.convertermoney.view.FrameMain;

import javax.swing.*;

public class ComboBoxFrame {
    public static void updateComboBoxInFrame() {
        FrameMain.getInstance().getListIncomingCurrencyType().setModel(new DefaultComboBoxModel<>(CurrenciesDB.selectValueFromDataBase()));
        FrameMain.getInstance().getListExpectedCurrencyType().setModel(new DefaultComboBoxModel<>(CurrenciesDB.selectValueFromDataBase()));
    }
}
