package models.BestellKomponente;

import models.ProduktKomponente.Produkt.IArtikel;

import java.util.List;

/**
 * Created by dima on 13.05.15.
 */
public interface IBestellKomponente {

    public IBestellung erstelleBestellung(List<IArtikel> artikelListe);


}
