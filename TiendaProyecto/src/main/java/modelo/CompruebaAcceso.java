package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Controlador;
import vista.LoginView;

public class CompruebaAcceso {

	public static void validarCredenciales(LoginView loginView) {
		Connection connection = Conexion.conectarABaseDeDatos();
		Controlador controlador = new Controlador();
		String username = loginView.getUsernameField();
		char[] password = loginView.getPasswordFieldValue();
		//System.out.println("Sout desde conexion " + username + " " + new String(password));

		if (verificarCredenciales(connection, username, password)) {
			System.out.println("Credenciales correctas. Accediendo a la siguiente vista...");

			// Llamar al controlador para cambiar a la siguiente vista
			controlador.mostrarVistaprincipal();
			loginView.setVisible(false);
		} else {
			System.out.println("Credenciales incorrectas. No se puede acceder a la siguiente vista.");

		}

	}

	public static boolean verificarCredenciales(Connection connection, String username, char[] password) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String sql = "SELECT * FROM propietario WHERE Nombre = ? AND Passwd = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, new String(password));

			resultSet = statement.executeQuery();
			// System.out.println(resultSet);

			return resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
			// Manejar errores de consulta SQL
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
