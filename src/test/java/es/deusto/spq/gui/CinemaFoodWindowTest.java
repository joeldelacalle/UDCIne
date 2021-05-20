package es.deusto.spq.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Film;

public class CinemaFoodWindowTest {
	private Film selectedFilm = new Film("Jon", "Infinity war",
			"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
			13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg",
			"https://www.youtube.com/watch?v=6ZfuNTqbHE8");

	private String urlS = "https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg";
	private JLabel jb = new JLabel();

	private CinemaFoodWindow cfw;
	private OrderWindow ow;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Before
	public void setUp() throws Exception {
		cfw = new CinemaFoodWindow(selectedFilm);
		ow = new OrderWindow(selectedFilm);

	}

	@Test
	public void btnSetImageIcon() {
		try {
			cfw.btnSetImageIcon(urlS, jb);
		} catch (IOException e) {
			logger.log(Level.WARNING, "ERROR", e);
			// e.printStackTrace();
		}
	}

	@Test
	public void addProducts() {
		cfw.addProducts(ow);

	}

}
