/** \file 
 * Breve descripción de es.deusto.spq Ticket.java. May 18, 2021
 */
package es.deusto.spq;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import es.deusto.spq.gui.FilmWindow;

@PersistenceCapable
public class Ticket {
	/**
	 * Clase base Ticket.
	 *
	 */

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	protected Cinema cinema = null;
	protected Film film = null;
	protected int room = -1;
	protected int row = -1;
	protected int seat = -1;
	protected long price = -1;
	protected Date session = null;

	/**
	 * Construir objeto Ticket con sus atributos correspondientes.
	 */
	public Ticket(Cinema cinema, Film film, int room, int row, int seat, long price, Date session) {
		super();
		this.cinema = cinema;
		this.film = film;
		this.room = room;
		this.row = row;
		this.seat = seat;
		this.price = price;
		this.session = session;
	}

	/**
	 * Obtener el Id de un Ticket
	 */

	public long getId() {
		return id;
	}

	/**
	 * Establecer el Id de un Ticket
	 */

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el Cine de un Ticket
	 */

	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * Establecer el Cine de un Ticket
	 */

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	/**
	 * Obtener la pelicula de un Ticket
	 */

	public Film getFilm() {
		return film;
	}

	/**
	 * Establecer la pelicula de un Ticket
	 */

	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * Obtener la Sala de un Ticket
	 */

	public int getRoom() {
		return room;
	}

	/**
	 * Establecer la Sala de un Ticket
	 */

	public void setRoom(int room) {
		this.room = room;
	}

	/**
	 * Obtener la Fila de un Ticket
	 */

	public int getRow() {
		return row;
	}

	/**
	 * Establecer la Fila de un Ticket
	 */

	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Obtener el asiento de un Ticket
	 */

	public int getSeat() {
		return seat;
	}

	/**
	 * Establecer el asiento de un Ticket
	 */

	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * Obtener el precio de un Ticket
	 */

	public long getPrice() {
		return price;
	}

	/**
	 * Establecer el precio de un Ticket
	 */

	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * Obtener la sesion de un Ticket
	 */

	public Date getSession() {
		return session;
	}

	/**
	 * Establecer la sesion de un Ticket
	 */

	public void setSession(Date session) {
		this.session = session;
	}

	/**
	 * ToString de El Ticket, saca un string con la estructura definida en el propio
	 * método.
	 */

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", cinema=" + cinema + ", film=" + film + ", room=" + room + ", row=" + row
				+ ", seat=" + seat + ", price=" + price + ", session=" + session + "]";
	}

}
