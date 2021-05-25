/** \file 
 * Breve descripción de es.deusto.spq.jdo MainTest.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainTest {

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Before
	public void setUp() throws Exception {
		new Main();
	}

	@After
	public void After() {

	}

	/*
	 * @Test public void startServer() { Main.startServer(); }
	 */
	/**
	 * Test main de la ventana.
	 *
	 */
	@Ignore
	@Test
	public void testMain() {
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "IOException", e);
		}
	}

}
