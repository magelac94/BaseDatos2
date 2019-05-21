import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permisos {
	
	@Id
	private int idPermiso;
	private String permisoFuncionalidad;
		
	public Permisos ( int idPermiso, String permisoFuncionalidad ) 
	{
		this.idPermiso = idPermiso;
		this.permisoFuncionalidad = permisoFuncionalidad;
	}
	
	@Column(name="id")
	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	@Column(name="functionality")
	public String getPermisoFuncionalidad() {
		return permisoFuncionalidad;
	}

	public void setPermisoFuncionalidad(String permisoFuncionalidad) {
		this.permisoFuncionalidad = permisoFuncionalidad;
	}
	
	
}
