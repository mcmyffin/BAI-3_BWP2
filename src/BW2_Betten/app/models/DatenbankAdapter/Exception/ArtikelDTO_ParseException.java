package models.DatenbankAdapter.Exception;

/**
 * Created by dima on 20.05.15.
 */
public class ArtikelDTO_ParseException extends Exception{

    private String message = "";

    public ArtikelDTO_ParseException(){}

    public ArtikelDTO_ParseException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
