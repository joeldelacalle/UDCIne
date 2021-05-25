/** \file 
 * Breve descripción de es.deusto.spq.gui AdminWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Main;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * Clase test Ventana Administrador
 *
 */
@Category(GuiTest.class)
public class AdminWindowTest {

	AdminWindow aw;
	private HttpServer server;

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");

	/**
	 * Metodo para construir la ventana admin e iniciar el servidor
	 *
	 */
	@Before
	public void setUp() {
		aw = new AdminWindow();
		server = Main.startServer();
	}

	/**
	 * parar el servidor
	 *
	 */
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	/**
	 * Test para iniciar la ventana adminFilmsWindow desde la AdminWindow
	 *
	 */
	@Test
	public void testinitAdminFilmsWindow() {
		aw = new AdminWindow();
		aw.initAdminFilmsWindow();
	}

	/**
	 * Test para iniciar la ventana adminRoomsWindow desde la AdminWindow
	 *
	 */
	@Test
	public void testinitAdminRoomsWindow() {
		aw = new AdminWindow();
		aw.initAdminRoomsWindow();
	}

	/**
	 * Test para iniciar la ventana adminUserWindow desde la AdminWindow
	 *
	 */
	@Test
	public void testinitAdminUsersWindow() {
		aw = new AdminWindow();
		aw.initAdminUsersWindow();
	}
}
