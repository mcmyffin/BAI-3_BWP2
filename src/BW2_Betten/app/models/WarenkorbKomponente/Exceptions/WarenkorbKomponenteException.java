package models.WarenkorbKomponente.Exceptions;

/**
 * Created by dima on 27.05.15.
 */
public class WarenkorbKomponenteException extends Exception {

    private String message;

    public WarenkorbKomponenteException(){}

    public WarenkorbKomponenteException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null ? message : super.getMessage());
    }
}
