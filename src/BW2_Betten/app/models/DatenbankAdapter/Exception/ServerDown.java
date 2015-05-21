package models.DatenbankAdapter.Exception;

/**
 * Created by dima on 20.05.15.
 */
public class ServerDown extends Exception{

    private String message = "";

    public ServerDown(){}

    public ServerDown(String message){
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
