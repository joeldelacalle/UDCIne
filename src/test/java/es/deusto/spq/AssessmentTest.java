package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssessmentTest {

	private Assessment a;

	@Before
	public void setup() {
		a = new Assessment("user", "name", "text");
	}

	@Test
	public void testSetGetId() {
		a.setId(5);
		assertEquals(5, a.getId());
	}

	@Test
	public void testSetGetUser() {
		a.setUser("testUser");
		assertEquals("testUser", a.getUser());
	}

	@Test
	public void testSetGetName() {
		a.setName("testName");
		assertEquals("testName", a.getName());
	}

	@Test
	public void testSetGetText() {
		a.setText("testText");
		assertEquals("testText", a.getText());
	}

	@Test
	public void testToString() {
		assertEquals("Assessment: [id=0" + ", user=user" + ", name=name" + ", text=text" + "]",
				a.toString());
	}

}
