/** \file 
 * Breve descripción de es.deusto.spq.gui ReceiptWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.User;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * Clase test Ventana de Recibos
 *
 */
@Category(GuiTest.class)
public class ReceiptWindowTest {

	private JTextField textFieldPhoneNumber = new JTextField();
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private int phone;
	private User u;
	private ReceiptWindow rw;
	Client client = ClientBuilder.newClient();
	private HttpServer server;

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget receiptTarget = appTarget.path("receipts");
	private JLabel labeluser;

	/**
	 * Metodo para construir objeto Usuario con sus atributos correspondientes. y la
	 * clase test ventana de recibos
	 */
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		u = new User();
		textFieldName = new JTextField("Jaime");
		textFieldNickname = new JTextField("jaimesanta");
		textFieldEmail = new JTextField("jaimesantamazo@hotmail.com");
		passwordField = new JPasswordField("1234");
		textFieldPhoneNumber = new JTextField("99");
		rw = new ReceiptWindow();
		phone = Integer.parseInt(textFieldPhoneNumber.getText().toString());
		u.setName(textFieldName.getText());
		u.setNickname(textFieldNickname.getText());
		u.setEmail(textFieldEmail.getText());
		u.setPassword(passwordField.getText());
		u.setPhoneNumber(phone);
		labeluser = new JLabel("jaimesanta");
	}

	/**
	 * Test para los recibos
	 *
	 */
	@Test
	public void ReceiptTest() {
		rw.SetUserName(u);
		rw.SetEmail(u);
	}

	/**
	 * TearDown Test
	 *
	 */
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	/**
	 * Test inicio de la ventana principal
	 *
	 */
	@Test
	public void testInitMainWindow() {
		rw.initMainWindow(labeluser);
	}
}
