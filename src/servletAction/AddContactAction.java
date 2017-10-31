package servletAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.AddContactValidationForm;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import service.ContactService;

public class AddContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final AddContactValidationForm lForm = (AddContactValidationForm) pForm;
		final String numSiret = lForm.getNumSiret();
		final String firstName = lForm.getFirstName();
		final String lastName = lForm.getLastName();
		final String email = lForm.getEmail();
		final String phoneNumber = lForm.getPhoneNumber();
		final String phoneKind = lForm.getPhoneKind();
		final String street = lForm.getStreet();
		final String city = lForm.getCity();
		final String zip = lForm.getZip();
		final String country = lForm.getCountry();

		// create a new Contact
		ContactService cs = new ContactService();
		Address add = new Address(street, city, zip, country);
		Set<PhoneNumber> sPn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber(phoneKind, phoneNumber, null);
		sPn.add(pn);
		Contact c = cs.addContact(firstName, lastName, email, add, sPn, Integer.parseInt(numSiret));

		List<Contact> lc = cs.listContact();
		pRequest.getServletContext().setAttribute("ListcontactResearch", lc);
		pRequest.setAttribute("contact", c);
		if (c != null) {
			return pMapping.findForward("success");
		} else {
			return pMapping.findForward("error");
		}

	}
}