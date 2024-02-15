package Exceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorLogger {
    private static ErrorLogger instance = new ErrorLogger();
    private List<ErrorEntry> errorLog = new ArrayList<>();

    private ErrorLogger() {}

    public static ErrorLogger getInstance() {
        return instance;
    }

    public void logError(int errorCode) {
        String errorMessage = AppErrors.getErrorMessage(errorCode);
        ErrorEntry errorEntry = new ErrorEntry(errorCode, errorMessage);
        errorLog.add(errorEntry);
    }

    public List<ErrorEntry> getErrorLog() {
        return Collections.unmodifiableList(errorLog);
    }

    // Clase interna para representar una entrada de error con código y mensaje
    public static class ErrorEntry {
        private int errorCode;
        private String errorMessage;

        public ErrorEntry(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public void generateErrorReport(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Informe de Errores - " + LocalDateTime.now() + "\n\n");

            List<ErrorEntry> errorLog = getErrorLog();
            for (ErrorEntry errorEntry : errorLog) {
                writer.write("Código de Error: " + errorEntry.getErrorCode() + "\n");
                writer.write("Mensaje de Error: " + errorEntry.getErrorMessage() + "\n");
                writer.write("\n");
            }

            System.out.println("Informe de errores generado exitosamente en: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al generar el informe de errores: " + e.getMessage());
        }
    }
}
