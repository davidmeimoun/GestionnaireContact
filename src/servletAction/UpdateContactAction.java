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
import domain.util.ApplicationContextUtils;
import service.ContactService;

public class UpdateContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {

		final UpdateContactValidationForm lForm = (UpdateContactValidationForm) pForm;
		final int id = lForm.getId();
		final String numSiret = lForm.getNumSiret();
		final String firstName = lForm.getFirstName();
		final String lastName = lForm.getLastName();
		final String email = lForm.getEmail();
		final String phoneNumber1 = lForm.getPhoneNumber1();
		final String phoneKind1 = lForm.getPhoneKind1();
		final String phoneNumber2 = lForm.getPhoneNumber2();
		final String phoneKind2 = lForm.getPhoneKind2();
		final String phoneNumber3 = lForm.getPhoneNumber3();
		final String phoneKind3 = lForm.getPhoneKind3();
		final String street = lForm.getStreet();
		final String city = lForm.getCity();
		final String zip = lForm.getZip();
		final String country = lForm.getCountry();

		// create a new Contact
		ContactService cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");
		Contact contactTmp = cs.getContact(id);
		Address address = new Address(street, city, zip, country);
		PhoneNumber pn1 = new PhoneNumber(phoneKind1, phoneNumber1, null);
		PhoneNumber pn2;
		PhoneNumber pn3;
		Set<PhoneNumber> sPn = new HashSet<PhoneNumber>();
		sPn.add(pn1);
		if (phoneKind2 != null && phoneNumber2 != null) {
			pn2 = new PhoneNumber(phoneKind2, phoneNumber2, null);
			sPn.add(pn2);
		}

		if (phoneKind3 != null && phoneNumber3 != null) {
			pn3 = new PhoneNumber(phoneKind3, phoneNumber3, null);
			sPn.add(pn3);
		}

		Contact c = cs.updateContact(contactTmp, firstName, lastName, email, address, sPn, Integer.parseInt(numSiret));
		List<Contact> lc = cs.listContact();

		pRequest.getServletContext().setAttribute("ListcontactResearch", lc);
		if (c == null) {
			return pMapping.findForward("error");
		} else {
			return pMapping.findForward("success");
		}
	}

}