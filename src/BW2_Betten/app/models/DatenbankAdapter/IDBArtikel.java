package models.DatenbankAdapter;


import models.ProduktKomponente.Produkt.IArtikel;
import models.DatenTypen.Pair;

import java.util.List;

/**
 * Created by dima on 15.05.15.
 */
public interface IDBArtikel {

    /**
     * Get Artikel
     *
     * Gibt alle Artikel aus
     * @return List<List<String>> artikel
     */
    public List<IArtikel> getAlleArtikel();

    /**
     * Suche Artikel nach Beriff
     *
     * Sucht nach Artikeln, die diesen Suchbegriff enthalten.
     * @param begriff
     * @return Gibt eine Liste der uebereinstimmungen zurueck, wenn nichts gefunden, dann Liste leer.
     */
    public List<IArtikel> getArtikelByBegriff(String begriff);

    /**
     * Get Artikel By ID
     *
     * Sucht ein Artikel mit genau dieser Artikelnummer.
     * @param artikelnummer
     * @return Gibt ein Artikel zurueck, wenn gefunden, null.
     */
    public IArtikel getArtikelByID(int artikelnummer);

    /**
     * Get Unterartikel
     * Sucht nach Unterartikel wenn vorhanden
     * @param artikelnummer
     * @return eine Liste mit Pair<IArtikel,Menge>.
     *          Wenn keine vorhanden, dann Liste leer.
     */
    public List<Pair<IArtikel,Integer>> getUnterArtikelByID(int artikelnummer);

    public List<IArtikel> getUnterartikelListe(int artikelnummer);
}
