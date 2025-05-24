package backend.stockstracker.Exceptions;

public class SessionTimeOutException extends Exception{

    public SessionTimeOutException(String message){
        super(message);
    }
}
