package es.deusto.spq.util;

import java.util.Calendar;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.Billboard;
import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Order;
import es.deusto.spq.PayPal;
import es.deusto.spq.Product;
import es.deusto.spq.Receipt;
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
					13, "https://pics.filmaffinity.com/iron_man-108960873-large.jpg",
					"https://www.youtube.com/watch?v=RLiO7pt8MbU");
			pm.makePersistent(filmA);
			Film filmB = new Film("Jon", "Iron Man 2",
					"El mundo sabe que el multimillonario Tony Stark es Iron Man, el superhÃ©roe enmascarado, el cual forja alianzas nuevas y se enfrenta a nuevas y poderosas fuerzas.",
					13, "https://pics.filmaffinity.com/iron_man_2-466103197-large.jpg",
					"https://www.youtube.com/watch?v=BoohRoVA9WQ");
			pm.makePersistent(filmB);
			Film filmC = new Film("Jon", "Iron Man 3",
					"Tony Stark tendrÃ¡ que enfrentarse a un enemigo cuyo alcance no conoce lÃ­mites. Cuando Stark encuentre su vida personal destruida a manos de su enemigo, se embarca en una difÃ­cil aventura para encontrar al responsable.",
					13, "https://pics.filmaffinity.com/iron_man_3_aka_ironman_3-972235216-large.jpg",
					"https://www.youtube.com/watch?v=6dhCPF_Jsco");
			pm.makePersistent(filmC);
			Film filmD = new Film("Jon", "Infinity war",
					"El todopoderoso Thanos ha despertado con la promesa de arrasar con todo a su paso, portando el Guantelete del Infinito. Los únicos capaces de pararle los pies son los Vengadores y el resto de superhéroes de la galaxia",
					13, "https://pics.filmaffinity.com/avengers_infinity_war-181539353-large.jpg",
					"https://www.youtube.com/watch?v=6ZfuNTqbHE8");
			pm.makePersistent(filmD);

			Billboard b1 = new Billboard();
			b1.setFilmBillboard(filmA);
			pm.makePersistent(b1);

			Billboard b2 = new Billboard();
			b2.setFilmBillboard(filmB);
			pm.makePersistent(b2);

			Billboard b3 = new Billboard();
			b3.setFilmBillboard(filmC);
			pm.makePersistent(b3);

			Cinema cinema1 = new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789);
			pm.makePersistent(cinema1);
			Cinema cinema2 = new Cinema("Cine Deusto Santander", "Santander", "Corte Ingles nueva montaña", 345345345);
			pm.makePersistent(cinema2);
			Cinema cinema3 = new Cinema("Cine Deusto Barakaldo", "Barakaldo", "Max Center", 458345345);
			pm.makePersistent(cinema3);

			Release release1 = new Release("Anthony y Joe Russo", "Cherry",
					"Basada en la historia real de Nico Walker, cuenta la historia de un joven que lidia con un trastorno de estrés postraumático tras volver de la guerra de Iraq.",
					18, "https://pics.filmaffinity.com/cherry-952736388-large.jpg",
					"https://www.youtube.com/watch?v=H5bH6O0bErk");
			pm.makePersistent(release1);
			Release release2 = new Release("Ramin Bahrani", "Tigre blanco",
					"Narra el ascenso épico de Balram Halwai (Adarsh Gourav) desde una aldea pobre hasta el estrellato del mundo empresarial en la cara más moderna de La India.",
					16, "https://pics.filmaffinity.com/the_white_tiger-462037700-large.jpg",
					"https://www.youtube.com/watch?v=rX7xv4G9wnI");
			pm.makePersistent(release2);
			Release release3 = new Release("Shaka King", "Judas y el Mesías negro",
					"Historia real que gira en torno a un delincuente y al que, tras ser detenido, el FBI le propone la absolución de sus delitos si coopera con ellos.",
					16, "https://pics.filmaffinity.com/judas_and_the_black_messiah-912646266-large.jpg",
					"https://www.youtube.com/watch?v=iaibc6LI1_g");
			pm.makePersistent(release3);
			User user1 = new User("Jaime", "jaimesanta", "jaimesantamazo@hotmail.com", "jaimesanta", 435345);
			pm.makePersistent(user1);

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

			Product p1 = new Product("Palomitas Medianas", "500g", 4,
					"https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg");
			pm.makePersistent(p1);
			Product p2 = new Product("Palomitas Grandes", "1000g", 6,
					"https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg");
			pm.makePersistent(p2);
			Product p3 = new Product("Palomitas Grandes + Coca Cola", "Unas palomitas grandes y coca cola", 8,
					"https://previews.123rf.com/images/imagestore/imagestore1606/imagestore160601787/58756143-palomitas-en-rect%C3%A1ngulo-con-el-color-en-la-copa-para-llevar-aislado-en-el-fondo-blanco.jpg");
			pm.makePersistent(p3);
			PayPal pp1 = new PayPal("jaimesantamazo@hotmail.com", "123");
			pm.makePersistent(pp1);

			Order o1 = new Order("jaimesantamazo@opendeusto.es", null, 3, "Pendiente de pago", "Vacio",
					"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2 Entrada:3 fila:3 asiento:3",
					24);
			pm.makePersistent(o1);
			Order o2 = new Order("jaimesantamazo@opendeusto.es", null, 1, "Pendiente de pago", "Vacio",
					"pelicula:Infinity war Entrada:1 fila:1 asiento:1", 8);
			pm.makePersistent(o2);
			Order o3 = new Order("jaimesantamazo@opendeusto.es", null, 2, "Pendiente de pago", "Vacio",
					"pelicula:Infinity war Entrada:1 fila:1 asiento:1 Entrada:2 fila:2 asiento:2", 16);
			pm.makePersistent(o3);
			Receipt r1 = new Receipt("jaimesantamazo@hotmail.com",null,o1,99);
			pm.makePersistent(r1);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}