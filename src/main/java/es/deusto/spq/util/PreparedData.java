package es.deusto.spq.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.Film;

public class PreparedData {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			Film filmA = new Film("Jon", "Iron Man", "hombre de hierro", 13);
			pm.makePersistent(filmA);
			Film filmB = new Film("Jon", "Iron Man 2", "hombre de hierro 2", 13);
			pm.makePersistent(filmB);
			Film filmC = new Film("Jon", "Iron Man 3", "hombre de hierro 3", 13);
			pm.makePersistent(filmC);
			
			tx.commit();
			
		}finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}
