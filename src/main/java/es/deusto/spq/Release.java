package es.deusto.spq;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Release {
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
    protected long id;

	protected String director=null;

    protected String name=null;

    protected String description=null;
    
    protected int ageRestriction=-1;
    
    protected String url = null;
    
    protected String trailer = null;
    

	public Release(String director, String name, String description, int ageRestriction, String url, String trailer) {
		super();
		this.director = director;
		this.name = name;
		this.description = description;
		this.ageRestriction = ageRestriction;
		this.url = url;
		this.trailer = trailer;
	}
	
	public String getTrailer() {
		return trailer;
	}


	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAgeRestriction() {
		return ageRestriction;
	}

	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return name + " ";
	}
    
    

}
