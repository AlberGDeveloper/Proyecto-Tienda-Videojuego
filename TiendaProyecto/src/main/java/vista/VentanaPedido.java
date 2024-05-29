package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*; 
import java.util.Vector;
import javax.imageio.ImageIO;
import controlador.Controlador;
import modelo.CompruebaAcceso;
import modelo.Conexion;
import modelo.ExportClientes;
import modelo.Exportxls;  

public class VentanaPedidos extends JFrame {
    private BufferedImage backgroundImage;
    private DefaultTableModel tableModel;
    private JTable table;
    private JPanel tablePanel;
    private Controlador controlador;
    private Exportxls modeloExport;
    private ExportPedidos modeloExport2;

    public VentanaPedido() {
        InsertJuegos insertjuegos = null;
        LoginView loginview = null;
        CompruebaAcceso compruebaacceso = null;
        Conexion conexion = null;
        VentanaprincipalApp ventanaPrincipal = null;
        VentanaStock ventanastock = null;
        modeloExport2 = new ExportPedidos(); 
        controlador = new Controlador(insertjuegos, loginview, compruebaacceso, conexion, ventanaPrincipal, ventanastock, this, modeloExport, modeloExport2);

        // Configuración de la ventana y otros componentes
        setTitle("Pedidos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Cargar la imagen de fondo desde el classpath
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/fondomockup2.png"));
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
                } else {
                    System.err.println("No se pudo cargar la imagen de fondo.");
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
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/images/iconback.png"));
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
        ImageIcon backIcon2 = new ImageIcon(getClass().getResource("/images/inserticon.png"));
        insertButton.setIcon(backIcon2);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la ventana actual y abre la ventana para insertar pedidos
                dispose();
                InsertPedidos insertPedidos = new InsertClientes();
                insertPedidos.setVisible(true);
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
        ImageIcon exporticon = new ImageIcon(getClass().getResource("/images/exporticon.png"));
        exportButton.setBorderPainted(false); // Elimina el borde para que parezca un icono
        exportButton.setContentAreaFilled(false); 
        exportButton.setFocusPainted(false); 
        exportButton.setIcon(exporticon);
        exportButton.setPreferredSize(new Dimension(exporticon.getIconWidth(), exporticon.getIconHeight()));

        // Agregar el botón "Exportar" al panel de exportación
        exportPanel.add(exportButton, gbc);

        // Agregar el panel de exportación al BorderLayout del JFrame
        add(exportPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.goToExport2();
            }
        });

        // Conexión a la base de datos y carga de datos
        loadPedidosData();
    }
    
    public TableModel obtenerTableModel() {
        return table.getModel();
    }

    // Método para cargar los datos de los pedidos desde la base de datos
    private void loadPedidosData() {
        String url = "jdbc:mysql://localhost:3306/tienda videojuegos g3";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM pedidos")) {

            // Obtener datos de la base de datos y actualizar la tabla
            tableModel.setRowCount(0);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Vector<String> columnNames = new Vector<>();

            // Obtener nombres de columnas
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            tableModel.setColumnIdentifiers(columnNames);

            // Obtener datos de filas
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    row.add(resultSet.getObject(columnIndex));
                }
                tableModel.addRow(row);
            }
cliente
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de los pedidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
