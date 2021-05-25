/** \file 
 * Breve descripción de PayPal es.deusto.spq.jdo Paypal.java. May 18, 2021
 */
package es.deusto.spq.jdo;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Clase base PayPal.
 *
 */
@PersistenceCapable
public class PayPal {

	protected String email;

	protected String password;

	/**
	 * Construir objeto Paypal con sus atributos correspondientes.
	 */
	public PayPal(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public PayPal() {
	}

	/**
	 * Obtener el email de Paypal
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establecer el email de Paypal
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtener la contraseña de Paypal
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establecer la contraseña de Paypal
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ToString del PayPal, saca un string con la estructura definida en el propio
	 * método.
	 */
	@Override
	public String toString() {
		return "PayPal [email=" + email + ", password=" + password + "]";
	}

}
