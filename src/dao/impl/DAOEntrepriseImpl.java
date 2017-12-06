package dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOEntreprise;
import domain.Address;
import domain.Entreprise;
import domain.PhoneNumber;

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

	@Override
	public void generate() {
		String prenoms[] = { "John", "Bevis", "Hasad", "Chiquita", "Ferris", "Whilemina", "Plato", "Rooney", "Silas",
				"Brielle", "Nina", "Zane", "Quyn", "Olga", "Zeph", "Michelle", "Lacy", "Cassidy", "Ori", "Isabella",
				"Griffin", "Nathan", "Chanda", "Tallulah", "Aline", "Melvin", "Hop", "Veronica", "Daniel", "Nelle",
				"Ryder", "Amanda", "Ava", "Caesar", "Hyatt", "Stuart", "Barclay", "Lester", "Joel", "Armand", "Dylan",
				"Samantha", "Lucius", "Noah", "Orla", "Claudia", "Naida", "Veda", "Armand", "Lucas" };

		String nom[] = { "Huffman", "Terry", "Riggs", "Reed", "Yang", "Randall", "Reynolds", "Ray", "Dominguez",
				"Travis", "Stanley", "Shields", "Nielsen", "Wyatt", "Wheeler", "Steele", "Flores", "Hancock", "Horn",
				"Irwin", "Mullen", "Hodge", "Pena", "Leblanc", "Cooper", "Brennan", "Nguyen", "Fleming", "Benson",
				"Head", "Melton", "Lamb", "Gates", "Cohen", "Guerrero", "Head", "Haley", "Beasley", "Crawford",
				"Hoffman", "Cohen", "Spears", "Dudley", "Witt", "Soto", "Simon", "Marshall", "Arnold", "Gilliam",
				"Nichols" };

		String[] city = { " Triel-sur-Seine", " Rothéneuf", " Le Trait", " Coudekerque-Branche", " Saints", " Albi",
				" Villers-le-Lac", " Wylder", " Saint-Valéry-en-Caux", " Paris 19ème", " Wolfisheim", " Marseille",
				" Venesmes", " Vrigne-aux-Bois", " Saint-Hilaire-les-Andrésis", " Lanslevillard", " Virignin",
				" La Vaupalière", " Pierre-Bénite", " Les Pavillons-sous-Bois", " Vidaillat",
				" Saint-Sulpice-le-Verdon", " Vironvay", " Reichstett", " Mortefontaine-en-Thelle", " Les Gras",
				" Saint-Pierre-Azif", " Sainte-Suzanne", " Vissec", " Saint-Hilaire-du-Maine", " Saint-Caprais",
				" Verdon", " Sceaux", " Sauzet", " Malbouzon", " Yvernaumont", " Cazaux-Fréchet", " Villetelle",
				" Villers-le-Sec", " Vidouville", " Puisieux", " Saint-Pierre-Eynac", " La Villedieu",
				" Treize-Septiers", " Quimiac", " Vérel-de-Montbel", " Serreslous-et-Arribans", " Villemorin",
				" Vallabrix", " Teillé" };

		String[] zip = { "75345", "43126", "05201", "83355", "89282", "59919", "60691", "79443", "87795", "01997",
				"26562", "50169", "15845", "28232", "14351", "08546", "99042", "40274", "30620", "95191", "27669",
				"20915", "02027", "21230", "57081", "41448", "30292", "92917", "13109", "17969", "83394", "21465",
				"27608", "74057", "65421", "47034", "61806", "07588", "30021", "15943", "48059", "39964", "74259",
				"52230", "07866", "12571", "04996", "43974", "71059", "61746" };

		String[] phoneNumber = { "01 80 04 64 29", "03 08 25 30 17", "03 02 04 90 00", "01 08 23 41 46",
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
				443269899, 764832309, 969865740, 111431508, 610294399, 305180994 };

		String[] street = { "198 Rue Cadet", "127 Rue de l\'Interne-Loëb", "283 Place du Général-Brocard",
				"244 Rue des Envierges", "19 Rue Francis-de-Miomandre", "31 Avenue Émile-Acollas",
				"251 Impasse Beaubourg", "298 Rue François-Truffaut", "232 Rue de la Harpe", "150 Rue Chanez",
				"72 Rue Desargues", "143 Rue Francis-Ponge", "14 Villa Guibert", "42 Boulevard des Italiens",
				"43 Pont des Invalides", "83 Rue de Galliera", "282 Rue Cardinale", "42 Rue de l\'Élysée",
				"30 Villa Ballu", "107 Rue de Longchamp", "7 Passage Geffroy-Didelot", "2 Villa Guizot",
				"124 Boulevard des Batignolles", "80 Rue Camille-Bombois", "37 Place de l\'Hôtel-de-Ville",
				"46 Rue Cavé", "10 Rue Henri-Pape", "86 Rue du Général-Bertrand", "150 Rue Frédéric-Schneider",
				"97 Avenue Daniel-Lesueur", "240 Pont de Grenelle", "107 Rue Beauregard", "219 Avenue Émile-Deschanel",
				"222 Rue du Général-Niessel", "69 Rue du Capitaine-Madon", "53 Rue Eugène-Poubelle",
				"246 Rue Gaston-Darboux", "164 Rue du Capitaine-Lagache", "282 Rue Érard", "176 Rue Guichard",
				"75 Avenue Dutuit", "148 Allée des Hortensias", "186 Rue d\'Aguesseau", "103 Rue de l\'Espérance",
				"283 Passage du Caire", "89 Avenue Adrien-Hébrard", "112 Place Alfred-Kastler",
				"94 Rue Édouard-Detaille", "257 Rue Friant", "238 Rue Frédérick-Lemaître" };

		for (int i = 0; i < 50; i++) {
			Address add = new Address(street[i], city[i], zip[i], "France");
			PhoneNumber pn = new PhoneNumber("Mobile", phoneNumber[i], null);
			Set<PhoneNumber> spn = new HashSet<PhoneNumber>();
			spn.add(pn);
			Entreprise e = new Entreprise(prenoms[i], nom[i], prenoms[i] + "." + nom[i] + "@gmail.com", add, spn,
					numSiret[i]);
			add(e);
		}

	}

	@Override
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

}
