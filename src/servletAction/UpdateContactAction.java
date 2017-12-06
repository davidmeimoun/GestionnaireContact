package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import actionForm.UpdateContactValidationForm;
import domain.Contact;
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
		final String version = lForm.getVersion();

		// create a new Contact
		ContactService cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");

		try {
			if (!cs.versionIsChanged(id, version)) {

				boolean result = cs.update(id, firstName, lastName, email, street, city, zip, country, phoneNumber1,
						phoneKind1, phoneNumber2, phoneKind2, phoneNumber3, phoneKind3, Integer.parseInt(numSiret));
				List<Contact> lc = cs.listContact();
				pRequest.getServletContext().setAttribute("versionChanged", false);
				pRequest.getServletContext().setAttribute("ListcontactResearch", lc);
				if (result) {
					return pMapping.findForward("success");
				} else {
					return pMapping.findForward("error");
				}
			} else {
				pRequest.getServletContext().setAttribute("versionChanged", true);
				ActionRedirect redirect = new ActionRedirect(pMapping.findForward("same"));
				redirect.addParameter("id", id);
				return redirect;
			}
		} catch (HibernateOptimisticLockingFailureException e) {
			e.printStackTrace();
			return pMapping.findForward("error");

		} catch (Exception e) {
			e.printStackTrace();
			return pMapping.findForward("error");
		}
	}
}
