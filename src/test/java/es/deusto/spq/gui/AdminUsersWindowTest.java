package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
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

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void eliminarUsuarioBd() {

		for (User user : users) {
			// System.out.println(user.getName());
			uList.addElement(user);
		}
		// System.out.println(uList);
		uList.addElement(u);
		//System.out.println(uList.toString());
		selectedUser = 0;
		lista = new JList<User>(uList);
		auw.eliminarUsuarioBd(lista, selectedUser);
	}

}
