/** \file 
 * Breve descripción de User es.deusto.spq User.java. May 18, 2021
 */
package es.deusto.spq;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
	/**
	 * Clase base Usuario.
	 *
	 */
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;
	
	protected String name;
	protected String nickname;
	protected String email;
	protected String password;
	protected int phoneNumber;
	/**
	 * Construir objeto Usuario con sus atributos correspondientes.
	 */
	public User(String name, String nickname, String email, String password, int phoneNumber) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	public User() {}
	
	/**
	 * Obtener el id de un Usuario
	 */

	public long getId() {
		return id;
	}
	/**
	 * Establecer el id de un Usuario
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Obtener el nombre de un Usuario
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establecer el nombre de un Usuario
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtener el apodo de un Usuario
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Establecer el apodo de un Usuario
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * Obtener el email de un Usuario
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Establecer el email de un Usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Obtener el password de un Usuario
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Establecer el password de un Usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Obtener el número de teléfono de un Usuario
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Establecer el número de teléfono de un Usuario
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * ToString del Usuario, saca un string con la estructura definida en el propio método.
	 */
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
}
