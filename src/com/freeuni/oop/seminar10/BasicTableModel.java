package com.freeuni.oop.seminar10;// BasicTableModel.java

/*
 Demonstrate a basic table model implementation
 using ArrayList of rows, where each row is itself an ArrayList
 of the data in that row.
 This code is free for any purpose -- Nick Parlante.
 
 A row may be shorter than the number of columns
 which complicates the data handling a bit.
*/

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
class BasicTableModel extends AbstractTableModel {

    private List<String> colNames;    // defines the number of cols
    private List<List> data;    // one List for each row

    public BasicTableModel() {
        colNames = new ArrayList<>();
        data = new ArrayList<>();
    }

	/*
     Basic getXXX methods required by an class implementing TableModel
	*/

    // Returns the name of each col, numbered 0..columns-1
    @Override
    public String getColumnName(int col) {
        return colNames.get(col);
    }

    // Returns the number of columns
    @Override
    public int getColumnCount() {
        return colNames.size();
    }

    // Returns the number of rows
    @Override
    public int getRowCount() {
        return data.size();
    }

    // Returns the data for each cell, identified by its
    // row, col index.
    @Override
    public Object getValueAt(int row, int col) {
        List rowList = data.get(row);
        Object result = null;
        if (col < rowList.size()) {
            result = rowList.get(col);
        }

        // _apparently_ it's ok to return null for a "blank" cell
        return (result);
    }

    // Returns true if a cell should be editable in the table
    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    // Changes the value of a cell
    @SuppressWarnings("unchecked")
    @Override
    public void setValueAt(Object value, int row, int col) {
        List rowList = data.get(row);

        // make this row long enough
        if (col >= rowList.size()) {
            while (col >= rowList.size()) {
                rowList.add(null);
            }
        }

        // install the data
        rowList.set(col, value);

        // notify model listeners of cell change
        fireTableCellUpdated(row, col);
    }

	/*
     Convenience methods of BasicTable
	*/

    // Adds the given column to the right hand side of the model
    public void addColumn(String name) {
        colNames.add(name);
        fireTableStructureChanged();
        /*
         At present, TableModelListener does not have a more specific
		 notification for changing the number of columns.
		*/
    }

    // Adds the given row, returns the new row index
    public int addRow(List row) {
        data.add(row);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        return (data.size() - 1);
    }

    // Adds an empty row, returns the new row index
    public int addRow() {
        // Create a new row with nothing in it
        List row = new ArrayList();
        return (addRow(row));
    }

    // Deletes the given row
    public void deleteRow(int row) {
        if (row == -1) {
            return;
        }

        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

}
