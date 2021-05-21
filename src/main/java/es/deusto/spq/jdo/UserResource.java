/** \file 
 * Descripción de la clase UserResource es.deusto.spq.jdo UserResource.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.PayPal;
import es.deusto.spq.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
/**
 * Clase para obtener datos de los usuarios de la base de datos
 */
@Path("users")
public class UserResource {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/**
	 * Metodo para obtener toda la lista de los usuarios de la base de datos
	 */
	@GET
	@Path("allusers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<User> q = pm.newQuery(User.class);
		q.setOrdering("name desc");

		List<User> users = q.executeList();

		pm.close();

		return users;
	}
	
	/**
	 * Metodo para obtener un usuario de la base de datos de un nombre de usuario en especifico
	 */
	@GET
	@Path("getuser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@QueryParam("nickname") String nickname) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		User user = new User();
		

		try (Query<User> u = pm
				.newQuery("SELECT FROM " + User.class.getName() + " WHERE nickname== '" + nickname + "'")) {
			List<User> userlista = u.executeList();

			user = userlista.get(0);
		} catch (Exception e) {
			logger.log(Level.WARNING, "ERROR",e);
			//e.printStackTrace();
		}
		pm.close();
		return user;
	}
	
	/**
	 * Metodo para obtener un usuario de la base de datos de un email y contraseña en especifico
	 */
	public boolean CheckUser(String email, String password) {

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		User user = new User();

		try (Query<User> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE nickname== '" + email
				+ "' && password== '" + password + "'")) {
			List<User> users = q.executeList();

			user = users.get(0);
			System.out.println(user.toString());

			return true;
		} catch (Exception e) {
			logger.log(Level.WARNING, "ERROR",e);
			//e.printStackTrace();

		}
		pm.close();

		return false;

	}

}
