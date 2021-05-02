package es.deusto.spq;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Billboard {
	

	private Film filmBillboard;

	
	public Film getFilmBillboard() {
		return filmBillboard;
	}

	public void setFilmBillboard(Film filmBillboard) {
		this.filmBillboard = filmBillboard;
	}
	
	
}
