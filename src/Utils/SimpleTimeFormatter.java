package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTimeFormatter {
    public static String formatMinutes(double minutes) {
        // Calcular los minutos y segundos
        int minutos = (int) minutes;
        int segundos = (int) ((minutes - minutos) * 60);

        // Formatear la cadena en "mm:ss"
        return String.format("%02d:%02d", minutos, segundos);
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
