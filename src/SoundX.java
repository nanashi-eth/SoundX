import Controller.ConnectionDB;
import Controller.Controller;
import Exceptions.AppErrors;
import Exceptions.ErrorLogger;
import Exceptions.MyException;
import UI.SoundXFrame;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;

import javax.swing.*;
import java.sql.SQLException;

public class SoundX {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.setup();
            try {
                UIManager.setLookAndFeel(new FlatDarculaLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }
            SoundXFrame ventana = new SoundXFrame();
            // Abre la conexión a la base de datos
            try {
                ConnectionDB.openConnection();
                ConnectionDB.executeScript();
            } catch (MyException e) {
                ErrorLogger.getInstance().logError(AppErrors.DATABASE_OPEN_ERROR);
                ventana.mostrarError(e.getMessage());
            }
        });
    }
}
