package es.deusto.spq;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Billboard {
	
	protected Set<Film> films= new HashSet<Film>();
	
	  public Set<Film> getFilms()
	    {
	        return films;
	    }
}
