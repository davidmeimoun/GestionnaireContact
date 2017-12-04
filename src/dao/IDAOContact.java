package dao;

import java.util.List;
import java.util.Set;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

public interface IDAOContact {
	public Contact addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> phones,
			int numSiret);

	public boolean deleteContact(long id);

	public void generateContact();

	public List<Contact> listContact();

	public Contact getContact(long id);

	public int getNumSiretEntreprise(long id);

	public Contact updateContact(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int siretnum);
	
	public Contact updateContact2(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int siretnum);

	public boolean versionIsChanged(long idContact, String version);

}
