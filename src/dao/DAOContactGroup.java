package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.Contact;
import domain.ContactGroup;
import domain.util.HibernateUtil;

public class DAOContactGroup extends HibernateDaoSupport{
	public DAOContactGroup() {
		super();
	}
	public boolean createContactGroup(String name) {

		ContactGroup contactGroup = new ContactGroup();
		contactGroup.setGroupName(name);

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			long id = (long) session.save(contactGroup);
			session.getTransaction().commit();
			System.out.println("Fin enregistrement GroupContact, ID GroupContact = " + id);
			session.close();
			return true;
		} catch (HibernateException e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean addContactToGroup(Long idGroupContact,Long idContact) {
		System.out.println("Début de deleteContactFromGroup() avec idGroup = " + idGroupContact + " et idContact = " +idContact);


		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Contact c = (Contact) session.get(Contact.class, idContact);
			ContactGroup cg = (ContactGroup) session.get(ContactGroup.class, idGroupContact);
			cg.getContacts().add(c);
			c.getBooks().add(cg);
			session.saveOrUpdate(cg);
			session.getTransaction().commit();
			System.out.println("Fin ajout contact : " + c.getFirstName() + " au  GroupContact "
					+ cg.getGroupName() + ", ID GroupContact = " + cg.getId_contactGroup());
			session.close();
			return true;
		} catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
	}

	public List<ContactGroup> listGroupContact() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ContactGroup> lcontactGroup = (List<ContactGroup>) session.createQuery("from ContactGroup").list();
			System.out.println(lcontactGroup.size());
			session.close();
			return lcontactGroup;
		} catch (HibernateException e) {
			System.out.println(e);
			return null;
		}

	}

	public ContactGroup getContactGroup(long id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ContactGroup cg = (ContactGroup) session.get(ContactGroup.class, id);
			session.close();
			return cg;
		} catch (HibernateException e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Contact> listContactInGroup(long idGroup) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Contact> lcontact = session.createCriteria(Contact.class).createCriteria("books")
					.add(Restrictions.like("id_contactGroup", idGroup)).list();
			session.close();
			return lcontact;
		} catch (HibernateException e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Contact> listContactOutsideGroup(long idGroup) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Contact> listContactInGroup = session.createCriteria(Contact.class).createCriteria("books")
				.add(Restrictions.like("id_contactGroup", idGroup)).list();
		List<Contact> listAllContact = session.createCriteria(Contact.class).list();
		for (Contact contact : listContactInGroup) {
			listAllContact.remove(contact);
		}

		session.close();
		return listAllContact;
	}

	public boolean deleteContactFromGroup(long idGroup, long idContact) {
		System.out.println(
				"Début de deleteContactFromGroup() avec idGroup = " + idGroup + " et idContact = " + idContact);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Contact contact = (Contact) session.get(Contact.class, idContact);
			System.out.println(contact.toString());
			ContactGroup contactGroup = (ContactGroup) session.get(ContactGroup.class, idGroup);
			System.out.println(contactGroup.toString());
			boolean result = contact.getBooks().remove(contactGroup);
			System.out.println("Group COntact supprimer " + result);
			boolean result2 = contactGroup.getContacts().remove(contact);
			System.out.println("Group COntact2 supprimer " + result2);
			session.update(contact);
			session.update(contactGroup);
			session.getTransaction().commit();
			System.out.println("Fin suppression contact : " + contact.getFirstName() + " au  GroupContact "
					+ contactGroup.getGroupName() + ", ID GroupContact = " + contactGroup.getId_contactGroup());
			session.close();
			return true;
		} catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public boolean deleteContactFromAllGroup(long idContact) {
		System.out.println(
				"Début de deleteContactFromAllGroup() avec idContact = " + idContact);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Contact contact = (Contact) session.get(Contact.class, idContact);
			System.out.println(contact.toString());
			List<ContactGroup> lcontactGroup = new DAOContactGroup().listGroupContact();
			for (ContactGroup contactGroup : lcontactGroup) {
				if(contactGroup.getContacts().contains(contact)){
					contactGroup.getContacts().remove(contact);
					System.out.println("Contact supprimé de "+contactGroup.getGroupName());
					session.update(contactGroup);
				}
			}
			session.getTransaction().commit();
			System.out.println("Fin suppression du contact : " + contact.getFirstName() + "dans tous les groupes contact");
			session.close();
			return true;
		} catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean deleteGroupContact(long idGroup) {
		System.out.println("Début de deleteGroupContact() avec idGroup = " + idGroup);
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ContactGroup contactGroup = (ContactGroup) session.get(ContactGroup.class, idGroup);

			List<Contact> listContactInGroup = session.createCriteria(Contact.class).createCriteria("books")
					.add(Restrictions.like("id_contactGroup", idGroup)).list();
			for (Contact contact : listContactInGroup) {
				contact.getBooks().remove(contactGroup);
				session.update(contact);
			}
			contactGroup.setContacts(null);
			session.update(contactGroup);
			System.out.println(contactGroup.toString());
			session.delete(contactGroup);
			session.getTransaction().commit();
			System.out.println("Fin de la suppression, ID ContactGroup = " + idGroup);
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
