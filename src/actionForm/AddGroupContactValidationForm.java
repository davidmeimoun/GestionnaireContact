package actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class AddGroupContactValidationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_contactGroup;
	private String groupName;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.id_contactGroup = 0;
		this.groupName = null;
	}

	public int getId_contactGroup() {
		return id_contactGroup;
	}

	public void setId_contactGroup(int id_contactGroup) {
		this.id_contactGroup = id_contactGroup;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean ifOnlyString(String chaine) {

		if (chaine.matches("^[a-zA-Z]*$")) {
			return false;
		} else {
			return true;
		}

	}

	public boolean isInt(String chaine) {
		boolean valeur = true;
		char[] tab = chaine.toCharArray();

		for (char carac : tab) {
			if (!Character.isDigit(carac) && valeur) {
				valeur = false;
			}
		}

		return valeur;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (getGroupName() == null || getGroupName().length() < 1) {
			errors.add("group name", new ActionMessage("creation.groupName.error.required"));
		}
		if (getGroupName() != null || getGroupName().length() >= 1) {
			if (ifOnlyString(getGroupName())) {
				errors.add("group name chiffre", new ActionMessage("creation.groupName.chiffre.error.required"));
			}

		}

		return errors;
	}
}
