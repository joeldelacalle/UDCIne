package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

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
	private WebTarget cinemasTarget;
	private WebTarget roomsTarget;
	//private WebTarget FilmsTarget;
	private GenericType<List<Cinema>> genericType0;
	private List<Cinema> cinemas;
	private GenericType<List<Room>> genericType1;
	private List<Room> rooms;
	
	
	private Room room1;
	private Cinema cinema1;
	private Film filmA;
	private long roomCinemaId;
	
	private final JComboBox<String> comboRoom = new JComboBox<String>();

	


	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target("http://localhost:8080/myapp");
		//FilmsTarget = appTarget.path("films");
		roomsTarget = appTarget.path("rooms");
		cinemasTarget = appTarget.path("cinemas");
		
		room1=new Room(cinema1, filmA, "ZUBIARTE SALA 1", Calendar.getInstance().getTime(), 100);
		
		genericType0 = new GenericType<List<Cinema>>() {
		};
		cinemas = cinemasTarget.request(MediaType.APPLICATION_JSON).get(genericType0);
		
		genericType1 = new GenericType<List<Room>>() {
		};
		rooms = roomsTarget.request(MediaType.APPLICATION_JSON).get(genericType1);
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void addFilmtoRoom() {
		GenericType<List<Room>> genericType1 = new GenericType<List<Room>>() {};
		List<Room> rooms = roomsTarget.request(MediaType.APPLICATION_JSON).get(genericType1);
		filmA = new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg", "https://www.youtube.com/watch?v=RLiO7pt8MbU");
		cinema1 = new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789);
		
		room1 = new Room(cinema1, filmA, "ZUBIARTE SALA 1", Calendar.getInstance().getTime(), 100);
		//System.out.println(rooms.get(2).getName());
		assertEquals(room1.getName(), "ZUBIARTE SALA 1");
	}
	@Test
	public void getCineYSalas() {
		
		//System.out.println(cinemas);
		if (cinemas.get(0).getName().equals("Cine Deusto Zubiarte")) {
			if (comboRoom.getItemCount() >= 1) {
				comboRoom.removeItemAt(2);
				comboRoom.removeItemAt(1);
				comboRoom.removeItemAt(0);
				assertEquals(comboRoom.getItemCount(), 0);
			}

			for (int i = 0; i < rooms.size(); i++) {
				roomCinemaId = rooms.get(i).getId();
				if (roomCinemaId == 1) {
					comboRoom.addItem(rooms.get(0).getName());
					comboRoom.addItem(rooms.get(1).getName());
					comboRoom.addItem(rooms.get(2).getName());
					assertEquals(comboRoom.getItemAt(0).toString(), rooms.get(0).getName());
					assertEquals(comboRoom.getItemAt(1).toString(), rooms.get(1).getName());
					assertEquals(comboRoom.getItemAt(2).toString(), rooms.get(2).getName());
				}
			}

		}
		if (cinemas.get(1).getName().equals("Cine Deusto Santander")) {
			if (comboRoom.getItemCount() >= 1) {
				comboRoom.removeItemAt(2);
				comboRoom.removeItemAt(1);
				comboRoom.removeItemAt(0);
				assertEquals(comboRoom.getItemCount(), 0);
			}

			for (int i = 0; i < rooms.size(); i++) {
				roomCinemaId = rooms.get(i).getId();
				if (roomCinemaId == 2) {
					comboRoom.addItem(rooms.get(3).getName());
					comboRoom.addItem(rooms.get(4).getName());
					comboRoom.addItem(rooms.get(5).getName());
					assertEquals(comboRoom.getItemAt(0).toString(), rooms.get(3).getName());
					assertEquals(comboRoom.getItemAt(1).toString(), rooms.get(4).getName());
					assertEquals(comboRoom.getItemAt(2).toString(), rooms.get(5).getName());
				}
			}

		}
		if (cinemas.get(2).getName().equals("Cine Deusto Santander")) {
			if (comboRoom.getItemCount() >= 1) {
				comboRoom.removeItemAt(2);
				comboRoom.removeItemAt(1);
				comboRoom.removeItemAt(0);
				assertEquals(comboRoom.getItemCount(), 0);
			}

			for (int i = 0; i < rooms.size(); i++) {
				roomCinemaId = rooms.get(i).getId();
				if (roomCinemaId == 3) {
					comboRoom.addItem(rooms.get(6).getName());
					comboRoom.addItem(rooms.get(7).getName());
					comboRoom.addItem(rooms.get(8).getName());
					assertEquals(comboRoom.getItemAt(0).toString(), rooms.get(6).getName());
					assertEquals(comboRoom.getItemAt(1).toString(), rooms.get(7).getName());
					assertEquals(comboRoom.getItemAt(2).toString(), rooms.get(8).getName());
				}
			}

		}
	}

}
