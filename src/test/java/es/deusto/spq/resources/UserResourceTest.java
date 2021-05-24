/** \file 
 * Breve descripción de es.deusto.spq.resources UserResourceTest.java. May 21, 2021
 */
package es.deusto.spq.resources;

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

import es.deusto.spq.jdo.Cinema;
import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.PayPal;
import es.deusto.spq.jdo.User;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase test UserResource
 *
 */
@Category(IntegrationTest.class)
public class UserResourceTest {
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
    
    @SuppressWarnings("deprecation")
	@After
    public void tearDown() throws Exception {
        server.stop();
    }
    /**
	 * TearDown Test
	 *
	 */
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testgetUsers() {
		WebTarget usersTarget = appTarget.path("users");
		WebTarget usersallTarget = usersTarget.path("allusers");
    	
	    List<User> listausers = Arrays.asList(new User("Jaime", "jaimesanta", "jaimesantamazo@gmail.com", "jaimesanta", 435345));

	    GenericType<List<User>> genericType = new GenericType<List<User>>() {};
	    List<User> users = usersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listausers.get(0).getName(), users.get(0).getName());
	}
	/**
	 * Test para obtener Usuarios
	 *
	 */
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testGetUser() {
		WebTarget usersTarget = appTarget.path("users");
		WebTarget getusersTarget = usersTarget.path("getuser").queryParam("nickname", "jaimesanta");
	    List<User> listauser = Arrays.asList(new User("jaime", "jaimesanta","jaimesantamazo@gmail.com","jaimesanta",99));
	    	 
	    GenericType<User> genericType = new GenericType<User>() {};
		User user = getusersTarget.request(MediaType.APPLICATION_JSON).get(genericType);
			 
		assertEquals(listauser.get(0).getNickname(), user.getNickname());
	   }

}
