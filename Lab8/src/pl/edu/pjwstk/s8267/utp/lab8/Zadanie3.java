package pl.edu.pjwstk.s8267.utp.lab8;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Zadanie3 extends JFrame {
	private JTextArea text;
	private JTextField targetLang;
	public Zadanie3() {
		super("Translator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		text = new JTextArea();
		targetLang = new JTextField();
		JButton button = new JButton("Tłumacz");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("groovy");
				try {
					engine.eval("translateFrom = '"+text.getText()+"';");
					engine.eval("targetLang = '"+targetLang.getText()+"';");
					Object ret = engine.eval(new FileReader("translate.groovy"));
					text.setText(ret.toString());
				} catch (ScriptException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		add(targetLang, BorderLayout.NORTH);
		add(text, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		setSize(300, 100);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Zadanie3();
	}
}
