package es.deusto.spq;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Film {
	/**
	 * Clase base Pelicula.
	 *
	 */
	
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;

	protected String director=null;

    protected String name=null;

    protected String description=null;
    
    protected int ageRestriction=-1;
    
    protected String url = null;
    
    protected String trailer = null;

    /**
	 * Construir objeto Pelicula con sus atributos correspondientes.
	 */
	public Film(String director, String name, String description, int ageRestriction, String url, String trailer) {
		super();
		this.director = director;
		this.name = name;
		this.description = description;
		this.ageRestriction = ageRestriction;
		this.url = url;
		this.trailer = trailer;
	}
	/**
	 * Obtener el trailer de una pelicula
	 */

	public String getTrailer() {
		return trailer;
	}

	/**
	 * Establecer el trailer de una pelicula
	 */
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	/**
	 * Obtener el url de una pelicula
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Establecer el URL de una pelicula
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Obtener el id de una pelicula
	 */

	public long getId() {
		return id;
	}
	/**
	 * Establecer el id de una pelicula
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Obtener el director de una pelicula
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * Establecer el director de una pelicula
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * Obtener el trailer de una pelicula
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establecer el nombre de una pelicula
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtener la descripción de una pelicula
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Establecer la descripción de una pelicula
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Obtener la edad recomendada de una pelicula
	 */
	public int getAgeRestriction() {
		return ageRestriction;
	}
	/**
	 * Establecer la edad recomendada de una pelicula
	 */
	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}
	/**
	 * ToString de la pelicula, saca un string con la estructura definida en el propio método.
	 */
	@Override
	public String toString() {
		return name + " " + "+" + ageRestriction + "";
	}
    
}

