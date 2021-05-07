package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReleaseTest {

	private Release r;

	@Before
	public void setup() {
		r = new Release("director", "name", "description", 13,"url", "");
	}
	@Test
	public void testSetGetTrailer() {
		r.setTrailer("trailer");
		assertEquals("trailer", r.getTrailer());
	}

	@Test
	public void testSetGetId() {
		r.setId(5);
		assertEquals(5, r.getId());
	}

	@Test
	public void testSetGetDirector() {
		r.setDirector("testDirector");
		assertEquals("testDirector", r.getDirector());
	}

	@Test
	public void testSetGetName() {
		r.setName("testName");
		assertEquals("testName", r.getName());
	}

	@Test
	public void testSetGetDescription() {
		r.setDescription("testDescription");
		assertEquals("testDescription", r.getDescription());
	}

	@Test
	public void testSetGetAgeRestriction() {
		r.setAgeRestriction(18);
		assertEquals(18, r.getAgeRestriction());
	}
	@Test
	public void testSetGetUrl() {
		r.setUrl("url");
		assertEquals("url", r.getUrl());
	}

	@Test
	public void testToString() {
		assertEquals("name " + "", r.toString());
	}

}
