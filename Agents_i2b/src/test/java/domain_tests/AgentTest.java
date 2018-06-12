package domain_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Agent;
import util.JasyptEncryptor;

/**
 * Created by Damian on 15/02/2017.
 */
public class AgentTest {

    private Agent nico;
    private Agent jorge;
    private Agent damian;
    private Agent carmen;

    @Before
    public void setUp() {
        nico = new Agent("Nicolás Rivero", "nico@nicomail.com","nico123", "usernameNico", "Person");
        jorge = new Agent("Jorge Zapatero", "jorge@jorgemail.com", "jorge123", "111111111A", "Entity");
        damian = new Agent("Damian Rubio", "damian@damianmail.com", "damian123", "222222222B", "Sensor");
        carmen = new Agent("Carmen", "c@c.com", "cccc", "181818S", "Coco", 20.48, 156.2);
    }

    @Test
    public void nameTest(){
        Assert.assertEquals("Nicolás Rivero", nico.getName());
        Assert.assertEquals("Jorge Zapatero", jorge.getName());
        Assert.assertEquals("Damian Rubio", damian.getName());

        nico.setName("Antonio A");
        Assert.assertEquals("Antonio A", nico.getName());

        jorge.setName("Pepe P");
        Assert.assertEquals("Pepe P", jorge.getName());

        damian.setName("Roberto R");
        Assert.assertEquals("Roberto R", damian.getName());
    }

    @Test
    public void emailTest(){
        nico.setEmail(damian.getEmail());
        Assert.assertEquals("damian@damianmail.com", nico.getEmail());

        jorge.setEmail("pepe@pepemail.com");
        Assert.assertEquals("pepe@pepemail.com", jorge.getEmail());

        damian.setEmail(jorge.getEmail());
        Assert.assertEquals("pepe@pepemail.com", damian.getEmail());
    }

    @Test
    public void passwordTest(){
    		JasyptEncryptor encryptor = new JasyptEncryptor();
    	
        nico.setPassword("1234");
        Assert.assertTrue(encryptor.checkPassword( "1234",nico.getPassword()));

        jorge.setPassword("abcd");
        Assert.assertTrue(encryptor.checkPassword( "abcd",jorge.getPassword()));

        damian.setPassword("yay");
        Assert.assertTrue(encryptor.checkPassword( "yay",damian.getPassword()));
    }

    @Test
    public void usernameTest(){
        Assert.assertEquals("usernameNico", nico.getUsername());
        Assert.assertEquals("111111111A", jorge.getUsername());
        Assert.assertEquals("222222222B", damian.getUsername());
        Assert.assertEquals("181818S", carmen.getUsername());
        
        carmen.setUsername("777");
        Assert.assertEquals("777", carmen.getUsername());
        
    }
    
    @Test
    public void kindTest() {
	    	Assert.assertEquals("Person", nico.getKind());
	    	Assert.assertEquals("Entity", jorge.getKind());
	    	Assert.assertEquals("Sensor", damian.getKind());
	
	    	nico.setKind("Entity");
	    	Assert.assertEquals("Entity", nico.getKind());
	    	
	    	Assert.assertEquals("Coco", carmen.getKind());
    }
    
    @Test
    public void locationTest() {
	    	Assert.assertNull(nico.getLocation());
	    	Assert.assertNull(jorge.getLocation());
	    	Assert.assertNull(damian.getLocation());
	    	
	    	Assert.assertNotNull(carmen.getLocation());
	    	Assert.assertTrue(20.48 == carmen.getLocation()[0]);
	    	Assert.assertTrue(156.2 == carmen.getLocation()[1]);
	    	
	    	nico.setLocation(new double[] {25.5, 185});
	    	Assert.assertNotNull(nico.getLocation());
	    	Assert.assertTrue(25.5 == nico.getLocation()[0]);
	    	Assert.assertTrue(185 == nico.getLocation()[1]);
    }
    
    @Test
    public void toStringTest() {
    		Assert.assertEquals("Agent [id=null, name=Nicolás Rivero, email=nico@nicomail.com, "
    				+ "username=usernameNico, kind=Person]", nico.toString());
    		Assert.assertEquals("Agent [id=null, name=Carmen, location=[20.48, 156.2], "
    				+ "email=c@c.com, username=181818S, kind=Coco]", carmen.toString());
    }
    
    @Test
    public void hashEqualityTest() {
    	Assert.assertEquals(carmen.getUsername().hashCode(), carmen.hashCode());
    	
    	nico.setUsername("181818S");
    	Assert.assertEquals(nico.getUsername().hashCode(), nico.hashCode());
    	
    	Assert.assertTrue(carmen.equals(nico));
    	Assert.assertFalse(carmen.equals(jorge));
    }
}
