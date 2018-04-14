package com.freeuni.oop.seminar10;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

@SuppressWarnings("Convert2Lambda")
public class TextExampleFancy {

    public static void main(String[] args) {
        // Set GUI Look And Feel Boilerplate.
        // Do this incantation at the start of main() to tell Swing
        // to use the GUI LookAndFeel of the native platform. It's ok
        // to ignore the exception.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTextField field1 = new JTextField(20);
                JTextField field2 = new JTextField(20);

                final JTextField infoField = new JTextField(15);

                field2.setDocument(field1.getDocument());

                Document doc = field1.getDocument();
                field2.setDocument(doc);

                doc.addDocumentListener(new DocumentListener() {

                    public void changedUpdate(DocumentEvent arg0) {
                        infoField.setText("Changed");
                    }

                    public void insertUpdate(DocumentEvent e) {
                        infoField.setText("Inserted");
                    }

                    public void removeUpdate(DocumentEvent e) {
                        infoField.setText("Removed");
                    }

                });

                JFrame f = new JFrame();
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.getContentPane().setLayout(new FlowLayout());
                f.getContentPane().add(field1);
                f.getContentPane().add(field2);
                f.getContentPane().add(new JLabel("Operation:"));
                f.getContentPane().add(infoField);

                f.pack();
                f.setVisible(true);
            }
        });
    }

}
