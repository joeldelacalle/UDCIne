package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import org.junit.experimental.categories.Category;
//import org.mockito.*;

import es.deusto.spq.Main;
import es.deusto.spq.Release;
import es.deusto.spq.types.GuiTest;
import jakarta.ws.rs.client.*;

@Category(GuiTest.class)
public class LastRealeasesTest {

	private HttpServer server;
	private WebTarget appTarget;
	WebTarget releaseTarget;
	private Release r;
	private int ageAll;
	private int age7;
	private int age13;
	private int age16;
	private Image image;
	private URL url;
	private String descRelease;
	private JLabel lblRecommendedAge = new JLabel("");
	private JLabel textFieldReleaseName = new JLabel("");
	private JLabel lblReleaseImage = new JLabel("");
	private JLabel textPaneDescription = new JLabel("");
	// private Film film = Mockito.mock(Film.class);

	@Before
	public void setUp() throws Exception {
		ageAll = 0;
		age7 = 7;
		age13 = 13;
		age16 = 16;

		url = new URL("https://pics.filmaffinity.com/cherry-952736388-large.jpg");
		r = new Release("Anthony y Joe Russo", "Cherry",
				"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
				18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg",
				"https://www.youtube.com/watch?v=H5bH6O0bErk");
		lblRecommendedAge = new JLabel();

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
	public void ageReleaseIconResize() {

		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();

		int width = myImg.getIconWidth() / 5;
		// System.out.println(width);
		int height = myImg.getIconHeight() / 5;
		// System.out.println(height);

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		lblRecommendedAge.setIcon(resizeImg);
		assertEquals(resizeImg, lblRecommendedAge.getIcon());
	}

	@Test
	public void NuevosLanzamientosTest() {

		textFieldReleaseName.setText(r.getName());
		String selected = textFieldReleaseName.getText();
		final DefaultListModel<Release> releases = new DefaultListModel<>();

		releases.addElement(new Release("Anthony y Joe Russo", "Cherry",
				"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
				18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg",
				"https://www.youtube.com/watch?v=H5bH6O0bErk"));

		// Mockito.when(film.getName()).thenReturn("Cherry");
		assertEquals(releases.get(0).getName(), selected);

		if (releases.get(0).getName().equals(selected)) {
			try {
				descRelease = releases.get(0).getDescription();
				url = new URL(releases.get(0).getUrl());
				image = ImageIO.read(url);
				ImageIcon myImg = new ImageIcon(url);
				image = myImg.getImage();

				int width = myImg.getIconWidth() / 7 * 2;
				// System.out.println(width);
				int height = myImg.getIconHeight() / 7 * 2;
				// System.out.println(height);

				Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon resizeImg = new ImageIcon(newImg);

				lblReleaseImage.setIcon(resizeImg);
				assertEquals(lblReleaseImage.getIcon(), resizeImg);
				textPaneDescription.setText(descRelease);
				assertEquals(releases.get(0).getDescription(), descRelease);
			} catch (IOException e7) {
			}
		}
	}

	@Test
	public void ReleaseAgeRestImage() {
		if (ageAll == r.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png", url.toString());
			} catch (IOException e7) {
			}
		} else if (age7 == r.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png", url.toString());
			} catch (IOException e7) {
			}

		} else if (age13 == r.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png", url.toString());
			} catch (IOException e7) {
			}

		} else if (age16 == r.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png", url.toString());
			} catch (IOException e7) {
			}

		} else {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png", url.toString());
			} catch (IOException e7) {
			}
		}
	}
}
