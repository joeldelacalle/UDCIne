/** \file 
 * Breve descripción de es.deusto.spq.jdo AssesmentTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.jdo.Assessment;
/**
 * Clase test Assessment
 *	
 */
public class AssessmentTest {
	
	private Assessment a;
	/**
	 * Metodo para construir objeto Valoración con sus atributos correspondientes.
	 *	
	 */
	@Before
	public void setup() {
		a = new Assessment("user", "name", "text");
	}
	/**
	 * Test para obtener el id de un Valoración
	 *
	 */
	@Test
	public void testSetGetId() {
		a.setId(5);
		assertEquals(5, a.getId());
	}
	/**
	 * Test para obtener el usuario de una Valoración
	 *
	 */
	@Test
	public void testSetGetUser() {
		a.setUser("testUser");
		assertEquals("testUser", a.getUser());
	}
	/**
	 * Test para obtener el nombre de un Valoración
	 *
	 */
	@Test
	public void testSetGetName() {
		a.setName("testName");
		assertEquals("testName", a.getName());
	}
	/**
	 * Test para obtener el texto de un Valoración
	 *
	 */
	@Test
	public void testSetGetText() {
		a.setText("testText");
		assertEquals("testText", a.getText());
	}
	/**
	 * Test para obtener el ToString del Valoración, saca un string con la estructura definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("Assessment: [id=0" + ", user=user" + ", name=name" + ", text=text" + "]", a.toString());
	}

}
