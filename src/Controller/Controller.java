package Controller;

import Exceptions.MyException;
import Modelo.Usuario;
import UI.LoginShadowPanel;
import UI.SoundXFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controller {
    private static Usuario usuarioActual = new Usuario();
    private final LoginShadowPanel loginShadowPanel;
    private final SoundXFrame ventana;

    public Controller(SoundXFrame ventana, LoginShadowPanel login) {
        this.ventana = ventana;
        this.loginShadowPanel = login;
        this.loginShadowPanel.setLogin(e -> login());
        this.ventana.exit(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ConnectionDB.deleteDB();
                ventana.dispose();
            }
        });
    }

    public void login(){
        try {
            // Crear una instancia de UserManager
            UserManager userManager = new UserManager(loginShadowPanel.getUsername().toLowerCase().trim());

            // Llamar al método validateUsuario con los campos de texto
            boolean isValid = userManager.validateUsuario(loginShadowPanel.getUsername().toLowerCase().trim(), loginShadowPanel.getPassword().toLowerCase().trim());

            // Si la validación es exitosa, puedes realizar acciones adicionales aquí
            if (isValid) {
                // Por ejemplo, mostrar un mensaje de éxito o cambiar a otra ventana
                loginShadowPanel.getParentFrame().mostrarMensaje("Inicio de sesión exitoso");
                usuarioActual.setNombreUsuario(loginShadowPanel.getUsername().toLowerCase().trim());
                usuarioActual.setPassword(loginShadowPanel.getPassword().toLowerCase().trim());
                usuarioActual.setUserID(userManager.getUserIdByName(usuarioActual.getNombreUsuario())); 
                ventana.changePanel();
            } else {
                // Si la validación falla, puedes mostrar un mensaje de error o tomar otras medidas
                loginShadowPanel.getParentFrame().mostrarError("Usuario o contraseña incorrectos");
            }
        } catch (MyException ex) {
            loginShadowPanel.getParentFrame().mostrarError(ex.getMessage());
        }        
    }
}
