package es.deusto.spq.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;

public class PreparedData {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			Film filmA = new Film("Jon", "Iron Man", "El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man."
								, 13 , "https://pics.filmaffinity.com/iron_man-108960873-large.jpg");
			pm.makePersistent(filmA);
			Film filmB = new Film("Jon", "Iron Man 2", "El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas."
								, 13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg");
			pm.makePersistent(filmB);
			Film filmC = new Film("Jon", "Iron Man 3", "Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable."
								, 13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg");
			pm.makePersistent(filmC);
			Cinema cinema1 = new Cinema("Cine Deusto Zubiarte","Bilbao","Centro Comercial Zubiarte",123456789);
			pm.makePersistent(cinema1);
			Cinema cinema2 = new Cinema("Cine Deusto Santander","Santander","Corte Ingles nueva montaña",345345345);
			pm.makePersistent(cinema1);
			Cinema cinema3 = new Cinema("Cine Deusto Bakacaldo","bakacaldo","Max Center",458345345);
			pm.makePersistent(cinema1);
			
			tx.commit();
			
		}finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}