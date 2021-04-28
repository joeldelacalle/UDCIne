package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {
	
	private Room r;
	private Cinema c;
	private Calendar dfd;
	private Date dfd1;
	
	
	@Before
	public void SetUp() {
		c = new Cinema("name","city","address",99);
		dfd = Calendar.getInstance();
		dfd.set(Calendar.YEAR, 2020);
		dfd.set(Calendar.MONTH, Calendar.NOVEMBER);
		dfd.set(Calendar.DAY_OF_MONTH, 20);
		dfd.set(Calendar.HOUR, 12);
		dfd.set(Calendar.MINUTE, 00);
		dfd.set(Calendar.SECOND, 00);
		dfd1 = dfd.getTime();
		
		r = new Room(c,null,"name",null,100);
	}
	@Test
	public void testSetGetId() {
		r.setId(0);
		assertEquals(0, r.getId());
	}
	@Test
	public void testSetGetCinema() {
		r.setCinema(c);
		assertEquals(c, r.getCinema());
	}
	@Test
	public void testSetGetName() {
		r.setName("testName");
		assertEquals("testName", r.getName());
	}
	@Test
	public void testGetSetDate() {
		r.setDate(dfd1);
		assertEquals(dfd1, r.getDate());
	}
	public void testSetGetSeats() {
		r.setSeats(0);
		assertEquals(0, r.getSeats());
	}
	@Test
	public void testToString() {
		assertEquals("Room [id=0" + ", cinema=" + c + ", film=null" + ", name=name" + ", date=null" + ", seats=100"
				 + "]", r.toString());
	}
}
