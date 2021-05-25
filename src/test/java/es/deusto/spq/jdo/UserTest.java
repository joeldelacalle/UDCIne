/** \file 
 * Breve descripción de es.deusto.spq.jdo UserTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase test Usuario
 *
 */
public class UserTest {
	private User u;

	/**
	 * Metodo para construir objeto Usuario con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setUp() {
		u = new User("name", "nickname", "email", "password", 99);
	}

	/**
	 * Test para obtener el id de un Usuario
	 *
	 */
	@Test
	public void testSetGetId() {
		u.setId(0);
		assertEquals(0, u.getId());
	}

	/**
	 * Test para obtener el nombre de un Usuario
	 *
	 */
	@Test
	public void testSetGetName() {
		u.setName("testName");
		assertEquals("testName", u.getName());
	}

	/**
	 * Test para obtener el apodo de un Usuario
	 *
	 */
	@Test
	public void testSetGetNickname() {
		u.setNickname("testNickname");
		assertEquals("testNickname", u.getNickname());
	}

	/**
	 * Test para obtener el email de un Usuario
	 *
	 */
	@Test
	public void testSetGetEmail() {
		u.setEmail("testEmail");
		assertEquals("testEmail", u.getEmail());
	}

	/**
	 * Test para obtener el password de un Usuario
	 *
	 */
	@Test
	public void testSetGetPassword() {
		u.setPassword("testPassword");
		assertEquals("testPassword", u.getPassword());
	}

	/**
	 * Test para obtener el numero de telefono de un Usuario
	 *
	 */
	@Test
	public void testSetGetPhoneNumber() {
		u.setPhoneNumber(0);
		assertEquals(0, u.getPhoneNumber());
	}

	/**
	 * Test para obtener el ToString del Usuario, saca un string con la estructura
	 * definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("User: Nombre = " + "name" + "| Username = " + "nickname" + "| Email = " + "email" + "| Teléfono = " + "99", u.toString());
		
	}

}
