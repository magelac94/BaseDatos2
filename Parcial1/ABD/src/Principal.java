import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal {

	public static void main(String[] args) throws IOException {
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\t\t  EJERCICIO 1 - API JPA");
		System.out.println("--------------------------------------------------------------------------------------");

		obtenerDatosJPA();

		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\t\t  EJERCICIO 2 - Consultas JDBC");
		System.out.println("--------------------------------------------------------------------------------------");

		obtenerDatosJDBC();

		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\t\t  LOGIN ");
		System.out.println("--------------------------------------------------------------------------------------");

		login();
	}

	private static void obtenerDatosJPA() {
		// Crea una EM Factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("abd_pu", new HashMap());
		// Crea un Entity Manager
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Obtiene una lista de Usuarios
		List<Usuario> usuarios = em.createQuery("Select u from Usuario u").getResultList();
		// Muestras los Usuarios y sus Datos
		for (Usuario usu : usuarios) {
			System.out.println("------------------");
			System.out.println("USERNAME: " + usu.getUsuarioNombreUsuario());
			System.out.println("Contraseña: " + usu.getUsuarioPassword());
			System.out.println("Nombre: " + usu.getUsuarioNombre());
			System.out.println("Apellido: " + usu.getUsuarioApellido());
			System.out.println("Rol: " + usu.getRolUsuario().getRolName());
			System.out.println("------------------");

		}

		// Libera los recursos
		em.close();
		emf.close();

	}

	public static void obtenerDatosJDBC() {
		Connection con = null;
		try {
			// Carga el driver JDBC
			Class.forName("org.postgresql.Driver").newInstance();
			// Obtiene una conexión a la base de datos
			con = DriverManager.getConnection("jdbc:postgresql://192.168.56.101:5432/prueba", "magela", "magela");
			if (!con.isClosed()) {
				System.out.println(" Conexión exitosa ");
			}

			DatabaseMetaData databaseMetaData = con.getMetaData();

			// Imprimir las tablas que hay en la base de datos
			// Fuente:
			// https://www.progress.com/blogs/jdbc-tutorial-extracting-database-metadata-via-jdbc-driver

			System.out
					.println("--------------------------------------------------------------------------------------");
			System.out.println("\t\t  Tablas Existentes en la base de datos: ");
			System.out
					.println("--------------------------------------------------------------------------------------");

			ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" });
			while (resultSet.next()) {
				// Print
				System.out.println(resultSet.getString("TABLE_NAME"));
			}

			// Imprimir las columnas de una tabla
			String nombretabla = "userapp";
			System.out
					.println("--------------------------------------------------------------------------------------");
			System.out.println("\t \t Tabla: " + nombretabla);
			System.out
					.println("--------------------------------------------------------------------------------------");

			ResultSet columns = databaseMetaData.getColumns(null, null, nombretabla, null);
			while (columns.next()) {
				String columnName = columns.getString("COLUMN_NAME");
				String datatype = columns.getString("DATA_TYPE");
				String isNullable = columns.getString("IS_NULLABLE");
				String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
				// String column_Default = columns.getString("COLUMN_DEFAULT"); // da error

				System.out.println(columnName + ": Tipo de Dato " + datatype + "\t Acepta Nulos " + isNullable
						+ "\t Autoincrementa " + is_autoIncrment + "\t Valor por defecto ");

			}

			System.out.println("------------------");

			// CLAVE PRIMARIA
			ResultSet PK = databaseMetaData.getPrimaryKeys(null, null, nombretabla);
			System.out.print("CLAVES PRIMARIAS: ");
			while (PK.next()) {
				System.out.println(PK.getString("COLUMN_NAME") + "\t");
			}

			// CLAVE FORANEA
			ResultSet FK = databaseMetaData.getImportedKeys(null, null, nombretabla);
			System.out.print("CLAVES FORANEAS: ");
			while (FK.next()) {
				System.out.println(FK.getString("FKCOLUMN_NAME") + "\t");
			}

			System.out
					.println("--------------------------------------------------------------------------------------");

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void login() throws IOException {
		while (true) {
			// Crea una EM Factory
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("abd_pu", new HashMap());
			// Crea un Entity Manager
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			// Solicita usuario
			System.out.println("Ingrese username (si no tiene ingrese guess)");
			BufferedReader ingresoUsuario = new BufferedReader(new InputStreamReader(System.in)); // Pide a usuario
																									// ingreso
			String nombre = ingresoUsuario.readLine();

			System.out.println(nombre);

			// Solicita contrase;a
			System.out.println("Ingrese contraseña (para guess pass guess)");
			BufferedReader ingresoUsuarioPass = new BufferedReader(new InputStreamReader(System.in)); // Pide a usuario
																										// pass
			String pass = ingresoUsuarioPass.readLine();

			System.out.println(pass);

			// Busca Usuario
			List<Usuario> usuarioLogueado = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioNombreUsuario='"
					+ nombre + "' AND u.usuarioPassword='" + pass + "'").getResultList();

			// Valida si existe el usuario
			if (usuarioLogueado.isEmpty()) {
				System.out.println("Usuario o Contraseña incorrectas, ingrese de nuevo");
			} else {
				// Muestras los Usuarios y sus Datos
				for (Usuario usu : usuarioLogueado) {
					System.out.println(
							"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					System.out.println("\t\t\tBienvenid@ " + usu.getUsuarioNombre() + usu.getUsuarioApellido());

					String rol = usu.getRolUsuario().getRolName();

					System.out.println("\tSu rol de Usuario es : " + rol);

					System.out.println("\tUsted podra: ");

					

					try {  // ver errores
						List<Permisos> listaPer = usu.getRolUsuario().getListaPermisos();

						for (Permisos p : listaPer) {
							System.out.println(p.getPermisoFuncionalidad());
						}
					} catch (Exception e) {
						System.out.println("Debug - error al obtener los permisos ");
					}

					System.out.println("\t--- ");
					System.out.println("\tChau! ");
					System.out.println(
							"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

					break; // salgo ya que solo es un usuario
				}

			}

			// Libera los recursos
			em.close();
			emf.close();

		}
	}
}
