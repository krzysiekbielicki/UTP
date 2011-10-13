package pl.edu.pjwstk.s8267.utp.lab3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import pl.edu.pjestk.s8267.utp.lab1.CountriesTableModel;
import pl.edu.pjestk.s8267.utp.lab1.CountryPopulationRenderer;
import pl.edu.pjestk.s8267.utp.lab1.FlagRenderer;

public class Main extends JFrame {
	public Main() throws IOException {
		super("Piped");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		CountriesTableModel ctm = new CountriesTableModel(null);
		JTable table = new JTable(ctm);
		table.getColumnModel().getColumn(0).setCellRenderer(new CountryPopulationRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new FlagRenderer());
		add(new JScrollPane(table));
		pack();
		setVisible(true);
		A writer = new A();
		B reader = new B();
		reader.start();
		writer.setObjectInputStream(new ObjectInputStream(reader.getPipedInputStream()));
		writer.setTableModel(ctm);
		writer.start();
	}
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
