package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertJuegos extends JFrame {
	private JTextField nombreField;
	private JTextField unidadesField;
	private JTextField precioField;
	private JTextField idField;
	private JTextField plataformaField;

	public InsertJuegos() {
		super("Insertar Videojuego");

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
				ImageIcon img = new ImageIcon("C:/Users/Alberto/Desktop/fondomockupinserts.png");
				g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		});

		// Agregar el panel al GridBagLayout principal
		getContentPane().add(panelComponentes);
	}

	private void agregarComponentes(JPanel panel, GridBagConstraints gbc) {
		// Campos y etiquetas
		JLabel idLabel = new JLabel("Id:");
		idField = new JTextField(20);
		idField.setPreferredSize(new Dimension(400, 20)); // Establecer 4 cm de ancho y 1 cm de alto
		idField.setOpaque(false); // Hacer el campo de texto transparente

		JLabel plataformaLabel = new JLabel("Plataforma:");
		plataformaField = new JTextField(20);
		plataformaField.setPreferredSize(new Dimension(400, 20));
		plataformaField.setOpaque(false);

		JLabel precioLabel = new JLabel("Precio:");
		precioField = new JTextField(10);
		precioField.setPreferredSize(new Dimension(400, 20));
		precioField.setOpaque(false);

		JLabel unidadesLabel = new JLabel("Stock:");
		unidadesField = new JTextField(10);
		unidadesField.setPreferredSize(new Dimension(400, 20));
		unidadesField.setOpaque(false);

		JLabel nombreLabel = new JLabel("Título:");
		nombreField = new JTextField(20);
		nombreField.setPreferredSize(new Dimension(400, 20));
		nombreField.setOpaque(false);

		// Agregar componentes al panel
		panel.add(idLabel, gbc);
		panel.add(idField, gbc);
		panel.add(plataformaLabel, gbc);
		panel.add(plataformaField, gbc);
		panel.add(precioLabel, gbc);
		panel.add(precioField, gbc);
		panel.add(unidadesLabel, gbc);
		panel.add(unidadesField, gbc);
		panel.add(nombreLabel, gbc);
		panel.add(nombreField, gbc);

		// Botón para añadir el videojuego
		JButton addButton = new JButton("Añadir videojuego");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aniadirVideojuego();
            }
        });
        panel.add(addButton, gbc);

		// Botón para volver
		JButton backButton = new JButton("Volver a Stock");
		backButton.addActionListener(e -> volverAVentanaStock());
		panel.add(backButton, gbc);
	}

	private void aniadirVideojuego() {
	    String titulo = nombreField.getText();
	    String id = idField.getText();
	    String plataforma = plataformaField.getText();
	    String unidades = unidadesField.getText();
	    String precio = precioField.getText();

	    String sql = "INSERT INTO videojuegos (ID_Videojuego, Plataforma, Precio, Stock, Titulo) VALUES (?, ?, ?, ?, ?)";
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda videojuegos g3", "root", "");
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, id);
	        statement.setString(2, plataforma);
	        statement.setString(3, precio);
	        statement.setString(4, unidades);
	        statement.setString(5, titulo);

	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            JOptionPane.showMessageDialog(this, "Se ha añadido el videojuego correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo añadir el videojuego.");
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(this, "Error al añadir el videojuego: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void volverAVentanaStock() {
		dispose(); // Cierra la ventana actual
		VentanaStock ventanaStock = new VentanaStock();
		ventanaStock.setVisible(true); // Muestra la ventana de stock
	}
}