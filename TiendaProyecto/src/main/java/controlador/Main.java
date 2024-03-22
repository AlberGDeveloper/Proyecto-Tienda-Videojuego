package controlador;

import javax.swing.SwingUtilities;

import modelo.Conexion;
import vista.LoginView;

public class Main {
	public static void main(String[] args) {
		LoginView vistalogin = new LoginView();
		SwingUtilities.invokeLater(() -> vistalogin.setVisible(true));	
	}
}