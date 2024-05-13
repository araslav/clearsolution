package test.clearsolution.exception;

public class CustomNoSuchFieldException extends RuntimeException {
    public CustomNoSuchFieldException(String message) {
        super(message);
    }
}
