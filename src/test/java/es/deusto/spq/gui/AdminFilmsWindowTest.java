/** \file 
 * Breve descripción de es.deusto.spq.gui AdminFilmsWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.types.GuiTest;
import es.deusto.spq.util.PreparedData;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase test Ventana Administrador para Peliculas
 *
 */
@Category(GuiTest.class)
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
	private String selectedFilm;
	private DefaultListModel<Film> fList;
	private AdminFilmsWindow afw;

	/**
	 * Metodo para construir objeto Pelicula con sus atributos correspondientes.
	 *
	 */
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

		fList = new DefaultListModel<Film>();
		new PreparedData();
		afw = new AdminFilmsWindow();

		filmA = new Film("Jon", "Iron Man 8",
				"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
				13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg",
				"https://www.youtube.com/watch?v=BoohRoVA9WQ");

		age = Integer.parseInt(cbAge.getSelectedItem().toString());
		txtName.setText(filmA.getName());
		txtDirector.setText(filmA.getDirector());
		txtFoto.setText(filmA.getUrl());
		filmA.setAgeRestriction(age);
		txtDescription.setText(filmA.getDescription());
		txtTrailer.setText(filmA.getTrailer());
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
	 * Test para añadir una pelicula a la BD
	 *
	 */
	// @Ignore
	@Test
	public void añadirPeliculaBD() {

		age = Integer.parseInt(cbAge.getSelectedItem().toString());
		f.setName(txtName.getText());
		f.setDirector(txtDirector.getText());
		f.setUrl(txtFoto.getText());
		f.setAgeRestriction(age);
		f.setDescription(txtDescription.getText());
		f.setTrailer(txtTrailer.getText());

		afw.añadirPeliculaBd(txtName, txtDirector, txtFoto, cbAge, txtDescription, txtTrailer.getText());
		fList.addElement(f);
		selectedFilm = f.getName();
		afw.eliminarPeliculaBd(fList, selectedFilm);
	}
	/**
	 * Test para que la descripcion no sea mas larga de 255 
	 *
	 */

	@Test
	public void maxLengthdescTest() {
		txtDescription.setText(
				"La historia. 1​ es la ciencia que estudia los sucesos del pasado; generalmente son de la humanidad, aunque, también puede no estar centrada en el humano n. 2​. Asimismo, es una disciplina académica que narra dichos acontecimientos. Es una ciencia social debido a su clasificación y método; pero, si no se centra en el humano, puede ser considerada como una ciencia natural, especialmente en un marco de la interdisciplinariedad; de cualquier forma, forma parte del la clasificación de la ciencia que engloba las anteriores dos, es decir, una ciencia fáctica (también llamada factual).\r\n"
						+ "\r\n"
						+ "Su propósito es averiguar los hechos y procesos que ocurrieron y se desarrollaron en el pasado e interpretarlos ateniéndose a criterios de la mayor objetividad posible; aunque la posibilidad de cumplimiento de tales propósitos y el grado en que sean posibles son en sí mismos objetos de estudio de la historiología o teoría de la historia, como epistemología o conocimiento científico de la historia.[cita requerida]\r\n"
						+ "\r\n"
						+ "Se denomina historiador o historiadora a la persona encargada del estudio de la historia. Al historiador profesional se le concibe como el especialista en la disciplina académica de la historia, y al historiador no profesional se le suele denominar cronista.2​");

		afw.maxLengthdesc(txtDescription);
		

	}

}
