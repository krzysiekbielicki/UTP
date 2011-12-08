package pl.edu.pjwstk.s8267.utp.lab7;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Zadanie1 extends JFrame {

	private JTextField f1;
	private JTextField f2;

	public Zadanie1() {
		super("PropertyChangeListener");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Box b = new Box(BoxLayout.LINE_AXIS);
		f1 = new JTextField();
		f2 = new JTextField("0");
		b.add(f1);
		b.add(f2);
		setContentPane(b);
		setSize(300, 50);
		setVisible(true);
		Randomizer t1 = new Randomizer();
		t1.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				f1.setText((Integer) evt.getNewValue() + "");
				f2.setText(Integer.parseInt(f2.getText())
						+ (Integer) evt.getNewValue() + "");
			}
		});
		t1.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Zadanie1();
	}

}
