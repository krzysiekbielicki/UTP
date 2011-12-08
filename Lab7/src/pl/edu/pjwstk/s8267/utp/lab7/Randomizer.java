package pl.edu.pjwstk.s8267.utp.lab7;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class Randomizer extends Thread {
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Randomizer() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		Random r = new Random(System.currentTimeMillis());
		while (true) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pcs.firePropertyChange("value", 0, r.nextInt(100));
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}
