/** \file 
 * Descripción de la clase ProductResource es.deusto.spq.resources ProductResource.java. May 21, 2021
 */
package es.deusto.spq.resources;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.jdo.Product;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Clase para obtener datos de los productos de la base de datos
 */
@Path("products")
public class ProductResource {

	/**
	 * Metodo para obtener toda la lista de los productos de la base de datos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Product> q = pm.newQuery(Product.class);
		q.setOrdering("name desc");

		List<Product> products = q.executeList();

		pm.close();

		return products;
	}
}
