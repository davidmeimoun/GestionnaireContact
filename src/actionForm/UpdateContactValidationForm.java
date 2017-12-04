package actionForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UpdateContactValidationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String version;
	private String numSiret;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber1;
	private String phoneKind1;
	private String phoneNumber2;
	private String phoneKind2;
	private String phoneNumber3;
	private String phoneKind3;
	private String street;
	private String city;
	private String zip;
	private String country;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.id = 0;
		this.numSiret = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.phoneKind1 = null;
		this.phoneNumber1 = null;
		this.phoneKind2 = null;
		this.phoneNumber2 = null;
		this.phoneKind3 = null;
		this.phoneNumber3 = null;
		this.street = null;
		this.city = null;
		this.zip = null;
		this.country = null;
	}
	

	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneKind1() {
		return phoneKind1;
	}

	public void setPhoneKind1(String phoneKind1) {
		this.phoneKind1 = phoneKind1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPhoneKind2() {
		return phoneKind2;
	}

	public void setPhoneKind2(String phoneKind2) {
		this.phoneKind2 = phoneKind2;
	}

	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	public String getPhoneKind3() {
		return phoneKind3;
	}

	public void setPhoneKind3(String phoneKind3) {
		this.phoneKind3 = phoneKind3;
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

	public boolean ifOnlyString(String chaine) {

		if (chaine.matches("(^[-a-zA-Z*(é|è|à|ù|\\s)]+$)*$")) {
			return false;
		} else {
			return true;
		}

	}

	public boolean isInt(String chaine) {
		boolean valeur = true;
		char[] tab = chaine.toCharArray();

		for (char carac : tab) {
			if (!Character.isDigit(carac) && valeur) {
				valeur = false;
			}
		}

		return valeur;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (getNumSiret() != null || getNumSiret().length() >= 1) {
			if (!isInt((getNumSiret()))) {
				errors.add("num siret chiffre", new ActionMessage("creation.numSiret.chiffre.error.required"));
			}

		} else {
			errors.add("numSiret", new ActionMessage("creation.numSiret.error.required"));
		}
		if (getFirstName() == null || getFirstName().length() < 1) {
			errors.add("first name", new ActionMessage("creation.fn.error.required"));
		}
		if (getFirstName() != null || getFirstName().length() >= 1) {
			if (ifOnlyString(getFirstName())) {
				errors.add("first name chiffre", new ActionMessage("creation.fn.chiffre.error.required"));
			}

		}
		if (getLastName() == null || getLastName().length() < 1) {
			errors.add("last name", new ActionMessage("creation.ln.error.required"));
		}
		if (getLastName() != null || getLastName().length() >= 1) {
			if (ifOnlyString(getLastName())) {
				errors.add("last name chiffre", new ActionMessage("creation.ln.chiffre.error.required"));
			}

		}
		Pattern p = Pattern.compile(".*\\@.*\\..*");
		Matcher m = p.matcher(getEmail());

		if (getEmail() == null || getEmail().length() < 1) {

			errors.add("email", new ActionMessage("creation.email.error.required"));
		}
		if (getEmail() != null || getEmail().length() >= 1) {

			if (!m.find()) {
				errors.add("emailFormat", new ActionMessage("format.email.error.required"));
			}
		}

		if (getPhoneKind1() == null || getPhoneKind1().length() < 1) {
			errors.add("phone kind", new ActionMessage("creation.phoneKind.error.required"));
		}
		if (getPhoneNumber1() == null || getPhoneNumber1().length() < 1) {
			errors.add("phone number", new ActionMessage("creation.phoneNumber.error.required"));
		}
		if (getStreet() == null || getStreet().length() < 1) {
			errors.add("street", new ActionMessage("creation.street.error.required"));
		}

		if (getZip() == null || getZip().length() < 1) {
			errors.add("zip", new ActionMessage("creation.zip.error.required"));
		}
		if (getZip() != null || getZip().length() >= 1) {
			if (!isInt(getZip())) {
				errors.add("zip chiffre", new ActionMessage("creation.zip.chiffre.error.required"));
			}

		}
		if (getCity() == null || getCity().length() < 1) {
			errors.add("city", new ActionMessage("creation.city.error.required"));
		}
		if (getCity() != null || getCity().length() >= 1) {
			if (ifOnlyString(getCity())) {
				errors.add("city chiffre", new ActionMessage("creation.city.chiffre.error.required"));
			}

		}
		if (getCountry() == null || getCountry().length() < 1) {
			errors.add("country", new ActionMessage("creation.country.error.required"));
		}
		if (getCountry() != null || getCountry().length() >= 1) {
			if (ifOnlyString(getCountry())) {
				errors.add("country chiffre", new ActionMessage("creation.country.chiffre.error.required"));
			}

		}
		return errors;
	}
}
