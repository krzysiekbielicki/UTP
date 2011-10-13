package pl.edu.pjestk.s8267.utp.lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.google.gson.Gson;

public class CountriesTable extends JFrame {
	public CountriesTable(String path) {
		super("Zadanie 1");
		String json = "";
		try {
		    // Create a URL for the desired page
		    URL url = new URL("http://api.geonames.org/countryInfoJSON?username=s8267");
		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String str;
		    while ((str = in.readLine()) != null) {
		        json += str;
		    }
		    in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		Gson gson = new Gson();
		GeoNamesResponse gnr = gson.fromJson(json, GeoNamesResponse.class);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTable table = new JTable(new CountriesTableModel(gnr.geonames));
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
