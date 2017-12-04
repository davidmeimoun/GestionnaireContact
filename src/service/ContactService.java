package service;

import java.util.List;
import java.util.Set;

import dao.IDAOContact;
import dao.IDAOContactGroup;
import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.PhoneNumber;

public class ContactService {

	IDAOContact daoContact;
	IDAOContactGroup daoContactGroup;

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

	public ContactService() {
		super();
	}

	public void generateContact() {
		daoContact.generateContact();
	}

	public Contact addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles,
			int numSiret) {
		return daoContact.addContact(fname, lname, email, address, profiles, numSiret);
	}

	public boolean versionIsChanged(long idContact, String version) {
		return daoContact.versionIsChanged(idContact, version);
	}

	public boolean deleteContact(long id) {

		return daoContact.deleteContact(id);

	}

	public List<Contact> listContact() {

		return daoContact.listContact();
	}

	public int getNumSiretEntreprise(long id) {

		return daoContact.getNumSiretEntreprise(id);
	}

	public Contact getContact(long id) {

		return daoContact.getContact(id);
	}

	public Contact updateContact(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int numSiret) {

		return daoContact.updateContact(c, fname, lname, email, address, profiles, numSiret);
	}

	public boolean createContactGroup(String name) {

		return daoContactGroup.createContactGroup(name);
	}

	public List<ContactGroup> getListContactGroupForOneContact(long id) {

		return daoContactGroup.getListContactGroupForOneContact(id);
	}

	public boolean addContactToGroup(Long idGroupContact, long idContact) {

		return daoContactGroup.addContactToGroup(idGroupContact, idContact);
	}

	public List<ContactGroup> listGroupContact() {

		return daoContactGroup.listGroupContact();
	}

	public ContactGroup getContactGroup(long id) {

		return daoContactGroup.getContactGroup(id);
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

		return daoContactGroup.deleteGroupContact(idGroup);
	}

}
