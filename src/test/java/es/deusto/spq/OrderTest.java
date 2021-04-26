package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
		film = new Film("john", "iron man", "el hombre de hierro", 13, null);
		ticket = new Ticket(cinema, film, 1, 2, 3, 4, dfd1);
		tickets.add(ticket);
		product = new Product("palomitas", "palomitas Grandes", 3);
		products.add(product);
		o = new Order("perro@gmail.com", dfd1, tickets, products, "En caja", 1);

	}

	@Test
	public void testSetGetMail() {
		o.setMail("perrosanxe@yahoo.com");
		assertEquals("perrosanxe@yahoo.com", o.getMail());
	}

	@Test
	public void testSetGetDate() {
		o.setDate(dfd1);
		assertEquals(dfd1, o.getDate());
	}

	@Test
	public void testSetGetPrice() {
		o.setPrice(7);
		assertEquals(7, o.getPrice());
	}

	@Test
	public void testSetGetTickets() {
		o.setTickets(tickets);
		assertEquals(tickets, o.getTickets());
	}

	@Test
	public void testSetGetProducts() {
		o.setProducts(products);
		assertEquals(products, o.getProducts());
	}

	@Test
	public void testSetGetPaymentMethod() {
		o.setPaymentMethod("paypal");
		assertEquals("paypal", o.getPaymentMethod());
	}

	@Test 
	public void testToString() {
		assertEquals("Order [id=0" + ", mail=perro@gmail.com" + ", date=" + dfd1 + ", tickets=" + tickets + ", products="
				+ products + ", paymentMethod=En caja"  + ", price=1" + "]", o.toString());
	}
	
}
