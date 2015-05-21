package controllers;

import models.KundenVerwaltungKomponente.IKundenVerwaltungKomponente;
import models.KundenVerwaltungKomponente.KundenVerwaltungKomponente;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

/**
 * Created by dima on 10.05.15.
 */
public class Account extends Controller {

    public static final String USER = "user";
    public static final String TIME = "time";

    public static Result doAnmelden(){

        DynamicForm anmeldenForm = Form.form().bindFromRequest();
        String email = anmeldenForm.get("email");
        String passwort = anmeldenForm.get("passwort");

        IKundenVerwaltungKomponente kundenverwaltung = new KundenVerwaltungKomponente();
        boolean ergebnis = kundenverwaltung.anmelden(email, passwort);

        if(ergebnis){
            session(USER,email);
            session(TIME,Long.toString(System.currentTimeMillis()));
            return showKundeProfil();
        }else{
            return ok(anmelden.render("Email oder Passwort <b>falsch</b>"));
        }
    }

    public static Result doAccount(){

        // Wenn POST und Angemeldet, dann LOGOUT
        if(checkUserSession()){
            return doAbmelden();
        }else{
            return doAnmelden();
        }
    }

    public static Result doAbmelden(){
        session().remove(USER);
        return ok(anmelden.render(null));
    }

    public static Result showKundeProfil(){
        if(!checkUserSession()) return ok(anmelden.render(null));
        return ok(kunde_account.render(session(USER)));
    }

    public static boolean checkUserSession(){
        return (session(USER) != null);
    }
}
