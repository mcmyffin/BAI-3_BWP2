package models.KundenVerwaltungKomponente.Exceptions;

/**
 * Created by dima on 28.05.15.
 */
public class SessionParseException extends Exception{

    private String message;

    public SessionParseException(){}

    public SessionParseException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null ? message : super.getMessage());
    }
}
