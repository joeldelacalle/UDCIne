/** \file 
 * Breve descripción de es.deusto.spq.gui PaymentWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Order;
/**
 * Clase test Ventana de Pago
 *
 */
public class PaymentWindowTest {
	
	private Order o;
	private PaymentWindow pw;
	private JPasswordField passwordField;
	private JTextField textField;
	/**
	 * 	Metodo para construir objeto Pedido con sus atributos correspondientes
	 *	y Construir la ventana de Pago con sus atributos correspondientes
	 */
	@Before 
	public void setUp() {
		o = new Order("jaimesantamazo@opendeusto.es", null, 3, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2 Entrada:3 fila:3 asiento:3",
				24);
		pw = new PaymentWindow(o);
		passwordField= new JPasswordField();
		textField = new JTextField();
	}
	/**
	 * Test para pagar con Paypal
	 *
	 */
	@Test
	public void testPagarPaypal() {
		textField.setText("jaimesantamazo@hotmail.com");
		passwordField.setText("123");
		pw.PagarPaypal(o, textField, passwordField);
	}
	/**
	 * Test para pagar en caja
	 *
	 */
	@Test 
	public void testPagarCaja() {
		pw.PagarCaja(o);
	}
	
	
}
