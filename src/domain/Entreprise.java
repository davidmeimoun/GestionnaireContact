package domain;

import java.util.Set;

public class Entreprise extends Contact {
	private int numSiret;

	public Entreprise(String firstname, String lastname, String email, Address address, int numSiret) {
		super(firstname, lastname, email, address);
		this.numSiret = numSiret;
	}

	public Entreprise(String firstname, String lastname, String email, Address address, Set<PhoneNumber> profiles,
			int numSiret) {
		super(firstname, lastname, email, address, profiles);
		this.setType("Entreprise");
		this.numSiret = numSiret;
	}

	public Entreprise() {
		super();
	}

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}

}
