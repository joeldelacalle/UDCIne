package es.deusto.spq.jdo;

import java.util.List;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Film;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("films")
public class FilmResources {
	
	/**
	 * Metodo para obtener toda la lista de peliculas de la base de datos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getFilms() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Film> q = pm.newQuery(Film.class);
		q.setOrdering("name desc");

		List<Film> films = q.executeList();

		pm.close();

		return films;
	}
}
