package pl.edu.pjwstk.s8267.utp.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Pipe;
import java.util.Random;

import pl.edu.pjestk.s8267.utp.lab1.Country;
import pl.edu.pjestk.s8267.utp.lab1.GeoNamesResponse;

import com.google.gson.Gson;

public class B extends Thread {
	private PipedOutputStream pipe;
	public B() {
		pipe = new PipedOutputStream();
	}
	
	public PipedInputStream getPipedInputStream() throws IOException {
		return new PipedInputStream(pipe);
	}
	@Override
	public void run() {
		String json = "";
		try {
			// Create a URL for the desired page
			URL url = new URL(
					"http://api.geonames.org/countryInfoJSON?username=s8267");
			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				json += str;
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		GeoNamesResponse gnr = gson.fromJson(json, GeoNamesResponse.class);
		try {
			ObjectOutputStream out = new ObjectOutputStream(pipe);
			Random rand = new Random(System.currentTimeMillis());
			for(Country c : gnr.geonames) {
				//System.out.println(c);
				out.writeObject(c);
				try {
					Thread.sleep(rand.nextInt(10)*500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
