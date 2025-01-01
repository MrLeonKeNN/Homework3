package aston.intensive.authentication_server.exeption;

public class InvalidPemFormatException extends RuntimeException {
    public InvalidPemFormatException(String message) {
        super(message);
    }
}
