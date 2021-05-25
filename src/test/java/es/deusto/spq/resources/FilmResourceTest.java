/** \file 
 * Breve descripción de es.deusto.spq.resources FilmResourceTest.java. May 21, 2021
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

import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.Main;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase test FilmResource
 *
 */
@Category(IntegrationTest.class)
public class FilmResourceTest {

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
	 * Test para obtener Peliculas
	 *
	 */
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testgetFilms() {
		WebTarget peliculasTarget = appTarget.path("films");

		List<Film> listapelis = Arrays.asList(new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg",
				"https://www.youtube.com/watch?v=RLiO7pt8MbU"),
				new Film("Jon", "Iron Man 2",
						"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
						13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg",
						"youtube.com/watch?v=Ab_mvS68xng"),
				new Film("Jon", "Iron Man 3",
						"Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable.",
						13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg",
						"https://www.youtube.com/watch?v=6dhCPF_Jsco"));

		GenericType<List<Film>> genericType = new GenericType<List<Film>>() {
		};
		List<Film> pelis = peliculasTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		List<Film> pelis2 = new ArrayList<Film>();
		for (int i = 0; i < pelis.size(); i++) {

			if (pelis.get(i).getName().equals(listapelis.get(0).getName())) {

				pelis2.add(pelis.get(i));
				assertEquals(listapelis.get(0).getName(), pelis2.get(0).getName());
			}

		}

		assertEquals(listapelis.get(0).getAgeRestriction(), pelis.get(0).getAgeRestriction());
		assertEquals(listapelis.get(1).getAgeRestriction(), pelis.get(1).getAgeRestriction());
		assertEquals(listapelis.get(2).getAgeRestriction(), pelis.get(2).getAgeRestriction());
	}

}
