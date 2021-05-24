/** \file 
 * Breve descripción de es.deusto.spq.gui RatingWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.User;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * Clase test Ventana de Valoraciones
 *
 */
@Category(GuiTest.class)
public class RatingWindowTest {

	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox;
	private DefaultListModel<String> listModelPelis;
	private DefaultListModel<String> listModelCines;

	private Film film;
	private Cinema cinema;
	private User user;

	private RatingWindow rw;
	private JLabel labeluser = new JLabel("jaimesanta");

	Client client = ClientBuilder.newClient();
	private HttpServer server;

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");
	final WebTarget CinemasTarget = appTarget.path("cinemas");

	/**
	 * Metodo para construir objetos Pelicula y usuario con sus atributos
	 * correspondientes.
	 *
	 */
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();

		textField = new JTextField("Test valoracion peli");
		listModelPelis = new DefaultListModel<String>();
		listModelPelis.addElement(textField.getText());
		textField_1 = new JTextField("Test valoracion cine");
		listModelCines = new DefaultListModel<String>();
		listModelCines.addElement(textField_1.getText());

		film = new Film("Jon", "Infinity war",
				"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
				13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg",
				"https://www.youtube.com/watch?v=6ZfuNTqbHE8");
		comboBox = new JComboBox<String>();
		comboBox.addItem(film.getName());
		cinema = new Cinema("Cine Deusto Bakacaldo", "bakacaldo", "Max Center", 458345345);
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem(cinema.getName());

		user = new User();

		rw = new RatingWindow();
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
	 * Test para añadir valoraciones a peliculas
	 *
	 */
	@Test
	public void AñadirValoracionesPeliTest() {
		rw.AñadirValoracionPeli(comboBox, listModelPelis, labeluser, textField);
	}

	/**
	 * Test para añadir valoraciones a cine
	 *
	 */
	@Test
	public void AñadirValoracionesCineTest() {
		rw.AñadirValoracionCine(comboBox_1, listModelCines, labeluser, textField_1);
	}

	/**
	 * Test para añadir nombre de usuario
	 *
	 */
	@Test
	public void SetUserNameTest() {
		rw.SetUserName(user);
	}

}
