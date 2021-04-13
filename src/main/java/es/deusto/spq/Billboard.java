package es.deusto.spq;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;

import es.deusto.spq.gui.FilmWindow;

@PersistenceCapable
public class Billboard {
	
	protected Set<FilmWindow> films= new HashSet<FilmWindow>();
	
	  public Set<FilmWindow> getFilms()
	    {
	        return films;
	    }
}
