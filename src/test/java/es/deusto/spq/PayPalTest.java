/** \file 
 * Breve descripción de es.deusto.spq PayPalTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Clase test Paypal
 *
 */
public class PayPalTest {

	private PayPal p;
	/**
	 * Metodo para construir objeto Paypal con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setUp() {
		p = new PayPal("jaimesantamazo@hotmail.com", "123");
	}
	/**
	 * Test para obtener el email de Paypal
	 *
	 */
	@Test
	public void testSetGetEmail() {
		p.setEmail("jaimesantamazo@hotmail.com");
		assertEquals("jaimesantamazo@hotmail.com", p.getEmail());
	}
	/**
	 * Test para obtener la contraseña de Paypal
	 *
	 */
	@Test
	public void testSetGetPassword() {
		p.setPassword("123");
		assertEquals("123", p.getPassword());
	}
	/**
	 * Test para obtener el ToString del PayPal, saca un string con la estructura definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("PayPal [email=jaimesantamazo@hotmail.com" + ", password=123"+ "]", p.toString());
	}

}
