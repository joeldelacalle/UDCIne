package es.deusto.spq.jdo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.PayPal;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("paypal")
public class PagoResource {
	/**
	 * Clase para obtener datos de paypal de la base de datos
	 */
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/**
	 * Metodo para obtener una cuenta paypal de la base de datos de un email en especifico
	 */
	@GET
	@Path("getemail")
	@Produces(MediaType.APPLICATION_JSON)
	public static PayPal getPaypal(@QueryParam("email") String email) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		PayPal paypal = new PayPal();

		try (Query<PayPal> q = pm
				.newQuery("SELECT FROM " + PayPal.class.getName() + " WHERE email== '" + email + "'")) {
			List<PayPal> paypallista = q.executeList();

			paypal = paypallista.get(0);
		} catch (Exception e) {
			logger.log(Level.WARNING, "ERROR",e);
			// e.printStackTrace();
		}
		pm.close();
		return paypal;
	}

}
