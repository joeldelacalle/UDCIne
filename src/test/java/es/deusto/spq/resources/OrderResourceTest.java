/** \file 
 * Breve descripción de es.deusto.spq.resources OrderResourceTest.java. May 21, 2021
 */
package es.deusto.spq.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.Order;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase test OrderResource
 *
 */
@Category(IntegrationTest.class)
public class OrderResourceTest {

	/**
	 * Rule test
	 *
	 */
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	private HttpServer server;
	private WebTarget appTarget;
	private Client c;

	/**
	 * Metodo para: iniciar el servidor Grizzly, crear un nuevo cliente
	 *
	 */
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		c = ClientBuilder.newClient();
		appTarget = c.target(Main.BASE_URI);
	}

	/**
	 * TearDown Test
	 *
	 */
	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	/**
	 * Test para obtener Pedidos
	 *
	 */
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testgetOrders() {
		WebTarget ordersTarget = appTarget.path("order");
		WebTarget getOrdersTarget = ordersTarget.path("getorders").queryParam("mail", "jaimesantamazo@gmail.com");
		List<Order> listOrders = Arrays.asList(new Order("jaimesantamazo@gmail.com", null, 3, "Pendiente de pago",
				"Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2 Entrada:3 fila:3 asiento:3",
				24),
				new Order("jaimesantamazo@gmail.com", null, 1, "Pendiente de pago", "Vacio",
						"pelicula:Infinity war Entrada:1 fila:1 asiento:1", 8),
				new Order("jaimesantamazo@gmail.com", null, 2, "Pendiente de pago", "Vacio",
						"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2", 16));

		GenericType<List<Order>> genericType = new GenericType<List<Order>>() {
		};
		List<Order> orders = getOrdersTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		List<Order> orders2 = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {

			if (orders.get(i).getMail().equals(listOrders.get(0).getMail())) {

				orders2.add(orders.get(i));
				assertEquals(listOrders.get(0).getMail(), orders2.get(0).getMail());
			}

		}

		assertEquals(listOrders.get(0).getMail(), orders.get(0).getMail());
		assertEquals(listOrders.get(1).getMail(), orders.get(1).getMail());
		assertEquals(listOrders.get(2).getMail(), orders.get(2).getMail());
	}

}
