package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {
	
	private Receipt r;
	private Calendar dfd;
	private Date dfd1;
	private Cinema cinema;
	private Film film;
	private Ticket ticket;
	private Product product;
	private Order o;
	private List<Product> products;
	private List<Ticket> tickets;
	

	@Before
	public void setup() {
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
		product = new Product("palomitas", "palomitas Grandes", 3,null);
		products.add(product);
		o = new Order("perro@gmail.com", dfd1,1, tickets.toString(), products.toString(), "En caja", 1);
		r = new Receipt("perro@gmail.com",dfd1,o,1);
	}

	@Test
	public void testSetGetId() {
		r.setId(5);
		assertEquals(5, r.getId());
	}

	@Test
	public void testSetGetMail() {
		r.setMail("perro@gmail.com");
		assertEquals("perro@gmail.com", r.getMail());
	}

	@Test
	public void testSetGetDate() {
		r.setDate(dfd1);
		assertEquals(dfd1, r.getDate());
	}
	@Test
	public void testSetGetOrder() {
		r.setOrder(o);
		assertEquals(o, r.getOrder());
	}
	@Test 
	public void testToString() {
		assertEquals("Receipt [id=0" + ", mail=perro@gmail.com" + ", date=" + dfd1 + ", order=" + o + ", price=1" + "]", r.toString());
	}

}