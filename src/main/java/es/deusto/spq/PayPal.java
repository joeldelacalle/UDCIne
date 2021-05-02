package es.deusto.spq;

import javax.jdo.annotations.PersistenceCapable;


@PersistenceCapable
public class PayPal {
	
	protected String email;
	
	protected String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PayPal(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public PayPal() {}

	@Override
	public String toString() {
		return "PayPal [email=" + email + ", password=" + password + "]";
	}

	


}
