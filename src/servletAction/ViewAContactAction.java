package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.UpdateContactValidationForm;
import domain.Contact;
import domain.ContactGroup;
import domain.PhoneNumber;
import domain.util.ApplicationContextUtils;
import service.ContactService;

public class ViewAContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		UpdateContactValidationForm lForm = new UpdateContactValidationForm();
		final int idt = Integer.parseInt(pRequest.getParameter("id"));
		ContactService cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");
		int numSiret = cs.getNumSiretEntreprise(idt);
		Contact c = cs.getContact(idt);
		lForm.setId(idt);
		lForm.setNumSiret(String.valueOf(numSiret));
		lForm.setFirstName(c.getFirstName());
		lForm.setLastName(c.getLastName());
		lForm.setEmail(c.getEmail());
		if (!c.getProfiles().isEmpty()) {
			PhoneNumber[] tabPn = c.getProfiles().toArray(new PhoneNumber[c.getProfiles().size()]);
			for (int i = 0; i < tabPn.length; i++) {
				if (i == 0)
					lForm.setPhoneNumber1(tabPn[i].getPhoneNumber());
				if (i == 1)
					lForm.setPhoneNumber2(tabPn[i].getPhoneNumber());
				if (i == 2)
					lForm.setPhoneNumber3(tabPn[i].getPhoneNumber());
			}

		}
		if (!c.getProfiles().isEmpty()) {
			PhoneNumber[] tabPn = c.getProfiles().toArray(new PhoneNumber[c.getProfiles().size()]);
			for (int i = 0; i < tabPn.length; i++) {
				if (i == 0)
					lForm.setPhoneKind1(tabPn[i].getPhoneKind());
				if (i == 1)
					lForm.setPhoneKind2(tabPn[i].getPhoneKind());
				if (i == 2)
					lForm.setPhoneKind3(tabPn[i].getPhoneKind());
			}
		}
		lForm.setStreet(c.getAddress().getStreet());
		lForm.setCity(c.getAddress().getCity());
		lForm.setZip(c.getAddress().getZip());
		lForm.setCountry(c.getAddress().getCountry());

		List<ContactGroup> listContactGroup = cs.getListContactGroupForOneContact(idt);
		pRequest.getServletContext().setAttribute("updateC", lForm);
		pRequest.getServletContext().setAttribute("listGroupContactForTheContact", listContactGroup );
		if (idt > 0) {
			// if no exception is raised,
			return pMapping.findForward("success");
		} else {
			// If any exception,
			return pMapping.findForward("error");
		}
	}

}
