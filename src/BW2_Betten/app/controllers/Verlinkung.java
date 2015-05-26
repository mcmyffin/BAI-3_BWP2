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

    public static Result showRegistrieren(){
        if(Account.checkUserSession()) return showIndex();
        return ok(registrieren.render(null));
    }

    /**** ADMIN BEREICH ****/
    // ADMIN
    public static Result showAbcAnalyse(){
        if(Account.checkAdminSession()){

        }else{
            index.render(null);
            return redirect("/");
        }
        return TODO;
    }

    // ADMIN
    public static Result showAccountVerwaltung(){
        if(Account.checkAdminSession()){

        }else{
            index.render(null);
            return redirect("/");
        }
        return TODO;
    }

    // ADMIN
    public static Result showAddArtikel(){
        if(Account.checkAdminSession()){

        }else{
            index.render(null);
            return redirect("/");
        }
        return TODO;
    }

    public static Result showRemArtikel(){
        if(Account.checkAdminSession()){

        }else{
            index.render(null);
            return redirect("/");
        }
        return TODO;
    }

    public static Result showArtikelBestand(){
        if(Account.checkAdminSession()){

        }else{
            index.render(null);
            return redirect("/");
        }
        return TODO;
    }

}
