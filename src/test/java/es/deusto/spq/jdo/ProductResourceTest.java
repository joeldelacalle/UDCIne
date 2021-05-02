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

import es.deusto.spq.Main;
import es.deusto.spq.Product;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@Category(IntegrationTest.class)
public class ProductResourceTest {
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
		WebTarget productTarget = appTarget.path("products");
    	
	    List<Product> listaproducts = Arrays.asList(new Product("Palomitas Medianas", "500g", 4, "https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg"),new Product("Palomitas Grandes", "1000g", 6, "https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg"),new Product("Palomitas Grandes + Coca Cola", "Unas palomitas grandes y coca cola", 8, "https://previews.123rf.com/images/imagestore/imagestore1606/imagestore160601787/58756143-palomitas-en-rect%C3%A1ngulo-con-el-color-en-la-copa-para-llevar-aislado-en-el-fondo-blanco.jpg"));

	    GenericType<List<Product>> genericType = new GenericType<List<Product>>() {};
	    List<Product> releases = productTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listaproducts.get(0).getName(), releases.get(0).getName());
	    assertEquals(listaproducts.get(1).getName(), releases.get(2).getName());
	    assertEquals(listaproducts.get(2).getName(), releases.get(1).getName());
	}
	
}
