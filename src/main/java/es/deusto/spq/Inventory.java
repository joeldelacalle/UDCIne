/** \file 
 * Breve descripción de Inventory es.deusto.spq Inventory.java. May 18, 2021
 */
package es.deusto.spq;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
/**
 * Clase base Inventario.
 *
 */
@PersistenceCapable
public class Inventory {
	
	@PrimaryKey
	protected String name = null;

	protected Set<Product> products = new HashSet<Product>();
	/**
	 * Construir objeto Inventario con sus atributos correspondientes.
	 */
	public Inventory(String name) {
		this.name = name;
	}
	/**
	 * Obtener el nombre de un Inventario
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Establecer el nombre de un Inventario
	 */
	public String getName() {
		return name;
	}
	/**
	 * Obtener los productos de un Inventario
	 */
	public Set<Product> getProducts() {
		return products;
	}
	/**
	 * ToString del Inventario, saca un string con la estructura definida en el propio método.
	 */
	public String toString() {
		return "Inventory : " + name;
	}
}
