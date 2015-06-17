package models.ProduktKomponente.Produkt;

import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.Exceptions.ArtikelCreateException;

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

    private Artikel(int artID, String bezeichnung, String beschreibung, ArtikelKategorie kategorie, ArtikelTyp typ,
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

    static IArtikel createArtikel(int artID, String bezeichnung, String beschreibung, ArtikelKategorie kategorie, ArtikelTyp typ,
                                  String bildURL, int bestand, int preis) throws ArtikelCreateException{

        // preconditions
        if(artID <= 0) throw new ArtikelCreateException("ID darf nicht NULL sein!");
        if(bezeichnung == null) throw new ArtikelCreateException("Bezeichnung darf nicht NULL sein!");
        if(beschreibung == null) throw new ArtikelCreateException("Beschreibung darf nicht NULL sein!");
        if(kategorie == null) throw new ArtikelCreateException("Kategorie darf nicht NULL sein!");
        if(typ == null) throw new ArtikelCreateException("Typ darf nicht NULL sein!");
        if(bildURL == null) throw new ArtikelCreateException("BildURL darf nicht NULL sein");

        return ((IArtikel) (new Artikel(artID,bezeichnung,beschreibung,kategorie,typ,bildURL,bestand,preis)));



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
    public ArtikelSimplelDTO toSimpleDTO() {

        ArtikelSimplelDTO simplelDTO = new ArtikelSimplelDTO(this);

        return simplelDTO;
    }

    @Override
    public void setBestand(int bestand){
        this.bestand = bestand;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public boolean equals(Object obj) {

        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof Artikel)) return false;

        Artikel aArtikel = (Artikel) obj;
        return (aArtikel.artID == this.artID);
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "artID=" + artID +
                '}';
    }
}
