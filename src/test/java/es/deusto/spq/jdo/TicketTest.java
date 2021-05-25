/** \file 
 * Breve descripción de es.deusto.spq.jdo TicketTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase test Ticket
 *
 */
public class TicketTest {
	private Ticket t;
	private Cinema c;
	private Film f;
	private Calendar dfd;
	private Date dfd1;

	/**
	 * Metodo para construir objeto Pelicula y Cine con sus atributos
	 * correspondientes.
	 *
	 */
	@Before
	public void Setup() {
		c = new Cinema("name", "city", "address", 99);
		f = new Film("director", "name", "description", 0, "url", "");
		dfd = Calendar.getInstance();
		dfd.set(Calendar.YEAR, 2020);
		dfd.set(Calendar.MONTH, Calendar.NOVEMBER);
		dfd.set(Calendar.DAY_OF_MONTH, 20);
		dfd.set(Calendar.HOUR, 12);
		dfd.set(Calendar.MINUTE, 00);
		dfd.set(Calendar.SECOND, 00);
		dfd1 = dfd.getTime();

		t = new Ticket(c, f, 1, 1, 1, 1, null);
	}

	/**
	 * Test para obtener el id de un Ticket
	 *
	 */
	@Test
	public void testSetGetId() {
		t.setId(0);
		assertEquals(0, t.getId());
	}

	/**
	 * Test para obtener el Cine de un Ticket
	 *
	 */
	@Test
	public void testSetGetCinema() {
		t.setCinema(c);
		assertEquals(c, t.getCinema());
	}

	/**
	 * Test para obtener la Pelicula de un Ticket
	 *
	 */
	@Test
	public void testSetGetFilm() {
		t.setFilm(f);
		assertEquals(f, t.getFilm());
	}

	/**
	 * Test para obtener la Sala de un Ticket
	 *
	 */
	@Test
	public void testSetGetRoom() {
		t.setRoom(0);
		assertEquals(0, t.getRoom());
	}

	/**
	 * Test para obtener la Fila de un Ticket
	 *
	 */
	@Test
	public void testSetGetRow() {
		t.setRow(0);
		assertEquals(0, t.getRow());
	}

	/**
	 * Test para obtener el asiento de un Ticket
	 *
	 */
	@Test
	public void testSetGetSeat() {
		t.setSeat(0);
		assertEquals(0, t.getSeat());
	}

	/**
	 * Test para obtener el precio de un Ticket
	 *
	 */
	@Test
	public void testSetGetPrice() {
		t.setPrice(0);
		assertEquals(0, t.getPrice());
	}

	/**
	 * Test para obtener la sesion de un Ticket
	 *
	 */
	@Test
	public void testGetSetSession() {
		t.setSession(dfd1);
		assertEquals(dfd1, t.getSession());
	}

	/**
	 * Test para obtener el ToString de El Ticket, saca un string con la estructura
	 * definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("Ticket [id=0" + ", cinema=" + c + ", film=" + f + ", room=" + 1 + ", row=" + 1 + ", seat=" + 1
				+ ", price=" + 1 + ", session=" + null + "]", t.toString());
	}

}
