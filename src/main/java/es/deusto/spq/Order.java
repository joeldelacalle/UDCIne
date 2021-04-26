package es.deusto.spq;

import java.util.Arrays;
import java.util.Date;

public class Order {

	private String mail;
	private Date date;
	private Ticket[] tickets;
	private Product[] product;
	private String paymentMethod;
	private long price = -1;

	public Order(String mail, Date date, Ticket[] tickets, Product[] product, String paymentMethod, long price) {
		super();
		this.mail = mail;
		this.date = date;
		this.tickets = tickets;
		this.product = product;
		this.paymentMethod= paymentMethod;
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

	public Ticket[] getTickets() {
		return tickets;
	}

	public void setTickets(Ticket[] tickets) {
		this.tickets = tickets;
	}

	public Product[] getProduct() {
		return product;
	}

	public void setProduct(Product[] product) {
		this.product = product;
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
		return "Order [mail=" + mail + ", date=" + date + ", tickets=" + Arrays.toString(tickets) + ", product="
				+ Arrays.toString(product) + ", price=" + price + "]";
	}

}
