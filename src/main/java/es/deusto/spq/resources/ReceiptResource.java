/** \file 
 * Descripción de la clase ReceiptResource es.deusto.spq.resources ReceiptResource.java. May 21, 2021
 */
package es.deusto.spq.resources;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.jdo.Receipt;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase para obtener datos de las facturas de la base de datos
 */
@Path("receipts")
public class ReceiptResource {

	/**
	 * Metodo para obtener una lista de facturas de la base de datos de un email en
	 * especifico
	 */
	@GET
	@Path("getreceipt")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Receipt> getReceipt(@QueryParam("mail") String mail) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Receipt> u = pm.newQuery("SELECT FROM " + Receipt.class.getName() + " WHERE mail== '" + mail + "'");
		List<Receipt> receiptlista = u.executeList();

		pm.close();
		return receiptlista;
	}
}
