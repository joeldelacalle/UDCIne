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
	private Order o1;
	private Order o2;
	private Order o3;
	
	@Before
	public void setUp() throws Exception {
		
		vw = new VipWindow();
		user = new User();
		or = new OrderResource();
		listExpected = new ArrayList<Order>();
		
		user.setEmail("jaimesantamazo@opendeusto.es");
		user.setNickname("jaimesanta");
		
		o1 = new Order("jaimesantamazo@opendeusto.es", null, 3, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2 Entrada:3 fila:3 asiento:3",
				24);
		
		o2 = new Order("jaimesantamazo@opendeusto.es", null, 1, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1", 8);
		
		o3 = new Order("jaimesantamazo@opendeusto.es", null, 2, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2", 16);
		
	}
	
	@Test
	public void SetUserNameTest() {
		
		vw.SetUserName(user);
	}
	
	/*@Test
	public void SetOrderRecordTest(){
		
		List<Order> oList = or.getOrders(user.getEmail());
		String lista = oList.toString();
		
		listExpected = vw.SetOrderRecord(user.getNickname());
		o1.setId(1);
		listExpected.add(o1);
		o2.setId(2);
		listExpected.add(o2);
		o3.setId(3);
		listExpected.add(o3);
		String listaEsperada = listExpected.toString();
	
		assertEquals(lista, listaEsperada);
		
	}*/

}
