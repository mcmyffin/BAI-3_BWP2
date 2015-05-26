package models.KundenVerwaltungKomponente;

/**
 * Created by dima on 23.05.15.
 */
public enum UserTyp {

    KUNDE(0),
    ADMIN(1);

    private int value;

    UserTyp(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static UserTyp getUserTypByInt(int typ){

        if (ADMIN.getValue() == typ) return ADMIN;
        else return KUNDE;
    }
}
