/** \file 
 * Breve descripción de es.deusto.spq TicketTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TicketTest {
	/**
	 * Clase test Ticket
	 *
	 */
	private Ticket t;
	private Cinema c;
	private Film f;
	private Calendar dfd;
	private Date dfd1;

	@Before
	/**
	 * Metodo para construir objeto Pelicula y Cine con sus atributos correspondientes.
	 *
	 */
	public void Setup() {
		c = new Cinema("name", "city", "address", 99);
		f = new Film("director", "name", "description", 0,"url","");
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

	@Test
	/**
	 * Test para obtener el id de un Ticket
	 *
	 */
	public void testSetGetId() {
		t.setId(0);
		assertEquals(0, t.getId());
	}

	@Test
	/**
	 * Test para obtener el Cine de un Ticket
	 *
	 */
	public void testSetGetCinema() {
		t.setCinema(c);
		assertEquals(c, t.getCinema());
	}
	@Test
	/**
	 * Test para obtener la Pelicula de un Ticket
	 *
	 */
	public void testSetGetFilm() {
		t.setFilm(f);
		assertEquals(f, t.getFilm());
	}

	@Test
	/**
	 * Test para obtener la Sala de un Ticket
	 *
	 */
	public void testSetGetRoom() {
		t.setRoom(0);
		assertEquals(0, t.getRoom());
	}

	@Test
	/**
	 * Test para obtener la Fila de un Ticket
	 *
	 */
	public void testSetGetRow() {
		t.setRow(0);
		assertEquals(0, t.getRow());
	}

	@Test
	/**
	 * Test para obtener el asiento de un Ticket
	 *
	 */
	public void testSetGetSeat() {
		t.setSeat(0);
		assertEquals(0, t.getSeat());
	}

	@Test
	/**
	 * Test para obtener el precio de un Ticket
	 *
	 */
	public void testSetGetPrice() {
		t.setPrice(0);
		assertEquals(0, t.getPrice());
	}

	@Test
	/**
	 * Test para obtener la sesion de un Ticket
	 *
	 */
	public void testGetSetSession() {
		t.setSession(dfd1);
		assertEquals(dfd1, t.getSession());
	}

	@Test
	/**
	 * Test para obtener el ToString de El Ticket, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Ticket [id=0" + ", cinema=" + c + ", film="+ f + ", room=" + 1 + ", row=" + 1 + ", seat=" + 1
				+ ", price=" + 1 + ", session=" + null + "]", t.toString());
	}

}
