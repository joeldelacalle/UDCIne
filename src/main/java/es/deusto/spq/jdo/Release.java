/** \file 
 * Breve descripción de es.deusto.spq.jdo Release.java. May 18, 2021
 */
package es.deusto.spq.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
/**
 * Clase base Estrenos.
 *
 */
@PersistenceCapable
public class Release {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	protected String director = null;

	protected String name = null;

	protected String description = null;

	protected int ageRestriction = -1;

	protected String url = null;

	protected String trailer = null;

	/**
	 * Construir objeto Estreno con sus atributos correspondientes.
	 */
	public Release(String director, String name, String description, int ageRestriction, String url, String trailer) {
		super();
		this.director = director;
		this.name = name;
		this.description = description;
		this.ageRestriction = ageRestriction;
		this.url = url;
		this.trailer = trailer;
	}

	/**
	 * Obtener el trailer de un estreno
	 */
	public String getTrailer() {
		return trailer;
	}

	/**
	 * Establecer el trailer de un estreno
	 */

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	/**
	 * Obtener el Id de un estreno
	 */

	public long getId() {
		return id;
	}

	/**
	 * Establecer el Id de un estreno
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el Director de un estreno
	 */

	public String getDirector() {
		return director;
	}

	/**
	 * Establecer el Director de un estreno
	 */

	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Obtener el Nombre de un estreno
	 */

	public String getName() {
		return name;
	}

	/**
	 * Establecer el Nombre de un estreno
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtener la descripcion de un estreno
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Establecer la descripcion de un estreno
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtener la Restricción de edad de un estreno
	 */

	public int getAgeRestriction() {
		return ageRestriction;
	}

	/**
	 * Establecer la Restricción de edad de un estreno
	 */

	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}

	/**
	 * Obtener la url de la imagen de un estreno
	 */

	public String getUrl() {
		return url;
	}

	/**
	 * Establecer la url de la imagen de un estreno
	 */

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ToString de estreno, saca un string con la estructura definida en el propio
	 * método.
	 */

	@Override
	public String toString() {
		return name + " ";
	}

}
