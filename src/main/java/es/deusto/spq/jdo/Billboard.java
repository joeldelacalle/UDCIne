/** \file 
 * Breve descripción de Billboard es.deusto.spq.jdo Billboard.java. May 18, 2021
 */
package es.deusto.spq.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
/**
 * Clase base Billboard.
 *
 */
@PersistenceCapable
public class Billboard {

	public Billboard() {
		super();
		this.id = -1;
		director = "";
		name = "";
		description = "";
		ageRestriction = -1;
		url = "";
		trailer = "";
	}

	public Billboard(String director, String name, String description, int ageRestriction, String url, String trailer) {
		super();

		this.director = director;
		this.name = name;
		this.description = description;
		this.ageRestriction = ageRestriction;
		this.url = url;
		this.trailer = trailer;
	}

	/**
	 * Clase base Cartelera.
	 *
	 */
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
	 * Establecer una pelicula en la Cartelera
	 */
	public void setFilmBillboard(Film filmBillboard) {
		this.director = filmBillboard.getDirector();
		this.name = filmBillboard.getName();
		this.description = filmBillboard.getDescription();
		this.ageRestriction = filmBillboard.getAgeRestriction();
		this.url = filmBillboard.getUrl();
		this.trailer = filmBillboard.getTrailer();
	}

	/**
	 * Obtener el id de una pelicula en la Cartelera
	 */

	public long getId() {
		return id;
	}

	/**
	 * Establecer el id de una pelicula en la Cartelera
	 */

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el Director de una pelicula en la Cartelera
	 */

	public String getDirector() {
		return director;
	}

	/**
	 * Establecer el Director de una pelicula en la Cartelera
	 */

	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Obtener el nombre de una pelicula en la Cartelera
	 */

	public String getName() {
		return name;
	}

	/**
	 * Establecer el nombre de una pelicula en la Cartelera
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtener la descripcion de una pelicula en la Cartelera
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establecer la descripcion de una pelicula en la Cartelera
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtener la edad restrictiva de una pelicula en la Cartelera
	 */
	public int getAgeRestriction() {
		return ageRestriction;
	}

	/**
	 * Establecer la edad restrictiva de una pelicula en la Cartelera
	 */
	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}

	/**
	 * Obtener la url de la imagen de una pelicula en la Cartelera
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Establecer la url de la imagen de una pelicula en la Cartelera
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtener la url del trailer de una pelicula en la Cartelera
	 */
	public String getTrailer() {
		return trailer;
	}

	/**
	 * Establecer la url del trailer de una pelicula en la Cartelera
	 */
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

}
