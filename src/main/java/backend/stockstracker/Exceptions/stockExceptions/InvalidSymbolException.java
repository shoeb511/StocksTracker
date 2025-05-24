package backend.stockstracker.Exceptions.stockExceptions;

public class InvalidSymbolException extends Exception {

    public InvalidSymbolException(String message) {
        super(message);
    }
}
