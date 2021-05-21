/** \file 
 * Breve descripción de es.deusto.spq PayPalTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PayPalTest {
	/**
	 * Clase test Paypal
	 *
	 */
	private PayPal p;
	
	@Before
	/**
	 * Metodo para construir objeto Paypal con sus atributos correspondientes.
	 *
	 */
	public void setUp() {
		p = new PayPal("jaimesantamazo@hotmail.com", "123");
	}
	@Test
	/**
	 * Test para obtener el email de Paypal
	 *
	 */
	public void testSetGetEmail() {
		p.setEmail("jaimesantamazo@hotmail.com");
		assertEquals("jaimesantamazo@hotmail.com", p.getEmail());
	}
	@Test
	/**
	 * Test para obtener la contraseña de Paypal
	 *
	 */
	public void testSetGetPassword() {
		p.setPassword("123");
		assertEquals("123", p.getPassword());
	}
	@Test
	/**
	 * Test para obtener el ToString del PayPal, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("PayPal [email=jaimesantamazo@hotmail.com" + ", password=123"+ "]", p.toString());
	}

}
