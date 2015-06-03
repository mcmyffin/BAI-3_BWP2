package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.KundenVerwaltungKomponente.*;
import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.KundenVerwaltungKomponente.Benutzer.KundeRegistrierenResult;
import models.KundenVerwaltungKomponente.Benutzer.UserTyp;
import models.KundenVerwaltungKomponente.DTO.KundeDTO;
import models.KundenVerwaltungKomponente.DTO.KundeSessionDTO;
import models.KundenVerwaltungKomponente.Exceptions.AnmeldungNichtMoeglichException;
import models.KundenVerwaltungKomponente.Exceptions.KundeNichtGefundenException;
import models.KundenVerwaltungKomponente.Exceptions.SessionParseException;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

/**
 * Created by dima on 10.05.15.
 */
public class Account extends Controller {

    public static final String USER = "user";
    public static final String TIME = "time";

    private static boolean anmelden(){

        DynamicForm anmeldenForm = Form.form().bindFromRequest();
        String email = anmeldenForm.get("email");
        String passwort = anmeldenForm.get("passwort");

        try {

            IKundenVerwaltungKomponente kundenverwaltung = new KundenVerwaltungKomponente();
            IKunde kunde = kundenverwaltung.anmelden(email, passwort);

            saveKundeToSession(kunde.toSessionDTO());
            return true;

        } catch (AnmeldungNichtMoeglichException e) {

            doAbmelden();
            return false;
        }
    }

    public static Result doAnmelden(){

        boolean anmeldeErgebnis = anmelden();

        if(anmeldeErgebnis){
            return showKundeProfil();
        }else{
            return ok(anmelden.render("Email oder Passwort falsch"));
        }
    }

    public static Result doRegistrieren(){

        DynamicForm registrierForm = Form.form().bindFromRequest();
        String email = registrierForm.get("email");
        String passwort1 = registrierForm.get("passwort1");
        String passwort2 = registrierForm.get("passwort2");

        String vn = registrierForm.get("vorname");
        String nachname = registrierForm.get("nachname");

        String gebDatum = registrierForm.get("gebDatum");

        String ort = registrierForm.get("ort");
        String plzString = registrierForm.get("plz");
        String str = registrierForm.get("strasse");
        String hnString = registrierForm.get("hn");
        String adzs = registrierForm.get("adresszusatz");

        int plz = 0;
        int hn = 0;

        // preconditions
        if(!passwort1.equals(passwort2)) return ok(registrieren.render("Passw&ouml;rter stimmen nicht &uuml;berein"));
        if(plzString == null) return ok(registrieren.render("plz darf nicht leer sein!"));
        if(hnString == null) return ok(registrieren.render("Hausnummer darf nicht leer sein!"));

        try{
            plz = Integer.parseInt(plzString);
            hn = Integer.parseInt(hnString);
        }catch (NumberFormatException e){
            return ok(registrieren.render("plz & hausnummer m&uumlssen aus Zahlen bestehen!"));
        }


        IKundenVerwaltungKomponente kundenverwalter = new KundenVerwaltungKomponente();

        KundeRegistrierenResult result = kundenverwalter.registrieren(email, passwort1, vn, nachname, gebDatum, ort, plz, str, hn, adzs);
        if(result.equals(KundeRegistrierenResult.OK)){
            return ok(registrieren.render(result.getMessage()));
        }else if(result.equals(KundeRegistrierenResult.FAIL_EMAIL_VERGEBEN)){
            return ok(registrieren.render(result.getMessage()));
        }else if(result.equals(KundeRegistrierenResult.FAIL_EMAIL_FALSCH)){
            return ok(registrieren.render(result.getMessage()));
        }else if(result.equals(KundeRegistrierenResult.FAIL_PASSWORT_LEER_UNGUELTIG)){
            return ok(registrieren.render(result.getMessage()));
        }else if(result.equals(KundeRegistrierenResult.FAIL_PASSWORT_ZUKURZ)){
            return ok(registrieren.render(result.getMessage()));
        }else if(result.equals(KundeRegistrierenResult.ERROR_SERVER)){
            return ok(registrieren.render(result.getMessage()));
        }else{
            return ok(registrieren.render(KundeRegistrierenResult.ERROR_UNDEFINIERT.getMessage()));
        }
    }



    public static Result doKundenProfil(){

//        DynamicForm dynamicForm = Form.form().bindFromRequest();
//        IKundenVerwaltungKomponente verwaltung = new KundenVerwaltungKomponente();
//        KundeSessionDTO sessionDTO = Json.fromJson(Json.parse(session(USER)),KundeSessionDTO.class);
//
//        String email = dynamicForm.get("email");
//        String vn    = dynamicForm.get("vorname");
//        String nn    = dynamicForm.get("nachname");
//        String gd    = dynamicForm.get("gebDatum");
//        String ort   = dynamicForm.get("ort");
//        String plz   = dynamicForm.get("plz");
//        String str   = dynamicForm.get("strasse");
//        String hn    = dynamicForm.get("hausnummer");
//        String adzs  = dynamicForm.get("adresszusatz");
//
//        if(
//                email == null || email.isEmpty() ||
//                vn == null || vn.isEmpty() ||
//                nn == null || nn.isEmpty() ||
//                ort == null || ort.isEmpty() ||
//                plz == null || plz.isEmpty() ||
//                str == null || str.isEmpty() ||
//                hn == null || hn.isEmpty()){
//
//            KundeDTO dto = verwaltung.getKundenDaten(sessionDTO);
//            return ok(profil.render(dto,"Eingaben beinhalten leere oder ung&uuml;tige Inhalte"));
//        }
//
//        KundeDTO kundeDTO = new KundeDTO(sessionDTO.getId() ,email,vn,nn,gd,ort,Integer.parseInt(plz),str, Integer.parseInt(hn),adzs);
//        boolean result = verwaltung.changeProfil(kundeDTO);
//
//        if(result){
//            return ok(profil.render(kundeDTO,"Erfolgreich gespeichert!"));
//        }else{
//            return ok(profil.render(kundeDTO,"Hat nicht funktioniert!"));
//        }
        return TODO;
    }


    public static Result doAccount(){

        // Wenn POST und Angemeldet, dann LOGOUT
        if(checkUserSession()){
            doAbmelden();
            return ok(anmelden.render(null));
        }else{
            return doAnmelden();
        }
    }

    static void doAbmelden(){
        session().remove(USER);
        session().remove(TIME);
    }

    public static Result showKundeProfil(){
        if(!checkUserSession()) return ok(anmelden.render(null));
        else{

            KundeSessionDTO sessionDTO = loadKundeFromSession();
            IKundenVerwaltungKomponente verwaltungKomponente = new KundenVerwaltungKomponente();
            KundeDTO kundeDTO = null;

            try {
                IKunde kunde = verwaltungKomponente.fromKundeSessionDTO(sessionDTO);
                kundeDTO = kunde.toDTO();
                saveKundeToSession(kunde.toSessionDTO());

            } catch (SessionParseException|KundeNichtGefundenException e) {
                doAbmelden();
                e.getMessage();
            }

            if(checkAdminSession()){
                // ADMIN
                return ok(admin_account.render(kundeDTO));
            }else{
                // USER
                return ok(kunde_account.render(kundeDTO));
            }
        }
    }

    public static boolean checkUserSession(){
        return (session(USER) != null);
    }

    public static boolean checkAdminSession(){

        if(session(USER) != null){

            KundeSessionDTO sessionDTO = loadKundeFromSession();
            if(sessionDTO == null) return false;

            if(sessionDTO.getTyp() == UserTyp.ADMIN.getValue()) return true;
        }

        return false;

    }

    public static Result kundeProfil(){

        if(!checkUserSession()) return Verlinkung.showAnmelden();

        KundeDTO kundeDTO = getIKundeFromSession().toDTO();

        return ok(profil.render(kundeDTO,""));
    }


    static IKunde getIKundeFromSession(){

        if(!checkUserSession()) return null;

        IKundenVerwaltungKomponente verwaltung = new KundenVerwaltungKomponente();
        IKunde kunde = null;

        try {
            kunde = verwaltung.fromKundeSessionDTO(loadKundeFromSession());
        } catch (SessionParseException|KundeNichtGefundenException e) {
            e.printStackTrace();
        }
        return kunde;
    }

    private static void saveKundeToSession(KundeSessionDTO sessionDTO){

        JsonNode jsonNode = Json.toJson(sessionDTO);
        session(USER,jsonNode.toString());

    }

    private static KundeSessionDTO loadKundeFromSession(){

        if(!session().containsKey(USER)) return null;

        String jsonNode_String = session(USER);
        JsonNode jsonNode = Json.parse(jsonNode_String);

        KundeSessionDTO sessionDTO = Json.fromJson(jsonNode,KundeSessionDTO.class);
        return sessionDTO;
    }
}
