package models.DatenbankAdapter;

import models.DatenTypen.Pair;
import models.ProduktKomponente.Produkt.IArtikel;

import java.util.List;

/**
 * Created by dima on 02.06.15.
 */
public interface IAnalyse {

    /*
    *
    *

    *
    * 1) Umsatz aller gekaufter Artikel
    *
    *
    * */

    public List<Pair<IArtikel,Integer>> getAlleVerkaufteArtikel();
}
