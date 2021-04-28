package es.deusto.spq;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

	private Inventory i;
	private Set<Product> products;
	private Product p;

	@Before
	public void setup() {
		i = new Inventory("name");
		products = new HashSet<Product>();
		p = new Product("testName", "testDescription", 0.1);
		products.add(p);
	}

	@Test
	public void testSetGetName() {
		i.setName("testName");
		assertEquals("testName", i.getName());
	}

	@Test
	public void testGetProducts() {
		assertEquals(products.size(), 1);
	}

	@Test
	public void testToString() {
		assertEquals("Inventory : name", i.toString());
	}

}
