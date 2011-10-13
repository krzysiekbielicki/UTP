package pl.edu.pjestk.s8267.utp.lab1;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

public class Flag extends Component {

	private Image icon;
	private Thread downloader = new Thread() {
		public void run() {
			try {
				URL url = new URL("http://www.crwflags.com/fotw/images/"
						+ countryCode.substring(0, 1) + "/" + countryCode
						+ ".gif");
				if (tmp.canWrite()) {
					// Read all the text returned by the server
					InputStream in = url.openStream();
					FileOutputStream out = new FileOutputStream(new File(tmp, countryCode + ".gif"));
					for (int b; (b = in.read()) != -1; ) {
	                    out.write(b);
	                }
					in.close();
					out.flush();
					out.close();
					icon = new ImageIcon(flag.getAbsolutePath()).getImage();
				} else {
					icon = new ImageIcon(url).getImage();
				}
				revalidate();
				repaint();
			} catch (MalformedURLException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
			} catch (IOException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
			} finally {
			}

		};
	};
	private String countryCode;
	private File tmp;
	private File flag;

	public Flag(String value) throws IOException {
		this.countryCode = value;
		tmp = new File(System.getProperty("java.io.tmpdir"));
		flag = new File(tmp, countryCode + ".gif");

		if (!flag.exists()) {
			downloader.start();
		} else {
			icon = new ImageIcon(flag.getAbsolutePath()).getImage();
		}
	}

	@Override
	public void paint(Graphics g) {
		if (icon == null)
			return;
		int newWidth = icon.getWidth(null) * getHeight() / icon.getHeight(null);
		g.drawImage(icon, 0, 0, newWidth, getHeight(), 0, 0,
				icon.getWidth(null), icon.getHeight(null), null);
	}

}
