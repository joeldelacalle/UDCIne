/** \file 
 * Breve descripción de es.deusto.spq.jdo CinemaTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase test Cine
 *
 */
public class CinemaTest {

	private Cinema c;

	/**
	 * Construir objeto Pelicula con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setUp() {
		c = new Cinema("name", "city", "address", 99);
	}

	/**
	 * Test para obtener el id de un cine
	 *
	 */
	@Test
	public void testSetGetId() {
		c.setId(5);
		assertEquals(5, c.getId());
	}

	/**
	 * Test para obtener el nombre de un cine
	 *
	 */
	@Test
	public void testSetGetName() {
		c.setName("testName");
		assertEquals("testName", c.getName());
	}

	/**
	 * Test para obtener la ciudad de un cine
	 *
	 */
	@Test
	public void testSetGetCity() {
		c.setCity("testCity");
		assertEquals("testCity", c.getCity());
	}

	/**
	 * Test para obtener la dirección de un cine
	 *
	 */
	@Test
	public void testSetGetAddress() {
		c.setAddress("testAddress");
		assertEquals("testAddress", c.getAddress());
	}

	/**
	 * Test para obtener el número de teléfono de un cine
	 *
	 */
	@Test
	public void testSetGetPhoneNumber() {
		c.setPhoneNumber(8);
		assertEquals(8, c.getPhoneNumber());
	}

	/**
	 * Test para obtener el ToString del cine, saca un string con la estructura
	 * definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("Nombre: name, ciudad:city",
				c.toString());
	}

}
