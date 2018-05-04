package seminar10;// TableFrame.java
/*
 Demonstrate a couple tables using one table model.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda"})
class TableFrame extends JFrame {
    private BasicTableModel model;

    private JTable table;
    private JTable table2;

    private JButton columnButton;
    private JButton rowButton;
    private JButton deleteButton;
    private JComponent content;

    public TableFrame(String title) {
        super(title);

        // Create a table model
        model = new BasicTableModel();

        // Create a table using that model
        table = new JTable(model);

        content = (JComponent) getContentPane();
        content.setLayout(new BorderLayout());

        // there are many options for col resize strategy
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // JTable.AUTO_RESIZE_OFF

        // Create a scroll pane in the center, and put
        // the table in it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        content.add(scrollPane, BorderLayout.CENTER);

        // Create a second table using the same model, and put in the south
        table2 = new JTable(model);
        scrollPane = new JScrollPane(table2);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        content.add(scrollPane, BorderLayout.SOUTH);

        // Create a bunch of controls in a box
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        content.add(panel, BorderLayout.EAST);

        rowButton = new JButton("Add Row");
        panel.add(rowButton);
        rowButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int i = model.addRow();
                        table.clearSelection();
                        table.addRowSelectionInterval(i, i);
                    }
                }
        );

        columnButton = new JButton("Add Column");
        panel.add(columnButton);
        columnButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String result = JOptionPane.showInputDialog("What name for the new column?");
                        if (result != null) {
                            model.addColumn(result);
                        }
                    }
                }
        );

        deleteButton = new JButton("Delete Row");
        panel.add(deleteButton);
        deleteButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = table.getSelectedRow();
                        if (row != -1) model.deleteRow(row);
                    }
                }
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    static public void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TableFrame("FUT TableFrame DEMO");
            }
        });
    }

}
