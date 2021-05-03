package es.deusto.spq.jdo;

import static org.junit.Assert.*;

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
import es.deusto.spq.PayPal;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@Category(IntegrationTest.class)
public class PagoResourceTest {

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
    public void testPaypal() {
    	WebTarget pagosTarget = appTarget.path("paypal");
    	WebTarget paypalTarget = pagosTarget.path("getemail").queryParam("email", "jaimesantamazo@hotmail.com");
    	List<PayPal> listapaypal = Arrays.asList(new PayPal("jaimesantamazo@hotmail.com", "123"));
    	 
    	GenericType<PayPal> genericType = new GenericType<PayPal>() {};
		PayPal paypal = paypalTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		 
		assertEquals(listapaypal.get(0).getEmail(), paypal.getEmail());
    }

}