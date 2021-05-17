package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Receipt;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("receipts")
public class ReceiptResource {
	@GET
	@Path("getreceipt")
	@Produces(MediaType.APPLICATION_JSON)
	public Receipt getReceipt(@QueryParam("mail") String mail) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Receipt r = new Receipt();

		try (Query<Receipt> u = pm.newQuery("SELECT FROM " + Receipt.class.getName() + " WHERE mail== '" + mail + "'")) {
			List<Receipt> receiptlista = u.executeList();

			r = receiptlista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pm.close();
		return r;
	}
}
