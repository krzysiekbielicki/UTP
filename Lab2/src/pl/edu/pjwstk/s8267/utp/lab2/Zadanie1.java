package pl.edu.pjwstk.s8267.utp.lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Zadanie1 {
	
	public Zadanie1() {
		JFileChooser fc = new JFileChooser(".");
		fc.setFileFilter(new FileNameExtensionFilter("Java source files", "java"));
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setDialogTitle("Wybierz plik źródłowy javy");
		//qif(fd)
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File selectedFile = fc.getSelectedFile();
			if(selectedFile.canRead()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					String line;
					String file = "";
					while((line = br.readLine()) != null)
						file += line +"\n";
					br.close();
					
					Pattern p = Pattern.compile("wariant");
					Matcher m = p.matcher(file);
					int war = 0;
					while(m.find()) {
						war++;
					}

					file = file.replaceAll("/\\*(?:.|[\\n\\r])*?\\*/", "");
					file = file.replaceAll("//.*\n", "");
					file = file.replaceAll("if", "\nif");
					System.out.println("-----------\n"+file+"\n---------");
					p = Pattern.compile("\\s*(if)\\s*(\\(.+\\))");
			        m = p.matcher(file);
			        int ifs = 0;
			        while(m.find()) {
			        	System.out.println(m.group());
			        	ifs++;
			        }
			        String rep = (System.getProperty("os.name").equals("Linux")?"/tmp/":"C:/Temp/")+selectedFile.getName()+".rep";
			        FileWriter fw = new FileWriter(rep);
			        fw.write("Liczba instrukcji if: "+ifs+"\nLiczba napisów: "+war);
			        fw.close();
			        JOptionPane.showMessageDialog(null, "Liczba instrukcji if: "+ifs+"\nLiczba napisów: "+war);
			        JOptionPane.showMessageDialog(null, "Zapisano plik raportu w "+rep);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Nie mozna odczytać wybranego pliku");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nie wybrano zadnego pliku");
		}
	}
	
	public static void main(String args[]) {
		Properties p = System.getProperties();
		new Zadanie1();
	}
}
