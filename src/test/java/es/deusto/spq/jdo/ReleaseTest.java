/** \file 
 * Breve descripción de es.deusto.spq.jdo ReleaseTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase test Estreno
 *
 */
public class ReleaseTest {

	private Release r;

	/**
	 * Metodo para construir objeto Estreno con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setup() {
		r = new Release("director", "name", "description", 13, "url", "");
	}

	/**
	 * Test para obtener el trailer de un estreno
	 *
	 */
	@Test
	public void testSetGetTrailer() {
		r.setTrailer("trailer");
		assertEquals("trailer", r.getTrailer());
	}

	/**
	 * Test para obtener el Id de un estreno
	 *
	 */
	@Test
	public void testSetGetId() {
		r.setId(5);
		assertEquals(5, r.getId());
	}

	/**
	 * Test para obtener el Director de un estreno
	 *
	 */
	@Test
	public void testSetGetDirector() {
		r.setDirector("testDirector");
		assertEquals("testDirector", r.getDirector());
	}

	/**
	 * Test para obtener el nombre de un estreno
	 *
	 */
	@Test
	public void testSetGetName() {
		r.setName("testName");
		assertEquals("testName", r.getName());
	}

	/**
	 * Test para obtener la descripcion de un estreno
	 *
	 */
	@Test
	public void testSetGetDescription() {
		r.setDescription("testDescription");
		assertEquals("testDescription", r.getDescription());
	}

	/**
	 * Test para obtener la restriccion de edad de un estreno
	 *
	 */
	@Test
	public void testSetGetAgeRestriction() {
		r.setAgeRestriction(18);
		assertEquals(18, r.getAgeRestriction());
	}

	/**
	 * Test para obtener la url de la imagen de un estreno
	 *
	 */
	@Test
	public void testSetGetUrl() {
		r.setUrl("url");
		assertEquals("url", r.getUrl());
	}

	/**
	 * Test para obtener el ToString de estreno, saca un string con la estructura
	 * definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("name " + "", r.toString());
	}

}
