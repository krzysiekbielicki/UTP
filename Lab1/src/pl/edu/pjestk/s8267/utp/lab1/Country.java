package pl.edu.pjestk.s8267.utp.lab1;

import java.io.Serializable;

public class Country implements Serializable {
	private String countryName;
	private String capital;
	private long population;
	private String countryCode;
	
	public Country(String name, String capitol, long population, String code) {
		this.countryName = name;
		this.capital = capitol;
		this.population = population;
		this.countryCode = code;
	}

	public String getName() {
		return countryName;
	}

	public String getCapitol() {
		return capital;
	}

	public long getPopulation() {
		return population;
	}

	public String getCountryCode() {
		// TODO Auto-generated method stub
		return countryCode;
	}
}
