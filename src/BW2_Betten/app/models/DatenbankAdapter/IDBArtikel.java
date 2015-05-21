package models.DatenbankAdapter;


import models.ProduktKomponente.Pair;

import java.sql.SQLException;
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
    public List<List<String>> getArtikel();

    /**
     * Suche Artikel nach Beriff
     *
     * Sucht nach Artikeln, die diesen Suchbegriff enthalten.
     * @param begriff
     * @return Gibt eine Liste der uebereinstimmungen zurueck, wenn nichts gefunden, dann Liste leer.
     */
    public List<List<String>> sucheArtikelNachBegriff(String begriff);

    /**
     * Get Artikel By ID
     *
     * Sucht ein Artikel mit genau dieser Artikelnummer.
     * @param artikelnummer
     * @return Gibt ein Artikel zurueck, wenn gefunden, sonst leere Liste.
     */
    public List<String> getArtikelByID(int artikelnummer);

    /**
     * Get Unterartikel
     * Sucht nach Unterartikel wenn vorhanden
     * @param artikelnummer
     * @return eine Liste mit Pair<ArtikelID,Menge>.
     *          Wenn keine vorhanden, dann Liste leer.
     */
    public List<Pair<Integer,Integer>> getUnterArtikel(int artikelnummer);
}
