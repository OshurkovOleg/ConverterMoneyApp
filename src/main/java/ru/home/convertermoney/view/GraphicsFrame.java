package ru.home.convertermoney.view;

import ru.home.convertermoney.factory.GetButton;
import ru.home.convertermoney.factory.GetComboBox;
import ru.home.convertermoney.factory.GetTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphicsFrame extends JFrame implements ActionListener {
    JComboBox startValue = GetComboBox.getNew();
    JComboBox resultValue = GetComboBox.getNew();
    JTextField textField = GetTextField.getNew(5);
    JButton startButton = GetButton.getNew();


    public GraphicsFrame() {
        super("Money Converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setBounds(300, 300, 300, 150);
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
            System.out.println(startValue.getSelectedItem());
        }

        if (e.getSource() == resultValue) {
            System.out.println(resultValue.getSelectedItem());
        }

        if (e.getSource() == textField) {
            System.out.println(textField.getText());
        }

        if (e.getSource() == startButton) {
            String str = "Finish";
            System.out.println("button worked");
            JOptionPane.showMessageDialog(null, str, "Result", JOptionPane.PLAIN_MESSAGE);
        }


    }


}
