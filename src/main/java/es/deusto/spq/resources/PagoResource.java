/** \file 
 * Descripción de la clase PagoResource es.deusto.spq.resources PagoResource.java. May 21, 2021
 */
package es.deusto.spq.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.jdo.PayPal;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase para obtener datos de paypal de la base de datos
 */
@Path("paypal")
public class PagoResource {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Metodo para obtener una cuenta paypal de la base de datos de un email en
	 * especifico
	 */
	@GET
	@Path("getemail")
	@Produces(MediaType.APPLICATION_JSON)
	public PayPal getPaypal(@QueryParam("email") String email) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		PayPal paypal = new PayPal();

		Query<PayPal> q = pm.newQuery(PayPal.class);
		List<PayPal> paypallista = q.executeList();

		List<PayPal> paypal2 = new ArrayList<PayPal>();
		for (int i = 0; i < paypallista.size(); i++) {

			if (paypallista.get(i).getEmail().equals(email)) {

				paypal2.add(paypallista.get(i));

			}
		}
		paypal = paypal2.get(0);

		pm.close();
		return paypal;
	}

}
