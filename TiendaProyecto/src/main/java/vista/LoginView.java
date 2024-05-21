package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.CompruebaAcceso;
import modelo.Conexion;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panelFondo;

    public String getUsernameField() {
        return usernameField.getText();
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public char[] getPasswordFieldValue() {
        return passwordField.getPassword();
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1124, 868);
        setLocationRelativeTo(null);

        // Crear un JPanel para actuar como Background
        panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/images/Login.png"));
                if (imagenFondo.getImage() != null) {
                    g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.err.println("No se pudo cargar la imagen de fondo.");
                }
            }
        };

        // Configuración del diseño del JPanel
        panelFondo.setLayout(new GridBagLayout());

        // Restricciones para controlar la posición y el tamaño de los componentes
        GridBagConstraints userConstraints = new GridBagConstraints();
        userConstraints.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes
        userConstraints.gridx = 1;
        userConstraints.gridy = 1;

        usernameField = new JTextField(25);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 65)); // Ajustar la altura
        usernameField.setOpaque(false);
        panelFondo.add(usernameField, userConstraints);

        GridBagConstraints pwdConstraints = new GridBagConstraints();
        pwdConstraints.insets = new Insets(20, 10, 10, 10); // Ajustar márgenes
        pwdConstraints.gridx = 1;
        pwdConstraints.gridy = 2;
        pwdConstraints.anchor = GridBagConstraints.NORTH; // Alinea hacia arriba

        passwordField = new JPasswordField(25);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 65));
        passwordField.setOpaque(false);
        panelFondo.add(passwordField, pwdConstraints);

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.insets = new Insets(20, 10, 10, 10); // Ajustar márgenes
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 3;
        buttonConstraints.anchor = GridBagConstraints.CENTER;

        // Botón de login
        loginButton = new JButton("Entrar");
        loginButton.setBackground(Color.decode("#DEB46C"));
        loginButton.setFont(new Font("Papyrus", Font.ITALIC, 16));
        loginButton.setPreferredSize(new Dimension(150, 40));
        panelFondo.add(loginButton, buttonConstraints);

        // Añadir el panelFondo al JFrame
        getContentPane().add(panelFondo);

        // Configurar el ActionListener para el botón de login
        loginButton.addActionListener(e -> {
            Conexion conexion = new Conexion(usernameField.getText(), passwordField.getPassword());
            Conexion.conectarABaseDeDatos();
            CompruebaAcceso.validarCredenciales(this);
        });

        // Agregar FocusListener para el campo de usuario
        addPlaceholder(usernameField, "Ingrese su nombre de usuario...");

        // Agregar FocusListener para el campo de contraseña
        addPlaceholder(passwordField, "Ingrese su contraseña...");
    }

    private void addPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.setHorizontalAlignment(JTextField.CENTER);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
}
