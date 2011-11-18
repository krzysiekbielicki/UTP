package pl.edu.pjwstk.s8267.utp.lab6;

import java.io.File;

import javax.swing.JOptionPane;

public class Zadanie3 {
	public static void main(String[] args) throws Exception {

		Number sum = NumArray.of("Integer").from("1 2 3").sum();
		System.out.println(sum);

		sum = NumArray.of("Double").from("1 2 3").sum();
		System.out.println(sum);

		sum = NumArray.of("Double").from(new File("test.txt")).sum();
		System.out.println(sum);

		NumArray arr = NumArray.of(Float.class).from(JOptionPane.showInputDialog("Podaj liczby"));
		System.out.println(arr.sum());

	}
}
