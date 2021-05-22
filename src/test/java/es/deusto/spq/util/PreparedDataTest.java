/** \file 
 * Breve descripción de es.deusto.spq.util PreparedDataTest.java. May 21, 2021
 */
package es.deusto.spq.util;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase Test PreparedData
 *
 */
public class PreparedDataTest {

	private static PreparedData pd;

	/**
	 * Metodo para iniciar PreparedData
	 *
	 */
	@Before
	public void setUp() throws Exception {

		pd = new PreparedData();

	}

	/**
	 * Test para crear Peliculas
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearPeliculasTest() {

		pd.crearPeliculas();
		pd.pm.deletePersistent(pd.filmA);
		pd.pm.deletePersistent(pd.filmB);
		pd.pm.deletePersistent(pd.filmC);
		pd.pm.deletePersistent(pd.filmD);

	}

	/**
	 * Test para crear Billboard
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearBillboardTest() {

		pd.crearBillboard();
		pd.pm.deletePersistent(pd.b1);
		pd.pm.deletePersistent(pd.b2);
		pd.pm.deletePersistent(pd.b3);
		pd.pm.deletePersistent(pd.b4);

	}

	/**
	 * Test para crear Cines
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearCinesTest() {

		pd.crearCines();
		pd.pm.deletePersistent(pd.cinema1);
		pd.pm.deletePersistent(pd.cinema2);
		pd.pm.deletePersistent(pd.cinema3);

	}

	/**
	 * Test para crear nuevos Estrenos
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearReleasesTest() {

		pd.crearReleases();
		pd.pm.deletePersistent(pd.release1);
		pd.pm.deletePersistent(pd.release2);
		pd.pm.deletePersistent(pd.release3);

	}

	/**
	 * Test para crear Usuarios
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearUsersTest() {

		pd.crearUsers();
		pd.pm.deletePersistent(pd.user1);

	}

	/*
	 * @SuppressWarnings("static-access")
	 * 
	 * @Test public void crearSalasTest() {
	 * 
	 * pd.crearSalas();
	 * 
	 * pd.pm.deletePersistent(pd.room1); pd.pm.deletePersistent(pd.room2);
	 * pd.pm.deletePersistent(pd.room3); pd.pm.deletePersistent(pd.room4);
	 * pd.pm.deletePersistent(pd.room5); pd.pm.deletePersistent(pd.room6);
	 * pd.pm.deletePersistent(pd.room7); pd.pm.deletePersistent(pd.room8);
	 * pd.pm.deletePersistent(pd.room9);
	 * 
	 * 
	 * }
	 */
	
	/**
	 * Test para crear Productos
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearProductosTest() {

		pd.crearProductos();
		pd.pm.deletePersistent(pd.p1);
		pd.pm.deletePersistent(pd.p2);
		pd.pm.deletePersistent(pd.p3);

	}

	/**
	 * Test para pagar con Paypal
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void PayPalTest() {

		pd.PayPal();
		pd.pm.deletePersistent(pd.pp1);

	}

	/**
	 * Test para crear Pedidos
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearOrdersTest() {

		pd.crearOrders();
		pd.pm.deletePersistent(pd.o1);
		pd.pm.deletePersistent(pd.o2);
		pd.pm.deletePersistent(pd.o3);

	}

	/**
	 * Test para crear Recibos
	 *
	 */
	@SuppressWarnings("static-access")
	@Test
	public void crearRecibosTest() {

		pd.crearRecibos();
		pd.pm.deletePersistent(pd.r1);

	}

}
