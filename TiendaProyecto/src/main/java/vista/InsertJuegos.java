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
        panelComponentes.setOpaque(false);

        // Configuración para el fondo (Ajusta la ruta y el tamaño según necesites)
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(getClass().getResource("/images/fondomockupinsertsc.png"));
                if (img.getImage() != null) {
                    g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                } else {
                    System.err.println("No se pudo cargar la imagen de fondo.");
                }
            }
        });

        // Agregar el panel al GridBagLayout principal
        getContentPane().add(panelComponentes);

        // Agregar componentes al panel
        agregarComponentes(panelComponentes);
    }

    private void agregarComponentes(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes

        // Campos y etiquetas
        JLabel idLabel = new JLabel("Id:");
        idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(400, 20)); // Establecer 4 cm de ancho y 1 cm de alto
        idField.setOpaque(false); // Hacer el campo de texto transparente

        GridBagConstraints gbcIdLabel = new GridBagConstraints();
        gbcIdLabel.gridx = 0;
        gbcIdLabel.gridy = 0;
        gbcIdLabel.insets = new Insets(5, 5, 5, 5);
        gbcIdLabel.anchor = GridBagConstraints.WEST;
        panel.add(idLabel, gbcIdLabel);

        GridBagConstraints gbcIdField = new GridBagConstraints();
        gbcIdField.gridx = 1;
        gbcIdField.gridy = 0;
        gbcIdField.insets = new Insets(5, 5, 5, 5);
        gbcIdField.fill = GridBagConstraints.HORIZONTAL;
        panel.add(idField, gbcIdField);

        JLabel plataformaLabel = new JLabel("Plataforma:");
        plataformaField = new JTextField(20);
        plataformaField.setPreferredSize(new Dimension(400, 20));
        plataformaField.setOpaque(false);

        GridBagConstraints gbcPlataformaLabel = new GridBagConstraints();
        gbcPlataformaLabel.gridx = 0;
        gbcPlataformaLabel.gridy = 1;
        gbcPlataformaLabel.insets = new Insets(5, 5, 5, 5);
        gbcPlataformaLabel.anchor = GridBagConstraints.WEST;
        panel.add(plataformaLabel, gbcPlataformaLabel);

        GridBagConstraints gbcPlataformaField = new GridBagConstraints();
        gbcPlataformaField.gridx = 1;
        gbcPlataformaField.gridy = 1;
        gbcPlataformaField.insets = new Insets(5, 5, 5, 5);
        gbcPlataformaField.fill = GridBagConstraints.HORIZONTAL;
        panel.add(plataformaField, gbcPlataformaField);

        JLabel precioLabel = new JLabel("Precio en €:");
        precioField = new JTextField(10);
        precioField.setPreferredSize(new Dimension(400, 20));
        precioField.setOpaque(false);

        GridBagConstraints gbcPrecioLabel = new GridBagConstraints();
        gbcPrecioLabel.gridx = 0;
        gbcPrecioLabel.gridy = 2;
        gbcPrecioLabel.insets = new Insets(5, 5, 5, 5);
        gbcPrecioLabel.anchor = GridBagConstraints.WEST;
        panel.add(precioLabel, gbcPrecioLabel);

        GridBagConstraints gbcPrecioField = new GridBagConstraints();
        gbcPrecioField.gridx = 1;
        gbcPrecioField.gridy = 2;
        gbcPrecioField.insets = new Insets(5, 5, 5, 5);
        gbcPrecioField.fill = GridBagConstraints.HORIZONTAL;
        panel.add(precioField, gbcPrecioField);

        JLabel unidadesLabel = new JLabel("Stock:");
        unidadesField = new JTextField(10);
        unidadesField.setPreferredSize(new Dimension(400, 20));
        unidadesField.setOpaque(false);

        GridBagConstraints gbcUnidadesLabel = new GridBagConstraints();
        gbcUnidadesLabel.gridx = 0;
        gbcUnidadesLabel.gridy = 3;
        gbcUnidadesLabel.insets = new Insets(5, 5, 5, 5);
        gbcUnidadesLabel.anchor = GridBagConstraints.WEST;
        panel.add(unidadesLabel, gbcUnidadesLabel);

        GridBagConstraints gbcUnidadesField = new GridBagConstraints();
        gbcUnidadesField.gridx = 1;
        gbcUnidadesField.gridy = 3;
        gbcUnidadesField.insets = new Insets(5, 5, 5, 5);
        gbcUnidadesField.fill = GridBagConstraints.HORIZONTAL;
        panel.add(unidadesField, gbcUnidadesField);

        JLabel nombreLabel = new JLabel("Título:");
        nombreField = new JTextField(20);
        nombreField.setPreferredSize(new Dimension(400, 20));
        nombreField.setOpaque(false);

        GridBagConstraints gbcNombreLabel = new GridBagConstraints();
        gbcNombreLabel.gridx = 0;
        gbcNombreLabel.gridy = 4;
        gbcNombreLabel.insets = new Insets(5, 5, 5, 5);
        gbcNombreLabel.anchor = GridBagConstraints.WEST;
        panel.add(nombreLabel, gbcNombreLabel);

        GridBagConstraints gbcNombreField = new GridBagConstraints();
        gbcNombreField.gridx = 1;
        gbcNombreField.gridy = 4;
        gbcNombreField.insets = new Insets(5, 5, 5, 5);
        gbcNombreField.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nombreField, gbcNombreField);

        // Botón para añadir el videojuego
        JButton addButton = new JButton("Añadir videojuego");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aniadirVideojuego();
            }
        });

        GridBagConstraints gbcAddButton = new GridBagConstraints();
        gbcAddButton.gridx = 0;
        gbcAddButton.gridy = 5;
        gbcAddButton.gridwidth = 2;
        gbcAddButton.insets = new Insets(5, 5, 5, 5);
        gbcAddButton.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbcAddButton);

        // Botón para volver
        JButton backButton = new JButton("Volver a Stock");
        backButton.addActionListener(e -> volverAVentanaStock());

        GridBagConstraints gbcBackButton = new GridBagConstraints();
        gbcBackButton.gridx = 0;
        gbcBackButton.gridy = 6;
        gbcBackButton.gridwidth = 2;
        gbcBackButton.insets = new Insets(5, 5, 5, 5);
        gbcBackButton.anchor = GridBagConstraints.CENTER;
        panel.add(backButton, gbcBackButton);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsertJuegos frame = new InsertJuegos();
            frame.setVisible(true);
        });
    }
}

