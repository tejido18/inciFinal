package main.asw.agents;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import main.asw.agents.Agent;
import main.asw.location.LatLng;

public class AgentsTest {

	private LatLng oviedo;

	@Before
	public void setUp() {
		oviedo = new LatLng(43.361914, -5.849389);
	}

	@Test
	public void testCreateAgentRight() {
		assertNotNull(new Agent(1, "Agent", "agent@gmail.com", "05936542N"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongName() {
		new Agent(1, null, "agent@gmail.com", "05936542N");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentEmptyName() {
		new Agent(0, "", "agent@gmail.com", "05936542N");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentEmptyMail() {
		new Agent(0, "Agent", "", "05936542N");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongEmail() {
		new Agent(0, "Agent", null, "05936542N");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongFormatEmail() {
		new Agent(0, "Agent", "agentgmail.com", "05936542N");
		new Agent(0, "Agent", "agent@gmailcom", "05936542N");
		new Agent(0, "Agent", "@@@", "05936542N");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongId() {
		new Agent(0, "Agent", "agent@gmail.com", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongIdLetter() {
		new Agent(0, "Agent", "agent@gmail.com", "098206468");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongIdLenght() {
		new Agent(0, "Agent", "agent@gmail.com", "0982064681454a");
		new Agent(0, "Agent", "agent@gmail.com", "");
	}

	@Test
	public void testCreateAgentRightLocation() {
		assertNotNull(new Agent(0, "Agent", "agent@gmail.com", "05936542N", oviedo));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAgentWrongLocation() {
		new Agent(0, "Agent", "agentgmail.com", "05936542N", null);
	}

}
