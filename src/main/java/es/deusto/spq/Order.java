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
	private List<Ticket> tickets;
	private List<Product> products;
	private String paymentMethod;
	private long price = -1;

	public Order(String mail, Date date, List<Ticket> tickets, List<Product> product, String paymentMethod,
			long price) {
		super();
		this.mail = mail;
		this.date = date;
		this.tickets = tickets;
		this.products = product;
		this.paymentMethod = paymentMethod;
		this.price = price;
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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
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
