package es.deusto.spq.jdo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Main;
import es.deusto.spq.Order;
import es.deusto.spq.Product;
import es.deusto.spq.Receipt;
import es.deusto.spq.Ticket;
import es.deusto.spq.User;
import es.deusto.spq.types.IntegrationTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@Category(IntegrationTest.class)
public class ReceiptResourceTest {
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	private HttpServer server;
    private WebTarget appTarget;
    private Client c;
    private Calendar dfd;
	private Date dfd1;
	private Cinema cinema;
	private Film film;
	private Ticket ticket;
	private Product product;
	private Order o;
	private List<Product> products;
	private List<Ticket> tickets;

    
    @Before
    public void setUp() throws Exception {
    	server = Main.startServer();
        c = ClientBuilder.newClient();
        appTarget = c.target(Main.BASE_URI);
        products = new ArrayList<Product>();
		tickets = new ArrayList<Ticket>();
		dfd = Calendar.getInstance();
		dfd.set(Calendar.YEAR, 2020);
		dfd.set(Calendar.MONTH, Calendar.NOVEMBER);
		dfd.set(Calendar.DAY_OF_MONTH, 20);
		dfd.set(Calendar.HOUR, 12);
		dfd.set(Calendar.MINUTE, 00);
		dfd.set(Calendar.SECOND, 00);
		dfd1 = dfd.getTime();
		cinema = new Cinema("artea", "bilbao", "landabarri", 99);
		film = new Film("john", "iron man", "el hombre de hierro", 13, null, null);
		ticket = new Ticket(cinema, film, 1, 2, 3, 4, dfd1);
		tickets.add(ticket);
		product = new Product("palomitas", "palomitas Grandes", 3,null);
		products.add(product);
		o = new Order("perro@gmail.com", dfd1,1, tickets.toString(), products.toString(), "En caja", 1);
    }
    
    @SuppressWarnings("deprecation")
	@After
    public void tearDown() throws Exception {
        server.stop();
    }
    
    @Test
    @PerfTest(invocations = 100, threads = 40)
	public void testgetReceipts() {
		WebTarget receiptsTarget = appTarget.path("receipts");
		WebTarget getreceiptsallTarget = receiptsTarget.path("getreceipt").queryParam("mail", "jaimesantamazo@hotmail.com");
    	
		List<Receipt> listareceipts = Arrays.asList(new Receipt("jaimesantamazo@hotmail.com", dfd1,o,99),new Receipt("jaimesantamazo@gmail.com", dfd1,o,99));
   	 
	    GenericType<List<Receipt>> genericType = new GenericType<List<Receipt>>() {};
		List<Receipt> r = getreceiptsallTarget.request(MediaType.APPLICATION_JSON).get(genericType);
			 
		assertEquals(listareceipts.get(0).getMail(), r.get(0).getMail());
	}
}
