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

import dao.IDAOContact;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOContactImplTest {
	@Autowired
	private IDAOContact DAOContact2;

	@Before
	public void initialize() {

	}

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
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setProfiles(hpn);

		long id = DAOContact2.addId(c);
		assertNotNull(id);
		Contact d = DAOContact2.get(id);
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
		assertEquals(id, tabPn[0].getContact().getId_contact());
	}

	@Transactional
	@Test
	public void testUpdate() {
		Contact c = new Contact();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setProfiles(hpn);
		c.setLastName("Meimouna");
		c.setEmail("David.Meimoun@gmail.coma");
		c.getAddress().setCity("Sarcellesa");
		c.getAddress().setCountry("Francea");
		c.getAddress().setStreet("21 place charcota");
		c.getAddress().setZip("95201");
		long id = DAOContact2.addId(c);
		Contact recup = DAOContact2.get(id);
		recup.setFirstName("Davida");
		
		recup.getProfiles().add(new PhoneNumber("Home", "01 39 94 88 54", recup));

		assertTrue(DAOContact2.update(recup));
		Contact d = DAOContact2.get(id);
		assertEquals("Davida", d.getFirstName());
		assertEquals("Meimouna", d.getLastName());
		assertEquals("David.Meimoun@gmail.coma", d.getEmail());
		assertEquals("Sarcellesa", d.getAddress().getCity());
		assertEquals("95201", d.getAddress().getZip());
		assertEquals("21 place charcota", d.getAddress().getStreet());
		assertEquals("Francea", d.getAddress().getCountry());
		
		PhoneNumber[] tabPn = d.getProfiles().toArray(new PhoneNumber[d.getProfiles().size()]);
//	 assertNotNull(d.getProfiles().contains(tabPn[0]));
//	 assertNotNull(d.getProfiles().contains(tabPn[1]));
	 
		assertEquals("Mobile", tabPn[1].getPhoneKind());
		assertEquals("06 37 47 93 74", tabPn[1].getPhoneNumber());
		assertEquals(id, tabPn[1].getContact().getId_contact());
		assertEquals("Home", tabPn[0].getPhoneKind());
		assertEquals("01 39 94 88 54", tabPn[0].getPhoneNumber());
		assertEquals(id, tabPn[0].getContact().getId_contact());
	}

	@Transactional
	@Test
	public void testList() {
		Contact c = new Contact();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setProfiles(hpn);

		DAOContact2.addId(c);
		List<Contact> listContact = DAOContact2.getList();
		assertNotNull(listContact);
		if (listContact != null && listContact.get(0) != null) {
			assertEquals("David", listContact.get(0).getFirstName());
		}
	}

	@Transactional
	@Test
	public void testDelete() {
		Contact c = new Contact();
		c.setFirstName("David");
		c.setLastName("Meimoun");
		c.setEmail("David.Meimoun@gmail.com");
		Address add = new Address("21 place charcot", "Sarcelles", "95200", "France");
		c.setAddress(add);
		HashSet<PhoneNumber> hpn = new HashSet<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("Mobile", "06 37 47 93 74", c);
		hpn.add(pn);
		c.setProfiles(hpn);

		long id = DAOContact2.addId(c);
		boolean bool = DAOContact2.delete(id);
		assertTrue(bool);
	}

}
