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
	private String numSiret;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String phoneKind;
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
		this.phoneKind = null;
		this.phoneNumber = null;
		this.street = null;
		this.city = null;
		this.zip = null;
		this.country = null;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
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

		if (chaine.matches("^[a-zA-Z]*$")) {
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

		if (getPhoneKind() == null || getPhoneKind().length() < 1) {
			errors.add("phone kind", new ActionMessage("creation.phoneKind.error.required"));
		}
		if (getPhoneNumber() == null || getPhoneNumber().length() < 1) {
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
