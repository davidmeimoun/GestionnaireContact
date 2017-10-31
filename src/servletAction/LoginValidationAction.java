package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.LoginForm;
import domain.Contact;
import domain.ContactGroup;
import service.ContactService;

public class LoginValidationAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final LoginForm lForm = (LoginForm) form;
		final String userName = lForm.getUserName();
		final String password = lForm.getPassword();
		ContactService cs = new ContactService();
		List<Contact> c = cs.listContact();
		List<ContactGroup> contactGroup = cs.listGroupContact();
		request.getServletContext().setAttribute("ListcontactResearch", c);
		request.getServletContext().setAttribute("ListGroupContact", contactGroup);

		if (userName.equals(password) && c != null) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("error");
		}

	}
}
