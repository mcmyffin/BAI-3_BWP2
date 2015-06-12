package models.ProduktKomponente.Produkt;

/**
 * Created by dima on 13.05.15.
 */
public enum ArtikelTyp {


    LEVEL_0(0),
    LEVEL_1(1),
    LEVEL_2(2);

    int typ;

    ArtikelTyp(int typ){
        this.typ = typ;
    }

    public int getValue(){
        return this.typ;
    }

    public static ArtikelTyp getTypByInt(int typ){

        if(LEVEL_2.getValue() == typ) return LEVEL_2;
        if(LEVEL_1.getValue() == typ) return LEVEL_1;
        else return LEVEL_0;
    }

}
