package controllers;

import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.ProduktKomponente.DTO.ArtikelAdvancedDTO;
import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.WarenkorbKomponente.IWarenkorb;
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
        return Produkte.sucheArtikel();
    }

    public static Result showAngebote(){

        return ok(angebote.render(""));
    }

    public static Result showWarenkorb(){

        return ok(warenkorb.render(WarenkorbPersistenz.getWarenkorbDTO()));
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

    public static Result showProduktListe(){
        if(Account.checkAdminSession()){
            List<ArtikelAdvancedDTO> unterartikel = Produkte.getAllAdvancedArtikel();

            return ok(produktliste.render(Produkte.getAllAdvancedArtikel()));

        }else{
            index.render(null);
            return redirect("/");
        }
    }

    public static Result doBestellen(){

        if(!Account.checkUserSession()){
            anmelden.render(null);
            return redirect("/account");
        }

        IKunde kunde = Account.getIKundeFromSession();

        if(kunde == null){
            Account.doAbmelden();
            anmelden.render(null);
            return redirect("/account");
        }

        IWarenkorb warenkorb = WarenkorbPersistenz.getIWarenkorbFromSession();

        boolean result = Bestellung.doBestellung(kunde,warenkorb);

        if(result){
            session().clear();
            redirect("/");
            return ok(index.render("Bestellung war erfolgreich"));
        }else{
            session().clear();
            redirect("/");
            return ok(index.render("Bei der Bestellung ist ein Fehler aufgetreten"));
        }
    }

    /**** ADMIN ****/

    public static Result showABC_Analyse(){

        // Wenn der Benutzer kein Admin ist dann umleiten
        if(! Account.checkAdminSession()){
            index.render(null);
            return redirect("/");
        }

        List<List<ArtikelSimplelDTO>> analyseErgebnis = Analyse.doABC_analyse();

        // Analyse muss drei Gruppierungen ausgeben, sonst falsch
        if(analyseErgebnis.size() < 3){
            index.render(null);
            return redirect("/");
        }

        List<ArtikelSimplelDTO> aObjekte = analyseErgebnis.get(0);
        List<ArtikelSimplelDTO> bObjekte = analyseErgebnis.get(1);
        List<ArtikelSimplelDTO> cObjekte = analyseErgebnis.get(2);


        return ok(abcAnalyse.render(aObjekte, bObjekte, cObjekte));
    }

    public static Result showAssAnalyse(){

        return ok(assAnalyse.render());
    }

}
