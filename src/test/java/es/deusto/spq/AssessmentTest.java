/** \file 
 * Breve descripción de es.deusto.spq AssesmentTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssessmentTest {
	/**
	 * Clase test Assessment
	 *	
	 */
	private Assessment a;

	@Before
	/**
	 * Metodo para construir objeto Valoración con sus atributos correspondientes.
	 *	
	 */
	public void setup() {
		a = new Assessment("user", "name", "text");
	}

	@Test
	/**
	 * Test para obtener el id de un Valoración
	 *
	 */
	public void testSetGetId() {
		a.setId(5);
		assertEquals(5, a.getId());
	}

	@Test
	/**
	 * Test para obtener el usuario de una Valoración
	 *
	 */
	public void testSetGetUser() {
		a.setUser("testUser");
		assertEquals("testUser", a.getUser());
	}

	@Test
	/**
	 * Test para obtener el nombre de un Valoración
	 *
	 */
	public void testSetGetName() {
		a.setName("testName");
		assertEquals("testName", a.getName());
	}

	@Test
	/**
	 * Test para obtener el texto de un Valoración
	 *
	 */
	public void testSetGetText() {
		a.setText("testText");
		assertEquals("testText", a.getText());
	}

	@Test
	/**
	 * Test para obtener el ToString del Valoración, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Assessment: [id=0" + ", user=user" + ", name=name" + ", text=text" + "]", a.toString());
	}

}
