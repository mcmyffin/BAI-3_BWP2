package controllers;

import models.ProduktKomponente.DTO.ArtikelAdvancedDTO;
import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.IProduktKomponente;
import models.ProduktKomponente.Produkt.IArtikel;
import models.ProduktKomponente.ProduktKomponente;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.produkte;
import views.html.warenkorb;

import java.util.List;

/**
 * Created by dima on 15.05.15.
 */

public class Produkte extends Controller {

    private static IProduktKomponente produktKomponente;


    public static Result sucheArtikel(){

        DynamicForm suche = Form.form().bindFromRequest();
        String begriff = suche.get("suchbegriff");
        int admin = (Account.checkAdminSession() ? 1 : 0);

        if(begriff == null || begriff.isEmpty()) return ok(produkte.render(getAlleArtikelAsDTO(), admin));
        return ok(produkte.render(getArtikelBySuchbegriffAsDTO(begriff),admin));
    }

    static IArtikel getArtikelByID(int id){

        produktKomponente = new ProduktKomponente();
        return produktKomponente.getArtikelByID(id);
    }

    static ArtikelSimplelDTO getArtikelByIDAsDTO(int id){

        produktKomponente = new ProduktKomponente();
        return produktKomponente.getArtikelByIDAsDTO(id);
    }

    static List<IArtikel> getAlleArtikel(){

        produktKomponente = new ProduktKomponente();
        return produktKomponente.getAlleArtikel();
    }

    static List<ArtikelSimplelDTO> getAlleArtikelAsDTO(){

        produktKomponente = new ProduktKomponente();
        return produktKomponente.getAlleArtikelAsDTO();
    }

    static List<IArtikel> getArtikelBySuchbegriff(String suchbegriff){

        produktKomponente = new ProduktKomponente();
        return produktKomponente.getArtikelByBegriff(suchbegriff);
    }

    static List<ArtikelSimplelDTO> getArtikelBySuchbegriffAsDTO(String suchbegriff){

        produktKomponente = new ProduktKomponente();
        return  produktKomponente.getArtikelByBegriffAsDTO(suchbegriff);
    }

    static ArtikelAdvancedDTO getAdvancedArtikelByID(int artikelID){
        produktKomponente = new ProduktKomponente();
        return produktKomponente.getAdvancedArtikelByID(artikelID);
    }

    public static Result addArtikel(){

        DynamicForm artForm = Form.form().bindFromRequest();

        int artikelnummer = Integer.parseInt(artForm.get("artikelNummer"));
        WarenkorbPersistenz.addArtikelToWarenkorb(artikelnummer);

        warenkorb.render(WarenkorbPersistenz.getWarenkorbDTO());
        return redirect("/warenkorb");
    }



    static List<ArtikelAdvancedDTO> getAllAdvancedArtikel(){
        produktKomponente = new ProduktKomponente();
        return produktKomponente.getAllAdvancedArtikel();
    }
}
