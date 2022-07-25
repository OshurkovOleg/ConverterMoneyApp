package ru.home.convertermoney.util;

import ru.home.convertermoney.connection.MethodDB;
import ru.home.convertermoney.view.FrameMain;

import javax.swing.*;

public class UpdateBoxFrame {

    public static void mainBoxs() {
        FrameMain.getInstance().getListIncomingCurrencyType().setModel(new DefaultComboBoxModel<>(MethodDB.selectValueFromDataBase()));
        FrameMain.getInstance().getListExpectedCurrencyType().setModel(new DefaultComboBoxModel<>(MethodDB.selectValueFromDataBase()));
    }
}
