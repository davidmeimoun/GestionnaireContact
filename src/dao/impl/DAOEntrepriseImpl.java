package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOEntreprise;
import domain.Entreprise;

public class DAOEntrepriseImpl extends HibernateDaoSupport implements IDAOEntreprise {

	@Override
	public boolean add(Entreprise object) {

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
	public Entreprise get(long id) {
		try {
			return getHibernateTemplate().get(Entreprise.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(long id) {
		try {
			Entreprise c = getHibernateTemplate().get(Entreprise.class, id);
			getHibernateTemplate().delete(c);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Entreprise object) {
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
	public List<Entreprise> getList() {
		try {
			return (List<Entreprise>) getHibernateTemplate().find(" from Entreprise");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long addId(Entreprise object) {
		try {
			return (long) getHibernateTemplate().save(object);
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0L;
		}

	}

}
