package dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOContact;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

public class DAOContactImpl extends HibernateDaoSupport implements IDAOContact {

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
	public boolean update(Contact object) throws HibernateException {

		getHibernateTemplate().update(object);
		return true;

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

	@Override
	public void generate() {
		String prenoms[] = { "Damian", "Chaney", "Nerea", "Driscoll", "Ava", "Nash", "Cameron", "Lenore", "Aline",
				"Hilary", "Fallon", "Hayley", "Hyatt", "Buckminster", "Adena", "Basil", "Herrod", "Aidan", "Martin",
				"Conan", "Ignatius", "Keiko", "Magee", "Isaac", "Akeem", "Basil", "Cheyenne", "Moana", "Gray", "Kiara",
				"Joel", "Blaine", "Dexter", "Denton", "Xerxes", "Acton", "Dana", "Cullen", "Levi", "Halla", "Lillith",
				"Raven", "Kirk", "Cecilia", "Peter", "Reece", "Zane", "Kiona", "Sybil", "Whitney" };

		String nom[] = { "Stanley", "Doyle", "Miller", "Short", "Hinton", "Hines", "Patrick", "Oneal", "Dixon",
				"Collins", "Gomez", "Shelton", "Griffin", "Holman", "Boyer", "Fisher", "Moody", "Dunlap", "Hutchinson",
				"Mcintosh", "Cross", "Ochoa", "Gilliam", "Powell", "Burt", "Goodman", "Richardson", "Mcmahon", "Rhodes",
				"Whitfield", "Farrell", "Cabrera", "Travis", "Macdonald", "Warner", "Crawford", "Delacruz", "Blackwell",
				"Tate", "Burch", "Olsen", "Higgins", "Whitney", "Leon", "Gillespie", "Mueller", "Mccray", "Rivera",
				"Hoover", "Puckett" };
		String[] city = { "Viala-du-Tarn", " Taverny", " Vissec", " Vergeroux", " La Faute-sur-Mer", " Sainte-Consorce",
				" Tollevast", " Thiré", " Ajaccio", " Santes", " Trévoux", " Villiers-Saint-Frédéric", " Chamalières",
				" Villancy", " Verreries-de-Moussans", " Morschwiller-le-Bas", " Souvigny", " Tourrenquets",
				" Sainte-Eulalie-en-Born", " Sampzon", " Chavanne", " Arrest", " Saint-Georges-de-Rouelley",
				" Passonfontaine", " Sainte-Geneviève-lès-Gasny", " Plouégat-Guérand", " Vic-sur-Cère",
				" La Faute-sur-Mer", " Villars", " Saint-Étienne-de-Tulmont", " Yzeure", " Thonnance-les-Moulins",
				" Sérandon", " Saint-Priest-la-Vêtre", " Vialas", " Ollainville", " Le Garric", " Voulpaix",
				" Villotte-sur-Aire", " Villedieu-le-Château", " Voutré", " Tramain", " Tralonca", " Tournan-en-Brie",
				" Rilhac-Rancon", " Panissières", " Varennes-sur-Amance", " Végennes", " Spay",
				" Saint-Amans-des-Cots" };

		String[] zip = { "49265", "59780", "98993", "51983", "79805", "77660", "95939", "24184", "39172", "43152",
				"98255", "06359", "03689", "58867", "56299", "46919", "48626", "56976", "75803", "43231", "01944",
				"30269", "41518", "32628", "61298", "38949", "66801", "67181", "50674", "30783", "45542", "82729",
				"99132", "30261", "28495", "37786", "84668", "60321", "32965", "92327", "54582", "15990", "48385",
				"32848", "08252", "84633", "39897", "94770", "02543", "76107" };

		String[] phoneNumber = { "09 41 43 75 77", "04 31 90 59 05", "08 71 47 84 25", "01 80 34 74 41",
				"07 22 30 34 70", "09 60 88 16 52", "06 07 60 01 47", "01 19 87 50 36", "03 60 83 08 30",
				"09 31 57 13 30", "01 87 59 77 83", "08 81 95 04 81", "08 17 13 84 93", "09 03 75 31 20",
				"07 27 58 15 72", "08 79 93 29 92", "05 59 53 28 09", "02 91 23 90 84", "04 25 50 95 16",
				"01 67 08 76 64", "03 56 67 48 38", "01 83 84 84 02", "01 91 63 87 86", "08 93 87 99 51",
				"07 25 33 34 58", "08 93 40 00 63", "01 21 67 56 39", "09 11 37 21 27", "07 15 06 12 68",
				"04 13 90 49 02", "08 33 62 00 71", "04 91 14 75 80", "04 49 14 17 57", "09 33 34 28 16",
				"09 61 70 56 54", "06 00 68 28 49", "02 90 27 45 09", "02 77 28 21 79", "05 89 79 25 61",
				"05 78 38 60 16", "03 63 43 82 47", "06 35 40 61 56", "03 20 67 96 70", "01 92 79 32 61",
				"08 57 97 70 96", "06 48 26 76 32", "06 64 63 17 59", "09 94 61 80 73", "06 65 19 15 12",
				"04 21 50 24 75" };

		String[] street = { "155 Rue Frémicourt", "35 Place Alfred-Kastler", "156 Avenue Daniel-Lesueur",
				"96 Avenue du Docteur-Gley", "127 Rue Becquerel", "280 Avenue Delecourt", "94 Rue Adolphe-Jullien",
				"32 Rue Furtado-Heine", "87 Impasse Fortin", "126 Rue d\'Alexandrie", "167 Villa Émile-Loubet",
				"259 Rue d\'Alésia", "69 Rue d\'Enghien", "113 Boulevard Gouvion-Saint-Cyr", "55 Rue Haxo",
				"299 Rue Erik-Satie", "186 Boulevard de la Chapelle", "73 Villa Baumann", "120 Villa Industrielle",
				"245 Rue Jacques-Henri-Lartigue", "261 Villa Hallé", "34 Rue Ernest-Cresson", "24 Rue du Guignier",
				"32 Passage Guilhem", "227 Boulevard de Denain", "286 Boulevard Delessert", "168 Rue Gay-Lussac",
				"148 Rue Dombasle", "153 Rue Henri-Ribière", "202 Cour Jasmin", "207 Rue Cadet", "227 Rue Gérard",
				"275 Place de l\'Hôtel-de-Ville", "60 Rue Ernest-Lacoste", "185 Rue Francis-de-Croisset",
				"196 Rue du Capitaine-Marchal", "53 Rue de Bellièvre", "260 Rue Béatrix-Dussane", "37 Rue Evette",
				"66 Cour Jasmin", "21 Rue du Gabon", "73 Rue Henri-Duchène", "195 Rue Fizeau", "85 Impasse du Gué",
				"149 Place Franz-Liszt", "182 Rue Doudeauville", "163 Rue Émile-Pierre-Casel", "291 Cour du Cantal",
				"247 Square Grangé", "117 Boulevard de Belleville" };

		// creation contact
		for (int i = 0; i < 50; i++) {
			Address add = new Address(street[i], city[i], zip[i], "France");
			PhoneNumber pn = new PhoneNumber("Mobile", phoneNumber[i], null);
			Set<PhoneNumber> spn = new HashSet<PhoneNumber>();
			spn.add(pn);
			Contact c = new Contact(prenoms[i], nom[i], prenoms[i] + "." + nom[i] + "@gmail.com", add, spn);
			add(c);
		}

	}
	
	@Override
	public boolean versionIsChanged(long idContact, String versionActuelle) {
		String versionBDD = String.valueOf(getHibernateTemplate().get(Contact.class, idContact).getVersion());
		if (versionBDD.equals(versionActuelle)) {
			return false;
		} else {
			return true;
		}
	}



}
