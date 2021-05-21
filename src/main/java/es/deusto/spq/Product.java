/** \file 
 * Breve descripción de es.deusto.spq Product.java. May 17, 2021
 */
package es.deusto.spq;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
/**
 * Clase base Producto.
 *
 */

@PersistenceCapable
public class Product {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	protected String name = null;

	protected String description = null;

	protected long price = 0;

	protected String url = null;

	/**
	 * Construir objeto Producto con sus atributos correspondientes.
	 */
	public Product(String name, String description, long price, String url) {
		super();

		this.name = name;
		this.description = description;
		this.price = price;
		this.url = url;
	}

	/**
	 * Obtener el id de un Producto
	 */
	public long getId() {
		return id;
	}

	/**
	 * Establecer el id de un Producto
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtener el nombre de un Producto
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtener la descripción de un Producto
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Obtener el precio de un Producto
	 */

	public long getPrice() {
		return price;
	}

	/**
	 * Establecer el nombre de un Producto
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Establecer la descripción de un Producto
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Establecer el precio de un Producto
	 */

	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * Obtener la url de la imagen de un Producto
	 */

	public String getUrl() {
		return url;
	}

	/**
	 * Establecer la url de la imagen de un Producto
	 */

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ToString de el Producto, saca un string con la estructura definida en el
	 * propio método.
	 */

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", url="
				+ url + "]";
	}

}
