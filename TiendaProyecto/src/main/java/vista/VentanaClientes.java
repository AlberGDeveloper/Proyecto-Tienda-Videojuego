package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controlador.Controlador;
import modelo.CompruebaAcceso;
import modelo.Conexion;
import modelo.ExportClientes;
import modelo.Exportxls;

public class VentanaClientes extends JFrame {
	private BufferedImage backgroundImage;
	private DefaultTableModel tableModel;
	private JTable table;
	private JPanel tablePanel;
	private Controlador controlador;
    private Exportxls modeloExport;
    private ExportClientes modeloExport2;

	public VentanaClientes() {
		InsertJuegos insertjuegos = null;
		LoginView loginview = null;
		CompruebaAcceso compruebaacceso = null;
		Conexion conexion = null;
		VentanaprincipalApp ventanaPrincipal = null;
		VentanaStock ventanastock = null;
		modeloExport2 = new ExportClientes(); 
        controlador = new Controlador(insertjuegos, loginview, compruebaacceso, conexion, ventanaPrincipal, ventanastock, this, modeloExport, modeloExport2);
		// Configuración de la ventana y otros componentes
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		// Cargar la imagen de fondo desde una URL
		try {
			String imagePath = "C:/Users/Alberto/Desktop/fondomockup2.png";
			backgroundImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Configurar el fondo del JFrame
		setContentPane(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Dibujar el fondo de pantalla si está cargado
				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		});

		// Inicializar tabla
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.setBackground(new Color(241, 234, 209));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 100));

		// Inicializar el JPanel que contendrá la tabla
		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBackground(new Color(241, 234, 209));

		// Calcular el tamaño preferido del panel
		int panelWidth = (int) (800 * 0.8); // Ancho ajustado
		int panelHeight = scrollPane.getPreferredSize().height; // Alto ajustado

		// Establecer el tamaño preferido del panel
		tablePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

		// Mover el JScrollPane hacia abajo dentro del tablePanel
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Agregar botones
		JButton backButton = new JButton("Volver");
		backButton.setVerticalAlignment(SwingConstants.BOTTOM);
		backButton.setBackground(new Color(241, 234, 209));
		ImageIcon backIcon = new ImageIcon("C:/Users/Alberto/Desktop/iconback.png");
		backButton.setIcon(backIcon);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cierra la ventana actual y vuelve a la ventana anterior
				dispose();
				VentanaprincipalApp ventanaPrincipal = new VentanaprincipalApp();
				ventanaPrincipal.setVisible(true);
			}
		});

		// Botón para insertar
		JButton insertButton = new JButton("Añadir");
		insertButton.setBackground(new Color(241, 234, 209));
		ImageIcon backIcon2 = new ImageIcon("C:/Users/Alberto/Desktop/inserticon.png");
		insertButton.setIcon(backIcon2);
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cierra la ventana actual y abre la ventana para insertar clientes
				dispose();
				InsertClientes insertClientes = new InsertClientes();
				insertClientes.setVisible(true);
			}
		});

		// Panel de botones
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(128, 0, 0));
		buttonPanel.setBorder(null);
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(10);
		flowLayout.setHgap(150);
		buttonPanel.add(backButton);
		buttonPanel.add(insertButton);

		// Agregar botones y JPanel al JFrame
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(tablePanel, BorderLayout.SOUTH);
		
		// Crear un JPanel para el botón "Exportar"
		JPanel exportPanel = new JPanel(new GridBagLayout());
		exportPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 10, 10); // Márgenes personalizados
		gbc.anchor = GridBagConstraints.SOUTHEAST; // Posiciona el botón en la esquina inferior derecha

		// Crear un botón "Exportar"
		JButton exportButton = new JButton();
		ImageIcon exporticon = new ImageIcon("C:/Users/Alberto/Desktop/exporticon.png");
		exportButton.setBorderPainted(false); // Elimina el borde para que parezca un icono
		exportButton.setContentAreaFilled(false); // Elimina el relleno
		exportButton.setFocusPainted(false); // Elimina el foco al hacer clic
		exportButton.setIcon(exporticon);
		//exportButton.setPreferredSize(new Dimension(100, 30));
		exportButton.setPreferredSize(new Dimension(exporticon.getIconWidth(), exporticon.getIconHeight()));

		// Agregar el botón "Exportar" al panel de exportación
		exportPanel.add(exportButton, gbc);

		// Agregar el panel de exportación al BorderLayout del JFrame
		add(exportPanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		//int x = 100; // Valor X deseado
		//int y = 100; // Valor Y deseado
		//exportPanel.setLocation(x, y);

		setLocationRelativeTo(null);
		

		
		exportButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	//setVisible(false);
		    	controlador.goToExport2();
		    }
		});

		// Conexión a la base de datos y carga de datos
		loadClientesData();
	}
	
	public TableModel obtenerTableModel() {
	    return table.getModel();
	}
	

	// Método para cargar los datos de los clientes desde la base de datos
	private void loadClientesData() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda videojuegos g3",
					"root", "");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes");

			// Obtener datos de la base de datos y actualizar la tabla
			tableModel.setRowCount(0);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			Vector<String> columnNames = new Vector<String>();

			// Obtener nombres de columnas
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}

			tableModel.setColumnIdentifiers(columnNames);

			// Obtener datos de filas
			while (resultSet.next()) {
				Vector<Object> row = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					row.add(resultSet.getObject(columnIndex));
				}
				tableModel.addRow(row);
			}

			// Cerrar los recursos al final
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}