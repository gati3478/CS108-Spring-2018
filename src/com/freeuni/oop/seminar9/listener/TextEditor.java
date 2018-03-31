package com.freeuni.oop.seminar9.listener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame {

    private static final int EDITOR_WIDTH = 800;
    private static final int EDITOR_HEIGHT = 400;

    private static final int FONT_SIZE = 12;

    private final JTextArea textArea;
    private String filename;

    // Clipboard for copy, cut and paste functionality
    private final Clipboard clipboard;

    public TextEditor(String title) throws HeadlessException {
        super(title);

        clipboard = this.getToolkit().getSystemClipboard();

        // Our root view
        JComponent rootPanel = (JComponent) getContentPane();
        rootPanel.setLayout(new BorderLayout());

        // Main text editor view
        textArea = new JTextArea();
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE));
        textArea.addKeyListener(new SaveShortcut());
        rootPanel.add(textArea, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootPanel.add(scroll);

        // Instantiates menu bar
        setJMenuBar(constructMenu());

        // This handles window closing via event callback
        addWindowListener(new MyWindowListener());

        setPreferredSize(new Dimension(EDITOR_WIDTH, EDITOR_HEIGHT));
        pack();
        setVisible(true);
    }

    private JMenuBar constructMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(constructFileMenu());
        menuBar.add(constructEditMenu());
        return menuBar;
    }

    private JMenu constructFileMenu() {
        JMenu menu = new JMenu("File");

        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save As...");
        JMenuItem exit = new JMenuItem("Exit");

        newFile.addActionListener(new New());
        menu.add(newFile);

        openFile.addActionListener(new Open());
        menu.add(openFile);

        saveFile.addActionListener(new Save());
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(saveFile);

        exit.addActionListener(new Exit());
        menu.add(exit);

        return menu;
    }

    private JMenu constructEditMenu() {
        JMenu menu = new JMenu("Edit");

        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        cut.addActionListener(new Cut());
        menu.add(cut);

        copy.addActionListener(new Copy());
        menu.add(copy);

        paste.addActionListener(new Paste());
        menu.add(paste);

        return menu;
    }

    private class New implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
        }
    }

    private class Open implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only txt files", "txt");
            fileChooser.setFileFilter(filter);

            int rv = fileChooser.showOpenDialog(TextEditor.this);
            if (rv == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try {
                    textArea.setText(readFile(file));
                } catch (IOException ex) {
                    System.out.println(ex + " " + ex.getMessage());
                }

                TextEditor.this.filename = file.getPath();
                TextEditor.this.setTitle(filename);
            }

            textArea.requestFocus();
        }

        private String readFile(File file) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                return sb.toString();
            }
        }
    }

    private class Save implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only txt files", "txt");
            fileChooser.setFileFilter(filter);

            int rv = fileChooser.showSaveDialog(TextEditor.this);
            if (rv == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                writeFile(file);

                TextEditor.this.filename = file.getPath();
                TextEditor.this.setTitle(filename);
            }

            textArea.requestFocus();
        }

        private void writeFile(File file) {
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(file));

                textArea.write(outFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (outFile != null) {
                    try {
                        outFile.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

    private class Exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class Cut implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String sel = textArea.getSelectedText();
            StringSelection stringSelection = new StringSelection(sel);
            clipboard.setContents(stringSelection, stringSelection);
            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        }
    }

    private class Copy implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String sel = textArea.getSelectedText();
            StringSelection stringSelection = new StringSelection(sel);
            clipboard.setContents(stringSelection, stringSelection);
        }
    }

    private class Paste implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Transferable clipTransf = clipboard.getContents(TextEditor.this);
            try {
                String selection = (String) clipTransf.getTransferData(DataFlavor.stringFlavor);
                textArea.replaceRange(selection, textArea.getSelectionStart(), textArea.getSelectionEnd());
            } catch (Exception ex) {
                System.out.println("Not a string flavor");
            }
        }
    }

    private class SaveShortcut extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            // CTRL + S shortcut
            if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                (new Save()).actionPerformed(null);
            }
        }
    }

    // Window listener for terminating program on close button
    private class MyWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextEditor("ტექსტური ედიტორი 2017");
            }
        });
    }

}
