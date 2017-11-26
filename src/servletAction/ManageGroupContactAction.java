package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import domain.Contact;
import domain.util.ApplicationContextUtils;
import service.ContactService;

public class ManageGroupContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final long idGroup = Integer.parseInt(pRequest.getParameter("idGroup"));

		// create a new Contact
		ContactService cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");
		List<Contact> listContactInGroup = cs.listContactInGroup(idGroup);
		List<Contact> listContactOutsideGroup = cs.listContactOutsideGroup(idGroup);
		pRequest.getServletContext().setAttribute("ListContactInGroup", listContactInGroup);
		pRequest.getServletContext().setAttribute("listContactOutsideGroup", listContactOutsideGroup);
		return pMapping.findForward("success");
	}
}