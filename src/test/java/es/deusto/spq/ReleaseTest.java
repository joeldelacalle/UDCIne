/** \file 
 * Breve descripción de es.deusto.spq ReleaseTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReleaseTest {
	/**
	 * Clase test Estreno
	 *
	 */
	private Release r;

	@Before
	/**
	 * Metodo para construir objeto Estreno con sus atributos correspondientes.
	 *
	 */
	public void setup() {
		r = new Release("director", "name", "description", 13,"url", "");
	}
	@Test
	/**
	 * Test para obtener el trailer de un estreno
	 *
	 */
	public void testSetGetTrailer() {
		r.setTrailer("trailer");
		assertEquals("trailer", r.getTrailer());
	}

	@Test
	/**
	 * Test para obtener el Id de un estreno
	 *
	 */
	public void testSetGetId() {
		r.setId(5);
		assertEquals(5, r.getId());
	}

	@Test
	/**
	 * Test para obtener el Director de un estreno
	 *
	 */
	public void testSetGetDirector() {
		r.setDirector("testDirector");
		assertEquals("testDirector", r.getDirector());
	}

	@Test
	/**
	 * Test para obtener el nombre de un estreno
	 *
	 */
	public void testSetGetName() {
		r.setName("testName");
		assertEquals("testName", r.getName());
	}

	@Test
	/**
	 * Test para obtener la descripcion de un estreno
	 *
	 */
	public void testSetGetDescription() {
		r.setDescription("testDescription");
		assertEquals("testDescription", r.getDescription());
	}

	@Test
	/**
	 * Test para obtener la restriccion de edad de un estreno
	 *
	 */
	public void testSetGetAgeRestriction() {
		r.setAgeRestriction(18);
		assertEquals(18, r.getAgeRestriction());
	}
	@Test
	/**
	 * Test para obtener la url de la imagen de un estreno
	 *
	 */
	public void testSetGetUrl() {
		r.setUrl("url");
		assertEquals("url", r.getUrl());
	}

	@Test
	/**
	 * Test para obtener el ToString de estreno, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("name " + "", r.toString());
	}

}
