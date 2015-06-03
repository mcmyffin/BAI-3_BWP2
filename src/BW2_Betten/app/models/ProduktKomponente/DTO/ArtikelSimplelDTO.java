package models.ProduktKomponente.DTO;

import models.DatenbankAdapter.Exception.ArtikelDTO_ParseException;
import models.ProduktKomponente.Produkt.Artikel;
import models.ProduktKomponente.Produkt.ArtikelKategorie;
import models.ProduktKomponente.Produkt.ArtikelTyp;
import models.ProduktKomponente.Produkt.IArtikel;

/**
 * Created by dima on 20.05.15.
 */
public class ArtikelSimplelDTO {

    private int artID;
    private String bezeichnung;
    private String beschreibung;
    private String kategorie;
    private int typ;
    private String bildURL;
    private int bestand;
    private int preis;

    public ArtikelSimplelDTO(IArtikel artikel) {
        parseArtikelToDTO(artikel);
    }


    private void parseArtikelToDTO(IArtikel artikel){

        artID = artikel.getArtikelID();
        bezeichnung = artikel.getBezeichnung();
        beschreibung = artikel.getBeschreibung();
        kategorie = artikel.getArtikelKategorie().getValue();
        typ = artikel.getTyp().getValue();
        bildURL = artikel.getBildURL();
        bestand = artikel.getBestand();
        preis = artikel.getPreis();
    }


    /** GETTER ***/
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

    public int getPreis() {
        return preis;
    }
}
