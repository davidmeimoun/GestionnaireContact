package domain;

public class Address {
	private long id_address;
	private String street;
	private String city;
	private String zip;
	private String country;

	public Address(long id_address, String street, String city, String zip, String country) {
		super();
		this.id_address = id_address;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}

	public Address(String street, String city, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}

	public Address() {
		super();
	}

	public long getId_address() {
		return id_address;
	}

	public void setId_address(long id_address) {
		this.id_address = id_address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
