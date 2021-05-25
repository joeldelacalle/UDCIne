/** \file 
 * Breve descripción de es.deusto.spq.gui CinemaLoginWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.types.GuiTest;

/**
 * Clase test Ventana Login
 *
 */
@Category(GuiTest.class)
public class CinemaLoginWindowTest {

	private JTextField txtUsername1;
	private JPasswordField txtPassword1;

	private JTextField txtUsername2;
	private JPasswordField txtPassword2;
	private JTextField txtUsername3;
	private JPasswordField txtPassword3;

	private CinemaLoginWindow lw;
	private CinemaLoginWindow lw2;
	private CinemaLoginWindow lw3;

	/**
	 * Metodo para crear la ventana Login, tener Usuarios y contraseñas
	 *
	 */
	@Before
	public void setUp() {
		txtUsername1 = new JTextField();
		txtPassword1 = new JPasswordField();

		txtUsername2 = new JTextField();
		txtPassword2 = new JPasswordField();

		txtUsername3 = new JTextField();
		txtPassword3 = new JPasswordField();

		lw = new CinemaLoginWindow();
		lw2 = new CinemaLoginWindow();
		lw3 = new CinemaLoginWindow();
	}

	/**
	 * Test Login. Añade usuario y contraseña
	 *
	 */
	@Test
	public void testLogin() {
		txtUsername1.setText("jaimesanta");
		txtPassword1.setText("jaimesanta");

		txtUsername2.setText("admin");
		txtPassword2.setText("admin");

		txtUsername3.setText("");
		txtPassword3.setText("");

		lw.Login(txtPassword1, txtUsername1);
		lw2.Login(txtPassword2, txtUsername2);
		lw3.Login(txtPassword3, txtUsername3);
	}

	/**
	 * Test main de la ventana.
	 *
	 */
	@Test
	public void testMain() {
		CinemaLoginWindow.main(new String[0]);
	}

}
