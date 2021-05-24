/** \file 
 * Breve descripción de es.deusto.spq.jdo Receipt.java. May 17, 2021
 */
package es.deusto.spq.jdo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
/**
 * Clase base Recibo.
 *
 */
@PersistenceCapable
public class Receipt {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	protected String mail;

	protected Date date;

	protected Order order = null;

	protected long price = -1;

	/**
	 * Construir objeto Recibo vacío.
	 */
	public Receipt() {
		super();
	}

	/**
	 * Construir objeto Recibo con sus atributos correspondientes.
	 */

	public Receipt(String mail, Date date, Order order, long price) {
		super();
		this.mail = mail;
		this.date = date;
		this.order = order;
		this.price = price;
	}

	/**
	 * Obtener el id de un Recibo
	 */

	public long getId() {
		return id;
	}

	/**
	 * Establecer el id de un Recibo
	 */

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el mail de un Recibo
	 */

	public String getMail() {
		return mail;
	}

	/**
	 * Establecer el mail de un Recibo
	 */

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Obtener la fecha de un Recibo
	 */

	public Date getDate() {
		return date;
	}

	/**
	 * Establecer la fecha de un Recibo
	 */

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Obtener el Pedido de un Recibo
	 */

	public Order getOrder() {
		return order;
	}

	/**
	 * Establecer el pedido de un Recibo
	 */

	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Obtener el precio de un Recibo
	 */

	public long getPrice() {
		return price;
	}

	/**
	 * Establecer el precio de un Recibo
	 */

	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * ToString de el Recivo, saca un string con la estructura definida en el propio
	 * método.
	 */
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", mail=" + mail + ", date=" + date + ", order=" + order + ", price=" + price
				+ "]";
	}

}
