package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order o;
	private Product[] products;
	private Ticket[] tickets;
	private Calendar dfd;
	private Date dfd1;

	@Before
	public void setUp() {
		o = new Order("perro@gmail.com",null, tickets, products,"En caja", 1);
		dfd = Calendar.getInstance();
		dfd.set(Calendar.YEAR, 2020);
		dfd.set(Calendar.MONTH, Calendar.NOVEMBER);
		dfd.set(Calendar.DAY_OF_MONTH, 20);
		dfd.set(Calendar.HOUR, 12);
		dfd.set(Calendar.MINUTE, 00);
		dfd.set(Calendar.SECOND, 00);
		dfd1 = dfd.getTime();
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
		assertEquals(7,o.getPrice());
	}

}
