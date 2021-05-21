/** \file 
 * Breve descripción de es.deusto.spq.gui RegisterWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.User;

public class RegisterWindowTest {
	/**
	 * Clase test Ventana de Registro
	 *
	 */
	private JTextField textFieldPhoneNumber = new JTextField();
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private int phone;
	private User u;

	private RegisterWindow rw;
	/**
	 * Metodo para construir objeto Usuario con sus atributos correspondientes.
	 *	y clase test ventana de registro y crea la ventana de registro
	 */
	@Before
	public void setUp() throws Exception {
		u = new User();
		textFieldName = new JTextField("Jaime");
		textFieldNickname = new JTextField("jaimesanta");
		textFieldEmail = new JTextField("jaimesantamazo@hotmail.com");
		passwordField = new JPasswordField("1234");
		textFieldPhoneNumber = new JTextField("99");
		rw = new RegisterWindow();
		

	}
	/**
	 * Test para el registro
	 *
	 */
	@Test
	public void RegistroTest() {

		phone = Integer.parseInt(textFieldPhoneNumber.getText().toString());
		u.setName(textFieldName.getText());
		u.setNickname(textFieldNickname.getText());
		u.setEmail(textFieldEmail.getText());
		u.setPassword(passwordField.getText());
		u.setPhoneNumber(phone);

		rw.Registro(textFieldPhoneNumber, textFieldName, textFieldNickname, textFieldEmail, passwordField);

	}
}
