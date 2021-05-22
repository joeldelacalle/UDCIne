/** \file 
 * Breve descripción de es.deusto.spq.gui MainWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.io.IOException;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.User;

/**
 * Clase test Ventana Principal
 *
 */
public class MainWindowTest {

	private MainWindow mw;
	private User u;
	private String urlS;
	private JButton jbutton;

	/**
	 * Metodo para construir la ventana main y un usuario con sus atributos
	 * correspondientes
	 *
	 */
	@Before
	public void setUp() {

		mw = new MainWindow();
		u = new User();
		urlS = "https://pics.filmaffinity.com/iron_man-108960873-large.jpg";
		jbutton = new JButton();

	}

	/**
	 * Test para añadir nombre de usuario
	 *
	 */
	@Test
	public void testSetUserName() {

		mw.SetUserName(u);
	}

	/**
	 * Test para añadir email
	 *
	 */
	@Test
	public void SetEmail() {

		mw.SetEmail(u);
	}

	/**
	 * Test para imagen a un boton
	 *
	 */
	@Test
	public void btnSetImageIcon() throws IOException {

		mw.btnSetImageIcon(urlS, jbutton);

	}
}
