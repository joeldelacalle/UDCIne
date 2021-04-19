package es.deusto.spq;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Inventory {

	@PrimaryKey
	protected String name = null;

	protected Set<Product> products = new HashSet<Product>();

	public Inventory(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public String toString() {
		return "Inventory : " + name;
	}
}
