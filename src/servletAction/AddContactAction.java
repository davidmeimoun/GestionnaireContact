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
import domain.util.ApplicationContextUtils;
import service.ContactService;

public class AddContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final AddContactValidationForm lForm = (AddContactValidationForm) pForm;
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
		ContactService cs = new ContactService();
		cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");

		boolean result = cs.add(firstName, lastName, email, street, city, zip, country, phoneNumber1, phoneKind1,phoneNumber2, phoneKind2,phoneNumber3, phoneKind3, Integer.parseInt(numSiret));

		List<Contact> lc = cs.listContact();
		pRequest.getServletContext().setAttribute("ListcontactResearch", lc);
		//pRequest.setAttribute("contact", c);
		if (result) {
			return pMapping.findForward("success");
		} else {
			return pMapping.findForward("error");
		}

	}
}