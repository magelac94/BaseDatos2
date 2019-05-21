import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rol")
public class Rol {

	private int idRol;
	private String rolName;	
	private List<Permisos> listaPermisos;
	
	public Rol () {	}
	
	public Rol ( int idRol, String rolName) 
	{	
		this.idRol = idRol;
		this.rolName = rolName;
		
	}	
		
	@Id
	@Column(name="idrol")
	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
		
	@Column(name="namerol")
	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	
	
	@ManyToMany
	@JoinTable(
			name = "rolpermission",
			joinColumns = @JoinColumn(name = "idrol"),
			inverseJoinColumns = @JoinColumn(name = "idpermission"))
	public List<Permisos> getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(List<Permisos> listaPermisos) {
		this.listaPermisos = listaPermisos;
	}

	
	
}
