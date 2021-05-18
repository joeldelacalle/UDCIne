package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.swing.*;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;

import es.deusto.spq.Film;
import es.deusto.spq.Main;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class AdminFilmsWindowTest {
	
	private JTextField txtName;
	private JTextField txtDirector;
	private JTextField txtFoto;
	private JComboBox <Integer> cbAge;
	private JTextArea txtDescription;
	private JTextField txtTrailer;
	private Film f;
	private int age;
	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget FilmsTarget;
	private List<Film> films;
	int size;
	
	private AdminFilmsWindow afw;
	
	@Before
	public void setUp() throws Exception {
		f = new Film("","","",-1,"","");
		txtName = new JTextField("Pelicula 1");
		txtDirector = new JTextField("Director 1");
		txtFoto = new JTextField("Foto 1");
		cbAge = new JComboBox<Integer>();
		cbAge.addItem(0);
		cbAge.setSelectedItem(0);
		txtDescription = new JTextArea("Descripcion 1");
		txtTrailer = new JTextField("Trailer 1");
		
		server = Main.startServer();
		Client client = ClientBuilder.newClient();
		appTarget = client.target("http://localhost:8080/myapp");
		FilmsTarget = appTarget.path("films");
		
		GenericType<List<Film>> genericType = new GenericType<List<Film>>() {};
		films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		size = films.size();
		
		afw = new AdminFilmsWindow();
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}
	
	@Test
	public void AñadirPeliculaBDTest() {
		
		age = Integer.parseInt(cbAge.getSelectedItem().toString());
		f.setName(txtName.getText());
		f.setDirector(txtDirector.getText());
		f.setUrl(txtFoto.getText());
		f.setAgeRestriction(age);
		f.setDescription(txtDescription.getText());
		f.setTrailer(txtTrailer.getText());
		
		films.add(f);
		
		assertEquals("Pelicula 1", f.getName());
		assertEquals("Director 1", f.getDirector());
		assertEquals("Foto 1", f.getUrl());
		assertEquals(0, f.getAgeRestriction());
		assertEquals("Descripcion 1", f.getDescription());
		assertEquals("Trailer 1", f.getTrailer());
		assertEquals(size+1, films.size());
		
		afw.AñadirPeliculaBd(txtName, txtDirector, txtFoto, cbAge, txtDescription);
	}
	
	@Test
	public void EliminarPeliculaBDTest() {
		double selectedNumber = Math.floor(Math.random()*size);
		int selectedFilm = (int) Math.round(selectedNumber);
		if(selectedNumber+1==films.get(selectedFilm).getId()) {
			films.remove(selectedFilm);
			assertEquals(films.size(), size-1);
			
		}
	}

}
