package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.DAOContact;
import domain.Contact;
import domain.util.ApplicationContextUtils;
import service.ContactService;

public class DeleteContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final int id = Integer.parseInt(pRequest.getParameter("id"));
		ContactService cs = (ContactService) ApplicationContextUtils.getApplicationContext().getBean("ContactService");
		 boolean result = cs.deleteContact(id);
		List<Contact> lc =  cs.listContact();
		pRequest.getServletContext().setAttribute("ListcontactResearch", lc);
		if (result) {
			// if no exception is raised,
			return pMapping.findForward("success");
		} else {
			// If any exception,
			return pMapping.findForward("error");
		}
	}
}