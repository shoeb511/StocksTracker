package backend.stockstracker.Exceptions.authExceptions;

public class SessionTimeOutException extends Exception{

    public SessionTimeOutException(String message){
        super(message);
    }
}
