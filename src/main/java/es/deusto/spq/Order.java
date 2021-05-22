/** \file 
 * Breve descripción de Order es.deusto.spq Order.java. May 18, 2021
 */
package es.deusto.spq;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Clase base Pedido.
 *
 */
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

	/**
	 * Construir objeto Pedido con sus atributos correspondientes.
	 */
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

	/**
	 * Obtener el id de un Pedido
	 */
	public long getId() {
		return id;
	}

	/**
	 * Establecer el id de un Pedido
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el email de un Pedido
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Establecer el email de un Pedido
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Obtener la fecha de un Pedido
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Establecer la fecha de un Pedido
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Obtener el número de tickets de un Pedido
	 */
	public int getNumberTickets() {
		return numberTickets;
	}

	/**
	 * Establecer el número de tickets de un Pedido
	 */
	public void setNumberTickets(int numberTickets) {
		this.numberTickets = numberTickets;
	}

	/**
	 * Obtener los tickets de un Pedido
	 */
	public String getTickets() {
		return tickets;
	}

	/**
	 * Establecer los tickets de un Pedido
	 */
	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	/**
	 * Obtener los productos de un Pedido
	 */
	public String getProducts() {
		return products;
	}

	/**
	 * Establecer los productos de un Pedido
	 */
	public void setProducts(String products) {
		this.products = products;
	}

	/**
	 * Obtener el método de pago de un Pedido
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Establecer el método de pago de un Pedido
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Obtener el precio de un Pedido
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * Establecer el precio de un Pedido
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * ToString del Pedido, saca un string con la estructura definida en el propio
	 * método.
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", mail=" + mail + ", date=" + date + ", tickets=" + tickets + ", products="
				+ products + ", paymentMethod=" + paymentMethod + ", price=" + price + "]";
	}

}
