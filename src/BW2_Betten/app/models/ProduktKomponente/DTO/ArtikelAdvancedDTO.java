package models.ProduktKomponente.DTO;

import models.ProduktKomponente.Produkt.IArtikel;

import java.util.List;

/**
 * Created by dima on 20.05.15.
 */
public class ArtikelAdvancedDTO {

    private int artID;
    private String bezeichnung;
    private String beschreibung;
    private String kategorie;
    private int typ;
    private String bildURL;
    private int bestand;

    private List<ArtikelAdvancedDTO> unterartikelListe;

    public ArtikelAdvancedDTO(IArtikel artikel, List<ArtikelAdvancedDTO> unterartikelListe){

        if(artikel == null) throw new NullPointerException("Artikel darf nicht NULL sein!");

        parseArtikelToDTO(artikel);
        this.unterartikelListe = unterartikelListe;
    }


    private void parseArtikelToDTO(IArtikel artikel){

        artID = artikel.getArtikelID();
        bezeichnung = artikel.getBezeichnung();
        beschreibung = artikel.getBeschreibung();
        kategorie = artikel.getArtikelKategorie().getValue();
        typ = artikel.getTyp().getValue();
        bildURL = artikel.getBildURL();
        bestand = artikel.getBestand();
    }



    public int getArtID() {
        return artID;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getKategorie() {
        return kategorie;
    }

    public int getTyp() {
        return typ;
    }

    public String getBildURL() {
        return bildURL;
    }

    public int getBestand() {
        return bestand;
    }

    public List<ArtikelAdvancedDTO> getUnterartikelListe(){
        return this.unterartikelListe;
    }
}
