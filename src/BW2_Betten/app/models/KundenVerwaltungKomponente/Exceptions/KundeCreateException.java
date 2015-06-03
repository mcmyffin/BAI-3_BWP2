package models.KundenVerwaltungKomponente.Exceptions;

/**
 * Created by dima on 28.05.15.
 */
public class KundeCreateException extends Exception{

    private String message;

    public KundeCreateException(){}

    public KundeCreateException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null ? message : super.getMessage());
    }
}
