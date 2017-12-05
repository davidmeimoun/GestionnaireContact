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

import dao.IDAOContact2;
import dao.IDAOContactGroup2;
import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import domain.PhoneNumber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOContactGroupImplTest2 {
	@Autowired
	private IDAOContactGroup2 DAOContactGroup2;

	@Autowired
	private IDAOContact2 DAOContact2;

	@Before
	public void initialize() {

	}

	@Transactional
	@Test
	public void testAdd() {
		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long id = DAOContactGroup2.addId(cg);
		assertNotNull(id);

		ContactGroup cgRecup = DAOContactGroup2.get(id);
		assertEquals("titi", cgRecup.getGroupName());

	}

	@Transactional
	@Test
	public void testAddContactToGroup() {

		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long idContactGroup = DAOContactGroup2.addId(cg);

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

		long idContact = DAOContact2.addId(c);

		assertTrue(DAOContactGroup2.addContactToGroup(idContactGroup, idContact));
	}

	@Transactional
	@Test
	public void testListGroupContact() {
		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		DAOContactGroup2.addId(cg);
		List<ContactGroup> listGroup = DAOContactGroup2.getList();
		assertNotNull(listGroup);
		assertEquals("titi", listGroup.get(0).getGroupName());
	}

	@Transactional
	@Test
	public void testGetContactGroup() {

		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long idContactGroup = DAOContactGroup2.addId(cg);

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

		ContactGroup cgTest = DAOContactGroup2.get(idContactGroup);
		assertNotNull(cgTest);
		assertEquals("titi", cg.getGroupName());
	}

	@Transactional
	@Test
	public void testListContactOutsideGroup() {
		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long idContactGroup = DAOContactGroup2.addId(cg);

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

		Contact c1 = new Contact();
		c1.setFirstName("Joan");
		c1.setLastName("Medjid");
		c1.setEmail("j.medjid@orange.fr");
		Address add2 = new Address("21 allee de l'asticot", "Paris", "75016", "France");
		c1.setAddress(add2);
		HashSet<PhoneNumber> hpn2 = new HashSet<PhoneNumber>();
		PhoneNumber pn2 = new PhoneNumber("Mobile", "06 12 34 56 78", c1);
		hpn2.add(pn2);
		c1.setProfiles(hpn2);
		long idContact = DAOContact2.addId(c1);
		DAOContactGroup2.addContactToGroup(idContactGroup, idContact);
		List<Contact> listContact = DAOContactGroup2.listContactOutsideGroup(idContactGroup);
		assertEquals("David", listContact.get(0).getFirstName());
	}

	@Transactional
	@Test
	public void testListContactInGroup() {
		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long idContactGroup = DAOContactGroup2.addId(cg);

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

		Contact c1 = new Contact();
		c1.setFirstName("Joan");
		c1.setLastName("Medjid");
		c1.setEmail("j.medjid@orange.fr");
		Address add2 = new Address("21 allee de l'asticot", "Paris", "75016", "France");
		c1.setAddress(add2);
		HashSet<PhoneNumber> hpn2 = new HashSet<PhoneNumber>();
		PhoneNumber pn2 = new PhoneNumber("Mobile", "06 12 34 56 78", c1);
		hpn2.add(pn2);
		c1.setProfiles(hpn2);
		long idContact = DAOContact2.addId(c1);
		assertTrue(DAOContactGroup2.addContactToGroup(idContactGroup, idContact));
		List<Contact> listContact = DAOContactGroup2.listContactInGroup(idContactGroup);
		assertEquals("Joan", listContact.get(0).getFirstName());
	}

	@Transactional
	@Test
	public void testDeleteContactFromGroup() {
		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long idContactGroup = DAOContactGroup2.addId(cg);

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
		long idContact = DAOContact2.addId(c);
		DAOContactGroup2.addContactToGroup(idContactGroup, idContact);
		boolean bool = DAOContactGroup2.deleteContactFromGroup(idContactGroup, idContact);
		assertTrue(bool);

	}

	@Transactional
	@Test
	public void testDeleteContactFromAllGroup() {

		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long idGroupContact = DAOContactGroup2.addId(cg);

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
		long idContact = DAOContact2.addId(c);
		DAOContactGroup2.addContactToGroup(idGroupContact, idContact);
		boolean bool = DAOContactGroup2.deleteContactFromAllGroup(idContact);
		assertTrue(bool);

	}

	@Transactional
	@Test
	public void testDeleteGroupContact() {

		ContactGroup cg = new ContactGroup();
		cg.setGroupName("titi");
		long id = DAOContactGroup2.addId(cg);

		assertTrue(DAOContactGroup2.delete(id));

	}
}
