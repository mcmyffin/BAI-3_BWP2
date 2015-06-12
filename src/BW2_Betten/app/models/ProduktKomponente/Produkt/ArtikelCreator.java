package models.ProduktKomponente.Produkt;

import models.ProduktKomponente.Exceptions.ArtikelCreateException;

/**
 * Created by dima on 28.05.15.
 */
public class ArtikelCreator {

    public static IArtikel createArtikel(int artID, String bezeichnung, String beschreibung, String kategorieString, int typInt,
                                         String bildURL, int bestand, int preis) throws ArtikelCreateException {

        ArtikelTyp typ = ArtikelTyp.getTypByInt(typInt);
        ArtikelKategorie kategorie = ArtikelKategorie.getKategorieByString(kategorieString);

        IArtikel artikel = Artikel.createArtikel(artID,bezeichnung,beschreibung,kategorie,typ,bildURL,bestand,preis);

        return artikel;
    }
}
