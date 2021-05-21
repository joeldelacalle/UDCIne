package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Cinema;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase para obtener datos de los cines de la base de datos
 */
@Path("cinemas")
public class CinemaResource {
	/**
	 * Metodo para obtener toda la lista de cines de la base de datos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cinema> getReleases() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Cinema> q = pm.newQuery(Cinema.class);
		q.setOrdering("name desc");

		List<Cinema> cinemas = q.executeList();

		pm.close();

		return cinemas;
	}


}
