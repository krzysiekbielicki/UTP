package pl.edu.pjwstk.s8267.utp.lab3;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTable;

import pl.edu.pjestk.s8267.utp.lab1.CountriesTableModel;
import pl.edu.pjestk.s8267.utp.lab1.Country;


public class A extends Thread {
	
	private ObjectInputStream pipe;
	private CountriesTableModel ctm;
	public void setObjectInputStream(ObjectInputStream pipe) {
		this.pipe = pipe;
	}
	
	public void setTableModel(CountriesTableModel ctm) {
		this.ctm = ctm;
	}
	
	@Override
	public void run() {
		Country c;
		try {
			while((c = (Country)pipe.readObject()) != null) {
				ctm.addRow(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}
