package UI;

import javax.swing.*;
import java.awt.*;

public class SplitPane extends JSplitPane {
    public SplitPane() {
        SideBar sideBar = new SideBar();

        MyScrollPane customScrollPane = new MyScrollPane(){
            @Override
            public Dimension getMinimumSize()
            {
                Dimension d = getSize();
                d.width = 600;
                return d;
            }
        };
        DefaultListModel<String> empleadosListModel = customScrollPane.getListModel();
        empleadosListModel.addElement("Jose");
        empleadosListModel.addElement("Antonio");
        empleadosListModel.addElement("Jesus");
        // Añade más elementos si es necesario

        // Configura el panel wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(customScrollPane, BorderLayout.CENTER);

        // Configura el JSplitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(200);
        setLeftComponent(sideBar);
        setRightComponent(wrapperPanel);
    }

    private JPanel getjPanel() {
        MyScrollPane customScrollPane = new MyScrollPane(){
            @Override
            public Dimension getMinimumSize()
            {
                Dimension d = getSize();
                d.width = 600;
                return d;
            }
        };
        DefaultListModel<String> empleadosListModel = customScrollPane.getListModel();
        empleadosListModel.addElement("Jose");
        empleadosListModel.addElement("Antonio");
        empleadosListModel.addElement("Jesus");
        empleadosListModel.addElement("Jesus");
        empleadosListModel.addElement("Jesus");
        empleadosListModel.addElement("Jesus");
        empleadosListModel.addElement("Jesus");
        empleadosListModel.addElement("Jesus");
        empleadosListModel.addElement("Jesus"); 
        empleadosListModel.addElement("Jesus"); 
        empleadosListModel.addElement("Jesus"); 
        empleadosListModel.addElement("Jesus"); 
        empleadosListModel.addElement("Jesus"); 
        

        // Configura el panel wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(customScrollPane, BorderLayout.CENTER);
        return wrapperPanel;
    }
}
