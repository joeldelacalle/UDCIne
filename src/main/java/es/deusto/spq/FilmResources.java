package es.deusto.spq;

import java.util.List;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("films")
public class FilmResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FilmWindow> getFilms() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<FilmWindow> q = pm.newQuery(FilmWindow.class);
		q.setOrdering("name desc");

		List<FilmWindow> films = q.executeList();

		pm.close();

		return films;
	}
}
