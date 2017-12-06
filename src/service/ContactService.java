package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.StaleObjectStateException;

import dao.IDAOContact;
import dao.IDAOContactGroup;
import dao.IDAOEntreprise;
import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.Entreprise;
import domain.PhoneNumber;

public class ContactService {

	IDAOContact daoContact;
	IDAOContactGroup daoContactGroup;
	IDAOEntreprise daoEntreprise;

	public IDAOContact getDaoContact() {
		return daoContact;
	}

	public void setDaoContact(IDAOContact daoContact) {
		this.daoContact = daoContact;
	}

	public IDAOContactGroup getDaoContactGroup() {
		return daoContactGroup;
	}

	public void setDaoContactGroup(IDAOContactGroup daoContactGroup) {
		this.daoContactGroup = daoContactGroup;
	}

	public IDAOEntreprise getDaoEntreprise() {
		return daoEntreprise;
	}

	public void setDaoEntreprise(IDAOEntreprise daoEntreprise) {
		this.daoEntreprise = daoEntreprise;
	}

	public ContactService() {
		super();
	}

	public void generateContact() {
		daoContact.generate();
		daoEntreprise.generate();
		daoContactGroup.generate();
	}

	public boolean add(String fname, String lname, String email, String street, String city, String zip, String country,
			String phoneNumber1, String phoneKind1, String phoneNumber2, String phoneKind2, String phoneNumber3,
			String phoneKind3, int numSiret) {
		Address add = new Address(street, city, zip, country);
		Set<PhoneNumber> sPn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber(phoneKind1, phoneNumber1, null);
		sPn.add(pn);
		if (phoneKind2 != null && !phoneKind2.isEmpty() && phoneNumber2 != null && !phoneNumber2.isEmpty()) {
			PhoneNumber pn2 = new PhoneNumber(phoneKind2, phoneNumber2, null);
			sPn.add(pn2);
		}
		if (phoneKind3 != null && !phoneKind3.isEmpty() && phoneNumber3 != null && !phoneNumber3.isEmpty()) {
			PhoneNumber pn3 = new PhoneNumber(phoneKind3, phoneNumber3, null);
			sPn.add(pn3);
		}
		if (numSiret == 0) {
			Contact c = new Contact(fname, lname, email, add, sPn);
			return daoContact.add(c);
		} else if (numSiret > 0) {
			Entreprise e = new Entreprise(fname, lname, email, add, sPn, numSiret);
			return daoEntreprise.add(e);
		} else {
			return false;
		}

	}

	public boolean deleteContact(long id) {

		return daoContact.delete(id);

	}

	public List<Contact> listContact() {

		return daoContact.getList();
	}

	public int getNumSiretEntreprise(long id) {

		return daoEntreprise.getNumSiretEntreprise(id);
	}

	public Contact getContact(long id) {

		return daoContact.get(id);
	}

	public boolean update(long id, String fname, String lname, String email, String street, String city, String zip,
			String country, String phoneNumber1, String phoneKind1, String phoneNumber2, String phoneKind2,
			String phoneNumber3, String phoneKind3, int numSiret) throws StaleObjectStateException {

		Address add = new Address(street, city, zip, country);
		PhoneNumber pn1 = new PhoneNumber(phoneKind1, phoneNumber1, null);
		PhoneNumber pn2;
		PhoneNumber pn3;

		if (numSiret == 0) {

			Contact c = daoContact.get(id);
			c.setFirstName(fname);
			c.setLastName(lname);
			c.setEmail(email);
			c.getAddress().setCity(city);
			c.getAddress().setCountry(country);
			c.getAddress().setStreet(street);
			c.getAddress().setZip(zip);
			Set<PhoneNumber> sPn = new HashSet<PhoneNumber>();
			sPn.add(pn1);
			if (phoneKind2 != null && phoneNumber2 != null) {
				pn2 = new PhoneNumber(phoneKind2, phoneNumber2, null);
				sPn.add(pn2);
			}

			if (phoneKind3 != null && phoneNumber3 != null) {
				pn3 = new PhoneNumber(phoneKind3, phoneNumber3, null);
				sPn.add(pn3);
			}
			// Contact c = new Contact(fname, lname, email, add, sPn);
			c.setProfiles(sPn);
			return daoContact.update(c);
		} else if (numSiret > 0) {
			Entreprise e = new Entreprise(fname, lname, email, add, null, numSiret);
			return daoEntreprise.update(e);
		} else {
			return false;
		}

	}

	public boolean createContactGroup(String name) {

		ContactGroup cg = new ContactGroup(name);
		return daoContactGroup.add(cg);
	}

	public List<ContactGroup> getListContactGroupForOneContact(long id) {

		return daoContactGroup.getListContactGroupForOneContact(id);
	}

	public boolean addContactToGroup(Long idGroupContact, long idContact) {

		return daoContactGroup.addContactToGroup(idGroupContact, idContact);
	}

	public List<ContactGroup> listGroupContact() {

		return daoContactGroup.getList();
	}

	public ContactGroup getContactGroup(long id) {

		return daoContactGroup.get(id);
	}

	public List<Contact> listContactInGroup(long idGroup) {

		return daoContactGroup.listContactInGroup(idGroup);
	}

	public List<Contact> listContactOutsideGroup(long idGroup) {

		return daoContactGroup.listContactOutsideGroup(idGroup);
	}

	public boolean deleteContactFromGroup(long idGroup, long idContact) {

		return daoContactGroup.deleteContactFromGroup(idGroup, idContact);

	}

	public boolean deleteGroupContact(long idGroup) {
		List<Contact> listContact = listContactInGroup(idGroup);
		for (Contact contact : listContact) {
			deleteContactFromGroup(idGroup, contact.getId_contact());
		}
		return daoContactGroup.delete(idGroup);
	}

	public boolean versionIsChanged(long idContact, String versionActuelle) {
		return daoContact.versionIsChanged(idContact, versionActuelle);
	}
}
