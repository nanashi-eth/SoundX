package UI;

import UI.CustomComponents.BigIconButton;
import UI.CustomComponents.MedIconButton;
import UI.CustomComponents.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class SplitPane extends JSplitPane {
    private JPanel wrapperPanel;
    private MyScrollPane customScrollPane;
    private PlaylistPane playlistPane;
    private MedIconButton add;
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

        // Configura el panel wrapper
        wrapperPanel = new RoundedPanel(new BorderLayout());
        playlistPane = new PlaylistPane();
        Dimension d = new Dimension(600, 150);
        playlistPane.setMinimumSize(d);
        playlistPane.setPreferredSize(d);
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        wrap.setOpaque(false);
        add = new MedIconButton("\ue48e");
        wrapperPanel.add(playlistPane,BorderLayout.NORTH );
        wrapperPanel.add(customScrollPane, BorderLayout.CENTER);
        wrap.add(add, BorderLayout.NORTH);
        wrapperPanel.add(wrap, BorderLayout.SOUTH);

        // Configura el JSplitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(200);
        setLeftComponent(sideBar);
        setRightComponent(wrapperPanel);
    }

    public MyScrollPane getCustomScrollPane() {
        return customScrollPane;
    }
    public PlaylistPane getPlaylistPane(){
        return playlistPane;
    }
}
