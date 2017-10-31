package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import domain.Contact;
import service.ContactService;

public class DeleteContactInGroup extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final long idGroup = Long.parseLong(pRequest.getParameter("idGroup"));
		final long idContact = Long.parseLong(pRequest.getParameter("idContact"));
		ContactService cs = new ContactService();
		boolean result = cs.deleteContactFromGroup(idGroup, idContact);
		List<Contact> listContactInGroup = cs.listContactInGroup(idGroup);
		List<Contact> listContactOutsideGroup = cs.listContactOutsideGroup(idGroup);
		pRequest.getServletContext().setAttribute("ListContactInGroup", listContactInGroup);
		pRequest.getServletContext().setAttribute("listContactOutsideGroup", listContactOutsideGroup);
		if (result) {
			// if no exception is raised,
			return pMapping.findForward("success");
		} else {
			// If any exception,
			return pMapping.findForward("error");
		}
	}
}