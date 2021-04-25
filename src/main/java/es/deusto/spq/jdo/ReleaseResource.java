package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import es.deusto.spq.Release;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("release")
public class ReleaseResource {

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
