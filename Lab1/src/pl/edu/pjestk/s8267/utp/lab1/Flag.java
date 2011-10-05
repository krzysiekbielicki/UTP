package pl.edu.pjestk.s8267.utp.lab1;

import java.awt.Component;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

public class Flag extends Component {

	private ImageIcon icon;

	public Flag(String value) throws MalformedURLException {
		icon = new ImageIcon(new URL("http://www.crwflags.com/fotw/images/"+value.substring(0, 1)+"/"+value+".gif"));
		//setIcon(icon);
	}
	
	
	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		int newWidth = icon.getIconWidth()*getHeight()/icon.getIconHeight();
		g.drawImage(icon.getImage(), 0, 0, newWidth, getHeight(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
	}

}
