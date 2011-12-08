package pl.edu.pjwstk.s8267.utp.lab7;

import javax.swing.JOptionPane;

public class Zadanie2 {
	public Zadanie2() {
		String className = JOptionPane.showInputDialog("Podaj nazwę klasy");
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
			Class<?>[] classes = cls.getDeclaredClasses();
			for (Class<?> c : classes) {
				System.out.println("Własciwości obiektu "
						+ c.getCanonicalName());
				Class<?>[] cl = c.getDeclaredClasses();
				for (Class<?> cc : cl) {
					System.out.println("\t" + cc.getCanonicalName());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Zadanie2();
	}
}
