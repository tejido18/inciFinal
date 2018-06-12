package persistance_tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Agent;
import main.Application;
import repositories.AgentsRepository;
import repositories.Database;
import util.JasyptEncryptor;

/**
 * Created by Nicolás on 15/02/2017.
 */
@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseTest {

	@Autowired
	private AgentsRepository repo;

	// User to use as reference for test
	private Agent testedUser;
	private Agent testedUser2;

	@Autowired
	private Database dat;

	@Before
	public void setUp() {
		testedUser = new Agent("Luis Gracia", "LGracia@gmail.com", "Luis123", "147986", "Sensor");
		repo.insert(testedUser);

		testedUser2 = new Agent("Maria MamaMia", "asd", "pass14753", "363636H", "Entity");
		repo.insert(testedUser2);
	}

	@After
	public void tearDown() {
		repo.delete(testedUser);
		repo.delete(testedUser2);
	}

	@Test
	public void testGetParticipant() {
		// It should be previously encoded if the DB is given so this may be changed.
		Agent user = dat.getAgent("147986");
		user.setUsername("USA");
		Assert.assertEquals(user.getUsername(), "USA");
		Assert.assertNotEquals(testedUser.getUsername(), user.getUsername());
		Agent DBUser = dat.getAgent("147986");
		// Should be different from as we changed a transient one.
		Assert.assertNotEquals(user.getUsername(), DBUser.getUsername());
	}

	@Test
	public void testUpdateInfoWithPassword() {
		// It should be previously encoded if the DB is given so this may be changed.
		Agent user = dat.getAgent("147986");
		user.setPassword("confidencial");
		JasyptEncryptor encryptor = new JasyptEncryptor();
		dat.updateInfo(user);
		Agent userAfter = dat.getAgent("147986");
		// They should be the same when we introduce the password.
		Assert.assertTrue(encryptor.checkPassword("confidencial", userAfter.getPassword()));
		Assert.assertEquals(user, userAfter); // They should be the same user by the equals.

	}

	@Test
	public void testUpdateInfo() {
		Agent user = dat.getAgent("363636H");
		Assert.assertEquals("Maria MamaMia", user.getName());
		Assert.assertEquals("Entity", user.getKind());
		Assert.assertEquals("363636H", user.getUsername());
		Assert.assertEquals("asd", user.getEmail());

		user.setName("Pepa Trump");

		dat.updateInfo(user);
		Agent updatedUser = dat.getAgent("363636H");
		Assert.assertEquals("Pepa Trump", updatedUser.getName());
		Assert.assertEquals("Entity", updatedUser.getKind());
		Assert.assertEquals("363636H", updatedUser.getUsername());
		Assert.assertEquals("asd", updatedUser.getEmail());

	}

}
