/** \file 
 * Descripción de la clase ReleaseResource es.deusto.spq.resources ReleaseResource.java. May 21, 2021
 */
package es.deusto.spq.resources;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.jdo.Release;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase para obtener datos de las valoraciones de la base de datos
 */
@Path("release")
public class ReleaseResource {

	/**
	 * Metodo para obtener toda la lista de los nuevos estrenos de la base de datos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Release> getReleases() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Release> q = pm.newQuery(Release.class);
		q.setOrdering("name desc");

		List<Release> releases = q.executeList();

		pm.close();

		return releases;
	}
}
