/** \file 
 * Breve descripción de es.deusto.spq.gui OrderWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Film;
import es.deusto.spq.Order;
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
		
		
	
		
		OrderWindow ow2 = new OrderWindow(f);
		Product p1 = new Product("Palomitas Medianas", "500g", 4,
				"https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg");
		
		Product p2 = new Product("Palomitas Grandes", "1000g", 6,
				"https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg");
		
		Product p3 = new Product("Palomitas Grandes + Coca Cola", "Unas palomitas grandes y coca cola", 8,
				"https://previews.123rf.com/images/imagestore/imagestore1606/imagestore160601787/58756143-palomitas-en-rect%C3%A1ngulo-con-el-color-en-la-copa-para-llevar-aislado-en-el-fondo-blanco.jpg");
		
		List<Product> products2 = new ArrayList<Product>();
		products2.add(p1);
		products2.add(p2);
		products2.add(p3);
		
		ow2.setProducts(products2);
		
		ow2.CrearPedido(listModelShoppingCart, f , nickName);
		

	}

}
