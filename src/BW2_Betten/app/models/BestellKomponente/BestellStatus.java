package models.BestellKomponente;

/**
 * Created by dima on 02.06.15.
 */
public enum BestellStatus {

    IN_BEARBEITUNG(0),
    VERSANDT(1),
    ABGESCHLOSSEN(2),
    STORNIERT(3);

    private int value;

    private BestellStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static BestellStatus getStatusByInt(int value){

        if(IN_BEARBEITUNG.getValue() == value) return IN_BEARBEITUNG;
        if(VERSANDT.getValue() == value) return VERSANDT;
        if(ABGESCHLOSSEN.getValue() == value) return ABGESCHLOSSEN;
        else return STORNIERT;
    }


}
