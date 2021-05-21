/** \file 
 * Breve descripción de es.deusto.spq FilmTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FilmTest {
	/**
	 * Clase test Pelicula
	 *
	 */
	private Film f;

	@Before
	/**
	 * Metodo para construir objeto Pelicula con sus atributos correspondientes
	 *
	 */
	public void setup() {
		f = new Film("director", "name", "description", 13,"url","trailer");
	}

	@Test
	/**
	 * Test para obtener el id de una pelicula
	 *
	 */
	public void testSetGetId() {
		f.setId(5);
		assertEquals(5, f.getId());
	}

	@Test
	/**
	 * Test para obtener el director de una pelicula
	 *
	 */
	public void testSetGetDirector() {
		f.setDirector("testDirector");
		assertEquals("testDirector", f.getDirector());
	}

	@Test
	/**
	 * Test para obtener el trailer de una pelicula
	 *
	 */
	public void testSetGetName() {
		f.setName("testName");
		assertEquals("testName", f.getName());
	}

	@Test
	/**
	 * Test para obtener la descripción de una pelicula
	 *
	 */
	public void testSetGetDescription() {
		f.setDescription("testDescription");
		assertEquals("testDescription", f.getDescription());
	}

	@Test
	/**
	 * Test para obtener la edad recomendada de una pelicula
	 *
	 */
	public void testSetGetAgeRestriction() {
		f.setAgeRestriction(18);
		assertEquals(18, f.getAgeRestriction());
	}
	@Test
	/**
	 * Test para obtener el url de una pelicula
	 *
	 */
	public void testSetGetUrl() {
		f.setUrl("url");
		assertEquals("url", f.getUrl());
	}
	@Test
	/**
	 * Test para obtener el trailer de una pelicula
	 *
	 */
	public void testSetGetTrailer() {
		f.setTrailer("trailer");
		assertEquals("trailer", f.getTrailer());
	}

	@Test
	/**
	 * Test para obtener el ToString de la pelicula, saca un string con la estructura definida en elpropio método.
	 *
	 */
	public void testToString() {
		assertEquals("name " + "+" + "13", f.toString());
	}

}
