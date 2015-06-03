package models.ProduktKomponente.Produkt;

import models.ProduktKomponente.DTO.ArtikelSimplelDTO;

/**
 * Created by dima on 13.05.15.
 */
public interface IArtikel {

    /**
     * Get Artikel ID
     *
     * @return Gibt die eindeutige Artikelnummer des Artikels zurueck
     */
    public int getArtikelID();

    /**
     * Get Bezeichnung
     *
     * Eine kurzbeschreibung des Artikels inform eines Textes
     * @return Gibt die Bezeichnung des Artikels zurueck
     */
    public String getBezeichnung();

    /**
     * Get Beschreibung
     *
     * Eine ausfuehrliche Beschreibung des Artikels
     * @return Gibt die Beschreibung des Artikels zurueck
     */
    public String getBeschreibung();

    /**
     * Get Artikelkategorie
     *
     * Eine Artikelkategorie ist eine Artikel Typisierung vom Typ ArtikelKategorie
     * @return Gibt die ArtikelKategorie als eigenen Typen an
     */
    public ArtikelKategorie getArtikelKategorie();

    /**
     * Get Typ
     *
     * Typ ist ein ENUM
     * @return Enum ArtikelTyp
     */
    public ArtikelTyp getTyp();

    /**
     * Get BildURL
     *
     * Die BildURL ist der Pfad des Bildes was dem Artikel zugeordnet ist
     * @return Gibt einen Link in textueller Frorm zurueck
     */
    public String getBildURL();

    /**
     * Get Bestand
     *
     * Der Bestand ist in verbindung mit ArtikelTyp erst relevant
     * @return Gibt den Bestand des Artikels zurueck
     */
    public int getBestand();

    /**
     * Get Preis
     *
     * @return Gibt den aktuellen Preis des Produktes
     */
    public int getPreis();


    public ArtikelSimplelDTO toSimpleDTO();

    public void setBestand(int bestand);

}
