/** \file 
 * Breve descripción de es.deusto.spq Cinema.java. May 17, 2021
 */
package es.deusto.spq;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Cinema {
	/**
	 * Clase base Cine.
	 *
	 */
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;
	
	protected String name;
	protected String city;
	protected String address;
	protected int phoneNumber;
	
	public Cinema(String name, String city, String address, int phoneNumber) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
	

}
