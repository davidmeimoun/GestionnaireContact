package dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOContact;
import dao.IDAOContactGroup;
import domain.Address;
import domain.Contact;
import domain.Entreprise;
import domain.PhoneNumber;

public class DAOContactImpl extends HibernateDaoSupport implements IDAOContact {

	private IDAOContactGroup daoContactGroup;

	public DAOContactImpl(IDAOContactGroup daoContactGroup) {
		super();
		this.daoContactGroup = daoContactGroup;
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

		long id = (long) getHibernateTemplate().save(c);
		c.setId_contact(id);
		return c;

	}

	public boolean deleteContact(long id) {
		boolean result = daoContactGroup.deleteContactFromAllGroup(id);
		try {
			Contact c = getContact(id);
			getHibernateTemplate().delete(c);
			result = result && true;
			return result;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Contact> listContact() {
		return (List<Contact>) getHibernateTemplate().find(" from Contact");

	}

	public Contact getContact(long id) {
		try {
			// String hql = "from Contact_table c where c.ID_CONTACT =
			// :idContact";
			// return (Contact)
			// getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setLong("idContact",
			// id);

			return getHibernateTemplate().get(Contact.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	public int getNumSiretEntreprise(long id) {

		try {
			Entreprise entreprise = getHibernateTemplate().get(Entreprise.class, id);
			if (entreprise != null) {
				return entreprise.getNumSiret();
			} else {
				return 0;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Contact updateContact(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int siretnum) {
		try {
			if (siretnum <= 0) {
				if (c instanceof Entreprise) {
					c.setType("Contact");
				}
			} else {
				if (c instanceof Entreprise) {
					c.setType("Entreprise");
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
			getHibernateTemplate().update(c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Contact updateContact2(Contact c, String fname, String lname, String email, Address address,
			Set<PhoneNumber> profiles, int siretnum) {
		try {
			if (siretnum <= 0) {
				if (c instanceof Entreprise) {
					c.setType("Contact");
				}
			} else {
				if (c instanceof Entreprise) {
					c.setType("Entreprise");
				}
			}
			Contact ctmp = getContact(c.getId_contact());
			ctmp.setFirstName(fname);
			ctmp.setLastName(lname);
			ctmp.setEmail(email);
			ctmp.setAddress(address);
			ctmp.setProfiles(profiles);

			if (profiles != null) {
				for (PhoneNumber profile : profiles) {
					profile.setContact(c);
					c.getProfiles().add(profile);
				}
			}
			c.setProfiles(profiles);
			getHibernateTemplate().update(c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void generateContact() {
		String prenoms[] = { "Damian", "Chaney", "Nerea", "Driscoll", "Ava", "Nash", "Cameron", "Lenore", "Aline",
				"Hilary", "Fallon", "Hayley", "Hyatt", "Buckminster", "Adena", "Basil", "Herrod", "Aidan", "Martin",
				"Conan", "Ignatius", "Keiko", "Magee", "Isaac", "Akeem", "Basil", "Cheyenne", "Moana", "Gray", "Kiara",
				"Joel", "Blaine", "Dexter", "Denton", "Xerxes", "Acton", "Dana", "Cullen", "Levi", "Halla", "Lillith",
				"Raven", "Kirk", "Cecilia", "Peter", "Reece", "Zane", "Kiona", "Sybil", "Whitney", "John", "Bevis",
				"Hasad", "Chiquita", "Ferris", "Whilemina", "Plato", "Rooney", "Silas", "Brielle", "Nina", "Zane",
				"Quyn", "Olga", "Zeph", "Michelle", "Lacy", "Cassidy", "Ori", "Isabella", "Griffin", "Nathan", "Chanda",
				"Tallulah", "Aline", "Melvin", "Hop", "Veronica", "Daniel", "Nelle", "Ryder", "Amanda", "Ava", "Caesar",
				"Hyatt", "Stuart", "Barclay", "Lester", "Joel", "Armand", "Dylan", "Samantha", "Lucius", "Noah", "Orla",
				"Claudia", "Naida", "Veda", "Armand", "Lucas" };

		String nom[] = { "Stanley", "Doyle", "Miller", "Short", "Hinton", "Hines", "Patrick", "Oneal", "Dixon",
				"Collins", "Gomez", "Shelton", "Griffin", "Holman", "Boyer", "Fisher", "Moody", "Dunlap", "Hutchinson",
				"Mcintosh", "Cross", "Ochoa", "Gilliam", "Powell", "Burt", "Goodman", "Richardson", "Mcmahon", "Rhodes",
				"Whitfield", "Farrell", "Cabrera", "Travis", "Macdonald", "Warner", "Crawford", "Delacruz", "Blackwell",
				"Tate", "Burch", "Olsen", "Higgins", "Whitney", "Leon", "Gillespie", "Mueller", "Mccray", "Rivera",
				"Hoover", "Puckett", "Huffman", "Terry", "Riggs", "Reed", "Yang", "Randall", "Reynolds", "Ray",
				"Dominguez", "Travis", "Stanley", "Shields", "Nielsen", "Wyatt", "Wheeler", "Steele", "Flores",
				"Hancock", "Horn", "Irwin", "Mullen", "Hodge", "Pena", "Leblanc", "Cooper", "Brennan", "Nguyen",
				"Fleming", "Benson", "Head", "Melton", "Lamb", "Gates", "Cohen", "Guerrero", "Head", "Haley", "Beasley",
				"Crawford", "Hoffman", "Cohen", "Spears", "Dudley", "Witt", "Soto", "Simon", "Marshall", "Arnold",
				"Gilliam", "Nichols" };

		String[] city = { "Viala-du-Tarn", " Taverny", " Vissec", " Vergeroux", " La Faute-sur-Mer", " Sainte-Consorce",
				" Tollevast", " Thiré", " Ajaccio", " Santes", " Trévoux", " Villiers-Saint-Frédéric", " Chamalières",
				" Villancy", " Verreries-de-Moussans", " Morschwiller-le-Bas", " Souvigny", " Tourrenquets",
				" Sainte-Eulalie-en-Born", " Sampzon", " Chavanne", " Arrest", " Saint-Georges-de-Rouelley",
				" Passonfontaine", " Sainte-Geneviève-lès-Gasny", " Plouégat-Guérand", " Vic-sur-Cère",
				" La Faute-sur-Mer", " Villars", " Saint-Étienne-de-Tulmont", " Yzeure", " Thonnance-les-Moulins",
				" Sérandon", " Saint-Priest-la-Vêtre", " Vialas", " Ollainville", " Le Garric", " Voulpaix",
				" Villotte-sur-Aire", " Villedieu-le-Château", " Voutré", " Tramain", " Tralonca", " Tournan-en-Brie",
				" Rilhac-Rancon", " Panissières", " Varennes-sur-Amance", " Végennes", " Spay", " Saint-Amans-des-Cots",
				" Triel-sur-Seine", " Rothéneuf", " Le Trait", " Coudekerque-Branche", " Saints", " Albi",
				" Villers-le-Lac", " Wylder", " Saint-Valéry-en-Caux", " Paris 19ème", " Wolfisheim", " Marseille",
				" Venesmes", " Vrigne-aux-Bois", " Saint-Hilaire-les-Andrésis", " Lanslevillard", " Virignin",
				" La Vaupalière", " Pierre-Bénite", " Les Pavillons-sous-Bois", " Vidaillat",
				" Saint-Sulpice-le-Verdon", " Vironvay", " Reichstett", " Mortefontaine-en-Thelle", " Les Gras",
				" Saint-Pierre-Azif", " Sainte-Suzanne", " Vissec", " Saint-Hilaire-du-Maine", " Saint-Caprais",
				" Verdon", " Sceaux", " Sauzet", " Malbouzon", " Yvernaumont", " Cazaux-Fréchet", " Villetelle",
				" Villers-le-Sec", " Vidouville", " Puisieux", " Saint-Pierre-Eynac", " La Villedieu",
				" Treize-Septiers", " Quimiac", " Vérel-de-Montbel", " Serreslous-et-Arribans", " Villemorin",
				" Vallabrix", " Teillé" };

		String[] zip = { "49265", "59780", "98993", "51983", "79805", "77660", "95939", "24184", "39172", "43152",
				"98255", "06359", "03689", "58867", "56299", "46919", "48626", "56976", "75803", "43231", "01944",
				"30269", "41518", "32628", "61298", "38949", "66801", "67181", "50674", "30783", "45542", "82729",
				"99132", "30261", "28495", "37786", "84668", "60321", "32965", "92327", "54582", "15990", "48385",
				"32848", "08252", "84633", "39897", "94770", "02543", "76107", "75345", "43126", "05201", "83355",
				"89282", "59919", "60691", "79443", "87795", "01997", "26562", "50169", "15845", "28232", "14351",
				"08546", "99042", "40274", "30620", "95191", "27669", "20915", "02027", "21230", "57081", "41448",
				"30292", "92917", "13109", "17969", "83394", "21465", "27608", "74057", "65421", "47034", "61806",
				"07588", "30021", "15943", "48059", "39964", "74259", "52230", "07866", "12571", "04996", "43974",
				"71059", "61746" };

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
				"04 21 50 24 75", "01 80 04 64 29", "03 08 25 30 17", "03 02 04 90 00", "01 08 23 41 46",
				"01 02 74 33 55", "07 61 63 82 73", "02 97 88 66 79", "05 96 78 12 51", "07 31 78 46 32",
				"07 11 00 97 63", "07 16 62 44 76", "03 27 63 21 43", "05 61 45 50 83", "01 03 41 74 39",
				"04 06 86 27 24", "06 23 03 12 02", "07 43 87 74 16", "01 64 43 18 64", "02 86 55 47 47",
				"04 40 88 50 46", "02 95 45 22 10", "04 12 24 90 06", "02 25 70 53 62", "05 22 85 51 65",
				"06 77 91 40 75", "06 19 39 52 23", "07 99 41 23 85", "04 15 90 25 37", "07 13 30 33 65",
				"05 15 70 79 65", "04 77 01 18 69", "03 40 86 79 80", "09 16 49 20 39", "01 76 95 05 68",
				"08 72 22 07 03", "03 53 83 56 24", "06 79 95 44 66", "09 18 79 53 36", "07 77 40 01 57",
				"04 71 06 90 98", "01 29 26 21 48", "01 14 22 21 27", "07 41 68 91 24", "08 31 02 97 61",
				"05 58 15 97 27", "09 34 46 95 16", "01 86 78 04 64", "07 39 95 20 20", "03 95 30 25 90",
				"01 85 60 10 32" };

		int[] numSiret = { 988598470, 309860724, 13847504, 818670200, 014404727, 91294850, 642789283, 662571942,
				847401288, 691055552, 84354737, 623078649, 600390215, 331246322, 905063624, 670742550, 670795111,
				106798143, 368784047, 060132552, 49670003, 522273713, 953804291, 500399506, 247345259, 311529424,
				405062613, 961226818, 46885406, 226016798, 175559202, 640792743, 759688187, 317826774, 166063917,
				22508840, 697568004, 872715586, 86037629, 614302982, 540688660, 840955694, 617999008, 74909359,
				443269899, 764832309, 969865740, 111431508, 610294399, 305180994, 917146326, 350532479, 967306606,
				934128729, 125509224, 703338608, 230882789, 997554670, 256614751, 700388820, 473494334, 250486719,
				257225003, 663249464, 308944222, 923807002, 825318298, 447494261, 596278937, 030752257, 634220602,
				431423532, 178070991, 507446854, 772416772, 849668850, 672926805, 329440283, 644384034, 545864423,
				888254117, 973328990, 248089831, 978447563, 629970682, 979035581, 63618847, 560602757, 168178085,
				485869796, 677195034, 150204972, 325561074, 698041217, 586200362, 236875126, 563890789, 28128759,
				23269699, 673319166 };

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
				"247 Square Grangé", "117 Boulevard de Belleville", "198 Rue Cadet", "127 Rue de l\'Interne-Loëb",
				"283 Place du Général-Brocard", "244 Rue des Envierges", "19 Rue Francis-de-Miomandre",
				"31 Avenue Émile-Acollas", "251 Impasse Beaubourg", "298 Rue François-Truffaut", "232 Rue de la Harpe",
				"150 Rue Chanez", "72 Rue Desargues", "143 Rue Francis-Ponge", "14 Villa Guibert",
				"42 Boulevard des Italiens", "43 Pont des Invalides", "83 Rue de Galliera", "282 Rue Cardinale",
				"42 Rue de l\'Élysée", "30 Villa Ballu", "107 Rue de Longchamp", "7 Passage Geffroy-Didelot",
				"2 Villa Guizot", "124 Boulevard des Batignolles", "80 Rue Camille-Bombois",
				"37 Place de l\'Hôtel-de-Ville", "46 Rue Cavé", "10 Rue Henri-Pape", "86 Rue du Général-Bertrand",
				"150 Rue Frédéric-Schneider", "97 Avenue Daniel-Lesueur", "240 Pont de Grenelle", "107 Rue Beauregard",
				"219 Avenue Émile-Deschanel", "222 Rue du Général-Niessel", "69 Rue du Capitaine-Madon",
				"53 Rue Eugène-Poubelle", "246 Rue Gaston-Darboux", "164 Rue du Capitaine-Lagache", "282 Rue Érard",
				"176 Rue Guichard", "75 Avenue Dutuit", "148 Allée des Hortensias", "186 Rue d\'Aguesseau",
				"103 Rue de l\'Espérance", "283 Passage du Caire", "89 Avenue Adrien-Hébrard",
				"112 Place Alfred-Kastler", "94 Rue Édouard-Detaille", "257 Rue Friant", "238 Rue Frédérick-Lemaître" };

		// Creation entreprise
		for (int i = 0; i < 49; i++) {

			Address add = new Address(street[i], city[i], zip[i], "France");
			PhoneNumber pn = new PhoneNumber("Mobile", phoneNumber[i], null);
			Set<PhoneNumber> spn = new HashSet<PhoneNumber>();
			spn.add(pn);
			addContact(prenoms[i], nom[i], prenoms[i] + "." + nom[i] + "@gmail.com", add, spn, numSiret[i]);
		}

		// creation contact
		for (int i = 49; i < 100; i++) {
			Address add = new Address(street[i], city[i], zip[i], "France");
			PhoneNumber pn = new PhoneNumber("Mobile", phoneNumber[i], null);
			Set<PhoneNumber> spn = new HashSet<PhoneNumber>();
			spn.add(pn);
			addContact(prenoms[i], nom[i], prenoms[i] + "." + nom[i] + "@gmail.com", add, spn, 0);
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
