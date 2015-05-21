package models.ProduktKomponente;

import java.util.List;

/**
 * Created by dima on 13.05.15.
 */
public interface IProduktKomponente {

    /**
     * Suche Artikel nach Begriff
     *
     * Sucht ein Artikel nach der eindeutigen Artikelnummer.
     * @param artikelID
     * @return Wenn ein Artikel gefunden, dann wird dieser zurueck gegeben, sonst null.
     */
    public IArtikel sucheArtikelNachArtikelID(int artikelID);

    /**
     * Suche Artikel nach Begriff
     *
     * Sucht nach Artikeln, die diesen Suchbegriff enthalten.
     * @param suchbegriff
     * @return Gibt eine Liste der uebereinstimmungen zurueck, wenn nichts gefunden, dann Liste leer.
     */
    public List<IArtikel> sucheArtikelNachBegriff(String suchbegriff);

    public List<IArtikel> getArtikel();
}
