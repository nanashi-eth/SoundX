package UI;
import javax.swing.*;
import java.awt.*;

public class SideBar extends RoundedPanel {
    public SideBar() {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(200, getHeight()));

        // Agrega los componentes de la barra lateral
        JLabel label = new JLabel("Opciones de usuario");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JButton button1 = new IconButton("Opción 1", "\uF0C7", e -> saludar());
        JButton button2 = new IconButton("Opción 2", "\uF0C7", e -> saludar());
        JButton button3 = new IconButton("Opción 3", "\uF0C7", e -> saludar());
        JButton button4 = new IconButton("Opción 4", "\uF0C7", e -> saludar());
        JButton button5 = new IconButton("Opción 5", "\uF0C7", e -> saludar());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void saludar() {
        System.out.println("GHola");
    }
}

