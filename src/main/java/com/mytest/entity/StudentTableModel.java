package com.mytest.entity;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {

	private List<Student> list = new ArrayList<Student>();
	private String[] columnNames = { "Name", "Average" };

	public void setList(List<Student> list) {
		this.list = list;
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getName();
		case 1:
			return list.get(rowIndex).getAverage();
		default:
			return null;
		}
	}

}
