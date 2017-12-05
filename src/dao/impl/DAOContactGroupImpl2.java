package dao.impl;

import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOContactGroup2;
import domain.Contact;
import domain.ContactGroup;

public class DAOContactGroupImpl2 extends HibernateDaoSupport implements IDAOContactGroup2 {

	@Override
	public boolean add(ContactGroup object) {
		try {
			long id = (long) getHibernateTemplate().save(object);
			if (id >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ContactGroup get(long id) {
		try {
			return getHibernateTemplate().get(ContactGroup.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(long id) {
		try {
			ContactGroup c = getHibernateTemplate().get(ContactGroup.class, id);
			getHibernateTemplate().delete(c);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(ContactGroup object) {
		try {
			getHibernateTemplate().update(object);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactGroup> getList() {
		try {
			return (List<ContactGroup>) getHibernateTemplate().find(" from ContactGroup");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long addId(ContactGroup object) {
		try {
			return (long) getHibernateTemplate().save(object);
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public boolean addContactToGroup(Long idGroupContact, Long idContact) {
		try {
			ContactGroup cg = getHibernateTemplate().get(ContactGroup.class, idGroupContact);
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
	@Override
	public List<Contact> listContactOutsideGroup(Long idGroupContact) {
		DetachedCriteria filter = DetachedCriteria.forClass(Contact.class).createCriteria("books");
		filter.add(Restrictions.like("id_contactGroup", idGroupContact));
		List<Contact> listContactInGroup = (List<Contact>) getHibernateTemplate().findByCriteria(filter);
		List<Contact> listAllContact = (List<Contact>) getHibernateTemplate()
				.findByCriteria(DetachedCriteria.forClass(Contact.class));
		for (Contact contact : listContactInGroup) {
			listAllContact.remove(contact);
		}
		return listAllContact;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> listContactInGroup(Long idGroupContact) {
		try {
			DetachedCriteria filter = DetachedCriteria.forClass(Contact.class).createCriteria("books");
			filter.add(Restrictions.like("id_contactGroup", idGroupContact));
			return (List<Contact>) getHibernateTemplate().findByCriteria(filter);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteContactFromAllGroup(long idContact) {
		try {
			Contact contact = getHibernateTemplate().get(Contact.class, idContact);
			List<ContactGroup> lcontactGroup = getList();
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

	@Override
	public boolean deleteContactFromGroup(long idGroup, long idContact) {
		try {

			Contact contact = getHibernateTemplate().get(Contact.class, idContact);
			ContactGroup contactGroup = get(idGroup);
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

}
