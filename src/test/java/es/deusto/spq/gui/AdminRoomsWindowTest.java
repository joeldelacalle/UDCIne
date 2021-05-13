package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
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

public class AdminRoomsWindowTest {
	private WebTarget appTarget;
	private HttpServer server;
	//private WebTarget CinemasTarget;
	private WebTarget RoomsTarget;
	//private WebTarget FilmsTarget;
	
	
	private Room room1;
	private Cinema cinema1;
	private Film filmA;
	


	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target(Main.BASE_URI);
		//FilmsTarget = appTarget.path("films");
		RoomsTarget = appTarget.path("rooms");
		//CinemasTarget = appTarget.path("cinemas");
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void addFilmtoRoom() {
		GenericType<List<Room>> genericType1 = new GenericType<List<Room>>() {};
		List<Room> rooms = RoomsTarget.request(MediaType.APPLICATION_JSON).get(genericType1);
		filmA = new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg", "https://www.youtube.com/watch?v=RLiO7pt8MbU");
		cinema1 = new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789);
		
		room1 = new Room(cinema1, filmA, "ZUBIARTE SALA 1", Calendar.getInstance().getTime(), 100);
		//System.out.println(rooms.get(2).getName());
		assertEquals(room1.getName(), rooms.get(2).getName());
		//assertEquals(room1.getFilm(), rooms.get(2).getFilm().toString()); 
		
	}

}
