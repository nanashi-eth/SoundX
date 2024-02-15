package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class IconButton extends JButton {
    private static final int ICON_SIZE = 15;
    private static final String SPACER = "        ";
    CustomButtonUI ui;

    public IconButton(String text, String icon, ActionListener actionListener) {
        // Configurar el texto del botón
        super(SPACER + text);
        addActionListener(actionListener);

        // Configurar las fuentes
        Font font = Utils.FontManager.cargarFuente("icon.otf", 13f).deriveFont(Font.PLAIN, ICON_SIZE);
        Font main = Utils.FontManager.cargarFuente("spotify-bold.otf", 13f);
        setFont(main);

        // Configurar el JLabel para el icono
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(font);

        // Configurar la posición del texto y del icono a la izquierda
        setHorizontalAlignment(JButton.LEFT);
        setVerticalTextPosition(JButton.CENTER);

        // Configurar espacio entre icono y texto
        setIconTextGap(10);

        // Configurar la alineación
        setAlignmentX(Component.LEFT_ALIGNMENT);

        // Quitar el borde
        setBorderPainted(false);

        // Obtener el color de fondo predeterminado de UIManager
        Color backgroundColor = getBackground();

        // Configurar el color de fondo del botón
        setBackground(backgroundColor.brighter().darker());

        // Configurar el diseño con un JLabel para el icono en la posición WEST (izquierda)
        setLayout(new BorderLayout());
        add(iconLabel, BorderLayout.WEST);
        ui = new CustomButtonUI();
        // Aplicar ButtonUI personalizado
        setUI(ui);
    }
    
    public void paintLine(IconButton b) {
        System.out.println(this.equals(b));
        if (this.equals(b)) {
            ui.setShouldPaintHoverLineAndRepaint(true);
            repaint();
        }
        else {
            ui.setShouldPaintHoverLineAndRepaint(false);
            repaint();
        }
    }
}
