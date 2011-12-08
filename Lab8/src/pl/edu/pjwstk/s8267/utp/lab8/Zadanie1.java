package pl.edu.pjwstk.s8267.utp.lab8;

import java.math.BigDecimal;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

public class Zadanie1 {
	public static void main(String[] args) {
		String script = "def url = 'http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml'\n"
				+ "def rates = new XmlParser().parse(url)\n"
				+ "EURmap = [:]\n"
				+ "rates.Cube.Cube.Cube.each {\n"
				+ "  EURmap[it.@currency] = it.@rate.toBigDecimal()\n"
				+ "}\n"
				+ "return EURmap\n";

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("groovy");
		try {
			@SuppressWarnings("unchecked")
			Map<String, BigDecimal> map = (Map<String, BigDecimal>) engine.eval(script);
			BigDecimal eur = new BigDecimal(JOptionPane.showInputDialog("Podaj kwote w EUR:"));
			String key = (String) JOptionPane.showInputDialog(null, "Wybierz walutę", "Przelicz "+eur+"EUR na:", JOptionPane.QUESTION_MESSAGE, null, map.keySet().toArray(), "PLN");
			JOptionPane.showMessageDialog(null, eur+"EUR to "+(map.get(key).multiply(eur))+key);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
