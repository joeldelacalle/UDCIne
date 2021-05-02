package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.Cinema;
import es.deusto.spq.Main;
import es.deusto.spq.Release;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@Category(IntegrationTest.class)
public class ReleaseResourceTest {
	@Rule public ContiPerfRule rule = new ContiPerfRule();
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
	@PerfTest(invocations = 100, threads = 40)
	public void testgetReleases() {
		WebTarget releaseTarget = appTarget.path("release");
    	
	    List<Release> listareleases = Arrays.asList(new Release("Anthony y Joe Russo", "Cherry",
				"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
				18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg"),new Release("Ramin Bahrani", "Tigre blanco",
						"Narra el ascenso épico de Balram Halwai (Adarsh Gourav) desde una aldea pobre hasta el estrellato del mundo empresarial en la cara más moderna de La India.",
						16, "https://pics.filmaffinity.com/the_white_tiger-462037700-large.jpg"),new Release("Shaka King", "Judas y el Mesías negro",
								"Historia real que gira en torno a un delincuente y al que, tras ser detenido, el FBI le propone la absolución de sus delitos si coopera con ellos.",
								16, "https://pics.filmaffinity.com/judas_and_the_black_messiah-912646266-large.jpg"));

	    GenericType<List<Release>> genericType = new GenericType<List<Release>>() {};
	    List<Release> releases = releaseTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listareleases.get(0).getName(), releases.get(2).getName());
	    assertEquals(listareleases.get(1).getName(), releases.get(0).getName());
	    assertEquals(listareleases.get(2).getName(), releases.get(1).getName());
	}

}
