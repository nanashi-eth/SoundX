package Exceptions;

public class MyException extends Exception {
    
    private int code;
    
    private String message;
    
    public MyException() {}
    
    public MyException(int code) {
        super();
        this.code=code;
        this.message = AppErrors.getErrorMessage(code);
    };    

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    
    
    
}
