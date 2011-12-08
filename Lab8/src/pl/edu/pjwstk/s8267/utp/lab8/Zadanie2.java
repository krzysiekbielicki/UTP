package pl.edu.pjwstk.s8267.utp.lab8;

import groovy.lang.GroovyCodeSource;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.CodeSource;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.Phases;

public class Zadanie2 extends JFrame {
	private JTextArea text;

	public Zadanie2() {
		super("GroovyCalc");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		text = new JTextArea();
		JButton button = new JButton("Policz");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("groovy");
				try {
					CompilationUnit unit = new CompilationUnit(
							CompilerConfiguration.DEFAULT, null, null);

					unit.addSource("test", text.getText());
					try {
						unit.compile(Phases.CONVERSION);
						Object ret = engine.eval(text.getText());
						text.setText(ret.toString());
					} catch (CompilationFailedException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				} catch (ScriptException e) {
					e.printStackTrace();
				}
			}
		});
		add(text, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		setSize(300, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Zadanie2();
	}
}
