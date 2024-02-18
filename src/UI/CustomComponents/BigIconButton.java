package UI.CustomComponents;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BigIconButton extends IconButton{
    public BigIconButton(String iconUnicode) {
        super(iconUnicode);
        setFont(getFont().deriveFont(48f));
        applyHoverEffect();
        setMinimumSize(new Dimension(60, 60));
    }
    
    private void applyHoverEffect() {
        this.setBorderPainted(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setFont(getFont().deriveFont(Font.PLAIN, 52f));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setFont(getFont().deriveFont(Font.PLAIN, 48f));
            }
        });
    }
    
}
