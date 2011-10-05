package pl.edu.pjestk.s8267.utp.lab1;

import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CountryPopulationRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel l = new JLabel(value.toString());
		if(Long.parseLong(table.getModel().getValueAt(row, 2).toString()) > 20000000)
			l.setForeground(Color.RED);
		return l;
	}

}
