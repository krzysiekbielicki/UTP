package pl.edu.pjestk.s8267.utp.lab1;

import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FlagRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		try {
			return new Flag((String) value);
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return new JLabel((String) value);
	}
	
	

}
