package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

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
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@Category(IntegrationTest.class)
public class CinemaResourceTest {
	
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
	    WebTarget cinemasTarget = appTarget.path("cinemas");
	    	
	    List<Cinema> listacines = Arrays.asList(new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789),new Cinema("Cine Deusto Santander", "Santander", "Corte Ingles nueva montaña", 345345345),new Cinema("Cine Deusto Bakacaldo", "bakacaldo", "Max Center", 458345345));

	    GenericType<List<Cinema>> genericType = new GenericType<List<Cinema>>() {};
	    List<Cinema> cines = cinemasTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listacines.get(0).getName(), cines.get(0).getName());
	    assertEquals(listacines.get(1).getName(), cines.get(1).getName());
	    assertEquals(listacines.get(2).getName(), cines.get(2).getName());
	}

}
