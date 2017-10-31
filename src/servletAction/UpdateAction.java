package servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.UpdateContactValidationForm;
import domain.Contact;
import domain.PhoneNumber;
import service.ContactService;

public class UpdateAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		UpdateContactValidationForm lForm = new UpdateContactValidationForm();
		final int idt = Integer.parseInt(pRequest.getParameter("id"));
		ContactService cs = new ContactService();
		int numSiret = cs.getNumSiretEntreprise(idt);
		Contact c = cs.getContact(idt);
		System.out.println("dans UpdateAction.class, le num Siret est "+numSiret);
		lForm.setId(idt);
		lForm.setNumSiret(String.valueOf(numSiret));
		lForm.setFirstName(c.getFirstName());
		lForm.setLastName(c.getLastName());
		lForm.setEmail(c.getEmail());
		if (!c.getProfiles().isEmpty()) {
			PhoneNumber[] tabPn = c.getProfiles().toArray(new PhoneNumber[c.getProfiles().size()]);
			lForm.setPhoneNumber(tabPn[0].getPhoneNumber());
		}
		if (!c.getProfiles().isEmpty()) {
			PhoneNumber[] tabPn = c.getProfiles().toArray(new PhoneNumber[c.getProfiles().size()]);
			lForm.setPhoneKind(tabPn[0].getPhoneKind());
		}
		lForm.setStreet(c.getAddress().getStreet());
		lForm.setCity(c.getAddress().getCity());
		lForm.setZip(c.getAddress().getZip());
		lForm.setCountry(c.getAddress().getCountry());

		pRequest.getServletContext().setAttribute("updateC", lForm);
		if (idt > 0) {
			// if no exception is raised,
			return pMapping.findForward("success");
		} else {
			// If any exception,
			return pMapping.findForward("error");
		}
	}
}
