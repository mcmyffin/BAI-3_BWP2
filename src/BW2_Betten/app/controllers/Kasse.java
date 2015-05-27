package controllers;

import models.DatenbankAdapter.Exception.ArtikelDTO_ParseException;
import models.ProduktKomponente.ArtikelDTO;
import models.ProduktKomponente.IArtikel;
import models.ProduktKomponente.IProduktKomponente;
import models.ProduktKomponente.ProduktKomponente;
import models.WarenkorbKomponente.WarenkorbDTO;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dima on 18.05.15.
 */
public class Kasse extends Controller{

/*
    public static Result showBestelluebersicht(){

        WarenkorbDTO warenkorbDTO = Warenkorb.getWarenkorb();
        Map<ArtikelDTO,Integer> nichtLieferbareMenge = new HashMap<>();

        IProduktKomponente produktKomponente = new ProduktKomponente();

        for(ArtikelDTO artikelDTO: warenkorbDTO.getWarenkorb().keySet()){

            int id = artikelDTO.getArtID();
            int menge = warenkorbDTO.getWarenkorb().get(artikelDTO);

            IArtikel artikel = produktKomponente.sucheArtikelNachArtikelID(id);

            if(menge > artikel.getBestand()){
                nichtLieferbareMenge.put(artikelDTO,artikel.getBestand());
            }



        }
        return ok(kasse.render(warenkorbDTO,nichtLieferbareMenge));
    }


    public static Result doBestellen(){

        return TODO;
    }
    */

    public static Result doBestellen(){

        WarenkorbDTO warenkorbDTO = Warenkorb.getWarenkorb();

        return TODO;
    }
}
