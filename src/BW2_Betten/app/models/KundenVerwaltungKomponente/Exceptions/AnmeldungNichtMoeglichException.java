package models.KundenVerwaltungKomponente.Exceptions;

/**
 * Created by dima on 28.05.15.
 */
public class AnmeldungNichtMoeglichException extends Exception{

    private String message;

    public AnmeldungNichtMoeglichException(){}

    public AnmeldungNichtMoeglichException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null ? message : super.getMessage());
    }
}
