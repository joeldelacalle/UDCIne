/** \file 
 * Breve descripción de es.deusto.spq RoomTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {
	/**
	 * Clase test Sala
	 *
	 */
	private Room r;
	private Cinema c;
	private Film f;
	private Calendar dfd;
	private Date dfd1;
	
	
	@Before
	/**
	 * Metodo para Construir objeto Pelicula y Cine con sus atributos correspondientes.
	 *
	 */
	public void SetUp() {
		c = new Cinema("name","city","address",99);
		f = new Film("director", "name", "description", 13,"url","");
		dfd = Calendar.getInstance();
		dfd.set(Calendar.YEAR, 2020);
		dfd.set(Calendar.MONTH, Calendar.NOVEMBER);
		dfd.set(Calendar.DAY_OF_MONTH, 20);
		dfd.set(Calendar.HOUR, 12);
		dfd.set(Calendar.MINUTE, 00);
		dfd.set(Calendar.SECOND, 00);
		dfd1 = dfd.getTime();
		
		r = new Room(c,f,"name",null,100);
	}
	@Test
	/**
	 * Test para obtener el Id de una Sala
	 *
	 */
	public void testSetGetId() {
		r.setId(0);
		assertEquals(0, r.getId());
	}
	@Test
	/**
	 * Test para obtener el cine de una Sala
	 *
	 */
	public void testSetGetCinema() {
		r.setCinema(c);
		assertEquals(c, r.getCinema());
	}
	@Test
	/**
	 * Test para obtener la pelicula de una Sala
	 *
	 */
	public void testSetGetFilm() {
		r.setFilm(f);
		assertEquals(f, r.getFilm());
	}
	@Test
	/**
	 * Test para obtener el Nombre de una Sala
	 *
	 */
	public void testSetGetName() {
		r.setName("testName");
		assertEquals("testName", r.getName());
	}
	@Test
	/**
	 * Test para obtener la Fecha de una Sala
	 *
	 */
	public void testGetSetDate() {
		r.setDate(dfd1);
		assertEquals(dfd1, r.getDate());
	}
	/**
	 * Test para obtener los asientos de una Sala
	 *
	 */
	public void testSetGetSeats() {
		r.setSeats(0);
		assertEquals(0, r.getSeats());
	}
	@Test
	/**
	 * Test para obtener el ToString de la Sala, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Room [id=0" + ", cinema=" + c + ", film="+ f + ", name=name" + ", date=null" + ", seats=100"
				 + "]", r.toString());
	}
}
