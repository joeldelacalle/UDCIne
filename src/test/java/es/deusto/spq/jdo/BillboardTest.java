/** \file 
 * Breve descripción de es.deusto.spq.jdo BillboardTest.java. May 20, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.jdo.Billboard;
import es.deusto.spq.jdo.Film;
/**
 * Clase test Cartelera
 *
 */
public class BillboardTest {

	
	private Billboard f;
	private Film f2;
	/**
	 * Metodo para construir objeto Cartelera vacio y con sus atributos
	 * correspondientes
	 *
	 */
	@Before
	public void setup() {
		f = new Billboard("", "", "", 13, "", "");
		f2 = new Film("director", "name", "description", 13, "url", "trailer");
		f = new Billboard();
	}
	/**
	 * Test para obtener el id de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetId() {
		f.setId(5);
		assertEquals(5, f.getId());
	}
	/**
	 * Test para obtener el director de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetDirector() {
		f.setDirector("testDirector");
		assertEquals("testDirector", f.getDirector());
	}
	/**
	 * Test para obtener el trailer de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetName() {
		f.setName("testName");
		assertEquals("testName", f.getName());
	}
	/**
	 * Test para obtener la descripción de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetDescription() {
		f.setDescription("testDescription");
		assertEquals("testDescription", f.getDescription());
	}
	/**
	 * Test para obtener la edad recomendada de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetAgeRestriction() {
		f.setAgeRestriction(18);
		assertEquals(18, f.getAgeRestriction());
	}
	/**
	 * Test para obtener el url de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetUrl() {
		f.setUrl("url");
		assertEquals("url", f.getUrl());
	}
	/**
	 * Test para obtener el trailer de una pelicula de la cartelera
	 *
	 */
	@Test
	public void testSetGetTrailer() {
		f.setTrailer("trailer");
		assertEquals("trailer", f.getTrailer());
	}
	/**
	 * Test para aladir una pelicula al billboard
	 *
	 */
	@Test
	public void testSetFilmBillboard() {

		f.setFilmBillboard(f2);
		assertEquals(f2.getAgeRestriction(), f.getAgeRestriction());
		assertEquals(f2.getTrailer(), f.getTrailer());
		assertEquals(f2.getUrl(), f.getUrl());
		assertEquals(f2.getDescription(), f.getDescription());
		assertEquals(f2.getName(), f.getName());
	}

}
