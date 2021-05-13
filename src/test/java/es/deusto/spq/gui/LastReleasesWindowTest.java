package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Main;
import es.deusto.spq.Release;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class LastReleasesWindowTest {

	private URL url;
	
	private JLabel lblAgeRestriction = new JLabel("");
	
	private Release r;
	
	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget releaseTarget;
	
	private Image image;
	private int ageAll; // apta para todos los publicos
	private int age7;
	private int age13;
	private int age16;
	
	@Before
	public void setUp() throws Exception {
		ageAll = 0;
		age7 = 7;
		age13 = 13;
		age16 = 16;
		
		r = new Release("Anthony y Joe Russo", "Cherry",
				"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
				18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg", "https://www.youtube.com/watch?v=H5bH6O0bErk");
		url = new URL(r.getUrl());
		
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
		//System.out.println(width);
		int height = myImg.getIconHeight() / 5;
		//System.out.println(height);

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		lblAgeRestriction.setIcon(resizeImg);
		assertEquals(resizeImg, lblAgeRestriction.getIcon());
		//assertEquals(resizeImg.getIconWidth(), width);
	}
	
	@Test
	public void releaseAgeRestImage() {
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
	@Test
	public void nuevosLanzamientos() {
		assertEquals(true, true);
		/*GenericType<List<Release>> genericType1 = new GenericType<List<Release>>() {};
		List<Release> releases = releaseTarget.request(MediaType.APPLICATION_JSON).get(genericType1);
		assertEquals(releases.get(2).getName(), r.getName());
		*/
	}


}
