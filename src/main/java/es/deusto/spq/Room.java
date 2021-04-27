package es.deusto.spq;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

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

	public Room(Cinema cinema, Film film, String name, Date date, int seats) {
		super();
		this.cinema = cinema;
		this.film = film;
		this.name = name;
		this.date = date;
		this.seats = seats;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", cinema=" + cinema.name + ", film=" + film.name + ", name=" + name + ", date=" + date
				+ ", seats=" + seats + "]";
	}

}

