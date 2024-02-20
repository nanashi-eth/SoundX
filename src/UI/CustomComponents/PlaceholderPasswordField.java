package UI.CustomComponents;

import Utils.FontManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderPasswordField extends JPasswordField {
    private String placeholder;
    private Font font;
    private Color containerBackground;
    private JLabel iconLabel;
    private Font icon = FontManager.cargarFuente("icon.otf", 15f);

    public PlaceholderPasswordField(Container parent, int columns, String placeholder) {
        super(columns);
        this.placeholder = placeholder;
        this.iconLabel = new JLabel();
        this.iconLabel.setFont(icon);
        setupUI(parent);
        setupEventListeners();
    }

    private void setupUI(Container parent) {
        setLayout(new BorderLayout());
        setOpaque(false);
        add(iconLabel, BorderLayout.EAST);

        // Eliminar el borde redondeado y utilizar una línea sólida en la parte inferior
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        setMargin(new Insets(5, 5, 5, 5));
        setOpaque(false);

        // Obtener el color de fondo del componente contenedor
        containerBackground = parent.getBackground();

        setForeground(Color.GRAY);
        setText(placeholder);
    }

    private void setupEventListeners() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                handleFocus(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                handleFocus(false);
            }
        });

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setForeground(Color.WHITE);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setForeground(Color.WHITE);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setForeground(Color.WHITE);
            }
        });
    }

    private void handleFocus(boolean hasFocus) {
        setBackground(hasFocus ? containerBackground.darker().brighter() : containerBackground);
        if (String.valueOf(getPassword()).equals(placeholder)) {
            setText(hasFocus ? "" : placeholder);
            setEchoChar(hasFocus ? '•' : '\0');
        } else {
            setForeground(hasFocus ? Color.WHITE : Color.GRAY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (String.valueOf(getPassword()).isEmpty() && !hasFocus()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }

    // Método para cambiar el color de fondo
    public void setCustomBackgroundColor(Color color) {
        setBackground(color);
        repaint(); // Es posible que necesites repintar el componente para que los cambios sean visibles
    }

    public void setPlaceholder(String newPlaceholder) {
        placeholder = newPlaceholder;
        setText(placeholder);
        if (!hasFocus()) {
            setForeground(Color.GRAY);
        }
    }

    public void valid() {
        this.iconLabel.setFont(icon);
        iconLabel.setText("\uf14a");
        iconLabel.setForeground(new Color(167, 167, 167));
    }

    public void invalid() {
        iconLabel.setText("");
        if (!placeholder.equals(getText())) {
            setForeground(new Color(204, 0, 0));
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }
}

