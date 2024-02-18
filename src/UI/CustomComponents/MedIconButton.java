package UI.CustomComponents;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MedIconButton extends IconButton{
    public MedIconButton(String iconUnicode) {
        super(iconUnicode);
        setFont(getFont().deriveFont(32f));
        applyHoverEffect();
        setMinimumSize(new Dimension(40, 40));
    }

    private void applyHoverEffect() {
        this.setBorderPainted(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setFont(getFont().deriveFont(Font.PLAIN, 34f));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setFont(getFont().deriveFont(Font.PLAIN, 32f));
            }
        });
    }
}
