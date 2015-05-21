package models.ProduktKomponente;

import models.DatenbankAdapter.Exception.ArtikelDTO_ParseException;

/**
 * Created by dima on 20.05.15.
 */
public class ArtikelDTO {

    private int artID;
    private String bezeichnung;
    private String beschreibung;
    private String kategorie;
    private int typ;
    private String bildURL;
    private int bestand;
    private int preis;

    public ArtikelDTO(IArtikel artikel) throws ArtikelDTO_ParseException {
        if(artikel == null) throw new ArtikelDTO_ParseException();
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

    public static IArtikel parseArtikelDTOToArtikel(ArtikelDTO artikelDTO){

        IArtikel artikel = new Artikel(
                artikelDTO.getArtID(),
                artikelDTO.getBezeichnung(),
                artikelDTO.getBeschreibung(),
                ArtikelKategorie.getKategorieByString(artikelDTO.getKategorie()),
                ArtikelTyp.getTypByInt(artikelDTO.getTyp()),
                artikelDTO.getBildURL(),
                artikelDTO.getBestand(),
                artikelDTO.getPreis()
        );

        return artikel;
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

    public int getPreis() {
        return preis;
    }
}
