package UI;

import Utils.FontManager;
import Utils.RequestFocusListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginShadowPanel extends RoundedShadowPanel {
    private PlaceholderTextField usernameField;
    private PlaceholderPasswordField passwordField;
    private Font title = FontManager.cargarFuente("spotify-bold.otf", 24f);
    private Font text = FontManager.cargarFuente("spotify.otf", 13f);
    private JFrame parentFrame;

    public LoginShadowPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout()); // Establecer el diseño como GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Crear y agregar el label de Login
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(title);
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el componente horizontalmente
        add(loginLabel, gbc);

        // Crear y agregar un panel para los campos de usuario y contraseña
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false); // Hacer que el panel sea transparente
        fieldsPanel.setPreferredSize(new Dimension(200, 100)); // Establecer un tamaño fijo para el panel
        gbc.gridy++;
        add(fieldsPanel, gbc);

        // Agregar el campo de nombre de usuario al panel de campos
        usernameField = new PlaceholderTextField(this, "Usuario", 15);
        usernameField.setFont(text);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el componente horizontalmente
        fieldsPanel.add(usernameField, gbc);

        // Agregar el campo de contraseña al panel de campos
        passwordField = new PlaceholderPasswordField(this, 15);
        passwordField.setFont(text);
        gbc.gridy++;
        fieldsPanel.add(passwordField, gbc);
        usernameField.addAncestorListener(new RequestFocusListener(true));
        passwordField.addAncestorListener(new RequestFocusListener(true));

        // Agregar botones personalizados en la parte inferior
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(createCustomButton("Iniciar Sesión"), gbc);
        gbc.gridy++;
        CustomButton register = createCustomButton("Registrarse");
        add(register, gbc);
        parentFrame.getRootPane().setDefaultButton(register);
        register.addAncestorListener(new RequestFocusListener(false));
        register.requestFocus();
        register.requestFocusInWindow();
        register.addActionListener(e -> clickListener(e));
    }

    private void clickListener(ActionEvent e) {
        
    }

    // Método para crear un botón personalizado
    private CustomButton createCustomButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setPreferredSize(new Dimension(130, 30)); // Tamaño deseado para los botones
        return button;
    }

    // Método para obtener el nombre de usuario ingresado
    public String getUsername() {
        return usernameField.getText();
    }

    // Método para obtener la contraseña ingresada
    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }
}
