package aston.intensive.authentication_server.exeption;

public class PemFileReadException extends RuntimeException {
    public PemFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
