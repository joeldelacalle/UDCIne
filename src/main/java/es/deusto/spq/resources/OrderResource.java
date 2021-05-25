/** \file 
 * Descripción de la clase OrderResource es.deusto.spq.resources OrderResource.java. May 21, 2021
 */
package es.deusto.spq.resources;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.jdo.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase para obtener datos de los pedidos de la base de datos
 */
@Path("order")
public class OrderResource {

	/**
	 * Metodo para obtener toda la lista de pedidos de la base de datos
	 */
	@GET
	@Path("getorders")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrders(@QueryParam("mail") String mail) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Order> orderlist = null;

		Query<Order> o = pm.newQuery(Order.class);
		// o.setFilter("mail == " + mail);
		orderlist = o.executeList();

		pm.close();
		return orderlist;
	}

}
