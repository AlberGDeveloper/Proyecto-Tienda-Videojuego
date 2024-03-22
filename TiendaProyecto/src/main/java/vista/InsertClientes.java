package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertClientes extends JFrame {
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField tlfField;
	private JTextField dniField;
	private JTextField drccField;
	private JTextField emailField;

	public InsertClientes() {
		super("Insertar Clientes");

		setSize(800, 600); // Establecer el tamaño de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla

		// Crear un panel que contendrá los componentes
		JPanel panelComponentes = new JPanel();
		// Establecer el tamaño preferido del panel (por ejemplo, 800x600)
		panelComponentes.setPreferredSize(new Dimension(800, 600));

		// Cambiar el layout del panel a GridBagLayout
		panelComponentes.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        panelComponentes.setOpaque(false);

		// Agregar componentes al panel
		agregarComponentes(panelComponentes, gbc);

		// Configuración para el fondo (Ajusta la ruta y el tamaño según necesites)
		setContentPane(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("C:/Users/Alberto/Desktop/fondomockupinsertsc.png");
				g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		});

		// Agregar el panel al GridBagLayout principal
		getContentPane().add(panelComponentes);
	}

	private void agregarComponentes(JPanel panel, GridBagConstraints gbc) {
		// Campos y etiquetas
		JLabel dnilabel = new JLabel("DNI_Cliente:");
		dniField = new JTextField(20);
		dniField.setPreferredSize(new Dimension(400, 20)); // Establecer 4 cm de ancho y 1 cm de alto
		dniField.setOpaque(false); // Hacer el campo de texto transparente

		JLabel nombreLabel = new JLabel("Nombre:");
		nombreField = new JTextField(20);
		nombreField.setPreferredSize(new Dimension(400, 20));
		nombreField.setOpaque(false);

		JLabel apellidoLabel = new JLabel("Apellidos:");
		apellidoField = new JTextField(10);
		apellidoField.setPreferredSize(new Dimension(400, 20));
		apellidoField.setOpaque(false);

		JLabel tlfLabel = new JLabel("Teléfono:");
		tlfField = new JTextField(10);
		tlfField.setPreferredSize(new Dimension(400, 20));
		tlfField.setOpaque(false);

		JLabel drccLabel = new JLabel("Dirección:");
		drccField = new JTextField(20);
		drccField.setPreferredSize(new Dimension(400, 20));
		drccField.setOpaque(false);
		
		JLabel emailLabel = new JLabel("Email:");
		emailField = new JTextField(20);
		emailField.setPreferredSize(new Dimension(400, 20));
		emailField.setOpaque(false);

		// Agregar componentes al panel
		panel.add(dnilabel, gbc);
		panel.add(dniField, gbc);
		panel.add(nombreLabel, gbc);
		panel.add(nombreField, gbc);
		panel.add(apellidoLabel, gbc);
		panel.add(apellidoField, gbc);
		panel.add(tlfLabel, gbc);
		panel.add(tlfField, gbc);
		panel.add(drccLabel, gbc);
		panel.add(drccField, gbc);
		panel.add(emailLabel, gbc);
		panel.add(emailField, gbc);

		// Botón para añadir el videojuego
		JButton addButton = new JButton("Añadir cliente");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	aniadircliente();
            }
        });
        panel.add(addButton, gbc);

		// Botón para volver
		JButton backButton = new JButton("Volver a Clientes");
		backButton.addActionListener(e -> volverAVentanaClientes());
		panel.add(backButton, gbc);
	}

	private void aniadircliente() {
	    String dni = dniField.getText();
	    String nombre = nombreField.getText();
	    String apellidos = apellidoField.getText();
	    String telefono = tlfField.getText();
	    String direccion = drccField.getText();
	    String email = emailField.getText();

	    String sql = "INSERT INTO clientes (DNI_Cliente, Nombre, Apellidos, Teléfono, Dirección, Email) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda videojuegos g3", "root", "");
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, dni);
	        statement.setString(2, nombre);
	        statement.setString(3, apellidos);
	        statement.setString(4, telefono);
	        statement.setString(5, direccion);
	        statement.setString(6, email);

	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(this, "Se ha añadido el cliente correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo añadir el cliente.");
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(this, "Error al añadir el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void volverAVentanaClientes() {
		dispose(); // Cierra la ventana actual
		VentanaClientes ventanaclientes = new VentanaClientes();
		ventanaclientes.setVisible(true); // Muestra la ventana de clientes
	}
}