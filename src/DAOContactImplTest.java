import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.IDAOContact;
import domain.Address;
import domain.Contact;
import domain.Entreprise;
import domain.PhoneNumber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOContactImplTest {

	@Autowired
	private IDAOContact DAOContact;

	@Before
	public void initialize() {
		DAOContact.generateContact();
	}

	@Transactional
	@Test
	public void testGetContact() {
		initialize();
		Contact c = DAOContact.getContact(1);
		assertEquals("Damian", c.getFirstName());
		assertEquals("Stanley", c.getLastName());
		assertEquals("Damian.Stanley@gmail.com", c.getEmail());
		assertEquals("Viala-du-Tarn", c.getAddress().getCity());
		assertEquals("49265", c.getAddress().getZip());
		assertEquals("155 Rue Frémicourt", c.getAddress().getStreet());
		assertEquals("France", c.getAddress().getCountry());
		assertEquals("09 41 43 75 77", c.getProfiles().iterator().next().getPhoneNumber());
		assertEquals("Mobile", c.getProfiles().iterator().next().getPhoneKind());
		assertEquals(1, c.getProfiles().iterator().next().getContact().getId_contact());
	}

	@Transactional
	@Test
	public void testUpdatecContact() {

		Contact c = DAOContact.getContact(1);
		c.setFirstName("Chaney");
		c.setLastName("Doyle");
		c.setEmail("Chaney.Doyle@gmail.com");
		Address add = new Address("35 Place Alfred-Kastler", "Taverny", "59780", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		hpn.add(c.getProfiles().iterator().next());
		PhoneNumber pn = new PhoneNumber("Home", "04 31 90 59 05", null);
		c.getProfiles().add(pn);
		hpn.add(pn);
		DAOContact.updateContact(c, "Chaney", "Doyle", "Chaney.Doyle@gmail.com",
				new Address("35 Place Alfred-Kastler", "Taverny", "59780", "France"), hpn, 988598470);
		Contact d = DAOContact.getContact(1);
		assertEquals("Chaney", d.getFirstName());
		assertEquals("Doyle", d.getLastName());
		assertEquals("Chaney.Doyle@gmail.com", d.getEmail());
		assertEquals("Taverny", d.getAddress().getCity());
		assertEquals("59780", d.getAddress().getZip());
		assertEquals("35 Place Alfred-Kastler", d.getAddress().getStreet());
		assertEquals("France", d.getAddress().getCountry());
		PhoneNumber[] tabPn = d.getProfiles().toArray(new PhoneNumber[c.getProfiles().size()]);
		assertEquals("Home", tabPn[0].getPhoneKind());
		assertEquals("04 31 90 59 05", tabPn[0].getPhoneNumber());
		assertEquals(1L, tabPn[0].getContact().getId_contact());
		assertEquals("Mobile", tabPn[1].getPhoneKind());
		assertEquals("09 41 43 75 77", tabPn[1].getPhoneNumber());
		assertEquals(1L, tabPn[1].getContact().getId_contact());
	}
	
	@Transactional
	@Test
	public void testListContact() {

		assertNotNull(DAOContact.listContact());	}
	
	@Transactional
	@Test
	public void testAddContact() {

		Contact c = new Contact();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", null);
		hpn.add(pn);
		DAOContact.addContact("David", "Meimoun", "David.Meimoun@gmail.com", new Address("21 place charcot", "Sarcelles", "95200", "France"), hpn, 0);
		Contact d = DAOContact.getContact(101);
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
		assertEquals(101L, tabPn[0].getContact().getId_contact());
	}
	
	@Transactional
	@Test
	public void testAddEntreprise() {

		Entreprise c = new Entreprise();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", null);
		hpn.add(pn);
		c.setNumSiret(120101);
		DAOContact.addContact("David", "Meimoun", "David.Meimoun@gmail.com", new Address("21 place charcot", "Sarcelles", "95200", "France"), hpn, 120101);
		Entreprise d = (Entreprise) DAOContact.getContact(101);
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
		assertEquals(101L, tabPn[0].getContact().getId_contact());
		assertEquals(120101, d.getNumSiret());
	}
	
	@Transactional
	@Test
	public void testDeleteContact() {

		boolean bool = DAOContact.deleteContact(2);
		assertTrue(bool);
	}
	
	@Transactional
	//@Test
	//TODO a reger
	public void testErrorAddDoublonContact() {
		Contact c = new Contact();
		c.setFirstName("Chaney");
		c.setLastName("Doyle");
		c.setEmail("Chaney.Doyle@gmail.com");
		Address add = new Address("35 Place Alfred-Kastler", "Taverny", "59780", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Home", "04 31 90 59 05", null);
		hpn.add(pn);
		assertNull(DAOContact.addContact("Chaney", "Doyle", "Chaney.Doyle@gmail.com", add, hpn, 309860724));
	}
	
	
	

}
