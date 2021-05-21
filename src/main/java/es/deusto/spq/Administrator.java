/** \file 
 * Breve descripción de es.deusto.spq Administrator.java. May 18, 2021
 */
package es.deusto.spq;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
/**
 * Clase base Administrador.
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Administrator extends User{
	
	
	protected long id;
	protected int nivel;
	/**
	 * Construir objeto Administrador con sus atributos correspondientes.
	 */
	public Administrator(String name, String nickname, String email, String password, int phoneNumber,int nivel) {
		super(name, nickname, email, password, phoneNumber);
		this.nivel = nivel;
		
	}
	/**
	 * Obtener el id de un Administrador
	 */

	public long getId() {
		return id;
	}
	/**
	 * Establecer el id de un Administrador
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Obtener el nivel de un Administrador
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Establecer el nivel de un Administrador
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * ToString del Administrador, saca un string con la estructura definida en el propio método.
	 */
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", nivel=" + nivel + ", name=" + name + ", nickname=" + nickname + ", email="
				+ email + ", password=" + password + ", phoneNumber=" + phoneNumber + "]";
	}
}
