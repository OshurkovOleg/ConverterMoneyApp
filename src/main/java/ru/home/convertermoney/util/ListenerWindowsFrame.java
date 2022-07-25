package ru.home.convertermoney.util;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.connection.ConnectionDB;
import ru.home.convertermoney.exceptions.ConverterException;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

public class ListenerWindowsFrame implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {
        Connection START_CONNECT_BASE = ConnectionDB.getInstance();

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            ConnectionDB.getInstance().close();
        } catch (SQLException ex) {
            throw new ConverterException(Settings.ERROR_CLOSE_CONNECT_DB + ex);
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
