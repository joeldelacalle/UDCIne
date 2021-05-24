package es.deusto.spq;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Main m;
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Before
	public void setUp() throws Exception {
		m = new Main();
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
	@Test
	public void testMain() {
		try {
			Main.main(new String[0], 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "IOException", e);
		}
	}

}
