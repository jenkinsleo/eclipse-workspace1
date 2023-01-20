package ex001;

import junit.framework.TestCase;

//Create a UnitCircle class which implements the Comparable interface.
//Refer to the unit test for expected behaviour.

public class UnitCircleTest extends TestCase {

	public void testCompareTo1() {
		UnitCircle c1 = new UnitCircle(1);
		UnitCircle c2 = new UnitCircle(3);
		UnitCircle c3 = new UnitCircle(3);
		
		assertEquals(true, c1.compareTo(c2) < 0);
		assertEquals(true, c2.compareTo(c1) > 0);
		assertEquals(0, c2.compareTo(c3));		
	}

	public void testCompareTo2() {
		UnitCircle c1 = new UnitCircle(50);
		UnitCircle c2 = new UnitCircle(5000);
		UnitCircle c3 = new UnitCircle(5000);
		
		assertEquals(true, c1.compareTo(c2) < 0);
		assertEquals(true, c2.compareTo(c1) > 0);
		assertEquals(0, c2.compareTo(c3));		
	}
	
	public void testToString() {
		UnitCircle c1 = new UnitCircle(1);
		UnitCircle c2 = new UnitCircle(3);
		UnitCircle c3 = new UnitCircle(8);
		UnitCircle c4 = new UnitCircle(9);
		
		assertEquals("UnitCircle[r=1]", c1.toString());		
		assertEquals("UnitCircle[r=3]", c2.toString());		
		assertEquals("UnitCircle[r=8]", c3.toString());		
		assertEquals("UnitCircle[r=9]", c4.toString());		

	}

}

