package backend.stockstracker.Exceptions.authExceptions;

public class UnauthorizedUserException extends Exception{

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
