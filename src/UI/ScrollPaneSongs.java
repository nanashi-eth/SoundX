package UI;

import UI.CustomComponents.CustomCellRenderer1;
import UI.CustomComponents.PlaceholderTextField;
import UI.CustomComponents.RoundedPanel;
import Utils.FontManager;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ScrollPaneSongs extends JScrollPane {
    private DefaultListModel<String[]> listModel;
    private JList<String[]> list;
    private JLabel headerLabel;
    private int mHoveredJListIndex;
    private PlaceholderTextField filterTextField;

    public ScrollPaneSongs() {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setCellRenderer(new CustomCellRenderer1(this));
        list.setBackground(new Color(18, 18, 18));
        list.setOpaque(false);
        setupFilterTextField();
        setupScrollPane();
        setupList();
        setupHeaderLabel();

        // Crear un JPanel para contener el filtro y el JList
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.add(filterTextField);
        panel.add(wrapper, BorderLayout.NORTH);
        panel.add(headerLabel, BorderLayout.CENTER);
        
        // Agregar el headerLabel encima del JList en un BorderLayout
        setColumnHeaderView(panel);
        getColumnHeader().setOpaque(false);

        // Agregar el panel al JScrollPane
        setViewportView(list);
        repaint();

        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShadowSize(10);
        shadow.setShowLeftShadow(false);
        shadow.setShowRightShadow(false);
        shadow.setShowBottomShadow(true);
        shadow.setShowTopShadow(false);

        // Agregar un listener de desplazamiento para controlar la sombra del headerLabel
        getVerticalScrollBar().addAdjustmentListener(e -> {
            int value = e.getValue();
            if (value > 0) {
                // Aplicar efecto de sombra si el desplazamiento es mayor que 0
                panel.setBorder(shadow);
            } else {
                // Eliminar el efecto de sombra si el desplazamiento es 0
                panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            }
        });
    }

    private void setupFilterTextField() {
        filterTextField = new PlaceholderTextField(this, "Buscar", 15);
        filterTextField.setOpaque(false);
        filterTextField.search();
        filterTextField.setPreferredSize(new Dimension(50, 30));
        filterTextField.setMaximumSize(new Dimension(50, 30));
        filterTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterList(filterTextField.getText());
            }
        });
    }

    private void filterList(String text) {
        DefaultListModel<String[]> filteredModel = new DefaultListModel<>();
        for (int i = 0; i < listModel.size(); i++) {
            String[] item = listModel.getElementAt(i);
            if (Arrays.toString(item).toLowerCase().contains(text.toLowerCase())) {
                filteredModel.addElement(item);
            }
        }
        list.setModel(filteredModel);
    }

    private void setupScrollPane() {
        setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        getViewport().setOpaque(false);
        setOpaque(false);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setBackground(new Color(18, 18, 18)); // Establecer color de fondo RGB (18, 18, 18)
    }

    private void setupList() {        
        list.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent me) {
                Point p = new Point(me.getX(), me.getY());
                int index = list.locationToIndex(p);
                // Obtener los límites del JList excluyendo el encabezado
                Rectangle bounds = list.getCellBounds(0, list.getModel().getSize() - 1);
                if (getColumnHeader().getHeight() > 0) {
                    bounds.y += getColumnHeader().getHeight() - 90; // Ajustar los límites para excluir el encabezado
                    bounds.height -= getColumnHeader().getHeight() - 30;
                }
                bounds.x += 10;
                bounds.width -= 10;
                // Verificar si el mouse está dentro de los límites del JList
                if (bounds.contains(p)) {
                    // El mouse está dentro del JList
                    if (index != mHoveredJListIndex) {
                        mHoveredJListIndex = index;
                    }
                } else {
                    // El mouse no está dentro del JList
                    mHoveredJListIndex = -1;
                }
                list.repaint();
            }
        });
    }

    private void setupHeaderLabel() {
        final String SPACER = "                                       ";
        String headerText = String.format("%-20s%-20s", "Title", "Artist");
        headerLabel = new JLabel(SPACER + headerText);
        String iconText = "\ue29e              \uf073             ";
        headerLabel.setLayout(new BorderLayout());
        JLabel iconLabel = new JLabel(iconText);
        iconLabel.setFont(FontManager.cargarFuente("icon.otf", 15));
        iconLabel.setForeground(new Color(186, 175, 161));
        headerLabel.add(iconLabel, BorderLayout.EAST);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        headerLabel.setOpaque(false);
        headerLabel.setFont(FontManager.cargarFuente("spotify.otf", 15));
        headerLabel.setForeground(new Color(186, 175, 161));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public DefaultListModel<String[]> getListModel() {
        return listModel;
    }

    public JList<String[]> getList() {
        return list;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar una línea entre el headerLabel y el JList
        int yHeader = headerLabel.getY(); // Obtener la altura del headerLabel
        int x1 = 5;
        int x2 = 580;
        int y = yHeader + 40; // Ajustar la posición de la línea

        g.setColor(new Color(186, 175, 161, 128)); // Color de la línea
        g.drawLine(x1, y, x2, y);
    }

    public int getmHoveredJListIndex() {
        return mHoveredJListIndex;
    }
}
