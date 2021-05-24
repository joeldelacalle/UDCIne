/** \file 
 * Breve descripción de es.deusto.spq.jdo ProductTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.jdo.Product;
/**
 * Clase test Producto
 *
 */
public class ProductTest {
	
	private Product p;
	/**
	 * Metodo para construir objeto Producto con sus atributos correspondientes.
	 *
	 */
	@Before
	public void setup() {
		p = new Product("name", "description", 1, null);
	}
	/**
	 * Test para obtener el nombre de un Producto
	 *
	 */
	@Test
	public void testGetSetName() {
		p.setName("testName");
		assertEquals("testName", p.getName());
	}
	/**
	 * Test para obtener la descripcion de un Producto
	 *
	 */
	@Test
	public void testGetSetDescription() {
		p.setDescription("testDescription");
		assertEquals("testDescription", p.getDescription());
	}
	/**
	 * Test para obtener el precio de un Producto
	 *
	 */
	@Test
	public void testGetSetPrice() {
		p.setPrice(2);
		assertEquals(2, p.getPrice(), 0.1);
	}
	/**
	 * Test para obtener el id de un Producto
	 *
	 */
	@Test
	public void testGetId() {
		assertEquals(0, p.getId());
	}
	/**
	 * Test para obtener la url de la imagen de un Producto
	 *
	 */
	@Test
	public void testSetGetUrl() {
		p.setUrl("url");
		assertEquals("url", p.getUrl());
	}
	/**
	 * Test para obtener el ToString de el Producto, saca un string con la estructura definida en el propio método.
	 *
	 */
	@Test
	public void testToString() {
		assertEquals("Product [id=0" + ", name=name" + ", description=description"+ ", price=1"+ ", url=null"+ "]", p.toString());
	}

}
