package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PayPalTest {

	private PayPal p;
	
	@Before
	public void setUp() {
		p = new PayPal("jaimesantamazo@hotmail.com", "123");
	}
	@Test
	public void testSetGetEmail() {
		p.setEmail("jaimesantamazo@hotmail.com");
		assertEquals("jaimesantamazo@hotmail.com", p.getEmail());
	}
	@Test
	public void testSetGetPassword() {
		p.setPassword("123");
		assertEquals("123", p.getPassword());
	}
	@Test
	public void testToString() {
		assertEquals("PayPal [email=jaimesantamazo@hotmail.com" + ", password=123"+ "]", p.toString());
	}

}
