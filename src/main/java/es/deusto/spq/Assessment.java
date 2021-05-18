/** \file 
 * Breve descripción de es.deusto.spq Assessment.java. May 18, 2021
 */ 
package es.deusto.spq;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Assessment {
	/**
	 * Clase base Assessment.
	 *
	 */
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;
	
	protected String user;
	
	protected String name;
	
	protected String text;
	/**
	 * Construir objeto Valoración con sus atributos correspondientes.
	 */
	public Assessment(String user, String name, String text) {
		super();
		this.user = user;
		this.name = name;
		this.text = text;
	}
	/**
	 * Obtener el id de un Valoración
	 */
	public long getId() {
		return id;
	}
	/**
	 * Establecer el id de una Valoración
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Obtener el usuario de una Valoración
	 */
	public String getUser() {
		return user;
	}
	/**
	 * Establecer el usuario de una Valoración
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * Obtener el nombre de un Valoración
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establecer el nombre de una Valoración
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtener el texto de un Valoración
	 */
	public String getText() {
		return text;
	}
	/**
	 * Establecer el texto de una Valoración
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * ToString del Valoración, saca un string con la estructura definida en el propio método.
	 */
	@Override
	public String toString() {
		return "Assessment: [id=" + id + ", user=" + user + ", name=" + name + ", text=" + text + "]";
	}
	
}
