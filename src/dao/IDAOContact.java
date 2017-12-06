package dao;

import domain.Contact;

public interface IDAOContact extends IDAO<Contact> {

	long addId(Contact object);

	boolean versionIsChanged(long idContact, String versionActuelle);


}
