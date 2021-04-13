package es.deusto.spq.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.FilmWindow;

public class PreparedData {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			FilmWindow filmA = new FilmWindow("Jon", "Iron Man", "hombre de hierro", 13);
			pm.makePersistent(filmA);
			FilmWindow filmB = new FilmWindow("Jon", "Iron Man 2", "hombre de hierro 2", 13);
			pm.makePersistent(filmB);
			FilmWindow filmC = new FilmWindow("Jon", "Iron Man 3", "hombre de hierro 3", 13);
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
