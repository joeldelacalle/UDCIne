/** \file 
 * Descripción de la clase RoomResource es.deusto.spq.jdo RoomResource.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Cinema;
import es.deusto.spq.Release;
import es.deusto.spq.Room;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase para obtener datos de las salas de los cines de la base de datos
 */
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

