package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Billboard;

public class BillboardResource {

	public List<Billboard> getBillboardFilms() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Billboard> q = pm.newQuery(Billboard.class);
	

		List<Billboard> billboardFilms = q.executeList();

		pm.close();

		return billboardFilms;
	}
	
}
