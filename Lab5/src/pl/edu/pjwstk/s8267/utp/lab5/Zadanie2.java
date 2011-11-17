package pl.edu.pjwstk.s8267.utp.lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zadanie2 {
	public static void main(String[] args) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("klienci.txt"));
		
			String line;
			HashMap<String, ArrayList<Klient>> data = new HashMap<String, ArrayList<Klient>>();
			while ((line = br.readLine()) != null) {
				Klient k = new Klient(line);
				ArrayList<Klient> l = data.get(k.getId());
				if (l == null) {
					l = new ArrayList<Klient>();
					data.put(k.getId(), l);
				}
				l.add(k);
			}
			List<Klient> allData = new ArrayList<Klient>();
			for(Map.Entry<String, ArrayList<Klient>> e : data.entrySet()) {
				allData.addAll(e.getValue());
			}
			System.out.println("Nazwiska");
			Collections.sort(allData, new Comparator<Klient>() {
				public int compare(Klient l1, Klient l2) {
					int c = l1.getName().compareTo(l2.getName());
					if(c == 0) {
						return l1.getId().compareTo(l2.getId());
					}
					return c;
				}
			});
			for (Klient k : allData) {
				System.out.println(k);
			}
			
			System.out.println("\nKoszty");
			Collections.sort(allData, new Comparator<Klient>() {
				public int compare(Klient l1, Klient l2) {
					double c = l2.getMul() - l1.getMul();
					if(c == 0) {
						return l1.getId().compareTo(l2.getId());
					}
					return (int) c;
				}
			});
			for (Klient k : allData) {
				System.out.println(k.toString(true));
			}
			
			System.out.println("\nKlient1");
			for (Klient k : data.get("c00001")) {
				System.out.println(k);
			}
			System.out.println("\nKlient2");
			for (Klient k : data.get("c00002")) {
				System.out.println(k);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
