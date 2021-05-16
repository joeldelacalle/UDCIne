package es.deusto.spq;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Receipt {
	protected long id;

	private String mail;
	
	private Date date;
	
	protected Order order = null;
	
	private long price = -1;
	
	public Receipt() {
		super();
	}

	public Receipt(String mail, Date date, Order order, long price) {
		super();
		this.mail = mail;
		this.date = date;
		this.order = order;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", mail=" + mail + ", date=" + date + ", order=" + order + ", price=" + price
				+ "]";
	}

}
