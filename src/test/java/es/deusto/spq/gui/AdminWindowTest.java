package es.deusto.spq.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdminWindowTest {

	AdminWindow aw;

	@Before
	public void setUp() {
		aw = new AdminWindow();
	}

	@Test
	public void testinitAdminFilmsWindow() {
		aw = new AdminWindow();
		aw.initAdminFilmsWindow();
	}

	@Test
	public void testinitAdminRoomsWindow() {
		aw = new AdminWindow();
		aw.initAdminRoomsWindow();
	}

	@Test
	public void testinitAdminUsersWindow() {
		aw = new AdminWindow();
		aw.initAdminUsersWindow();
	}
}
