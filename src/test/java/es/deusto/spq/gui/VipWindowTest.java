/** \file 
 * Breve descripción de es.deusto.spq.gui VipWindowTest.java. May 21, 2021
 */
package es.deusto.spq.gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.ListModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.jdo.Order;
import es.deusto.spq.jdo.User;
import es.deusto.spq.resources.OrderResource;
import es.deusto.spq.types.GuiTest;

/**
 * Clase test Ventana de Usuarios VIP
 *
 */
@Category(GuiTest.class)
public class VipWindowTest {

	private VipWindow vw;
	private User user;
	private ListModel<Order> listModelOrders;
	private JLabel lblUserName;

	/**
	 * Metodo para construir: la ventana vip, usuario, Pedido con sus atributos
	 * correspondientes.
	 *
	 */
	@Before
	public void setUp() throws Exception {

		vw = new VipWindow();
		user = new User();
		new OrderResource();
		new ArrayList<Order>();

		user.setEmail("jaimesantamazo@gmail.com");
		user.setNickname("jaimesanta");

		new Order("jaimesantamazo@gmail.com", null, 3, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2 Entrada:3 fila:3 asiento:3",
				24);

		new Order("jaimesantamazo@gmail.com", null, 1, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1", 8);

		new Order("jaimesantamazo@gmail.com", null, 2, "Pendiente de pago", "Vacio",
				"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2", 16);
		listModelOrders = new DefaultListModel<Order>();
		lblUserName = new JLabel();
	}

	/**
	 * Test para añadir nombre de usuario
	 *
	 */
	@Test
	public void SetUserNameTest() {

		vw.SetUserName(user);
	}

	/**
	 * Test para establecer registro de pedidos
	 *
	 */
	@Test
	public void SetOrderRecordTest() {

		vw.SetOrderRecord(user.getNickname());

	}

	/**
	 * Test para mostrar pedidos
	 *
	 */
	@Test
	public void testDisplayOrders() {

		lblUserName.setText(user.getNickname());
		vw.displayOrders(lblUserName.getText(), listModelOrders);

	}

}
