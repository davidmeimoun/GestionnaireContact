package dao.impl;

import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOContactGroup;
import domain.Contact;
import domain.ContactGroup;

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
			if (cg.getContacts() == null)
				cg.setContacts(new HashSet<Contact>());
			cg.getContacts().add(c);
			if (c.getBooks() == null)
				c.setBooks(new HashSet<ContactGroup>());
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

	@SuppressWarnings("unchecked")
	public boolean deleteGroupContact(long idGroup) {
		System.out.println("Début de deleteGroupContact() avec idGroup = " + idGroup);
		try {

			ContactGroup contactGroup = getContactGroup(idGroup);
			DetachedCriteria filter = DetachedCriteria.forClass(Contact.class).createCriteria("books");
			filter.add(Restrictions.like("id_contactGroup", idGroup));
			List<Contact> listContactInGroup = (List<Contact>) getHibernateTemplate().findByCriteria(filter);
			for (Contact contact : listContactInGroup) {
				contact.getBooks().remove(contactGroup);
				getHibernateTemplate().update(contact);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactGroup> getListContactGroupForOneContact(long id) {

		try {
			DetachedCriteria filter = DetachedCriteria.forClass(ContactGroup.class).createCriteria("contacts");
			filter.add(Restrictions.like("id_contact", id));
			return (List<ContactGroup>) getHibernateTemplate().findByCriteria(filter);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void generateGroupContact() {
		createContactGroup("Parent");
		addContactToGroup(1L, 1L);
		addContactToGroup(1L, 2L);

	}

}
