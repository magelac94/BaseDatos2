import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userapp")
public class Usuario {

	private int usuarioCi;
	private String usuarioNombre;
	private String usuarioPassword;
	private String usuarioApellido;
	private String usuarioNombreUsuario;
	private Rol rolUsuario;			// Un usuario solo tiene un Rol
	
	public Usuario() {}
	
	public Usuario(int usuarioCi, String usuarioNombre, String usuarioApellido, String usuarioNombreUsuario, String usuarioPassword, Rol rolUsuario) 
	{
		this.usuarioCi= usuarioCi;
		this.usuarioNombre= usuarioNombre;
		this.usuarioApellido= usuarioApellido;
		this.usuarioNombreUsuario= usuarioNombreUsuario;
		this.usuarioPassword = usuarioPassword;
		this.rolUsuario = rolUsuario;
	} 
	
	

	@Id
	@Column(name="ci")
	public int getUsuarioCi() {
		return usuarioCi;
	}
	public void setUsuarioCi(int usuarioCi) {
		this.usuarioCi = usuarioCi;
	}
	@Column(name="name")
	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	@Column(name="surname")
	public String getUsuarioApellido() {
		return usuarioApellido;
	}
	
	public void setUsuarioApellido(String usuarioApellido) {
		this.usuarioApellido = usuarioApellido;
	}
	
	@Column(name="username")
	public String getUsuarioNombreUsuario() {
		return usuarioNombreUsuario;
	}

	public void setUsuarioNombreUsuario(String usuarioNombreUsuario) {
		this.usuarioNombreUsuario = usuarioNombreUsuario;
	}
	

	@Column(name="password")
	public String getUsuarioPassword() {
		return usuarioPassword;
	}
	
	public void setUsuarioPassword(String usuarioPassword) {
		this.usuarioPassword = usuarioPassword;
	}
	
	@ManyToOne
	@JoinColumn(name="idroluser")
	public Rol getRolUsuario() {
		return rolUsuario;
	}

	
	public void setRolUsuario(Rol rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	
}
