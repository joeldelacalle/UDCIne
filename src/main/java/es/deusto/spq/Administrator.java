package es.deusto.spq;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Administrator extends User{
	
	protected long id;
	protected int nivel;
	
	public Administrator(String name, String nickname, String email, String password, int phoneNumber,int nivel) {
		super(name, nickname, email, password, phoneNumber);
		this.nivel = nivel;
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", nivel=" + nivel + ", name=" + name + ", nickname=" + nickname + ", email="
				+ email + ", password=" + password + ", phoneNumber=" + phoneNumber + "]";
	}
}
