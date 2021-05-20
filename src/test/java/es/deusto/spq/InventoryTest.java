package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
	/**
	 * Clase test Inventario
	 *
	 */
	private Inventory i;
	private Set<Product> products;
	private Product p;

	@Before
	/**
	 *	Metodo para construir objeto Inventario con sus atributos correspondientes.
	 *	Y construir objeto Producto con sus atributos correspondientes.
	 */
	public void setup() {
		i = new Inventory("name");
		products = new HashSet<Product>();
		p = new Product("TestName", "TestDescription", 1, null);
		products.add(p);
	}

	@Test
	/**
	 * Test para establecer el nombre de un Inventario
	 *
	 */
	public void testSetGetName() {
		i.setName("testName");
		assertEquals("testName", i.getName());
	}

	@Test
	/**
	 * Test para obtener el tamaño de los productos
	 *
	 */
	public void testGetProducts() {
		assertEquals(products.size(), 1);
	}

	@Test
	/**
	 * Test para obtener el ToString del Inventario, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Inventory : name", i.toString());
	}

}
