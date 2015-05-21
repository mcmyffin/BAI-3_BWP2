package models.ProduktKomponente;

/**
 * Created by dima on 15.05.15.
 */
public class Artikel implements IArtikel {


    private int artID;
    private String bezeichnung;
    private String beschreibung;
    private ArtikelKategorie kategorie;
    private ArtikelTyp typ;
    private String bildURL;
    private int bestand;
    private int preis;

    public Artikel(int artID, String bezeichnung, String beschreibung, ArtikelKategorie kategorie, ArtikelTyp typ,
                                                                                String bildURL, int bestand, int preis){

        this.artID = artID;
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.typ = typ;
        this.bildURL = bildURL;
        this.bestand = bestand;
        this.preis = preis;
    }

    @Override
    public int getArtikelID() {
        return this.artID;
    }

    @Override
    public String getBezeichnung() {
        return this.bezeichnung;
    }

    @Override
    public String getBeschreibung() {
        return this.beschreibung;
    }

    @Override
    public ArtikelKategorie getArtikelKategorie(){
        return this.kategorie;
    }

    @Override
    public ArtikelTyp getTyp() {
        return this.typ;
    }

    @Override
    public String getBildURL() {
        return this.bildURL;
    }

    @Override
    public int getBestand() {
        return this.bestand;
    }

    @Override
    public int getPreis() {
        return this.preis;
    }

    @Override
    public void setBestand(int bestand) {
        this.bestand = bestand;
    }
}
