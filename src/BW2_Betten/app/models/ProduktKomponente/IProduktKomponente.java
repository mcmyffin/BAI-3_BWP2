package models.ProduktKomponente;

import models.ProduktKomponente.DTO.ArtikelAdvancedDTO;
import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.Produkt.IArtikel;

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
    public IArtikel getArtikelByID(int artikelID);

    public ArtikelSimplelDTO getArtikelByIDAsDTO(int artikelID);

    /**
     * Suche Artikel nach Begriff
     *
     * Sucht nach Artikeln, die diesen Suchbegriff enthalten.
     * @param suchbegriff
     * @return Gibt eine Liste der uebereinstimmungen zurueck, wenn nichts gefunden, dann Liste leer.
     */
    public List<IArtikel> getArtikelByBegriff(String suchbegriff);

    public List<ArtikelSimplelDTO> getArtikelByBegriffAsDTO(String suchbegriff);


    public List<IArtikel> getAlleArtikel();

    public List<ArtikelSimplelDTO> getAlleArtikelAsDTO();

    public ArtikelAdvancedDTO getAdvancedArtikelByID(int artikelID);

    public List<ArtikelAdvancedDTO> getAllAdvancedArtikel();
}
