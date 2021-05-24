/** \file 
 * Breve descripción de es.deusto.spq.jdo FilmTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.jdo.Film;
/**
 * Clase test Pelicula
 *
 */
public class FilmTest {

	private Film f;
	/**
	 * Metodo para construir objeto Pelicula con sus atributos correspondientes
	 *
	 */
	@Before
	public void setup() {
		f = new Film("director", "name", "description", 13,"url","trailer");
	}
	/**
	 * Test para obtener el id de una pelicula
	 *
	 */
	@Test
	public void testSetGetId() {
		f.setId(5);
		assertEquals(5, f.getId());
	}
	/**
	 * Test para obtener el director de una pelicula
	 *
	 */
	@Test
	public void testSetGetDirector() {
		f.setDirector("testDirector");
		assertEquals("testDirector", f.getDirector());
	}
	/**
	 * Test para obtener el nombre de una pelicula
	 *
	 */
	@Test
	public void testSetGetName() {
		f.setName("testName");
		assertEquals("testName", f.getName());
	}
	/**
	 * Test para obtener la descripción de una pelicula
	 *
	 */
	@Test
	public void testSetGetDescription() {
		f.setDescription("testDescription");
		assertEquals("testDescription", f.getDescription());
	}
	/**
	 * Test para obtener la edad recomendada de una pelicula
	 *
	 */
	@Test
	public void testSetGetAgeRestriction() {
		f.setAgeRestriction(18);
		assertEquals(18, f.getAgeRestriction());
	}
	/**
	 * Test para obtener el url de una pelicula
	 *
	 */
	@Test
	public void testSetGetUrl() {
		f.setUrl("url");
		assertEquals("url", f.getUrl());
	}
	/**
	 * Test para obtener el trailer de una pelicula
	 *
	 */
	@Test
	public void testSetGetTrailer() {
		f.setTrailer("trailer");
		assertEquals("trailer", f.getTrailer());
	}
	/**
	 * Test para obtener el ToString de la pelicula, saca un string con la estructura definida en elpropio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("name " + "+" + "13", f.toString());
	}

}
