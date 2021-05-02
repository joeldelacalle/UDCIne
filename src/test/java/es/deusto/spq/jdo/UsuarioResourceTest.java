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
import es.deusto.spq.Main;
import es.deusto.spq.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class UsuarioResourceTest {
	
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
		WebTarget usersTarget = appTarget.path("users");
    	
	    List<User> listausers = Arrays.asList(new User("Jaime", "jaimesanta", "jaimesantamazo@opendeusto.es", "jaimesanta", 435345),new User("Jaime", "jaimesanta", "jaimesantamazo@opendeusto.es", "jaimesanta", 435345),new User("Jaime", "jaimesanta", "jaimesantamazo@opendeusto.es", "jaimesanta", 435345));

	    GenericType<List<User>> genericType = new GenericType<List<User>>() {};
	    List<User> users = usersTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listausers.get(0).getName(), users.get(0).getName());
	    assertEquals(listausers.get(1).getName(), users.get(1).getName());
	    assertEquals(listausers.get(2).getName(), users.get(2).getName());
	}

}
