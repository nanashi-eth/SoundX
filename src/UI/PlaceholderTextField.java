package UI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField {
    private String placeholder;
    private final JLabel iconLabel;
    private Font font;
    private Color containerBackground;

    public PlaceholderTextField(Container parent, String text, int columns) {
        super(columns);
//        initializeFont();
        this.placeholder = text;
        this.iconLabel = new JLabel();
        this.iconLabel.setFont(font);

        setupUI(parent);
        setupEventListeners();
    }

//    private void initializeFont() {
//        font = Utils.FontManager.getCustomIconFont().deriveFont(Font.PLAIN, 13);
//    }

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
        if (getText().equals(placeholder)) {
            setText(hasFocus ? "" : placeholder);
        } else {
            setForeground(hasFocus ? Color.WHITE : Color.GRAY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !hasFocus()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }

    public void addExternalDocumentListener(DocumentListener listener) {
        getDocument().addDocumentListener(listener);
    }

    // Método para cambiar el color de fondo
    public void setCustomBackgroundColor(Color color) {
        setBackground(color);
        repaint(); // Es posible que necesites repintar el componente para que los cambios sean visibles
    }

    public void valid() {
        this.iconLabel.setFont(font);
        iconLabel.setText("\uf14a");
        iconLabel.setForeground(new Color(255, 255, 255));
    }

    public void invalid() {
        iconLabel.setText("");
        if (!placeholder.equals(getText())) {
            setForeground(new Color(204, 0, 0));
        }
    }

    public void reset() {
        iconLabel.setText("");
    }
    public void setPlaceholder(String newPlaceholder) {
        placeholder = newPlaceholder;
        setText(placeholder);
        if (!hasFocus()) {
            setForeground(Color.GRAY);
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }
}
