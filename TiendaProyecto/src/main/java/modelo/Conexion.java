package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import vista.LoginView;

public class Conexion {
	private String username;
	private char[] password;

	public Conexion(String text, char[] password) {
		this.username = text;
		this.password = password;
	}

	public static Connection conectarABaseDeDatos() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda videojuegos g3", "root", "");
			System.out.println("Conexion realizada");
			Properties pr = connection.getClientInfo();
			System.out.println(pr);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
	

}
