package servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import domain.Contact;
import service.ContactService;

public class ViewAContactAction extends Action{
	
	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		final int idt = Integer.parseInt(pRequest.getParameter("id"));
		ContactService cs = new ContactService();
		Contact c = cs.getContact(idt);
		
		 
		 pRequest.setAttribute("contact", c);
		if (idt > 0) {
			// if no exception is raised,
			return pMapping.findForward("success");
		} else {
			// If any exception,
			return pMapping.findForward("error");
		}
	}

}
