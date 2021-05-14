package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("order")
public class OrderResource {

	@GET
	@Path("getorders")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrders(@QueryParam("mail") String mail) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Order> orderlist = null;

		Query <Order> o = pm.newQuery(Order.class);
		//o.setFilter("mail == " + mail);
			orderlist = o.executeList();
			

		pm.close();
		return orderlist;
	}

}
