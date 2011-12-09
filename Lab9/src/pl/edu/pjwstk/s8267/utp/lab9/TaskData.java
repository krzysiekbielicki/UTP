package pl.edu.pjwstk.s8267.utp.lab9;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TaskData {
	String name;
	String data;
	private PropertyChangeListener listener;
	
	public TaskData(String name) {
		this.name = name;
		data = "";
	}
	
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}
	
	public void appendData(String line) {
		if(listener != null)
			listener.propertyChange(new PropertyChangeEvent(this, "data", data, data+=line));
		else
			data+=line;
	}
}
