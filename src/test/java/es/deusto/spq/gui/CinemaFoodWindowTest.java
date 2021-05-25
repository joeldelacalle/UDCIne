/** \file 
 * Breve descripción de es.deusto.spq.gui CinemaFoodWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.Product;
import es.deusto.spq.jdo.User;
import es.deusto.spq.types.GuiTest;

/**
 * Clase test Ventana Alimentos
 *
 */
@Category(GuiTest.class)
public class CinemaFoodWindowTest {
	private Film selectedFilm = new Film("Jon", "Infinity war",
			"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
			13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg",
			"https://www.youtube.com/watch?v=6ZfuNTqbHE8");
	private Product p = new Product("Palomitas Medianas", "500g", 4,
			"https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg");

	private String urlS = "https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg";
	private JLabel jb = new JLabel();
	private DefaultListModel<Product> listmodelAlimentos = new DefaultListModel<Product>();

	private CinemaFoodWindow cfw;
	private OrderWindow ow;
	private User u;
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private int phone;
	private ListModel<Film> listModelShoppingCart;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Metodo para crear la ventana alimentos y construir la ventana Order con sus
	 * atributos correspondientes
	 *
	 */
	@Before
	public void setUp() throws Exception {
		cfw = new CinemaFoodWindow(selectedFilm, null);
		ow = new OrderWindow(selectedFilm);
		u = new User();
		textFieldName = new JTextField("Jaime");
		textFieldNickname = new JTextField("jaimesanta");
		textFieldEmail = new JTextField("jaimesantamazo@hotmail.com");
		passwordField = new JPasswordField("1234");
		new JTextField("99");
		u.setName(textFieldName.getText());
		u.setNickname(textFieldNickname.getText());
		u.setEmail(textFieldEmail.getText());
		u.setPassword(passwordField.getText());
		u.setPhoneNumber(phone);

	}

	/**
	 * Test para añadir una imagen a la ventana alimentos
	 *
	 */
	@Test
	public void btnSetImageIcon() {
		try {
			cfw.btnSetImageIcon(urlS, jb);
		} catch (IOException e) {
			logger.log(Level.WARNING, "ERROR", e);
			// e.printStackTrace();
		}
	}

	/**
	 * Test para añadir productos a la ventana alimentos
	 *
	 */
	@Test
	public void addProducts() {
		listmodelAlimentos.addElement(p);
		cfw.addProducts(ow, listmodelAlimentos);

	}

	/**
	 * Test para comprobar si el usuario se pasa bien de ventna en ventana
	 *
	 */
	@Test
	public void comprobaruser() {
		cfw.SetUserName(u);
	}

	/**
	 * Test inicio de la ventana principal
	 *
	 */
	@Test
	public void testInitOrderWindow() {

		cfw.initOrderWindow(selectedFilm, listModelShoppingCart, listmodelAlimentos);
	}

	/**
	 * Test inicio de la ventana principal
	 *
	 */
	@Test
	public void testInitMainWindow() {
		cfw.initMainWindow();
	}

}
