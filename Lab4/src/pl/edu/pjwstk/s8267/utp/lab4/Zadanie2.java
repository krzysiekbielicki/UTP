package pl.edu.pjwstk.s8267.utp.lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Zadanie2 {
	public static void main(String[] args) {

		Map<String, List<String>> m = new HashMap<String, List<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("unixdict.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				char[] ch = line.toCharArray();
				Arrays.sort(ch);
				String s = new String(ch);
				List<String> l = m.get(s);
				if (l == null)
					m.put(s, l = new ArrayList<String>());
				l.add(line);

			}

			List<List<String>> top = new ArrayList<List<String>>();
			for (List<String> l : m.values()) {
				if (l.size() > 1)
					top.add(l);
			}

			Collections.sort(top, new Comparator<List<String>>() {
				public int compare(List<String> l1, List<String> l2) {
					return l2.size() - l1.size();
				}
			});

			for (List<String> l : top) {
				System.out.println("Liczba anagramów - " + l.size() + ": " + l);
			}
			
			String word = JOptionPane.showInputDialog("podaj słowo");
			char[] ch = word.toCharArray();
			Arrays.sort(ch);
			System.out.println(m.get(new String(ch)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}