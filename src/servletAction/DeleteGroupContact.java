package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import domain.ContactGroup;
import domain.util.ApplicationContextUtils;
import service.ContactService;

public class DeleteGroupContact extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final long idGroup = Long.parseLong(pRequest.getParameter("idGroup"));
		ContactService cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");
		boolean result = cs.deleteGroupContact(idGroup);

		List<ContactGroup> lc = cs.listGroupContact();
		pRequest.getServletContext().setAttribute("ListGroupContact", lc);
		if (result) {
			// if no exception is raised,
			return pMapping.findForward("success");
		} else {
			// If any exception,
			return pMapping.findForward("error");
		}
	}
}