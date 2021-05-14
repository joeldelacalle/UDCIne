package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Order;
import jakarta.ws.rs.Path;

public class OrderResource {

	public List<Order> getOrders(String mail) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Order> orderlist = null;

		try (Query<Order> o = pm.newQuery("SELECT FROM " + Order.class.getName() + " WHERE mail== '" + mail + "'")) {
			orderlist = o.executeList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		pm.close();
		return orderlist;
	}

}
