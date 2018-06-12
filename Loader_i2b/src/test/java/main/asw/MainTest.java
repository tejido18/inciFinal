package main.asw;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class MainTest {

	String[] args;
	String argument1 = "str1";
	String argument2 = "str2";
	
	@Test (expected = IllegalArgumentException.class)
	public void mainArgsNotOk() {
		args = new String[1];
		args[0] = argument1;
		LoadAgents.main(args);
	}

	
	@SuppressWarnings("deprecation")
	@Test (expected = IllegalArgumentException.class)
	public void mainArgsOk() {
		args = new String[2];
		args[0] = argument1;
		args[1] = argument2;
		Assert.assertEquals(2, args.length);
		LoadAgents.main(args);
	}
	
}
