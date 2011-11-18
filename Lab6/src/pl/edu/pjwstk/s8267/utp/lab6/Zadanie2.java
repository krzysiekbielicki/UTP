package pl.edu.pjwstk.s8267.utp.lab6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Zadanie2 extends JFrame {
	private JTextField field;
	private JLabel label;
	private static Map<String, Method> operations;
	static {
		operations = new HashMap<String, Method>();
		try {
			operations.put("+", BigDecimal.class.getMethod("add", BigDecimal.class));
			operations.put("-", BigDecimal.class.getMethod("subtract", BigDecimal.class));
			operations.put("*", BigDecimal.class.getMethod("multiply", BigDecimal.class));
			operations.put("/", BigDecimal.class.getMethod("divide", BigDecimal.class));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Zadanie2() {
		super("Kalkulator");
		setLayout(new BorderLayout());
		field = new JTextField();
		add(field, BorderLayout.CENTER);
		JButton b = new JButton("=");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText(process(field.getText())+"");
				} catch (Exception e1) {
					label.setText("Error");
				}
			}
		});
		add(b, BorderLayout.EAST);
		label = new JLabel(" ");
		add(label, BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(150, 80);
		setVisible(true);
		
	}
	
	protected BigDecimal process(String text) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] s = text.trim().split(" ");
		BigDecimal a = new BigDecimal(s[0]);
		BigDecimal b = new BigDecimal(s[2]);
		
		return (BigDecimal)operations.get(s[1]).invoke(a, b);
	}

	public static void main(String[] args) {
		new Zadanie2();
	}
}
