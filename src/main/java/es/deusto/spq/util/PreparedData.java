package es.deusto.spq.util;

import java.util.Calendar;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Release;
import es.deusto.spq.Room;
import es.deusto.spq.User;

public class PreparedData {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Film filmA = new Film("Jon", "Iron Man",
					"El acto principal es Tony Stark, un magnate multimillonario y hÃ¡bil ingeniero con abundantes vicios que construye un exoesqueleto mecÃ¡nico y se convierte en el superhÃ©roe Iron Man.",
					13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg");
			pm.makePersistent(filmA);
			Film filmB = new Film("Jon", "Iron Man 2",
					"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
					13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg");
			pm.makePersistent(filmB);
			Film filmC = new Film("Jon", "Iron Man 3",
					"Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable.",
					13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg");
			pm.makePersistent(filmC);
			Cinema cinema1 = new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789);
			pm.makePersistent(cinema1);
			Cinema cinema2 = new Cinema("Cine Deusto Santander", "Santander", "Corte Ingles nueva montaña", 345345345);
			pm.makePersistent(cinema2);
			Cinema cinema3 = new Cinema("Cine Deusto Bakacaldo", "bakacaldo", "Max Center", 458345345);
			pm.makePersistent(cinema3);

			Release release1 = new Release("Anthony y Joe Russo", "Cherry",
					"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
					18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg");
			pm.makePersistent(release1);
			Release release2 = new Release("Ramin Bahrani", "Tigre blanco",
					"Narra el ascenso épico de Balram Halwai (Adarsh Gourav) desde una aldea pobre hasta el estrellato del mundo empresarial en la cara más moderna de La India.",
					16, "https://pics.filmaffinity.com/the_white_tiger-462037700-large.jpg");
			pm.makePersistent(release2);
			Release release3 = new Release("Shaka King", "Judas y el Mesías negro",
					"Historia real que gira en torno a un delincuente y al que, tras ser detenido, el FBI le propone la absolución de sus delitos si coopera con ellos.",
					16, "https://pics.filmaffinity.com/judas_and_the_black_messiah-912646266-large.jpg");
			pm.makePersistent(release3);
			User user1 = new User("Jaime", "jaimesanta", "jaimesantamazo@opendeusto.es", "jaimesanta", 435345);
			pm.makePersistent(user1);
			User user2 = new User("Jaime", "jaimesanta", "jaimesantamazo@opendeusto.es", "jaimesanta", 435345);
			pm.makePersistent(user2);
			User user3 = new User("Jaime", "jaimesanta", "jaimesantamazo@opendeusto.es", "jaimesanta", 435345);
			pm.makePersistent(user3);
			
			Room room1 = new Room(cinema1, filmA, "ZUBIARTE SALA 1", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room1);
			Room room2 = new Room(cinema1, filmB, "ZUBIARTE SALA 2", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room2);
			Room room3 = new Room(cinema1, filmC, "ZUBIARTE SALA 3", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room3);
			Room room4 = new Room(cinema2, filmA, "SANTANDER SALA 1", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room4);
			Room room5 = new Room(cinema2, filmB, "SANTANDER SALA 2", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room5);
			Room room6 = new Room(cinema2, filmC, "SANTANDER SALA 3", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room6);
			Room room7 = new Room(cinema3, filmA, "BARAKALDO SALA 1", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room7);
			Room room8 = new Room(cinema3, filmB, "BARAKALDO SALA 2", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room8);
			Room room9 = new Room(cinema3, filmC, "BARAKALDO SALA 3", Calendar.getInstance().getTime(), 100);
			pm.makePersistent(room9);
			
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}