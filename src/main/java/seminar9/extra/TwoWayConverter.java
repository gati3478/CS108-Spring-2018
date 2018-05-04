package seminar9.extra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoWayConverter extends JFrame implements ActionListener {

    private JTextField dollarField;
    private JTextField poundField;

    private JButton convertToPoundsBtn;
    private JButton convertToDollarsBtn;

    public TwoWayConverter() {
        dollarField = new JTextField(8);
        poundField = new JTextField(8);

        JLabel dollarLabel = new JLabel("Dollars ($)");
        JLabel poundLabel = new JLabel("<html>Pounds (&pound;)</html>");

        convertToPoundsBtn = new JButton("Convert to Pounds");
        convertToPoundsBtn.addActionListener(this);

        convertToDollarsBtn = new JButton("Convert to Dollars");
        convertToDollarsBtn.addActionListener(this);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertToPoundsBtn) {
            Double dollars = Double.valueOf(dollarField.getText());
            poundField.setText(Double.toString(dollars * 0.7314));
        } else if (e.getSource() == convertToDollarsBtn) {
            Double pounds = Double.valueOf(poundField.getText());
            dollarField.setText(Double.toString(pounds * 1.367));
        }
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

        new TwoWayConverter();
    }

}
