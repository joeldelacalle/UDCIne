/** \file 
 * Breve descripción de es.deusto.spq Cinema.java. May 17, 2021
 */
package es.deusto.spq;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
/**
 * Clase base Cine.
 *
 */
@PersistenceCapable
public class Cinema {

	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;
	
	protected String name;
	protected String city;
	protected String address;
	protected int phoneNumber;
	/**
	 * Construir objeto Pelicula con sus atributos correspondientes.
	 */
	public Cinema(String name, String city, String address, int phoneNumber) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Obtener el id de un cine
	 */
	
	public long getId() {
		return id;
	}
	/**
	 * Establecer el id de un cine
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Obtener el nombre de un cine
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establecer el nombre de un cine
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtener la ciudad de un cine
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Establecer la ciudad de un cine
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Obtener la dirección de un cine
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Establecer la dirección de un cine
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Obtener el número de teléfono de un cine
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Establecer el número de teléfono de un cine
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * ToString del cine, saca un string con la estructura definida en el propio método.
	 */
	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
	

}
