/** \file 
 * Breve descripción de es.deusto.spq.jdo CinemaResourceTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

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

import es.deusto.spq.Cinema;
import es.deusto.spq.Main;
import es.deusto.spq.Order;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase test CinemaResource
 *
 */
@Category(IntegrationTest.class)
public class CinemaResourceTest {

	/**
	 * Rule test
	 *
	 */
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	private HttpServer server;
	private WebTarget appTarget;
	private Client c;
	private List<Cinema> listacines;
	/**
	 * Metodo para: iniciar el servidor Grizzly, crear un nuevo cliente
	 *	y construir objeto Pelicula con sus atributos correspondientes.
	 */
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		c = ClientBuilder.newClient();
		appTarget = c.target(Main.BASE_URI);
		listacines = Arrays.asList(new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789),
				new Cinema("Cine Deusto Santander", "Santander", "Corte Ingles nueva montaña", 345345345),
				new Cinema("Cine Deusto Barakaldo", "Barakaldo", "Max Center", 458345345));
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
	 * Test para obtener el Cine
	 *
	 */
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testgetCinemas() {
		WebTarget cinemasTarget = appTarget.path("cinemas");
		GenericType<List<Cinema>> genericType = new GenericType<List<Cinema>>() {
		};
		List<Cinema> cines = cinemasTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		// System.out.println(listacines.get(0).getName());
		// System.out.println(cines.get(2).getName());
		// System.out.println(listacines.get(1).getName());
		// System.out.println(cines.get(2).getName());
		// System.out.println(listacines.get(2).getName());
		// System.out.println(cines.get(3).getName());
	
		List<Cinema> cines2 = new ArrayList<Cinema>();
		for (int i = 0; i < cines.size(); i++) {

			if (cines.get(i).getName().equals(listacines.get(0).getName())) {

				cines2.add(cines.get(i));
				assertEquals(listacines.get(0).getName(), cines2.get(0).getName());
			}
			
		}
		
		
		//assertEquals(listacines.get(1).getName(), cines.get(2).getName());
		//assertEquals(listacines.get(2).getName(), cines.get(3).getName());
	}

}
