package es.deusto.spq.gui;

import static org.junit.Assert.*;

import javax.swing.DefaultListModel;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.mockito.*;

import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.Release;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.*;

@Category(GuiTest.class)
public class LastRealeasesTest {

	private HttpServer server;
	private WebTarget appTarget;
	WebTarget releaseTarget;
	private Film film = Mockito.mock(Film.class);
	
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target(Main.BASE_URI);
		releaseTarget = appTarget.path("release");
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void NuevosLanzamientosTest() {
		final DefaultListModel<Release> releases = new DefaultListModel<>();

		releases.addElement(new Release("Anthony y Joe Russo", "Cherry",
				"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
				18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg"));
		
		Mockito.when(film.getName()).thenReturn("Cherry");
		String nombrePeli = film.getName();
		
		assertEquals(releases.get(0).getName(), nombrePeli);
	}
}
