package es.deusto.spq;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Ticket {

	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;
	
	protected Cinema cinema=null;
	protected FilmWindow film=null;
	protected int room=-1;
	protected int row=-1;
	protected int seat=-1;
	protected long price=-1;
	protected Date session=null;
	public Ticket(Cinema cinema, FilmWindow film, int room, int row, int seat, long price, Date session) {
		super();
		this.cinema = cinema;
		this.film = film;
		this.room = room;
		this.row = row;
		this.seat = seat;
		this.price = price;
		this.session = session;
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
	public FilmWindow getFilm() {
		return film;
	}
	public void setFilm(FilmWindow film) {
		this.film = film;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Date getSession() {
		return session;
	}
	public void setSession(Date session) {
		this.session = session;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", cinema=" + cinema + ", film=" + film + ", room=" + room + ", row=" + row
				+ ", seat=" + seat + ", price=" + price + ", session=" + session + "]";
	}
	
	
	
	
}
