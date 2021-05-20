package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	/**
	 * Clase test Producto
	 *
	 */
	private Product p;

	@Before
	/**
	 * Metodo para construir objeto Producto con sus atributos correspondientes.
	 *
	 */
	public void setup() {
		p = new Product("name", "description", 1, null);
	}

	@Test
	/**
	 * Test para obtener el nombre de un Producto
	 *
	 */
	public void testGetSetName() {
		p.setName("testName");
		assertEquals("testName", p.getName());
	}

	@Test
	/**
	 * Test para obtener la descripcion de un Producto
	 *
	 */
	public void testGetSetDescription() {
		p.setDescription("testDescription");
		assertEquals("testDescription", p.getDescription());
	}

	@Test
	/**
	 * Test para obtener el precio de un Producto
	 *
	 */
	public void testGetSetPrice() {
		p.setPrice(2);
		assertEquals(2, p.getPrice(), 0.1);
	}
	@Test
	/**
	 * Test para obtener el id de un Producto
	 *
	 */
	public void testGetId() {
		assertEquals(0, p.getId());
	}
	@Test
	/**
	 * Test para obtener la url de la imagen de un Producto
	 *
	 */
	public void testSetGetUrl() {
		p.setUrl("url");
		assertEquals("url", p.getUrl());
	}

	@Test
	/**
	 * Test para obtener el ToString de el Producto, saca un string con la estructura definida en el propio método.
	 *
	 */
	public void testToString() {
		assertEquals("Product [id=0" + ", name=name" + ", description=description"+ ", price=1"+ ", url=null"+ "]", p.toString());
	}

}
