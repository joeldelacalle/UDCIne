/** \file 
 * Breve descripción de es.deusto.spq.gui FilmWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Billboard;
import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.User;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * Clase test Ventana Pelicula
 *
 */
@Category(GuiTest.class)
public class FilmWindowTest {

	private URL url;

	private Film f;
	private FilmWindow fw;
	private int selectedFilm;

	private JComboBox<String> comboBox;

	private User u;

	private List<Billboard> billList;
	private List<Film> filmList;

	private HttpServer server;
	private WebTarget appTarget;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Metodo para construir objeto Usuario y Pelicula con sus atributos
	 * correspondientes.
	 *
	 */
	@Before
	public void setUp() throws Exception {

		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target(Main.BASE_URI);

		u = new User();

		f = new Film("Jon", "Infinity war",
				"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
				13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg",
				"https://www.youtube.com/watch?v=6ZfuNTqbHE8");

		comboBox = new JComboBox<String>();
		comboBox.addItem(f.toString());
		comboBox.setSelectedItem(f.toString());

		url = new URL(f.getUrl());

		selectedFilm = 0;

		billList = new ArrayList<Billboard>();
		filmList = new ArrayList<Film>();

		fw = new FilmWindow(selectedFilm);
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
	 * Test para el tamaño de la imagen de la edad en una Pelicula
	 *
	 */
	@Test
	public void ageFilmIconResizeTest() {

		try {
			fw.ageFilmIconResize(url);
		} catch (IOException e) {
			logger.log(Level.WARNING, "ERROR", e);
		}
	}

	/**
	 * Test para nuevas Peliculas
	 *
	 */
	@Test
	public void nuevasPeliculasTest() {

		fw.nuevasPeliculas(comboBox);

	}

	/**
	 * Test para el tamaño de la imagen de la edad en una Pelicula
	 *
	 */
	@Test
	public void filmAgeRestImageTest() {

		fw.filmAgeRestImage();

	}

	/**
	 * Test para añadir nombre de usuario
	 *
	 */
	@Test
	public void SetUserNameTest() {
		fw.SetUserName(u);
	}

	/**
	 * Test para transformar Peliculas
	 *
	 */
	@Test
	public void transformFilmsTest() {
		fw.transformFilms(billList, filmList);
	}

}
