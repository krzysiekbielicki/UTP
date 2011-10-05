package pl.edu.pjestk.s8267.utp.lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CountriesTable extends JFrame {
	public CountriesTable(String path) {
		super("Zadanie 1");
		List<Country> list = new ArrayList<Country>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.trim().split("\t+", 4);
				if(parts.length == 4) {
					list.add(new Country(parts[0], parts[1], Long.parseLong(parts[2]), parts[3]));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTable table = new JTable(new CountriesTableModel(list));
		table.getColumnModel().getColumn(0).setCellRenderer(new CountryPopulationRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new FlagRenderer());
		add(new JScrollPane(table));
		pack();
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new CountriesTable("data.dat");
	}
}
