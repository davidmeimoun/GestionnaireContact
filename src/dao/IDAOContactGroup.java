package dao;

import java.util.List;

import domain.Contact;
import domain.ContactGroup;

public interface IDAOContactGroup {

	public boolean createContactGroup(String name);

	public boolean addContactToGroup(Long idGroupContact, Long idContact);

	public List<ContactGroup> listGroupContact();

	public ContactGroup getContactGroup(long id);

	public List<ContactGroup> getListContactGroupForOneContact(long id);

	public List<Contact> listContactInGroup(long idGroup);

	public List<Contact> listContactOutsideGroup(long idGroup);

	public boolean deleteContactFromGroup(long idGroup, long idContact);

	public boolean deleteContactFromAllGroup(long idContact);

	public boolean deleteGroupContact(long idGroup);

	public void generateGroupContact();

}
