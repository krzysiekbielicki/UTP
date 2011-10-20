package pl.edu.pjwstk.s8267.utp.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

public class Zadanie1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] l = JOptionPane.showInputDialog("Podaj elementy listy A rozdzielone spacjami").split(" ");
		List<Integer> A = new ArrayList<Integer>();
		for(String s : l) {
			A.add(Integer.parseInt(s));
		}
		
		l = JOptionPane.showInputDialog("Podaj elementy listy B rozdzielone spacjami").split(" ");
		List<Integer> B = new ArrayList<Integer>();
		for(String s : l) {
			B.add(Integer.parseInt(s));
		}
		
		Collections.sort(A);
		Collections.sort(B);
		
		ArrayList<Integer> sum = new ArrayList<Integer>();
		sum.addAll(A);
		for(Integer b : B) {
			if(!A.contains(b))
				sum.add(b);
		}
		
		ArrayList<Integer> mul = new ArrayList<Integer>();
		int ia = 0;
		int ib = 0;
		for(; ia < A.size(); ia++) {
			int a = A.get(ia);
			for(; ib < A.size(); ib++) {
				int b = B.get(ib);
				if(a < b)
					break;
				else if (a > b)
					continue;
				else {
					mul.add(a);
					ib++;
				}
				break;
			}
		}
		List<Integer> AnoB = new ArrayList<Integer>();
		AnoB.addAll(A);
		for(Integer b : B) {
			AnoB.remove(b);
		}
		System.out.println(A);
		System.out.println(B);
		System.out.println(mul);
		System.out.println(AnoB);
	}

}
