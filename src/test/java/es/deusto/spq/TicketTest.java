package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TicketTest {

	private Ticket t;
	private Cinema c;
	private Film f;
	private Calendar dfd;
	private Date dfd1;

	@Before
	public void Setup() {
		c = new Cinema("name", "city", "address", 99);
		f = new Film("director", "name", "description", 0,"url");
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
	public void testSetGetId() {
		t.setId(0);
		assertEquals(0, t.getId());
	}

	@Test
	public void testSetGetCinema() {
		t.setCinema(c);
		assertEquals(c, t.getCinema());
	}
	@Test
	public void testSetGetFilm() {
		t.setFilm(f);
		assertEquals(f, t.getFilm());
	}

	@Test
	public void testSetGetRoom() {
		t.setRoom(0);
		assertEquals(0, t.getRoom());
	}

	@Test
	public void testSetGetRow() {
		t.setRow(0);
		assertEquals(0, t.getRow());
	}

	@Test
	public void testSetGetSeat() {
		t.setSeat(0);
		assertEquals(0, t.getSeat());
	}

	@Test
	public void testSetGetPrice() {
		t.setPrice(0);
		assertEquals(0, t.getPrice());
	}

	@Test
	public void testGetSetSession() {
		t.setSession(dfd1);
		assertEquals(dfd1, t.getSession());
	}

	@Test
	public void testToString() {
		assertEquals("Ticket [id=0" + ", cinema=" + c + ", film="+ f + ", room=" + 1 + ", row=" + 1 + ", seat=" + 1
				+ ", price=" + 1 + ", session=" + null + "]", t.toString());
	}

}
