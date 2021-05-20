/** \file 
 * Breve descripción de es.deusto.spq AdministratorTest.java. May 20, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdministratorTest {
	/**
	 * Clase test Administrador
	 *
	 */
	private Administrator a;
	
	@Before
	/**
	 * Metodo para construir objeto Administrador con sus atributos correspondientes.
	 *
	 */
	public void setUp() {
		a = new Administrator("name", "nickname", "email", "password", 99,1);
	}
	@Test
	/**
	 * Test para obtener el id de un Administrador
	 *
	 */
	public void testSetGetId() {
		a.setId(0);
		assertEquals(0, a.getId());
	}
	@Test
	/**
	 * Test para obtener el nivel de un Administrador
	 *
	 */
	public void testSetGetNivel() {
		a.setNivel(0);
		assertEquals(0, a.getNivel());
	}
	@Test
	/**
	 * Test para obtener el ToString del Administrador, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Administrator [id=0" + ", nivel=1" + ", name=name" + ", nickname=nickname" + ", email=email" + ", password=password"
				+ ", phoneNumber=99" + "]", a.toString());
	}

}
