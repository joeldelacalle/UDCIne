/** \file 
 * Descripción de la clase BillBoardResource es.deusto.spq.jdo BillBoardResource.java. May 21, 2021
 */
package es.deusto.spq.jdo;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.Billboard;
import jakarta.ws.rs.Path;
/**
 * Clase para obtener datos de BillBoard de la base de datos
 */
@Path("billboards")
public class BillboardResource {

	/**
	 * Metodo para obtener toda la lista de BillBoards de la base de datos
	 */
	public List<Billboard> getBillboardFilms() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Billboard> q = pm.newQuery(Billboard.class);

		List<Billboard> billboardFilms = q.executeList();

		pm.close();

		return billboardFilms;
	}

}
