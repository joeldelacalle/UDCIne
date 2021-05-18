/** \file 
 * Breve descripción de Billboard es.deusto.spq Billboard.java. May 18, 2021
 */
package es.deusto.spq;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Billboard {
	/**
	 * Clase base Cartelera.
	 *
	 */
	
	private Film filmBillboard;

	/**
	 * Obtener una pelicula de la Cartelera
	 */
	public Film getFilmBillboard() {
		return filmBillboard;
	}
	/**
	 * Establecer una pelicula de la Cartelera
	 */
	public void setFilmBillboard(Film filmBillboard) {
		this.filmBillboard = filmBillboard;
	}
	
	
}
