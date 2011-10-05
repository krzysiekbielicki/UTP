package pl.edu.pjestk.s8267.utp.lab1;

public class Country {
	private String name;
	private String capitol;
	private long population;
	private String code;
	
	public Country(String name, String capitol, long population, String code) {
		this.name = name;
		this.capitol = capitol;
		this.population = population;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCapitol() {
		return capitol;
	}

	public long getPopulation() {
		return population;
	}

	public String getCountryCode() {
		// TODO Auto-generated method stub
		return code;
	}
}
