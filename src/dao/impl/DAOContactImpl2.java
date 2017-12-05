package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOContact2;
import domain.Contact;

public class DAOContactImpl2 extends HibernateDaoSupport implements IDAOContact2 {

	@Override
	public boolean add(Contact object) {

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
	public long addId(Contact object) {

		try {
			return (long) getHibernateTemplate().save(object);
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0L;
		}

	}

	@Override
	public Contact get(long id) {
		try {
			return getHibernateTemplate().get(Contact.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean delete(long id) {
		try {
			Contact c = getHibernateTemplate().get(Contact.class, id);
			getHibernateTemplate().delete(c);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Contact object) {
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
	public List<Contact> getList() {
		try {
			return (List<Contact>) getHibernateTemplate().find(" from Contact");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
