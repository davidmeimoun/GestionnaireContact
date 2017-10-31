package servletAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.AddGroupContactValidationForm;
import domain.ContactGroup;
import service.ContactService;

public class AddGroupContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		boolean result = false;
		final AddGroupContactValidationForm lForm = (AddGroupContactValidationForm) pForm;
		final String groupName = lForm.getGroupName();
		ContactService cs = new ContactService();
		ContactGroup contactGroup = new ContactGroup();
		contactGroup.setGroupName(groupName);
		result = cs.createContactGroup(groupName);

		List<ContactGroup> lc = cs.listGroupContact();
		pRequest.getServletContext().setAttribute("ListGroupContact", lc);
		if (result) {
			return pMapping.findForward("success");
		} else {
			return pMapping.findForward("error");
		}
	}
}