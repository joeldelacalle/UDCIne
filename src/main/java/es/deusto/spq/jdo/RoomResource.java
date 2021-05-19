package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import es.deusto.spq.Room;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("rooms")
public class RoomResource {
	
	/**
	 * Metodo para obtener toda la lista de las salas de un cine de la base de datos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getReleases() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Room> q = pm.newQuery(Room.class);
		q.setOrdering("name desc");

		List<Room> room = q.executeList();

		pm.close();

		return room;
	}
}

