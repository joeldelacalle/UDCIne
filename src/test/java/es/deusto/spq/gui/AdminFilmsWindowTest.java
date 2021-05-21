/** \file 
 * Breve descripción de es.deusto.spq.gui AdminFilmsWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.swing.*;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;

import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class AdminFilmsWindowTest {

	private JTextField txtName;
	private JTextField txtDirector;
	private JTextField txtFoto;
	private JComboBox<Integer> cbAge;
	private JTextArea txtDescription;
	private JTextField txtTrailer;
	private Film f;
	private int age;
	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget FilmsTarget;
	private List<Film> films;
	private Film filmA;
	private int size;
	private int selectedFilm;
	private DefaultListModel<Film> fList;
	private JList<Film> lista = new JList<Film>();

	private AdminFilmsWindow afw;

	@Before
	public void setUp() throws Exception {
		f = new Film("", "", "", -1, "", "");
		txtName = new JTextField("Pelicula 1");
		txtDirector = new JTextField("Director 1");
		txtFoto = new JTextField("Foto 1");
		cbAge = new JComboBox<Integer>();
		cbAge.addItem(13);
		cbAge.setSelectedItem(13);
		txtDescription = new JTextArea("Descripcion 1");
		txtTrailer = new JTextField("Trailer 1");

		server = Main.startServer();
		Client client = ClientBuilder.newClient();
		appTarget = client.target("http://localhost:8080/myapp");
		FilmsTarget = appTarget.path("films");

		GenericType<List<Film>> genericType = new GenericType<List<Film>>() {
		};
		films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		size = films.size();

		filmA = new Film("Jon", "Iron Man",
				"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
				13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg",
				"https://www.youtube.com/watch?v=RLiO7pt8MbU");
		fList = new DefaultListModel<Film>();
		afw = new AdminFilmsWindow();
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void añadirPeliculaBD() {

		age = Integer.parseInt(cbAge.getSelectedItem().toString());
		f.setName(txtName.getText());
		f.setDirector(txtDirector.getText());
		f.setUrl(txtFoto.getText());
		f.setAgeRestriction(age);
		f.setDescription(txtDescription.getText());
		f.setTrailer(txtTrailer.getText());

		films.add(f);

		afw.añadirPeliculaBd(txtName, txtDirector, txtFoto, cbAge, txtDescription);
	}

	@Ignore
	@Test
	public void eliminarPeliculaBD() {
		// System.out.println(films);
		for (Film film : films) {
			fList.addElement(film);
		}
		// fList.addElement(filmA);
		System.out.println(fList);
		selectedFilm = fList.size() - 1;
		System.out.println(selectedFilm);
		lista = new JList<Film>(fList);
		System.out.println(lista.getModel().toString());
		afw.eliminarPeliculaBd(lista, selectedFilm);

	}

}
