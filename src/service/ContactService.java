package service;

import java.util.List;
import java.util.Set;

import dao.DAOContact;
import dao.DAOContactGroup;
import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.PhoneNumber;

public class ContactService {

	public ContactService() {
		super();
	}

	public Contact addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles,
			int numSiret) {
		DAOContact daoContact = new DAOContact();
		return daoContact.addContact(fname, lname, email, address, profiles, numSiret);
	}

	public boolean deleteContact(long id) {
		DAOContact daoContact = new DAOContact();
		return daoContact.deleteContact(id);

	}

	public List<Contact> listContact() {
		DAOContact daoContact = new DAOContact();
		return daoContact.listContact();
	}

	public int getNumSiretEntreprise(long id) {
		DAOContact daoContact = new DAOContact();
		return daoContact.getNumSiretEntreprise(id);
	}

	public Contact getContact(long id) {
		DAOContact daoContact = new DAOContact();
		return daoContact.getContact(id);
	}

	public Contact updateContact(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int numSiret) {
		DAOContact daoContact = new DAOContact();
		return daoContact.updateContact(c, fname, lname, email, address, profiles, numSiret);
	}

	public boolean createContactGroup(String name) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.createContactGroup(name);
	}

	public boolean addContactToGroup(Long idGroupContact, Long idContact) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.addContactToGroup(idGroupContact, idContact);
	}

	public List<ContactGroup> listGroupContact() {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.listGroupContact();
	}

	public ContactGroup getContactGroup(long id) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.getContactGroup(id);
	}

	public List<Contact> listContactInGroup(long idGroup) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.listContactInGroup(idGroup);
	}

	public List<Contact> listContactOutsideGroup(long idGroup) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.listContactOutsideGroup(idGroup);
	}

	public boolean deleteContactFromGroup(long idGroup, long idContact) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.deleteContactFromGroup(idGroup, idContact);

	}

	public boolean deleteGroupContact(long idGroup) {
		DAOContactGroup daocg = new DAOContactGroup();
		return daocg.deleteGroupContact(idGroup);
	}

}
