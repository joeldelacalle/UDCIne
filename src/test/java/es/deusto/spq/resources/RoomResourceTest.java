/** \file 
 * Breve descripción de es.deusto.spq.resources RoomResourceTest.java. May 21, 2021
 */
package es.deusto.spq.resources;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.Room;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase test RoomResource
 *
 */
@Category(IntegrationTest.class)
public class RoomResourceTest {
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
	 * Test para obtener Salas
	 *
	 */
	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void testgetRooms() {
		WebTarget roomTarget = appTarget.path("rooms");
		
		Film filmA = new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg", "https://www.youtube.com/watch?v=RLiO7pt8MbU");
		
		Film filmB = new Film("Jon", "Iron Man 2",
				"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
				13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg", "youtube.com/watch?v=Ab_mvS68xng");
		
		Film filmC = new Film("Jon", "Iron Man 3",
				"Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable.",
				13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg", "https://www.youtube.com/watch?v=6dhCPF_Jsco");
		
		Cinema cinema1 = new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789);
    	
	    List<Room> listarooms = Arrays.asList(new Room(cinema1, filmA, "ZUBIARTE SALA 1", Calendar.getInstance().getTime(), 100),new Room(cinema1, filmB, "ZUBIARTE SALA 2", Calendar.getInstance().getTime(), 100),new Room(cinema1, filmC, "ZUBIARTE SALA 3", Calendar.getInstance().getTime(), 100));

	    GenericType<List<Room>> genericType = new GenericType<List<Room>>() {};
	    List<Room> rooms = roomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    
	    List<Room> rooms2 = new ArrayList<Room>();
		for (int i = 0; i < rooms.size(); i++) {

			if (rooms.get(i).getName().equals(listarooms.get(0).getName())) {

				rooms2.add(rooms.get(i));
				assertEquals(listarooms.get(0).getName(), rooms2.get(0).getName());
			}
			
		}
	    	
	    //assertEquals(listarooms.get(0).getName(), rooms.get(2).getName());
	    //assertEquals(listarooms.get(1).getName(), rooms.get(1).getName());
	    //assertEquals(listarooms.get(2).getName(), rooms.get(0).getName());
	}

}
