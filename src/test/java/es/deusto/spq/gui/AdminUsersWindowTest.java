/** \file 
 * Breve descripción de es.deusto.spq.gui AdminUsersWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Main;
import es.deusto.spq.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase test Ventana Administrador para usuarios
 *
 */
public class AdminUsersWindowTest {

	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget UsersTarget;
	private WebTarget UsersallTarget;

	private DefaultListModel<User> uList;
	private GenericType<List<User>> genericType;
	private List<User> users;

	private JList<User> lista = new JList<User>();
	private User u;
	private int selectedUser;
	private AdminUsersWindow auw;

	/**
	 * Metodo para construir objeto Usuario con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setUp() throws Exception {

		server = Main.startServer();
		Client client = ClientBuilder.newClient();
		appTarget = client.target("http://localhost:8080/myapp");
		UsersTarget = appTarget.path("users");
		UsersallTarget = UsersTarget.path("allusers");

		uList = new DefaultListModel<User>();
		genericType = new GenericType<List<User>>() {
		};
		users = UsersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		u = new User("Jaime", "jaimesanta", "jaimesanta@hotmail.com", "jaimesanta", 435345);
		auw = new AdminUsersWindow();

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
	 * Test para eliminar Usuario de BD
	 *
	 */
	@Test
	public void eliminarUsuarioBd() {

		for (User user : users) {
			uList.addElement(user);
		}

		uList.addElement(u);

		selectedUser = 0;
		lista = new JList<User>(uList);
		auw.eliminarUsuarioBd(lista, selectedUser);
	}

	@Test
	public void testRerunAdminUsersWindow() {
		auw.rerunAdminUsersWindow();
	}

	@Test
	public void testinitAdminWindow() {
		auw.initAdminWindow();
	}

}
