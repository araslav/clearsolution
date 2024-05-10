package test.clearsolution.exception;

public class CustomValidationException extends RuntimeException {
    public CustomValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
