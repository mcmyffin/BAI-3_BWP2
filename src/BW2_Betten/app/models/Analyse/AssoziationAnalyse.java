package models.Analyse;

import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.IDatenbankAdapter;
import models.ProduktKomponente.Produkt.IArtikel;
import play.Logger;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by dima on 03.06.15.
 */
public class AssoziationAnalyse {

    IDatenbankAdapter dbPersistenz = new DatenbankAdapter();


    public void doAssoziationsAnalyse(){

        List<Set<IArtikel>> bestellungen = dbPersistenz.getBestellungenMitMinZweiArtikeln();
        List<Set<IArtikel>> artikelMitMinSupport = new LinkedList();

        for(Set<IArtikel> eineBestellung : bestellungen){

            /**
             *
             * {Zahnbürste,Zahnpasta}
             *
             *
             */


        }



//        Logger.debug(aehnlicheBestellungen.toString());
    }
}
