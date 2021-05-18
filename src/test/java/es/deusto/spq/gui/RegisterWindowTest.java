package es.deusto.spq.gui;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.User;

public class RegisterWindowTest {

	private JTextField textFieldPhoneNumber = new JTextField();
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private int phone;
	private User u;

	private RegisterWindow rw;

	@Before
	public void setUp() throws Exception {
		u = new User();
		textFieldName = new JTextField("Nico");
		textFieldNickname = new JTextField("nico");
		textFieldEmail = new JTextField("nico@gmail.com");
		passwordField = new JPasswordField("1234");
		textFieldPhoneNumber = new JTextField("672098761");
		rw = new RegisterWindow();

	}

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
