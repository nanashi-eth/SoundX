package Utils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FontManager {
    private static HashMap<String, Font> fonts = new HashMap<>();

    public static Font cargarFuente(String rutaFuente, float size) {
        rutaFuente ="../assets/fonts/" + rutaFuente;
        if (fonts.containsKey(rutaFuente)) {
            return fonts.get(rutaFuente).deriveFont(size);
        } else {
            try {
                InputStream is = FontManager.class.getResourceAsStream(rutaFuente);
                Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                fonts.put(rutaFuente, font);
                return font.deriveFont(size);
            } catch (IOException | FontFormatException e) {
                return null;
            }
        }
    }
}
