package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.Order;
import es.deusto.spq.Product;
import es.deusto.spq.Room;
import es.deusto.spq.Ticket;
import es.deusto.spq.User;
import es.deusto.spq.jdo.CinemaResource;
import es.deusto.spq.jdo.RoomResource;
import es.deusto.spq.jdo.UserResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class OrderWindowTest {

	private WebTarget appTarget;
	private HttpServer server;
	private WebTarget cinemasTarget;
	private WebTarget roomsTarget;
	private Client c;

	private CinemaResource cr = new CinemaResource();
	private List<Cinema> cinemas = cr.getReleases();
	private JComboBox<Cinema> cbCinema;
	private RoomResource rr = new RoomResource();
	private List<Room> rooms = rr.getReleases();
	private JComboBox<Room> cbSession;
	private List<Product> products = null;
	private int numberTickets = 0;
	private JLabel lblUserName;
	private Film f = new Film(null, null, null, numberTickets, null, null);
	private User u = new User();

	private Product p;

	@Before
	public void setUp() throws Exception {
		// server = Main.startServer();
		// c = ClientBuilder.newClient();

		// appTarget = c.target("http://localhost:8080/myapp");
		// FilmsTarget = appTarget.path("films");
		// roomsTarget = appTarget.path("rooms");
		// cinemasTarget = appTarget.path("cinemas");
		lblUserName = new JLabel("");
		u.setNickname("hola");
		p = new Product("palomitas", "descripcion", numberTickets, "");

	}

	@Test
	public void testSetGetProducts() {
		OrderWindow ow = new OrderWindow(f);
		ow.setProducts(products);
		assertEquals(products, ow.getProducts());

	}

	@Test
	public void testSetUserName() {
		OrderWindow ow = new OrderWindow(f);
		ow.SetUserName(u);
		assertEquals(u.getNickname(), ow.getLblUserName().getText());

	}

	@Test
	public void testCrearPedido() {

		final DefaultListModel<Film> listModelShoppingCart = new DefaultListModel<Film>();
		listModelShoppingCart.addElement(f);
		String nickName = null;

		OrderWindow ow = new OrderWindow(f);

		ow.setProducts(products);

		ow.CrearPedido(listModelShoppingCart, f, nickName);

	}

}
