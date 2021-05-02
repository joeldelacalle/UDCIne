package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("users")
public class UserResource {
	@GET
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

}
