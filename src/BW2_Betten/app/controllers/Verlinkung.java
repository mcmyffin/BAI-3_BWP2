package controllers;

import models.ProduktKomponente.IArtikel;
import models.ProduktKomponente.IProduktKomponente;
import models.ProduktKomponente.ProduktKomponente;
import play.mvc.*;
import views.html.*;

import java.util.List;

/**
 * Created by dima on 06.05.15.
 */

public class Verlinkung extends Controller{

    public static Result showIndex(){

        return ok(index.render("willkommen"));
    }

    public static Result showProdukte(){

        IProduktKomponente produktKomponente = new ProduktKomponente();
        List<IArtikel> artikelList = produktKomponente.getArtikel();

        return ok(produkte.render(artikelList));
    }

    public static Result showAngebote(){

        return ok(angebote.render(""));
    }

    public static Result showWarenkorb(){

        return ok(warenkorb.render(Warenkorb.getWarenkorb()));
    }

    public static Result showAnmelden(){

        return ok(anmelden.render(""));
    }

}
