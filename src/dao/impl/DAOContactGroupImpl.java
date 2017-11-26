package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOContactGroup;
import domain.Contact;
import domain.ContactGroup;
import domain.util.HibernateUtil;

public class DAOContactGroupImpl extends HibernateDaoSupport implements IDAOContactGroup {

	public DAOContactGroupImpl() {
		super();
	}

	public boolean createContactGroup(String name) {

		ContactGroup contactGroup = new ContactGroup();
		contactGroup.setGroupName(name);

		try {
			getHibernateTemplate().save(contactGroup);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean addContactToGroup(Long idGroupContact, Long idContact) {

		try {
			ContactGroup cg = getContactGroup(idGroupContact);
			Contact c = getHibernateTemplate().get(Contact.class, idContact);
			cg.getContacts().add(c);
			c.getBooks().add(cg);
			getHibernateTemplate().saveOrUpdate(cg);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ContactGroup> listGroupContact() {
		try {
			return (List<ContactGroup>) getHibernateTemplate().find(" from ContactGroup");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ContactGroup getContactGroup(long id) {
		try {
			return getHibernateTemplate().get(ContactGroup.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contact> listContactInGroup(long idGroup) {
		try {
			DetachedCriteria filter = DetachedCriteria.forClass(Contact.class).createCriteria("books");
			filter.add(Restrictions.like("id_contactGroup", idGroup));
			return (List<Contact>) getHibernateTemplate().findByCriteria(filter);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contact> listContactOutsideGroup(long idGroup) {
		DetachedCriteria filter = DetachedCriteria.forClass(Contact.class).createCriteria("books");
		filter.add(Restrictions.like("id_contactGroup", idGroup));
		List<Contact> listContactInGroup = (List<Contact>) getHibernateTemplate().findByCriteria(filter);
		List<Contact> listAllContact = (List<Contact>) getHibernateTemplate()
				.findByCriteria(DetachedCriteria.forClass(Contact.class));
		for (Contact contact : listContactInGroup) {
			listAllContact.remove(contact);
		}
		return listAllContact;
	}

	public boolean deleteContactFromGroup(long idGroup, long idContact) {
		try {

			Contact contact = getHibernateTemplate().get(Contact.class, idContact);
			ContactGroup contactGroup = getContactGroup(idGroup);
			boolean result = contact.getBooks().remove(contactGroup);
			boolean result2 = contactGroup.getContacts().remove(contact);
			getHibernateTemplate().update(contact);
			getHibernateTemplate().update(contactGroup);

			return result & result2;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteContactFromAllGroup(long idContact) {
		System.out.println("D�but de deleteContactFromAllGroup() avec idContact = " + idContact);
		try {
			Contact contact = getHibernateTemplate().get(Contact.class, idContact);
			List<ContactGroup> lcontactGroup = listGroupContact();
			for (ContactGroup contactGroup : lcontactGroup) {
				if (contactGroup.getContacts().contains(contact)) {
					contactGroup.getContacts().remove(contact);
					getHibernateTemplate().update(contactGroup);
				}
			}
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteGroupContact(long idGroup) {
		System.out.println("D�but de deleteGroupContact() avec idGroup = " + idGroup);
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ContactGroup contactGroup = getContactGroup(idGroup);
			DetachedCriteria filter = DetachedCriteria.forClass(Contact.class).createCriteria("books");
			filter.add(Restrictions.like("id_contactGroup", idGroup));
			List<Contact> listContactInGroup = (List<Contact>) getHibernateTemplate().findByCriteria(filter);
			for (Contact contact : listContactInGroup) {
				contact.getBooks().remove(contactGroup);
				session.update(contact);
			}
			contactGroup.setContacts(null);
			getHibernateTemplate().update(contactGroup);
			getHibernateTemplate().delete(contactGroup);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
