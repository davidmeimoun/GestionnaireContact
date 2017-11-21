package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.Address;
import domain.Contact;
import domain.Entreprise;
import domain.PhoneNumber;
import domain.util.HibernateUtil;

public class DAOContact extends HibernateDaoSupport {
	public DAOContact() {
		super();
	}

	public Contact addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> phones,
			int numSiret) {

		Contact c;
		if (numSiret <= 0) {
			c = new Contact(fname, lname, email, address, phones);
			c.setType("Contact");
		} else {
			c = new Entreprise(fname, lname, email, address, phones, numSiret);
			c.setType("Entreprise");
		}

		if (phones != null) {
			for (PhoneNumber profile : phones) {
				profile.setContact(c);
				c.getProfiles().add(profile);
			}
		}

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			long id = (long) session.save(c);
			c.setId_contact(id);
			session.getTransaction().commit();
			System.out.println("Fin enregistrement, ID Contact = " + id);
			session.close();
			return c;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteContact(long id) {
		boolean result = new DAOContactGroup().deleteContactFromAllGroup(id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Contact c = (Contact) session.get(Contact.class, id);
			session.delete(c);
			session.getTransaction().commit();
			System.out.println("Fin de la suppression, ID Contact = " + id);
			session.close();
			result = result && true;
			return result;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Contact> listContact() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Contact> lc = (List<Contact>) session.createQuery("from Contact").list();
			System.out.println(lc.size());
			// session.getTransaction().commit();
			session.close();
			return lc;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Contact getContact(long id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Contact c = (Contact) session.get(Contact.class, id);

			session.getTransaction().commit();

			session.close();
			return c;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	public int getNumSiretEntreprise(long id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Entreprise> lc = (List<Entreprise>) session.createQuery("from Entreprise").list();
			System.out.println(lc.toString());
			for (Entreprise entreprise : lc) {
				if (entreprise.getId_contact() == id) {
					System.out.println("pour l'entreprise " + entreprise.getId_contact() + " le num siret est "
							+ entreprise.getNumSiret());
					return entreprise.getNumSiret();

				}
			}
			session.close();
			return 0;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public Contact updateContact(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int siretnum) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			if (siretnum <= 0) {
				if (c instanceof Entreprise) {
					System.out.println("Num siret n'est pas valide!....");
					c.setType("Contact");
					c = (Contact) c;
					session.delete("from entreprise_table where id_contact = '" + c.getId_contact()+"'");
					session.createQuery(" delete from entreprise_table where classId= :classId").setString("",  String.valueOf(c.getId_contact())).executeUpdate();
				}
			} else {
				if (c instanceof Entreprise) {
					((Entreprise) c).setNumSiret(siretnum);
					c.setType("Entreprise");
					c = (Entreprise) c;
				}
			}
			if (!fname.equals(c.getFirstName()))
				c.setFirstName(fname);
			if (!lname.equals(c.getLastName()))
				c.setLastName(lname);
			if (!email.equals(c.getEmail()))
				c.setEmail(email);
			if (!address.getCity().equals(c.getAddress().getCity()))
				c.getAddress().setCity(address.getCity());
			if (!address.getCountry().equals(c.getAddress().getCountry()))
				c.getAddress().setCountry(address.getCountry());
			if (!address.getStreet().equals(c.getAddress().getStreet()))
				c.getAddress().setStreet(address.getStreet());
			if (!address.getZip().equals(c.getAddress().getZip()))
				c.getAddress().setZip(address.getZip());

			if (profiles != null) {
				for (PhoneNumber profile : profiles) {
					profile.setContact(c);
					c.getProfiles().add(profile);
				}
			}
			c.setProfiles(profiles);
			System.out.println("Updating all the thing" + c);

			session.saveOrUpdate(c);
			session.getTransaction().commit();
			System.out.println("Fin MAJ, ID Contact = " + c.getId_contact());
			session.close();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
