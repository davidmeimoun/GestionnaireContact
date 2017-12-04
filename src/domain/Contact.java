package domain;

import java.util.Set;

import javax.persistence.Version;

public class Contact {

	@Version
	private int version;
	
	private long id_contact;

	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private Set<PhoneNumber> profiles;
	private Set<ContactGroup> books;
	private String type;

	public Contact(long id_contact, String firstName, String lastName, String email, Address address,
			Set<PhoneNumber> profiles, Set<ContactGroup> books) {
		super();
		this.id_contact = id_contact;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.profiles = profiles;
		this.books = books;
	}

	public Contact(String firstName, String lastName, String email, Address address, Set<PhoneNumber> profiles,
			Set<ContactGroup> books) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.profiles = profiles;
		this.books = books;
	}

	public Contact(String firstName, String lastName, String email, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	public Contact(String firstName, String lastName, String email, Address address, Set<PhoneNumber> profiles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.profiles = profiles;
	}

	public Contact() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId_contact() {
		return id_contact;
	}

	public void setId_contact(long id_contact) {
		this.id_contact = id_contact;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Contact [id_contact=" + id_contact + ", version=" + version + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + "]";
	}

}
