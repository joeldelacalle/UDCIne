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
import es.deusto.spq.PayPal;
import es.deusto.spq.User;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@Category(IntegrationTest.class)
public class UserResourceTest {
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
	public void testgetUsers() {
		WebTarget usersTarget = appTarget.path("users");
		WebTarget usersallTarget = usersTarget.path("allusers");
    	
	    List<User> listausers = Arrays.asList(new User("Jaime", "jaimesanta", "jaimesantamazo@hotmail.com", "jaimesanta", 435345));

	    GenericType<List<User>> genericType = new GenericType<List<User>>() {};
	    List<User> users = usersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listausers.get(0).getName(), users.get(4).getName());
	}
	
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testGetUser() {
		WebTarget usersTarget = appTarget.path("users");
		WebTarget getusersTarget = usersTarget.path("getuser").queryParam("nickname", "jaimesanta");
	    List<User> listauser = Arrays.asList(new User("jaime", "jaimesanta","jaimesantamazo@hotmail.com","jaimesanta",99));
	    	 
	    GenericType<User> genericType = new GenericType<User>() {};
		User user = getusersTarget.request(MediaType.APPLICATION_JSON).get(genericType);
			 
		assertEquals(listauser.get(0).getNickname(), user.getNickname());
	   }

}
