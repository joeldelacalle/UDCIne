package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;

import es.deusto.spq.Main;
import es.deusto.spq.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class RegisterWindowTest {
	
	private JTextField textFieldPhoneNumber = new JTextField();
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private int phone;
	private User u;
	
	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget UsersTarget;
	private WebTarget UsersallTarget;
	private List<User> users;
	private int size;

	
	@Before
	public void setUp() throws Exception {
		u = new User();
		textFieldName = new JTextField("Nico");
		textFieldNickname = new JTextField("nico");
		textFieldEmail = new JTextField("nico@gmail.com");
		passwordField = new JPasswordField("1234");
		textFieldPhoneNumber = new JTextField("672098761");
		
		server = Main.startServer();
		Client client = ClientBuilder.newClient();
		appTarget = client.target("http://localhost:8080/myapp");
		UsersTarget = appTarget.path("users");
		UsersallTarget = UsersTarget.path("allusers");
		
		GenericType<List<User>> genericType = new GenericType<List<User>>() {};
		users = UsersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		size = users.size();
		
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}
	
	@Test
	public void RegistroTest() {
		
		phone = Integer.parseInt(textFieldPhoneNumber.getText().toString());
		u.setName(textFieldName.getText());
		u.setNickname(textFieldNickname.getText());
		u.setEmail(textFieldEmail.getText());
		u.setPassword(passwordField.getText());
		u.setPhoneNumber(phone);
		
		users.add(u);
		
		assertEquals(u.getName(), "Nico");
		assertEquals(u.getNickname(), "nico");
		assertEquals(u.getEmail(), "nico@gmail.com");
		assertEquals(u.getPassword(), "1234");
		assertEquals(u.getPhoneNumber(), 672098761);
		assertEquals(users.size(), size+1);
		
	}
}
