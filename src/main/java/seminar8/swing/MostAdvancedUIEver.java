package seminar8.swing;
/*
 MostAdvancedUIEver.java
 Demonstrates basic use of JCheckBox and JSlider
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"FieldCanBeLocal"})
public class MostAdvancedUIEver extends JFrame {
    // keep ivars pointing to on-screen objects that
    // we want to access later
    private JCheckBox checkbox;
    private JTextArea textField;

    // Set up the frame -- create and install some controls in the frame
    @SuppressWarnings("Convert2Lambda")
    public MostAdvancedUIEver(String title) {
        // MANDATORY CALL
        super(title);    // superclass ctor takes frame title

        addMenuBar();

        // Get content pane -- contents of the window
        JComponent content = (JComponent) getContentPane();

        // Set to use the "flow" layout
        // (controls the arrangement of the components in the content)
        content.setLayout(new FlowLayout());

        // ****
        // Set up controls in the frame
        // ****

        // Create a vertical box component
        JComponent mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        content.add(mainPanel);

        addItemsToMainPanel(mainPanel);
        addSeparatePanelToMainPanel(mainPanel);

        // later, access the control's state with:
        // (boolean) checkbox.isSelected()
        // (int) slider.getValue()

        // ****
        // Done installing controls
        // ****

        // Standard three lines to put frame on screen
        content.setBackground(new Color(80, 180, 200));
        setPreferredSize(new Dimension(640, 480));

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);   // make it show up on screen
    }

    private void addMenuBar() {
        // Creates menu bar
        MenuBar menuBar = new MenuBar();

        // Creating menu with title "File", including menu items
        Menu fileMenu = new Menu("File");

        MenuItem newItem = new MenuItem("New");
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MostAdvancedUIEver("New Window");
            }
        });
        fileMenu.add(newItem);

        MenuItem openItem = new MenuItem("Open");
        openItem.addActionListener(new Open());
        fileMenu.add(openItem);

        MenuItem saveItem = new MenuItem("Save");
        fileMenu.add(saveItem);

        // Adding "File" menu to the menu bar
        menuBar.add(fileMenu);

        // Actually showing the menu bar
        setMenuBar(menuBar);
    }

    @SuppressWarnings("Convert2Lambda")
    private void addItemsToMainPanel(JComponent mainPanel) {
        // put a label in the panel
        textField = new JTextArea("Test Label");
        mainPanel.add(textField);

        // put a checkbox in the panel
        checkbox = new JCheckBox("Panic Mode");
        mainPanel.add(checkbox);

        mainPanel.add(Box.createVerticalStrut(20)); // 20 pixels vertical space

        // put some things in the box
        mainPanel.add(new JLabel("სიჩქარე:"));
        mainPanel.add(Box.createVerticalStrut(20));

        // Adding button
        JButton button = new MyButton("Click Me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("Button Clicked");
            }
        });
        mainPanel.add(button);
    }

    private void addSeparatePanelToMainPanel(JComponent mainPanel) {
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        mainPanel.add(panel);

        panel.add(new JLabel("Label"));

        panel.add(new JSlider(0, 100, 66));

        panel.add(new MyButton("MyButton"));
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

        new MostAdvancedUIEver("FreeUni Control Frame");
    }

    private class Open implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            textField.setText("Open Clicked");
        }
    }

    public class MyButton extends JButton {

        public MyButton(String text) {
            super(text);
            this.setForeground(new Color(60, 120, 150));
            this.setFocusPainted(false);
            this.setFont(new Font("Tahoma", Font.BOLD, 12));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRoundRect(0, 0,
                    getWidth(), getHeight(),
                    16, 16);
        }

    }

}
