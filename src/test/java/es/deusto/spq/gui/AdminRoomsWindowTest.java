/** \file 
 * Breve descripción de es.deusto.spq.gui AdminRoomsWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.toedter.calendar.JDateChooser;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.Room;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase test Ventana Administrador para salas
 *
 */
public class AdminRoomsWindowTest {

	private WebTarget appTarget;
	private HttpServer server;
	private WebTarget cinemasTarget;
	private WebTarget roomsTarget;
	private WebTarget FilmsTarget;
	private GenericType<List<Cinema>> genericType0;
	private List<Cinema> cinemas;
	private GenericType<List<Room>> genericType1;
	private List<Room> rooms;
	private GenericType<List<Film>> genericType2;
	private List<Film> films;

	private Room room1;
	private Cinema cinema1;
	private Film filmA;
	private long roomCinemaId;

	private Date date = null;
	private final JComboBox<Film> comboFilm = new JComboBox<Film>();
	private final JComboBox<Cinema> comboCinema = new JComboBox<Cinema>();
	private final JComboBox<String> comboRoom = new JComboBox<String>();
	private JDateChooser calendar = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');

	private int selectedCinema0;
	private int selectedCinema1;
	private int selectedCinema2;

	private AdminRoomsWindow arw;
	private AdminRoomsWindow arw2;
	private AdminRoomsWindow arw3;

	/**
	 * Metodo para construir objeto Sala con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target("http://localhost:8080/myapp");
		FilmsTarget = appTarget.path("films");
		roomsTarget = appTarget.path("rooms");
		cinemasTarget = appTarget.path("cinemas");

		room1 = new Room(cinema1, filmA, "SANTANDER SALA 1", Calendar.getInstance().getTime(), 100);

		genericType0 = new GenericType<List<Cinema>>() {
		};
		cinemas = cinemasTarget.request(MediaType.APPLICATION_JSON).get(genericType0);

		genericType1 = new GenericType<List<Room>>() {
		};
		rooms = roomsTarget.request(MediaType.APPLICATION_JSON).get(genericType1);
		genericType2 = new GenericType<List<Film>>() {
		};
		films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType2);
		for (Cinema cinema : cinemas) {
			comboCinema.addItem(cinema);
		}
		for (Film film : films) {
			comboFilm.addItem(film);
		}

	}

	/**
	 * TearDown Test
	 *
	 */
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	/**
	 * Test para añadir Pelicula a Sala
	 *
	 */
	@Test
	public void addFilmtoRoom() {
		selectedCinema0 = 0;
		selectedCinema1 = 1;
		selectedCinema2 = 2;

		comboRoom.addItem(room1.getName());
		comboRoom.addItem(room1.getName());
		comboRoom.addItem(room1.getName());
		// for (int i = 3; selectedCinema0 <= i; selectedCinema0++) {
		arw = new AdminRoomsWindow();
		arw.getCineYSalas(comboCinema, comboRoom, selectedCinema0);
		arw.dispose();

		arw2 = new AdminRoomsWindow();

		arw2.getCineYSalas(comboCinema, comboRoom, selectedCinema1);
		arw2.dispose();
		arw3 = new AdminRoomsWindow();
		arw3.getCineYSalas(comboCinema, comboRoom, selectedCinema2);
		arw3.dispose();
		arw.addFilmtoRoom(comboCinema, comboFilm, comboRoom, date, selectedCinema0);

	}

	/**
	 * Test para obtener el Cine y Sala
	 *
	 */
	@Test
	public void getCineYSalas() {
		selectedCinema0 = 0;

	}
}
