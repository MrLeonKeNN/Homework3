package aston.intensive.authentication_server.exeption;

public class PublicKeyNotFoundException extends RuntimeException {
    public PublicKeyNotFoundException(String message) {
        super(message);
    }
}
