package Controller;

import Exceptions.AppErrors;
import Exceptions.ErrorLogger;
import Exceptions.MyException;

import java.io.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/practica06";
    private static final String USER = "admin";
    private static final String PASSWORD = "password";
    
    private static Connection connection;
    
    
    // Method to open the connection to the database
    public static void openConnection() throws MyException {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(URL + "?allowPublicKeyRetrieval=true" + "&useSSL=false", USER, PASSWORD);
            connection.setAutoCommit(false);

        } catch (ClassNotFoundException | SQLException e) {
            int errorCode = AppErrors.DATABASE_OPEN_ERROR;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }
    
    // Method to close the connection to the databse
    public static boolean cerrarConexion() throws MyException {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            int errorCode = AppErrors.DATABASE_CLOSE_ERROR;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(AppErrors.DATABASE_CLOSE_ERROR);
        } finally {
            return false;
        }
    }

    // Método para ejecutar un script SQL desde un archivo
    public static void executeScript() throws MyException {
        String filePath = System.getProperty("user.dir") + "/src/Data/db/Music.sql";
        try (Connection conn = getConnection()) {
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setLogWriter(null); 

            try (Reader reader = new BufferedReader(new FileReader(filePath))) {
                runner.runScript(reader);
            } catch (FileNotFoundException e) {
                throw new MyException(201);
            } catch (IOException e) {
                throw new MyException(201);
            }
        } catch (SQLException e) {
            int errorCode = AppErrors.DATABASE_OPEN_ERROR;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}
