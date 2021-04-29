package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdministratorTest {
	
	private Administrator a;

	@Before
	public void setUp() {
		a = new Administrator("name", "nickname", "email", "password", 99,1);
	}
	@Test
	public void testSetGetId() {
		a.setId(0);
		assertEquals(0, a.getId());
	}
	@Test
	public void testSetGetNivel() {
		a.setNivel(0);
		assertEquals(0, a.getNivel());
	}
	@Test
	public void testToString() {
		assertEquals("Administrator [id=0" + ", nivel=1" + ", name=name" + ", nickname=nickname" + ", email=email" + ", password=password"
				+ ", phoneNumber=99" + "]", a.toString());
	}

}
