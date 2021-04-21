package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FilmTest {

	private Film f;

	@Before
	public void setup() {
		f = new Film("director", "name", "description", 13,"");
	}

	@Test
	public void testSetGetId() {
		f.setId(5);
		assertEquals(5, f.getId());
	}

	@Test
	public void testSetGetDirector() {
		f.setDirector("testDirector");
		assertEquals("testDirector", f.getDirector());
	}

	@Test
	public void testSetGetName() {
		f.setName("testName");
		assertEquals("testName", f.getName());
	}

	@Test
	public void testSetGetDescription() {
		f.setDescription("testDescription");
		assertEquals("testDescription", f.getDescription());
	}

	@Test
	public void testSetGetAgeRestriction() {
		f.setAgeRestriction(18);
		assertEquals(18, f.getAgeRestriction());
	}

	@Test
	public void testToString() {
		assertEquals("name " + "+" + "13", f.toString());
	}

}
