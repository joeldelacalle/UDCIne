package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.Room;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class RoomResourceTest {
	
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
		WebTarget roomTarget = appTarget.path("rooms");
		
		Film filmA = new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg");
		Film filmB = new Film("Jon", "Iron Man 2",
				"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
				13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg");
		Film filmC = new Film("Jon", "Iron Man 3",
				"Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable.",
				13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg");
		
		Cinema cinema1 = new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789);
    	
	    List<Room> listarooms = Arrays.asList(new Room(cinema1, filmA, "ZUBIARTE SALA 1", Calendar.getInstance().getTime(), 100),new Room(cinema1, filmB, "ZUBIARTE SALA 2", Calendar.getInstance().getTime(), 100),new Room(cinema1, filmC, "ZUBIARTE SALA 3", Calendar.getInstance().getTime(), 100));

	    GenericType<List<Room>> genericType = new GenericType<List<Room>>() {};
	    List<Room> rooms = roomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    	
	    assertEquals(listarooms.get(0).getName(), rooms.get(2).getName());
	    assertEquals(listarooms.get(1).getName(), rooms.get(1).getName());
	    assertEquals(listarooms.get(2).getName(), rooms.get(0).getName());
	}

}
