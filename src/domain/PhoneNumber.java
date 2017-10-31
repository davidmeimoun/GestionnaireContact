package domain;

import java.util.HashSet;
import java.util.Set;

public class PhoneNumber {

	public static final String HOME_CATEGORY = "home", MOBILE_CATEGORY = "mobile", WORK_CATEGORY = "work";
	private long id_phoneNumber;
	private String phoneKind;
	private String phoneNumber;
	private Contact contact;

	public PhoneNumber(long id_phoneNumber, String phoneKind, String phoneNumber, Contact contact) {
		super();
		this.id_phoneNumber = id_phoneNumber;
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public PhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		super();
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public PhoneNumber() {
		super();
	}

	public long getId_phoneNumber() {
		return id_phoneNumber;
	}

	public void setId_phoneNumber(long id_phoneNumber) {
		this.id_phoneNumber = id_phoneNumber;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public static Set<PhoneNumber> newSet(String homeNum, String mobileNum, String officeNum) {
		if (homeNum.isEmpty() && officeNum.isEmpty() && mobileNum.isEmpty()) {
			return null;
		} else {
			Set<PhoneNumber> profiles = new HashSet<PhoneNumber>();
			if (homeNum != null && !homeNum.isEmpty())
				profiles.add(newHome(homeNum));

			if (officeNum != null && !officeNum.isEmpty())
				profiles.add(newWork(officeNum));

			if (mobileNum != null && !mobileNum.isEmpty())
				profiles.add(newMobile(mobileNum));

			return profiles;
		}
	}

	public static PhoneNumber newHome(String num) {
		PhoneNumber tmp = new PhoneNumber();
		tmp.setPhoneNumber(num);
		tmp.setPhoneKind(HOME_CATEGORY);
		return tmp;
	}

	public static PhoneNumber newWork(String num) {
		PhoneNumber tmp = new PhoneNumber();
		tmp.setPhoneNumber(num);
		tmp.setPhoneKind(WORK_CATEGORY);
		return tmp;
	}

	public static PhoneNumber newMobile(String num) {
		PhoneNumber tmp = new PhoneNumber();
		tmp.setPhoneNumber(num);
		tmp.setPhoneKind(MOBILE_CATEGORY);
		return tmp;
	}

}
