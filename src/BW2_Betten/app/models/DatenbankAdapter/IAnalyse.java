package models.DatenbankAdapter;

import models.DatenTypen.Pair;
import models.ProduktKomponente.Produkt.IArtikel;

import java.util.List;
import java.util.Set;

/**
 * Created by dima on 02.06.15.
 */
public interface IAnalyse {


    public List<Pair<IArtikel,Integer>> getAlleVerkaufteArtikel();


    /*
    *
    *   1) getAlle Bestellungen | mit Artikelanzahl groesser 1
    *   -> BestellID's = SELECT BEST_BestellID FROM Bestellliste GROUP BY BEST_BestellID HAVING COUNT(BEST_BestellID) > 1
    *      umwandeln zu Bestellung = Set<IArtikel>
    *
    *      Finde heraus welches Artikel am häufigsten in den Bestellungen vorkommt, das nicht bereits behandelt wurde
    *       -> hierzu würde eine Vereinigung der gesamten BestellMengen (Set's) sich anbieten
    *
    *
    *
    *
    *
    *   2) 2^n ... (artikel x artikel) mit zweier Kombination
    *
    *   3) filter diese Permutation, deren häufigkeit > 2 (mindest Support)
    *
    *   4) fertig
    * */

    public List<Set<IArtikel>> getBestellungenMitMinZweiArtikeln();

}
