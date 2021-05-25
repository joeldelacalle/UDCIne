/** \file 
 * Breve descripción de es.deusto.spq.jdo OrderTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase test Pedido
 *
 */
public class OrderTest {

	private Order o;
	private List<Product> products;
	private List<Ticket> tickets;
	private Calendar dfd;
	private Date dfd1;
	private Ticket ticket;
	private Product product;
	private Cinema cinema;
	private Film film;

	/**
	 * Metodo para construir objeto:Ticket, Cine, Pelicula, Producto, Pedido con sus
	 * atributos correspondientes
	 *
	 */
	@Before
	public void setUp() {
		products = new ArrayList<Product>();
		tickets = new ArrayList<Ticket>();
		dfd = Calendar.getInstance();
		dfd.set(Calendar.YEAR, 2020);
		dfd.set(Calendar.MONTH, Calendar.NOVEMBER);
		dfd.set(Calendar.DAY_OF_MONTH, 20);
		dfd.set(Calendar.HOUR, 12);
		dfd.set(Calendar.MINUTE, 00);
		dfd.set(Calendar.SECOND, 00);
		dfd1 = dfd.getTime();
		cinema = new Cinema("artea", "bilbao", "landabarri", 99);
		film = new Film("john", "iron man", "el hombre de hierro", 13, null, null);
		ticket = new Ticket(cinema, film, 1, 2, 3, 4, dfd1);
		tickets.add(ticket);
		product = new Product("palomitas", "palomitas Grandes", 3, null);
		products.add(product);
		o = new Order("perro@gmail.com", dfd1, 1, tickets.toString(), products.toString(), "En caja", 1);

	}

	/**
	 * Test para obtener el id de un Pedido
	 *
	 */
	@Test
	public void testSetGetId() {
		o.setId(5);
		assertEquals(5, o.getId());
	}

	/**
	 * Test para obtener el email de un Pedido
	 *
	 */
	@Test
	public void testSetGetMail() {
		o.setMail("perrosanxe@yahoo.com");
		assertEquals("perrosanxe@yahoo.com", o.getMail());
	}

	/**
	 * Test para obtener la fecha de un Pedido
	 *
	 */
	@Test
	public void testSetGetDate() {
		o.setDate(dfd1);
		assertEquals(dfd1, o.getDate());
	}

	/**
	 * Test para obtener el precio de un Pedido
	 *
	 */
	@Test
	public void testSetGetPrice() {
		o.setPrice(7);
		assertEquals(7, o.getPrice());
	}

	/**
	 * Test para obtener los tickets de un Pedido
	 *
	 */
	@Test
	public void testSetGetTickets() {
		o.setTickets(tickets.toString());
		assertEquals(tickets.toString(), o.getTickets().toString());
	}

	/**
	 * Test para obtener los productos de un Pedido
	 *
	 */
	@Test
	public void testSetGetProducts() {
		o.setProducts(products.toString());
		assertEquals(products.toString(), o.getProducts());
	}

	/**
	 * Test para obtener el metodo de pago de un Pedido
	 *
	 */
	@Test
	public void testSetGetPaymentMethod() {
		o.setPaymentMethod("paypal");
		assertEquals("paypal", o.getPaymentMethod());
	}

	/**
	 * Test para obtener el ToString del Pedido, saca un string con la estructura
	 * definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("PEDIDO CONFIRMADO" + " \r\n " + 
				"-------------------------------" + " \r\n " +
				"Id de pedido: " + "0" + " \r\n " + 
				"Fecha: " + dfd1 +  " \r\n " +
				"Tickets: " + tickets + " \r\n " +
				"Productos: "+ products + " \r\n " +
				"Método de pago: " + "En caja" + " \r\n " + 
				"Precio: " + "1", o.toString());

	}

}
