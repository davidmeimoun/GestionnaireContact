package domain;

import java.util.Set;

public class ContactGroup {

	private long id_contactGroup;
	private String groupName;
	private Set<Contact> contacts;

	public ContactGroup(long id_contactGroup, String groupName, Set<Contact> contact) {
		super();
		this.id_contactGroup = id_contactGroup;
		this.groupName = groupName;
		this.contacts = contact;
	}

	public ContactGroup(String groupName, Set<Contact> contact) {
		super();
		this.groupName = groupName;
		this.contacts = contact;
	}

	public ContactGroup() {
		super();
	}

	public ContactGroup(String groupName) {
		this.groupName = groupName;
	}

	public long getId_contactGroup() {
		return id_contactGroup;
	}

	public void setId_contactGroup(long id_contactGroup) {
		this.id_contactGroup = id_contactGroup;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "ContactGroup [id_contactGroup=" + id_contactGroup + ", groupName=" + groupName + ", contacts="
				+ contacts + "]";
	}

}
