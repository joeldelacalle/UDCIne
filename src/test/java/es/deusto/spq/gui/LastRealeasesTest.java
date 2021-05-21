/** \file 
 * Breve descripción de es.deusto.spq.gui LastRealeasesTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import org.junit.experimental.categories.Category;
//import org.mockito.*;

import es.deusto.spq.Main;
import es.deusto.spq.Release;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.*;
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

	private JLabel lblRecommendedAge = new JLabel("");
	private JTextField textFieldReleaseName = new JTextField("");
	private JLabel lblReleaseImage = new JLabel("");
	private JLabel textPaneDescription = new JLabel("");

	private int ageRelease;
	private String urlRelease;
	private String descRelease;
	private String releaseName;
	private String trailer;

	private DefaultListModel<Release> releases;
	private GenericType<List<Release>> genericType;
	List<Release> lastReleases;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private JList<Release> listReleases;

	private int selectedUser;

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
		selectedUser = 0;
		for (Release release : lastReleases) {
			releases.addElement(release);
		}
		listReleases = new JList<Release>(releases);
		lrw.nuevosLanzamientos(listReleases, selectedUser, textFieldReleaseName, ageRelease, urlRelease, descRelease,
				releaseName, trailer);

	}
	/**
	 * Test para el tamaño de la imagen de la edad en un ultimo estreno
	 *
	 */
	@Test
	public void releaseAgeRestImage() {
		lrw.releaseAgeRestImage();
	}
}
