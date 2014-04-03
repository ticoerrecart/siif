package ar.com.siif.utils;

import junit.framework.TestCase;

public class MathUtilsTest extends TestCase {

	public void testRound(){
		assertEquals(MathUtils.round(10.53544, 2), 10.54);
		assertEquals(MathUtils.round(10.53444, 2), 10.53);
		assertEquals(MathUtils.round(10.53544, 1), 10.5);
		assertEquals(MathUtils.round(10.53544, 0), 11.0);
		assertEquals(MathUtils.round(10.53544, -1), 10.0);
	} 
	
}
