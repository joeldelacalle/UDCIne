package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Main;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class FilmResourceTest {
	
	private HttpServer server;
    private WebTarget appTarget;
    private Client c;

    
    @Before
    public void setUp() throws Exception {
    	server = Main.startServer();
        c = ClientBuilder.newClient();
        appTarget = c.target(Main.BASE_URI);
    }
    
    @SuppressWarnings("deprecation")
	@After
    public void tearDown() throws Exception {
        server.stop();
    }

	@Test
	public void test() {
		WebTarget peliculasTarget = appTarget.path("films");
    	
	    List<Film> listapelis = Arrays.asList(new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg"),new Film("Jon", "Iron Man 2",
						"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
						13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg"),new Film("Jon", "Iron Man 3",
								"Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable.",
								13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg"));

	    GenericType<List<Film>> genericType = new GenericType<List<Film>>() {};
	    List<Film> pelis = peliculasTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listapelis.get(0).getAgeRestriction(), pelis.get(0).getAgeRestriction());
	    assertEquals(listapelis.get(1).getAgeRestriction(), pelis.get(1).getAgeRestriction());
	    assertEquals(listapelis.get(2).getAgeRestriction(), pelis.get(2).getAgeRestriction());
	}

}
