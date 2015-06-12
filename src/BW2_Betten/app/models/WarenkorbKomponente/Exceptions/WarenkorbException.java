package models.WarenkorbKomponente.Exceptions;

/**
 * Created by dima on 27.05.15.
 */
public class WarenkorbException extends Exception {

    private String message;

    public WarenkorbException(){}

    public WarenkorbException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null ? message : super.getMessage());
    }
}
