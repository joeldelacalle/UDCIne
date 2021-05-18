package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.swing.DefaultListModel;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Film;
import es.deusto.spq.Product;
import es.deusto.spq.User;

public class OrderWindowTest {

	private List<Product> products = null;
	private int numberTickets = 0;

	private Film f = new Film(null, null, null, numberTickets, null, null);
	private User u = new User();

	private Product p;

	@Before
	public void setUp() throws Exception {

		u.setNickname("hola");
		p = new Product("palomitas", "descripcion", numberTickets, "");

	}

	@Test
	public void testSetGetProducts() {
		OrderWindow ow = new OrderWindow(f);
		ow.setProducts(products);
		assertEquals(products, ow.getProducts());

	}

	@Test
	public void testSetUserName() {
		OrderWindow ow = new OrderWindow(f);
		ow.SetUserName(u);
		assertEquals(u.getNickname(), ow.getLblUserName().getText());

	}

	@Test
	public void testCrearPedido() {

		final DefaultListModel<Film> listModelShoppingCart = new DefaultListModel<Film>();
		listModelShoppingCart.addElement(f);
		String nickName = null;

		OrderWindow ow = new OrderWindow(f);

		ow.setProducts(products);

		ow.CrearPedido(listModelShoppingCart, f, nickName);

	}

}
