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

import actionForm.UpdateContactValidationForm;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import service.ContactService;

public class UpdateContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		
		
		final UpdateContactValidationForm lForm =  (UpdateContactValidationForm) pForm;
		final int id = lForm.getId();
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
		Contact contactTmp = cs.getContact(id);
		Address address = new Address(street, city, zip, country);
		PhoneNumber pn = new PhoneNumber(phoneKind, phoneNumber, null);
		Set<PhoneNumber> sPn = new HashSet<PhoneNumber>();
		sPn.add(pn);
		Contact c = cs.updateContact(contactTmp, firstName, lastName, email, address, sPn, Integer.parseInt(numSiret));
		List<Contact> lc = cs.listContact();
		
		
		if(c == null) {
			pRequest.getServletContext().setAttribute("ListcontactResearch", lc);
			return pMapping.findForward("error");
		}
		else {
			return pMapping.findForward("success");
		}
	}
}