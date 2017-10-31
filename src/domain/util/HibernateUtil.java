package domain.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {

		try {

			// Créer une SessionFactory à partir de hibernate.cfg.xml

			Configuration configuration = new Configuration().configure();

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;

		}

		catch (Throwable ex) {

			// Gestion exception

			System.err.println("Echec création SessionFactory" + ex);

			throw new ExceptionInInitializerError(ex);

		}

	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;

	}

}