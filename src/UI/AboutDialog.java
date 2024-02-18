package UI;

import Utils.FontManager;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "Acerca de", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.setBackground(new Color(18, 18, 18));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Acerca de la Aplicación");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        JLabel authorsLabel = new JLabel("Autores:");
        authorsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(authorsLabel);
        JLabel author2Label = new JLabel("Jesus Rodriguez");
        author2Label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(author2Label);
        JLabel author1Label = new JLabel("Jose Benitez");
        author1Label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(author1Label);

        JLabel versionLabel = new JLabel("Versión:");
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(versionLabel);
        JLabel version1Label = new JLabel("1.0");
        version1Label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(version1Label);
        getRootPane().setBackground(new Color(18, 18, 18));
        getContentPane().setBackground(new Color(18, 18, 18));
        getContentPane().add(panel);
        setFontForAllComponents(FontManager.cargarFuente("spotify.otf", 13f));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 19f));
        authorsLabel.setFont(authorsLabel.getFont().deriveFont(Font.BOLD, 15f));
        versionLabel.setFont(versionLabel.getFont().deriveFont(Font.BOLD, 15f));
    }

    public void setFontForAllComponents(Font font) {
        for (Component component : getComponents()) {
            setFontForComponentAndChildren(component, font);
        }
    }

    private void setFontForComponentAndChildren(Component component, Font font) {
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                setFontForComponentAndChildren(child, font);
            }
        }
        component.setFont(font);
        component.setForeground(Color.WHITE);
    }
}
