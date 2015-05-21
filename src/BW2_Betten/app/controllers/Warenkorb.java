package controllers;

import com.fasterxml.jackson.databind.JsonNode;
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
import views.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 18.05.15.
 */
public class Warenkorb extends Controller {


    public static Result addArtikelToWarenkorb(){

        // {artNummer -> menge}
        DynamicForm artForm = Form.form().bindFromRequest();
        String artikelNummer = (artForm.get("artikelNummer"));

        if(session(artikelNummer) == null){
            session(artikelNummer,"1");
            System.out.println(artikelNummer+" -> "+1);
        }else{
            int menge = Integer.parseInt(session(artikelNummer));
            menge++;
            session(artikelNummer,Integer.toString(menge));
            System.out.println(artikelNummer+" -> "+menge);
        }
        return redirect("/produkte");
    }

    public static WarenkorbDTO getWarenkorb(){

        WarenkorbDTO warenkorbDTO = new WarenkorbDTO();
        IProduktKomponente produktKomponente = new ProduktKomponente();

        for(String key : session().keySet()){

            if(key.equals(Account.USER) || key.equals(Account.TIME)) continue;

            int artikelNummer = Integer.parseInt(key);
            int menge = Integer.parseInt(session(key));
            if(menge <= 0) continue;

            IArtikel art = produktKomponente.sucheArtikelNachArtikelID(artikelNummer);

            try {
                warenkorbDTO.addArtikelDTO(new ArtikelDTO(art),menge);
            } catch (ArtikelDTO_ParseException e) {

            }
        }

        return warenkorbDTO;
    }

    public static Result doWarenkorb(){

        DynamicForm artForm = Form.form().bindFromRequest();

        if(artForm.get("delete") != null){
            String artID = artForm.get("delete");
            System.out.println(artID);
            session().remove(artID);
        }else if(artForm.get("artikelID") != null){
            String artikelID = artForm.get("artikelID");
            String m = artForm.get("menge");
            int menge = Integer.parseInt(m);
            if(menge <= 0) session().remove(artikelID);
            else session(artikelID,Integer.toString(menge));
        }


        return Verlinkung.showWarenkorb();
    }
}
