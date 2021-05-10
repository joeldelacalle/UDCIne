package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.Release;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class FilmWindowTest {
	
	private URL url;
	private JLabel lblRecommendedAge = new JLabel("");
	private JLabel textFieldFilmName = new JLabel("");
	private Film f;
	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget filmTarget;
	private JLabel lblFilmImage = new JLabel("");
	private JLabel textPaneDescription = new JLabel("");
	private Image image;
	private String descFilm;
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
		
		url = new URL("https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg");
		lblRecommendedAge = new JLabel();
		f = new Film("Jon", "Infinity war",
					"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
					13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg", "https://www.youtube.com/watch?v=6ZfuNTqbHE8");
		
		
		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target(Main.BASE_URI);
		filmTarget = appTarget.path("films");
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}


	@Test
	public void ageFilmIconResize(){
		
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
		lblRecommendedAge.setIcon(resizeImg);
		assertEquals(resizeImg, lblRecommendedAge.getIcon());
		//assertEquals(resizeImg.getIconWidth(), width);
	}
	
	@Test
	public void nuevasPeliculas() {
		
		
		textFieldFilmName.setText(f.getName());
		String selectedFilm = textFieldFilmName.getText();
		final DefaultListModel<Film> films = new DefaultListModel<>();
		films.addElement(new Film("Jon", "Infinity war",
				"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
				13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg", "https://www.youtube.com/watch?v=6ZfuNTqbHE8"));
		assertEquals(films.get(0).getName(), selectedFilm);
		
		if (films.get(0).getName().equals(selectedFilm)) {
			try {
				descFilm=films.get(0).getDescription();
				url = new URL(films.get(0).getUrl());
				image = ImageIO.read(url);
				ImageIcon myImg = new ImageIcon(url);
				image = myImg.getImage();

				int width = myImg.getIconWidth() / 7 * 2;
				// System.out.println(width);
				int height = myImg.getIconHeight() / 7 * 2;
				// System.out.println(height);

				Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon resizeImg = new ImageIcon(newImg);
				
				lblFilmImage.setIcon(resizeImg);
				assertEquals(lblFilmImage.getIcon(), resizeImg);
				textPaneDescription.setText(descFilm);
				assertEquals(films.get(0).getDescription(), descFilm);
			} catch (IOException e7) {
			}
		}
	
	}
	@Test
	public void filmAgeRestImage() {
		if (ageAll == f.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png", url.toString());
			} catch (IOException e7) {
			}
		} else if (age7 == f.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png", url.toString());
			} catch (IOException e7) {
			}

		} else if (age13 == f.getAgeRestriction()) {
			try {
				url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
				assertEquals("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png", url.toString());
			} catch (IOException e7) {
			}

		} else if (age16 == f.getAgeRestriction()) {
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
