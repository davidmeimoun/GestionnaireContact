import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.IDAOEntreprise;
import domain.Address;
import domain.Entreprise;
import domain.PhoneNumber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOEntrepriseImplTest {
	@Autowired
	private IDAOEntreprise DAOEntreprise;

	@Before
	public void initialize() {

	}

	@Transactional
	@Test
	public void testAdd() {

		Entreprise c = new Entreprise();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setNumSiret(123456789);
		c.setProfiles(hpn);

		long id = DAOEntreprise.addId(c);
		assertNotNull(id);
		Entreprise d = DAOEntreprise.get(id);
		assertEquals("David", d.getFirstName());
		assertEquals("Meimoun", d.getLastName());
		assertEquals("David.Meimoun@gmail.com", d.getEmail());
		assertEquals("Sarcelles", d.getAddress().getCity());
		assertEquals("95200", d.getAddress().getZip());
		assertEquals("21 place charcot", d.getAddress().getStreet());
		assertEquals("France", d.getAddress().getCountry());
		PhoneNumber[] tabPn = d.getProfiles().toArray(new PhoneNumber[d.getProfiles().size()]);
		assertEquals("Mobile", tabPn[0].getPhoneKind());
		assertEquals("06 37 47 93 74", tabPn[0].getPhoneNumber());
		assertEquals(123456789, d.getNumSiret());
		assertEquals(id, tabPn[0].getContact().getId_contact());
	}

	@Transactional
	@Test
	public void testUpdate() {
		Entreprise c = new Entreprise();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setNumSiret(123456789);
		c.setProfiles(hpn);

		long id = DAOEntreprise.addId(c);
		Entreprise recup = DAOEntreprise.get(id);
		recup.getProfiles().add(new PhoneNumber("Home", "01 39 94 88 54", recup));
		recup.setNumSiret(123);
		assertTrue(DAOEntreprise.update(recup));
		Entreprise d = DAOEntreprise.get(id);
		assertEquals("David", d.getFirstName());
		assertEquals("Meimoun", d.getLastName());
		assertEquals("David.Meimoun@gmail.com", d.getEmail());
		assertEquals("Sarcelles", d.getAddress().getCity());
		assertEquals("95200", d.getAddress().getZip());
		assertEquals("21 place charcot", d.getAddress().getStreet());
		assertEquals("France", d.getAddress().getCountry());
		assertEquals(123, d.getNumSiret());
		PhoneNumber[] tabPn = d.getProfiles().toArray(new PhoneNumber[d.getProfiles().size()]);
		assertNotNull(d.getProfiles().contains(tabPn[0]));
		assertNotNull(d.getProfiles().contains(tabPn[1]));
		/*
		 * assertEquals("Mobile", tabPn[0].getPhoneKind());
		 * assertEquals("06 37 47 93 74", tabPn[0].getPhoneNumber());
		 * assertEquals(id, tabPn[0].getEntreprise().getId_Entreprise());
		 * assertEquals("Home", tabPn[1].getPhoneKind());
		 * assertEquals("01 39 94 88 54", tabPn[1].getPhoneNumber());
		 * assertEquals(id, tabPn[1].getEntreprise().getId_Entreprise());
		 */
	}

	@Transactional
	@Test
	public void testList() {
		Entreprise c = new Entreprise();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		c.setNumSiret(123456789);
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setProfiles(hpn);

		DAOEntreprise.addId(c);
		List<Entreprise> listEntreprise = DAOEntreprise.getList();
		assertNotNull(listEntreprise);
		if (listEntreprise != null && listEntreprise.get(0) != null) {
			assertEquals(123456789, listEntreprise.get(0).getNumSiret());
		}
	}

	@Transactional
	@Test
	public void testDelete() {
		Entreprise c = new Entreprise();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		c.setNumSiret(123456789);
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setProfiles(hpn);

		long id = DAOEntreprise.addId(c);
		boolean bool = DAOEntreprise.delete(id);
		assertTrue(bool);
	}

}
