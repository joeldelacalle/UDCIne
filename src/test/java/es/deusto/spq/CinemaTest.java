package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CinemaTest {

	private Cinema c;

	@Before
	public void setUp() {
		c = new Cinema("name", "city", "address", 99);
	}

	@Test
	public void testSetGetId() {
		c.setId(5);
		assertEquals(5, c.getId());
	}

	@Test
	public void testSetGetName() {
		c.setName("testName");
		assertEquals("testName", c.getName());
	}

	@Test
	public void testSetGetCity() {
		c.setCity("testCity");
		assertEquals("testCity", c.getCity());
	}

	@Test
	public void testSetGetAddress() {
		c.setAddress("testAddress");
		assertEquals("testAddress", c.getAddress());
	}

	@Test
	public void testSetGetPhoneNumber() {
		c.setPhoneNumber(8);
		assertEquals(8, c.getPhoneNumber());
	}

	@Test
	public void testToString() {
		assertEquals("Cinema [id=1" + ", name=name" + ", city=city" + ", address=address" + ", phoneNumber=99" + "]",
				c.toString());
	}

}
