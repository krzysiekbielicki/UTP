package pl.edu.pjestk.s8267.utp.lab1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CountriesTableModel implements TableModel {

	private List<Country> data;
	private String[] columns = new String[] {"Państwo", "Stolica", "Populacja", "Flaga"};
	private TableModelListener modelListener;

	public CountriesTableModel(List<Country> list) {
		this.data = list;
	}

	@Override
	public int getRowCount() {
		if(data == null)
			return 0;
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	public void addRow(Country c) {
		if(data == null)
			data = new ArrayList<Country>();
		data.add(c);
		int row = data.size();
		modelListener.tableChanged(new TableModelEvent(this, row-1, row, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0:
		case 1:
		case 3:
			return String.class;
		default:
			return Integer.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data == null)
			return null;
		switch(columnIndex) {
		case 0:
			return data.get(rowIndex).getName();
		case 1:
			return data.get(rowIndex).getCapitol();
		case 2:
			return data.get(rowIndex).getPopulation();
		case 3:
			return data.get(rowIndex).getCountryCode();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		this.modelListener = l;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
