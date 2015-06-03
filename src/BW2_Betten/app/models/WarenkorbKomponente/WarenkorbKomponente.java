package models.WarenkorbKomponente;

import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.IProduktKomponente;
import models.ProduktKomponente.Produkt.IArtikel;
import models.ProduktKomponente.ProduktKomponente;
import models.WarenkorbKomponente.DTO.WarenkorbDTO;
import models.WarenkorbKomponente.Exceptions.WarenkorbException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 13.05.15.
 */
public class WarenkorbKomponente implements IWarenkorbKomponente{




    @Override
    public IWarenkorb fromSession(Map<Integer,Integer> artikelWarenkorb){


        IProduktKomponente produktVerwaltung = new ProduktKomponente();
        IWarenkorb resultWarenkorb = this.createEmtyWarenkorb();


        for(Integer artikelNummer : artikelWarenkorb.keySet()){

            IArtikel artikel = produktVerwaltung.getArtikelByID(artikelNummer);

            // precondition auf NULL
            if(artikel == null) throw new NullPointerException("Mit NULL kann ich nicht arbeiten!!!!!");

            int menge = artikelWarenkorb.get(artikelNummer);
            resultWarenkorb.setArtikel(artikel,menge);


        }

        System.out.println("WarenkorbKomponente fromSession() = "+resultWarenkorb.getArtikelMenge());
        return resultWarenkorb;
    }

    @Override
    public IWarenkorb createEmtyWarenkorb() {

        List<IArtikel> artikelListe = new ArrayList();
        Map<Integer,Integer> artikelMenge = new HashMap();

        IWarenkorb warenkorb = Warenkorb.createWarenkorb(artikelListe,artikelMenge);

        return warenkorb;
    }
}
