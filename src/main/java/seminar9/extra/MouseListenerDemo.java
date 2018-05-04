package seminar9.extra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseListenerDemo extends JFrame {

    private JTextField xField;
    private JTextField yField;

    public MouseListenerDemo() {
        xField = new JTextField(5);
        yField = new JTextField(5);

        add(new JLabel("X:"));
        add(xField);
        add(new JLabel("Y:"));
        add(yField);

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                xField.setText(e.getX() + "");
                yField.setText(e.getY() + "");
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                System.out.println("(" + e.getX() + "," + e.getY() + ")");
            }
        });

        setLayout(new FlowLayout());

        setPreferredSize(new Dimension(640, 480));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        new MouseListenerDemo();
    }

}
