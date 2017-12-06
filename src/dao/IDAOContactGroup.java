package dao;

import java.util.List;

import domain.Contact;
import domain.ContactGroup;

public interface IDAOContactGroup extends IDAO<ContactGroup> {
	long addId(ContactGroup c);

	boolean addContactToGroup(Long idGroupContact, Long idContact);

	List<Contact> listContactOutsideGroup(Long idGroupContact);

	List<Contact> listContactInGroup(Long idGroupContact);

	boolean deleteContactFromAllGroup(long idContact);

	boolean deleteContactFromGroup(long idGroup, long idContact);

	List<ContactGroup> getListContactGroupForOneContact(long id);
}
