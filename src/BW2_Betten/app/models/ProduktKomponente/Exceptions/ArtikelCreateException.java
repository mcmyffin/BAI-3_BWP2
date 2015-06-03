package models.ProduktKomponente.Exceptions;

/**
 * Created by dima on 28.05.15.
 */
public class ArtikelCreateException extends Exception{

    private String message;

    public ArtikelCreateException(){}

    public ArtikelCreateException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null ? message : super.getMessage());
    }
}
