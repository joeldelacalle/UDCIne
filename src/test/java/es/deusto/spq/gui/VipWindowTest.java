package es.deusto.spq.gui;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.Order;
import es.deusto.spq.User;
import es.deusto.spq.jdo.OrderResource;

public class VipWindowTest {
	
	private VipWindow vw;
	private User user;
	private List<Order> listExpected;
	private OrderResource or;
	
	@Before
	public void setUp() throws Exception {
		
		vw = new VipWindow();
		user = new User();
		or = new OrderResource();
		listExpected = new ArrayList<Order>();
		
	}
	
	@Test
	public void SetUserNameTest() {
		
		vw.SetUserName(user);
	}
	
	@Test
	public void SetOrderRecordTest(){
		
		user.setEmail("jaimesantamazo@opendeusto.es");
		user.setNickname("jaimesanta");
		
		List<Order> oList = or.getOrders(user.getEmail());
		String lista = oList.toString();
		
		listExpected = vw.SetOrderRecord(user.getNickname());
		String listaEsperada = listExpected.toString();
	
		assertEquals(lista, listaEsperada);
		
	}

}
