/** \file 
 * Breve descripción de es.deusto.spq.gui LastRealeasesTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
//import org.mockito.*;

import es.deusto.spq.jdo.Main;
import es.deusto.spq.jdo.Release;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase test Ventana ultimos estrenos
 *
 */
@Category(GuiTest.class)
public class LastRealeasesTest {

	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget releaseTarget;

	private URL url;

	private JTextField textFieldReleaseName = new JTextField("");
	private JLabel lblReleaseImage = new JLabel("");
	private JTextPane textPaneDescription = new JTextPane();
	private JButton trailerButton = new JButton();

	private String urlRelease;
	private String trailer;

	private DefaultListModel<Release> releases;
	private GenericType<List<Release>> genericType;
	List<Release> lastReleases;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private JList<Release> listReleases;

	private int selected;

	private LastReleasesWindow lrw;

	/**
	 * Metodo para crear la ventana de ultimos estrenos
	 *
	 */
	@Before
	public void setUp() throws Exception {

		url = new URL("https://pics.filmaffinity.com/cherry-952736388-large.jpg");

		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		appTarget = c.target(Main.BASE_URI);
		releaseTarget = appTarget.path("release");

		releases = new DefaultListModel<>();

		genericType = new GenericType<List<Release>>() {
		};
		lastReleases = releaseTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		lrw = new LastReleasesWindow();
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
	 * Test para el tamaño de la imagen de la edad en un Ultimo estreno
	 *
	 */
	@Test
	public void ageReleaseIconResize() {
		try {
			lrw.ageReleaseIconResize(url);
		} catch (IOException e) {
			logger.log(Level.WARNING, "ERROR", e);
		}
	}

	/**
	 * Test para nuevos lanzamientos
	 *
	 */
	@Test
	public void nuevosLanzamientos() {
		selected = 0;
		for (Release release : lastReleases) {
			releases.addElement(release);
		}
		listReleases = new JList<Release>(releases);
		listReleases.setSelectedIndex(0);
		try {
			lrw.nuevosLanzamientos(listReleases,selected,textFieldReleaseName, lblReleaseImage, textPaneDescription,trailer,trailerButton);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test para la imagen de la edad en un ultimo estreno
	 *
	 */
	@Test
	public void releaseAgeRestImage() {
		int ageRelease;
		ageRelease = 0;
		lrw.releaseAgeRestImage(ageRelease);
		ageRelease = 7;
		lrw.releaseAgeRestImage(ageRelease);
		ageRelease = 13;
		lrw.releaseAgeRestImage(ageRelease);
		ageRelease = 16;
		lrw.releaseAgeRestImage(ageRelease);

	}

	/**
	 * Test para poner la imagen de un tamaño estandar en el label
	 */
	@Test
	public void testsetRelease() {
		urlRelease = "https://pics.filmaffinity.com/cherry-952736388-large.jpg";
		try {
			lrw.setRelease(urlRelease, lblReleaseImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test para obtener el trailer del próximo lanzamiento
	 */
	@Test
	public void getTrailerTest() {
		lrw.getTrailer();
	}
	
	/**
	 * Test para poner el trailer del próximo lanzamiento
	 */
	@Test
	public void setTrailerTest() {
		lrw.setTrailer(trailer);
	}
}
