package Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageManager {
    private static HashMap<String, BufferedImage> images = new HashMap<>();
    private static final String DEFAULT_IMAGE_PATH = "default_cover.jpg";

    public static Image cargarImagen(String rutaImagen, int width, int height) {
        rutaImagen = System.getProperty("user.dir") + "/src/assets/images/" + rutaImagen;
        return getImage(rutaImagen, width, height);
    }
    
    public static Image cargarImagen(String rutaImagen, int width, int height, boolean cover) {
        String ruta = System.getProperty("user.dir") + "/src/Data/";
        String endpoint;
        if (cover) {
            endpoint = "Cover/" + rutaImagen;
        }else{
            endpoint = "Profile/" + rutaImagen;
        }
        ruta += endpoint;
        return getImage(ruta, width, height);
    }

    private static Image getImage(String rutaImagen, int width, int height) {
        if (images.containsKey(rutaImagen)) {
            return images.get(rutaImagen).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } else {
            try {
                BufferedImage img = ImageIO.read(new File(rutaImagen));
                images.put(rutaImagen, img);
                return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                return null;
            }
        }
    }

    public static Image cargarCover(String rutaImagen) {
        // Tamaño predeterminado para la portada
        int width = 40;
        int height = 40;

        // Intenta cargar la imagen especificada
        Image imagen = cargarImagen(rutaImagen, width, height, true);

        // Si la imagen es nula, cargar la imagen predeterminada
        if (imagen == null) {
            imagen = cargarImagen(DEFAULT_IMAGE_PATH, width, height);
        }

        return imagen;
    }
}

