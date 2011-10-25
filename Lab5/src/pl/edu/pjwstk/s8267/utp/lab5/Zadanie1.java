package pl.edu.pjwstk.s8267.utp.lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zadanie1 {

	public static void main(String[] args) {

		Map<String, Integer> m = new HashMap<String, Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("test.txt"));
			String line;
			StringBuilder buffer = new StringBuilder();
			while ((line = br.readLine()) != null) {
				buffer.append(line+" ");
			}
			Pattern pattern = Pattern.compile("([a-zA-ZżółćęśąźńŻÓŁĆĘŚĄŹŃ]+)");
			Matcher matcher = pattern.matcher(buffer.toString());
			String match;
			while(matcher.find()) {
				match = matcher.group();
				if(m.containsKey(match)) {
					m.put(match, m.get(match)+1);
				} else {
					m.put(match, 1);
				}
					
			}

			ArrayList<String> keys = new ArrayList<String>(m.keySet());
			Collections.sort(keys, Collator.getInstance(new Locale("pl")));
			for (String key : keys) {
				System.out.println(key+" "+m.get(key));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
