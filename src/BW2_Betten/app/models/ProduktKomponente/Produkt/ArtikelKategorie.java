package models.ProduktKomponente.Produkt;

/**
 * Created by dima on 13.05.15.
 */
public enum ArtikelKategorie {


    BETT("Bett"),
    GESTELL("Gestell"),
    MATRATZE("Matratze"),
    LATTENROST("Lattenrost"),
    ZUBEHOER("Zubehoer"),
    ALLINONE("AllInOne"),
    UNDEFINIERT("Undefiniert");

    private String kategorie;

    ArtikelKategorie(String kategorie){
        this.kategorie = kategorie;
    }

    public String getValue(){
        return this.kategorie;
    }

    public static ArtikelKategorie getKategorieByString(String kategorie){

        if(BETT.getValue().equals(kategorie)) return BETT;
        if(GESTELL.getValue().equals(kategorie)) return GESTELL;
        if(LATTENROST.getValue().equals(kategorie)) return LATTENROST;
        if(ZUBEHOER.getValue().equals(kategorie)) return ZUBEHOER;
        if(ALLINONE.getValue().equals(kategorie)) return ALLINONE;
        if(MATRATZE.getValue().equals(kategorie)) return MATRATZE;
        else return UNDEFINIERT;
    }
}
