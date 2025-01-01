package aston.intensive.authentication_server.exeption;

public class JwtParseException extends RuntimeException {
    public JwtParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
