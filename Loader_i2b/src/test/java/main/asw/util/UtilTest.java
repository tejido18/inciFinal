package main.asw.util;

import org.junit.Test;

import main.asw.util.Checker;

public class UtilTest {

	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEmpty() {
		Checker.isEmpty("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsNull() {
		Checker.isNull(null);
	}
	
	@Test
	public void testIsNotEmpty() {
		Checker.isEmpty("notempty");
	}
	
	@Test
	public void testIsNotNull() {
		Checker.isNull(new String());
	}
}
