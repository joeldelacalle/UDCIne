package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

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
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client client = ClientBuilder.newClient();
		appTarget = client.target("http://localhost:8080/myapp");
		UsersTarget = appTarget.path("users");
		UsersallTarget = UsersTarget.path("allusers");
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}
	@Test
	public void EliminarUsuarioBd() {
		GenericType<List<User>> genericType = new GenericType<List<User>>() {};
		List<User> users = UsersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		int size = users.size();
		double selectedNumber = Math.floor(Math.random()*size);
		int selectedUser = (int) Math.round(selectedNumber);
		if(selectedNumber+1==users.get(selectedUser).getId()) {
			users.remove(selectedUser);
			assertEquals(users.size(), size-1);
			
		}
		
		
		
	}

}
