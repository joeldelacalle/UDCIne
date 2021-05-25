/** \file 
 * Breve descripción de es.deusto.spq.gui PaymentWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Order;
import es.deusto.spq.jdo.Receipt;
import es.deusto.spq.jdo.User;
import es.deusto.spq.types.GuiTest;

/**
 * Clase test Ventana de Pago
 *
 */
@Category(GuiTest.class)
public class PaymentWindowTest {

	private Order o;
	private PaymentWindow pw;
	private JPasswordField passwordField;
	private JTextField textField;
	private Receipt r;
	private JTextField textFieldPhoneNumber = new JTextField();
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField password = new JPasswordField();
	private int phone;
	private User u;

	/**
	 * Metodo para construir objeto Pedido con sus atributos correspondientes y
	 * Construir la ventana de Pago con sus atributos correspondientes
	 */
	@Before
	public void setUp() {
		o = new Order("pruebasjaimedeusto@gmail.com", null, 3, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2 Entrada:3 fila:3 asiento:3",
				24);
		r = new Receipt("pruebasjaimedeusto@gmail.com",null,o,20);
		pw = new PaymentWindow(o);
		passwordField = new JPasswordField();
		textField = new JTextField();
		u = new User();
		textFieldName = new JTextField("Jaime");
		textFieldNickname = new JTextField("jaimesanta");
		textFieldEmail = new JTextField("jaimesantamazo@hotmail.com");
		password = new JPasswordField("1234");
		u.setName(textFieldName.getText());
		u.setNickname(textFieldNickname.getText());
		u.setEmail(textFieldEmail.getText());
		u.setPassword(password.getText());
		u.setPhoneNumber(phone);
		textFieldPhoneNumber = new JTextField("99");
	}

	/**
	 * Test para pagar con Paypal
	 *
	 */
	@Test
	public void testPagarPaypal() {
		textField.setText("jaimesantamazo@gmail.com");
		passwordField.setText("123");
		pw.PagarPaypal(o, textField, passwordField);
	}
	@Test
	public void comprobaruser() {
		pw.SetUserName(u);
	}

	/**
	 * Test para pagar en caja
	 *
	 */
	@Test
	public void testPagarCaja() {
		pw.PagarCaja(o);
	}
	
	/**
	 * Test para mandar Email
	 *
	 */
	@Test
	public void mandarMensajeTest() {
		pw.mandarMensaje("pruebasjaimedeusto@gmail.com", r);
	}

}
