package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	/**
	 * Clase test Usuario
	 *
	 */
	private User u;

	@Before
	/**
	 * Metodo para construir objeto Usuario con sus atributos correspondientes.
	 *
	 */
	public void setUp() {
		u = new User("name", "nickname", "email", "password", 99);
	}

	@Test
	/**
	 * Test para obtener el id de un Usuario
	 *
	 */
	public void testSetGetId() {
		u.setId(0);
		assertEquals(0, u.getId());
	}

	@Test
	/**
	 * Test para obtener el nombre de un Usuario
	 *
	 */
	public void testSetGetName() {
		u.setName("testName");
		assertEquals("testName", u.getName());
	}

	@Test
	/**
	 * Test para obtener el apodo de un Usuario
	 *
	 */
	public void testSetGetNickname() {
		u.setNickname("testNickname");
		assertEquals("testNickname", u.getNickname());
	}

	@Test
	/**
	 * Test para obtener el email de un Usuario
	 *
	 */
	public void testSetGetEmail() {
		u.setEmail("testEmail");
		assertEquals("testEmail", u.getEmail());
	}

	@Test
	/**
	 * Test para obtener el password de un Usuario
	 *
	 */
	public void testSetGetPassword() {
		u.setPassword("testPassword");
		assertEquals("testPassword", u.getPassword());
	}

	@Test
	/**
	 * Test para obtener el numero de telefono de un Usuario
	 *
	 */
	public void testSetGetPhoneNumber() {
		u.setPhoneNumber(0);
		assertEquals(0, u.getPhoneNumber());
	}

	@Test
	/**
	 * Test para obtener el ToString del Usuario, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("User [id=0" + ", name=name" + ", nickname=nickname" + ", email=email" + ", password=password"
				+ ", phoneNumber=99" + "]", u.toString());
	}

}
