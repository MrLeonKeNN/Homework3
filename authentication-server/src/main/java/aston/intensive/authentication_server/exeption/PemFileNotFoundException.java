package aston.intensive.authentication_server.exeption;

public class PemFileNotFoundException extends RuntimeException {
    public PemFileNotFoundException(String message) {
        super(message);
    }
}

