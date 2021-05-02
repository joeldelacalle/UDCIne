package es.deusto.spq;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Order {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	private String mail;
	private Date date;
	private int numberTickets;
	private String tickets;
	private String products;
	private String paymentMethod;
	private long price = -1;

	public Order(String mail, Date date, int numberTickets, String tickets, String product, String paymentMethod,
			long price) {
		super();
		this.mail = mail;
		this.date = date;
		this.numberTickets = numberTickets;
		this.tickets = tickets;
		this.products = product;
		this.paymentMethod = paymentMethod;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberTickets() {
		return numberTickets;
	}

	public void setNumberTickets(int numberTickets) {
		this.numberTickets = numberTickets;
	}

	public String getTickets() {
		return tickets;
	}

	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", mail=" + mail + ", date=" + date + ", tickets=" + tickets + ", products="
				+ products + ", paymentMethod=" + paymentMethod + ", price=" + price + "]";
	}

}
