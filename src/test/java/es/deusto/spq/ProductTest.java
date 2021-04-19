package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private Product p;

	@Before
	public void setup() {
		p = new Product("name", "description", 0.1);
	}

	@Test
	public void testGetId() {
		assertEquals(0, p.getId());
	}

	@Test
	public void testGetSetName() {
		p.setName("testName");
		assertEquals("testName", p.getName());
	}

	@Test
	public void testGetSetDescription() {
		p.setDescription("testDescription");
		assertEquals("testDescription", p.getDescription());
	}

	@Test
	public void testGetSetPrice() {
		p.setPrice(2);
		assertEquals(2, p.getPrice(), 0.1);
	}

	@Test
	public void testToString() {
		assertEquals("Product : 0" + " name=name" + " [description]", p.toString());
	}

}
