package es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User u;

	@Before
	public void setUp() {
		u = new User("name", "nickname", "email", "password", 99);
	}

	@Test
	public void testSetGetId() {
		u.setId(0);
		assertEquals(0, u.getId());
	}

	@Test
	public void testSetGetName() {
		u.setName("testName");
		assertEquals("testName", u.getName());
	}

	@Test
	public void testSetGetNickname() {
		u.setNickname("testNickname");
		assertEquals("testNickname", u.getNickname());
	}

	@Test
	public void testSetGetEmail() {
		u.setEmail("testEmail");
		assertEquals("testEmail", u.getEmail());
	}

	@Test
	public void testSetGetPassword() {
		u.setPassword("testPassword");
		assertEquals("testPassword", u.getPassword());
	}

	@Test
	public void testSetGetPhoneNumber() {
		u.setPhoneNumber(0);
		assertEquals(0, u.getPhoneNumber());
	}

	@Test
	public void testToString() {
		assertEquals("User [id=0" + ", name=name" + ", nickname=nickname" + ", email=email" + ", password=password"
				+ ", phoneNumber=99" + "]", u.toString());
	}

}
