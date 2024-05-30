package controlador;

import java.util.List;

import javax.swing.SwingUtilities;

import modelo.CompruebaAcceso;
import modelo.Conexion;
import modelo.ExportClientes;
import modelo.ExportPedidos;
import modelo.Exportxls;
import vista.InsertJuegos;
import vista.LoginView;
import vista.VentanaClientes;
import vista.VentanaPedido;
import vista.VentanaStock;
import vista.VentanaprincipalApp;
import javax.swing.table.TableModel;

public class Controlador {

	private VentanaprincipalApp ventanaPrincipal;
	private InsertJuegos insertjuegos;
	private LoginView loginview;
	private CompruebaAcceso compruebaacceso;
	private Conexion conexion;
	private VentanaStock ventanaStock;
	private Exportxls modeloExport;
	private ExportClientes modeloExport2;
	private ExportPedidos modeloExport3;
	private VentanaPedido ventanaPedido;
	private VentanaClientes ventanaClientes;

	public Controlador() {

		super();
	}

	public Controlador(InsertJuegos insertjuegos, LoginView loginview, CompruebaAcceso compruebaacceso,
			Conexion conexion, VentanaprincipalApp ventanaPrincipal, VentanaStock ventanaStock, VentanaClientes ventanaClientes, VentanaPedido ventanaPedido, Exportxls modeloExport, ExportClientes modeloExport2, ExportPedidos modeloExport3) {
		super();
		this.insertjuegos = insertjuegos;
		this.loginview = loginview;
		this.compruebaacceso = compruebaacceso;
		this.conexion = conexion;
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaStock = ventanaStock;
		this.ventanaClientes = ventanaClientes;
		this.ventanaPedido = ventanaPedido;
		this.modeloExport = modeloExport;
		this.modeloExport2 = modeloExport2;
		this.modeloExport3 = modeloExport3;

	}
/*
 * 
 * 	public void mostrarInsertjuegos() {

		// loginview.setVisible(false);
		// loginview.setVisible(false);
		SwingUtilities.invokeLater(() -> {
			InsertJuegos ij = new InsertJuegos();
			ij.setVisible(true);
		});
	}
 */


	public void mostrarVistaprincipal() { // Código para cambiar a la vista
		VentanaprincipalApp 
		vistaGeneralApp = new VentanaprincipalApp();
		vistaGeneralApp.setVisible(true);

	}

	public void mostrarVentanaStock() { // Código para cambiar a la vista
		VentanaStock 
		vistaStockApp = new VentanaStock();
		vistaStockApp.setVisible(true);
		// ventanaPrincipal.setVisible(false);
	}
	
	public void mostrarVentanaClientes() { // Código para cambiar a la vista
		VentanaClientes 
		vistaClientesApp = new VentanaClientes();
		vistaClientesApp.setVisible(true);
		// ventanaPrincipal.setVisible(false);
	}

	public void goToExport() {
		TableModel model = ventanaStock.obtenerTableModel();
		modeloExport.exportarAExcel(model);
	}
	
	public void goToExport2() {
		TableModel model2 = ventanaClientes.obtenerTableModel();
		modeloExport2.exportarAExcel(model2);
	}
}
