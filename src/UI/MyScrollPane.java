package UI;

import javax.swing.*;
import java.awt.*;
import Utils.FontManager;

public class MyScrollPane extends JScrollPane {
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public MyScrollPane() {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        setViewportView(list);
        setupScrollPane();
        setupList();
    }

    private void setupScrollPane() {
        setBorder(BorderFactory.createEmptyBorder());
        getViewport().setOpaque(false);
        setOpaque(false);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setBackground(new Color(18, 18, 18)); // Establecer color de fondo RGB (18, 18, 18)
    }

    private void setupList() {
        Font font = FontManager.cargarFuente("spotify.otf", 13f);
        setFontRecursively(this, font);
        list.setCellRenderer(new DefaultListCellRenderer() {
            private final int padding = 10;

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
                label.setForeground(Color.WHITE);
                if (isSelected) {
                    label.setBackground(Color.WHITE.darker());
                    label.setForeground(new Color(18, 18, 18));
                } else {
                    label.setBackground(new Color(18, 18, 18));
                    label.setForeground(Color.WHITE);
                }
                return label;
            }
        });
        list.setBackground(new Color(18, 18, 18));
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JList<String> getList() {
        return list;
    }

    private void setFontRecursively(Container container, Font font) {
        for (Component comp : container.getComponents()) {
            comp.setFont(font);
            if (comp instanceof Container) {
                setFontRecursively((Container) comp, font);
            }
        }
    }
}
