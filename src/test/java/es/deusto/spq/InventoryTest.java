/** \file 
 * Breve descripción de es.deusto.spq InventoryTest.java. May 21, 2021
 */
package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
/**
 * Clase test Inventario
 *
 */
public class InventoryTest {
	
	private Inventory i;
	private Set<Product> products;
	private Product p;
	/**
	 *	Metodo para construir objeto Inventario con sus atributos correspondientes.
	 *	Y construir objeto Producto con sus atributos correspondientes.
	 */
	@Before
	public void setup() {
		i = new Inventory("name");
		products = new HashSet<Product>();
		p = new Product("TestName", "TestDescription", 1, null);
		products.add(p);
	}
	/**
	 * Test para establecer el nombre de un Inventario
	 *
	 */
	@Test
	public void testSetGetName() {
		i.setName("testName");
		assertEquals("testName", i.getName());
	}
	/**
	 * Test para obtener el tamaño de los productos
	 *
	 */
	@Test
	public void testGetProducts() {
		assertEquals(products.size(), 1);
	}
	/**
	 * Test para obtener el ToString del Inventario, saca un string con la estructura definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("Inventory : name", i.toString());
	}

}
