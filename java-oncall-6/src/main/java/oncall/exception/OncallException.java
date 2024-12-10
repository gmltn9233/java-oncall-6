package oncall.exception;

public class OncallException extends IllegalArgumentException{
    private static final String PREFIX = "[ERROR] ";
    private final String errorMessage;

    public OncallException(String message){
        super(PREFIX + message);
        this.errorMessage = PREFIX + message;
    }
}
