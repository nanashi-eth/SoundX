package Exceptions;

public class AppErrors {

    public static final int DATABASE_CONNECTION_ERROR = 101;
    public static final int DATABASE_OPEN_ERROR = 102;
    public static final int DATABASE_CLOSE_ERROR = 103;

    public static final int INVALID_CREDENTIALS = 201;
    public static final int USER_NOT_FOUND = 202;
    public static final int AUTHOR_NOT_FOUND = 203;
    public static final int UNAUTHORIZED_ACCESS = 204;

    public static final int INSERT_ERROR = 205;
    public static final int DELETE_ERROR = 206;
    public static final int MODIFY_ERROR = 207;
    public static final int SELECT_ERROR = 208;

    public static final int SQL_QUERY_ERROR = 401;
    public static final int INVALID_RECORD_ID = 402;

    public static final int SQL_DELETE_ERROR = 403;

    public static final int UNEXPECTED_ERROR = 999;

    public static String getErrorMessage(int errorCode) {
        String errorMessage = "Error " + errorCode + ": ";
        switch (errorCode) {
            case DATABASE_CONNECTION_ERROR:
                errorMessage += "al cargar los drivers de la base de datos";
                break;
            case DATABASE_OPEN_ERROR:
                errorMessage += "al abrir la base de datos";
                break;
            case DATABASE_CLOSE_ERROR:
                errorMessage += "al cerrar la base de datos";
                break;
            case INVALID_CREDENTIALS:
                errorMessage += "usuario o contraseña no válidas";
                break;
            case USER_NOT_FOUND:
                errorMessage += "No se ha encontrado ningún usuario";
                break;
            case AUTHOR_NOT_FOUND:
                errorMessage += "No se ha encontrado ningún autor";
                break;
            case UNAUTHORIZED_ACCESS:
                errorMessage += "acceso no autorizado";
                break;
            case INSERT_ERROR:
                errorMessage += "al insertar datos";
                break;
            case DELETE_ERROR:
                errorMessage += "al eliminar datos";
                break;
            case MODIFY_ERROR:
                errorMessage += "al modificar datos";
                break;
            case SELECT_ERROR:
                errorMessage += "al seleccionar datos";
                break;
            case SQL_QUERY_ERROR:
                errorMessage += "error en la consulta SQL";
                break;
            case INVALID_RECORD_ID:
                errorMessage += "identificador de registro inválido";
                break;
            case UNEXPECTED_ERROR:
                errorMessage += "error inesperado";
                break;
            default:
                errorMessage += "DESCONOCIDO";
        }
        return errorMessage;
    }
}
