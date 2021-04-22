package es.deusto.spq.jdo;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Film;

@Path("cinemas")
public class CinemaResources {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cinema> getCinemas() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Cinema> q = pm.newQuery(Cinema.class);
		q.setOrdering("name");

		List<Cinema> cinemas = q.executeList();

		pm.close();

		return cinemas;
	}

}
