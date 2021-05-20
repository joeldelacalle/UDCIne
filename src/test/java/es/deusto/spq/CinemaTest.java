package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CinemaTest {
	/**
	 * Clase test Cine
	 *
	 */
	private Cinema c;

	@Before
	/**
	 * Construir objeto Pelicula con sus atributos correspondientes.
	 *
	 */
	public void setUp() {
		c = new Cinema("name", "city", "address", 99);
	}

	@Test
	/**
	 * Test para obtener el id de un cine
	 *
	 */
	public void testSetGetId() {
		c.setId(5);
		assertEquals(5, c.getId());
	}

	@Test
	/**
	 * Test para obtener el nombre de un cine
	 *
	 */
	public void testSetGetName() {
		c.setName("testName");
		assertEquals("testName", c.getName());
	}

	@Test
	/**
	 * Test para obtener la ciudad de un cine
	 *
	 */
	public void testSetGetCity() {
		c.setCity("testCity");
		assertEquals("testCity", c.getCity());
	}

	@Test
	/**
	 * Test para obtener la dirección de un cine
	 *
	 */
	public void testSetGetAddress() {
		c.setAddress("testAddress");
		assertEquals("testAddress", c.getAddress());
	}

	@Test
	/**
	 * Test para obtener el número de teléfono de un cine
	 *
	 */
	public void testSetGetPhoneNumber() {
		c.setPhoneNumber(8);
		assertEquals(8, c.getPhoneNumber());
	}

	@Test
	/**
	 * Test para obtener el ToString del cine, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Cinema [id=0" + ", name=name" + ", city=city" + ", address=address" + ", phoneNumber=99" + "]",
				c.toString());
	}

}
