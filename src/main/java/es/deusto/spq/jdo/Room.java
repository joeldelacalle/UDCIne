/** \file 
 * Breve descripción de es.deusto.spq.jdo Room.java. May 18, 2021
 */
package es.deusto.spq.jdo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Clase base Salas.
 *
 */
@PersistenceCapable
public class Room {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	protected Cinema cinema = null;
	protected Film film = null;
	protected String name = null;
	protected Date date = null;
	protected int seats = 100;

	/**
	 * Construir objeto Sala con sus atributos correspondientes.
	 */
	public Room(Cinema cinema, Film film, String name, Date date, int seats) {
		super();
		this.cinema = cinema;
		this.film = film;
		this.name = name;
		this.date = date;
		this.seats = seats;
	}

	/**
	 * Obtener el Id de una Sala
	 */

	public long getId() {
		return id;
	}

	/**
	 * Establecer el Id de una Sala
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el Cine de una Sala
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * Establecer el Cine de una Sala
	 */
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	/**
	 * Obtener la Pelicula de una Sala
	 */

	public Film getFilm() {
		return film;
	}

	/**
	 * Establecer la Pelicula de una Sala
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * Obtener el Nombre de una Sala
	 */

	public String getName() {
		return name;
	}

	/**
	 * Establecer el Nombre de una Sala
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtener la fecha de una Sala
	 */

	public Date getDate() {
		return date;
	}

	/**
	 * Establecer la fecha de una Sala
	 */

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Obtener los asientos de una Sala
	 */

	public int getSeats() {
		return seats;
	}

	/**
	 * Establecer los asientos de una Sala
	 */

	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * ToString de la Sala, saca un string con la estructura definida en el propio
	 * método.
	 */

	@Override
	public String toString() {
		return "Room [id=" + id + ", cinema=" + cinema + ", film=" + film + ", name=" + name + ", date=" + date
				+ ", seats=" + seats + "]";
	}

}
