package com.freeuni.oop.seminar9.extra;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("Convert2Lambda")
public class TwoWayConverterAnonymous extends JFrame {

    private JTextField dollarField;
    private JTextField poundField;

    public TwoWayConverterAnonymous() {
        dollarField = new JTextField(8);
        poundField = new JTextField(8);

        JLabel dollarLabel = new JLabel("Dollars ($)");
        JLabel poundLabel = new JLabel("<html>Pounds (&pound;)</html>");

        JButton convertToPoundsBtn = new JButton("Convert to Pounds");
        convertToPoundsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double dollars = Double.valueOf(dollarField.getText());
                poundField.setText(Double.toString(dollars * 0.731421884));
            }
        });

        JButton convertToDollarsBtn = new JButton("Convert to Dollars");
        convertToDollarsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double pounds = Double.valueOf(poundField.getText());
                dollarField.setText(Double.toString(pounds * 1.367));
            }
        });

        add(dollarLabel);
        add(dollarField);
        add(poundLabel);
        add(poundField);
        add(convertToPoundsBtn);
        add(convertToDollarsBtn);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        // Set GUI Look And Feel Boilerplate.
        // Do this incantation at the start of main() to tell Swing
        // to use the GUI LookAndFeel of the native platform. It's ok
        // to ignore the exception.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        new TwoWayConverterAnonymous();
    }
}
