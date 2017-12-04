import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import dao.IDAOContactGroup;
import domain.ContactGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOContactGroupImplTest {

	@Autowired
	private IDAOContactGroup DAOContactGroup;

	@Autowired
	private IDAOContact DAOContact;

	@Before
	public void initialize() {
		DAOContact.generateContact();
		DAOContactGroup.generateGroupContact();
		
	}

	@Transactional
	@Test
	public void testCreateContactGroup() {
		initialize();
		assertTrue(DAOContactGroup.createContactGroup("titi"));

	}

	@Transactional
	@Test
	public void testAddContactToGroup() {

		DAOContactGroup.addContactToGroup(1L, 1L);
	}

	@Transactional
	@Test
	public void testListGroupContact() {

		assertNotNull(DAOContactGroup.listGroupContact());
	}

	@Transactional
	@Test
	public void testGetContactGroup() {

		ContactGroup cg = DAOContactGroup.getContactGroup(1L);
		assertNotNull(cg);
		assertEquals("Parent", cg.getGroupName());
	}

	@Transactional
	@Test
	public void testListContactOutsideGroup() {
		assertNotNull(DAOContactGroup.listContactOutsideGroup(1));
	}

	@Transactional
	@Test
	public void testListContactInGroup() {
		assertNotNull(DAOContactGroup.listContactInGroup(1));
	}

	@Transactional
	@Test
	public void testDeleteContactFromGroup() {

		boolean bool = DAOContactGroup.deleteContactFromGroup(1, 1);
		assertTrue(bool);

	}

	@Transactional
	@Test
	public void testDeleteContactFromAllGroup() {

		boolean bool = DAOContactGroup.deleteContactFromAllGroup(1);
		assertTrue(bool);

	}

	@Transactional
	@Test
	public void testDeleteGroupContact() {

		boolean bool = DAOContactGroup.deleteGroupContact(1);
		assertTrue(bool);

	}

}
