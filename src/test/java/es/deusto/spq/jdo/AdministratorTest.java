/** \file 
 * Breve descripción de es.deusto.spq.jdo AdministratorTest.java. May 20, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.jdo.Administrator;
/**
 * Clase test Administrador
 *
 */
public class AdministratorTest {
	
	private Administrator a;
	/**
	 * Metodo para construir objeto Administrador con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setUp() {
		a = new Administrator("name", "nickname", "email", "password", 99,1);
	}
	/**
	 * Test para obtener el id de un Administrador
	 *
	 */
	@Test
	public void testSetGetId() {
		a.setId(0);
		assertEquals(0, a.getId());
	}
	/**
	 * Test para obtener el nivel de un Administrador
	 *
	 */
	@Test
	public void testSetGetNivel() {
		a.setNivel(0);
		assertEquals(0, a.getNivel());
	}
	/**
	 * Test para obtener el ToString del Administrador, saca un string con la estructura definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("Administrator [id=0" + ", nivel=1" + ", name=name" + ", nickname=nickname" + ", email=email" + ", password=password"
				+ ", phoneNumber=99" + "]", a.toString());
	}

}
