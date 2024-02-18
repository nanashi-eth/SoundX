package UI;

import UI.CustomComponents.MyScrollPane;
import UI.CustomComponents.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SplitPane extends JSplitPane {
    private JPanel wrapperPanel;
    private MyScrollPane customScrollPane;
    public SplitPane() {
        setBackground(Color.BLACK);
        SideBar sideBar = new SideBar();
        customScrollPane = new MyScrollPane(){
            @Override
            public Dimension getMinimumSize()
            {
                Dimension d = getSize();
                d.width = 600;
                return d;
            }
        };
        DefaultListModel<String[]> empleadosListModel = customScrollPane.getListModel();
        String [] hola = {"4Roses.jpg", "Jose", "Antonio", "Jesus"};
        empleadosListModel.addElement(hola);
        empleadosListModel.addElement(hola);
        empleadosListModel.addElement(hola);
        // Añade más elementos si es necesario

        // Configura el panel wrapper
        wrapperPanel = new RoundedPanel(new BorderLayout());
        wrapperPanel.add(customScrollPane, BorderLayout.CENTER);

        // Configura el JSplitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(200);
        setLeftComponent(sideBar);
        setRightComponent(wrapperPanel);
    }
}
