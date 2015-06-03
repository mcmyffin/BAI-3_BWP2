package models.WarenkorbKomponente;

import models.ProduktKomponente.Produkt.IArtikel;
import models.WarenkorbKomponente.DTO.WarenkorbDTO;
import models.WarenkorbKomponente.Exceptions.WarenkorbException;

import java.util.List;
import java.util.Map;

/**
 * Created by dima on 27.05.15.
 */
public interface IWarenkorb {



    public List<IArtikel> getArtikelListe();

    public Map<Integer,Integer> getArtikelMenge();

    public int getGesamtPreis();

    public boolean addArtikel(IArtikel artikel);

    public boolean setArtikel(IArtikel artikel, int menge);

    public boolean removeArtikel(IArtikel artikel);

    public WarenkorbDTO toDTO();
}
