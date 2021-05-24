/** \file 
 * Breve descripción de es.deusto.spq.gui MainWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

//import org.apache.maven.settings.Server;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.User;
import es.deusto.spq.types.GuiTest;

/**
 * Clase test Ventana Principal
 *
 */
@Category(GuiTest.class)
public class MainWindowTest {

	private MainWindow mw;
	private User u;
	private String urlS;
	private JButton jbutton;
	private JLabel lblUserName;
	private HttpServer server;

	/**
	 * Metodo para construir la ventana main y un usuario con sus atributos
	 * correspondientes
	 *
	 */
	@Before
	public void setUp() {
		server = Main.startServer();
		mw = new MainWindow();
		u = new User();
		urlS = "https://pics.filmaffinity.com/iron_man-108960873-large.jpg";
		jbutton = new JButton();
		lblUserName = new JLabel("jaimesanta");

	}

	@After
	public void tearDown() throws Exception {
		server.stop();
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

	/**
	 * Test para iniciar la ventana ReceiptWindow desde la MainWindow
	 *
	 */
	@Test
	public void testInitReceiptWindow() {
		mw.initReceiptWindow(lblUserName);
	}

	/**
	 * Test para iniciar la ventana FilmWindow desde la MainWindow
	 *
	 */
	@Test
	public void testInitFilmWindow() {
		mw.initFilmWindow(lblUserName, 0);
	}

	/**
	 * Test para iniciar la ventana ReleasesWindow desde la MainWindow
	 *
	 */
	@Test
	public void testInitReleasesWindow() {
		mw.initLastReleasesWindow();
	}

	/**
	 * Test para iniciar la ventana RatingWindow desde la MainWindow
	 *
	 */
	@Test
	public void testInitRatingWindow() {
		mw.initRatingWindow(lblUserName);
	}

	/**
	 * Test para iniciar la ventana VipWindow desde la MainWindow
	 *
	 */
	@Test
	public void testInitVipWindow() {
		mw.initVipWindow(lblUserName);
	}
}
