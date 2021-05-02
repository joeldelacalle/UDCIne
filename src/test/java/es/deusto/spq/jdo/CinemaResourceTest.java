package es.deusto.spq.jdo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import es.deusto.spq.Cinema;
import es.deusto.spq.Main;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

@RunWith(MockitoJUnitRunner.class)
public class CinemaResourceTest {

	@Mock
	CinemaResource cr = mock(CinemaResource.class);
	List<Cinema> listacinesResources;

	@Before
	public void setUp() throws Exception {
		List<Cinema> listacines = Arrays.asList(
				new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789),
				new Cinema("Cine Deusto Santander", "Santander", "Corte Ingles nueva montaña", 345345345),
				new Cinema("Cine Deusto Bakacaldo", "bakacaldo", "Max Center", 458345345));
		when(cr.getReleases()).thenReturn(listacines);
		listacinesResources = cr.getReleases();
	}

	@Test
	public void testGetReleases() {

		List<Cinema> listacines = Arrays.asList(
				new Cinema("Cine Deusto Zubiarte", "Bilbao", "Centro Comercial Zubiarte", 123456789),
				new Cinema("Cine Deusto Santander", "Santander", "Corte Ingles nueva montaña", 345345345),
				new Cinema("Cine Deusto Bakacaldo", "bakacaldo", "Max Center", 458345345));

		assertEquals(listacines.get(0).getId(), cr.getReleases().get(0).getId());

	}

}

