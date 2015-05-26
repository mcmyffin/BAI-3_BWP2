package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.KundenVerwaltungKomponente.*;
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

    public static Result doAnmelden(){

        DynamicForm anmeldenForm = Form.form().bindFromRequest();
        String email = anmeldenForm.get("email");
        String passwort = anmeldenForm.get("passwort");

        IKundenVerwaltungKomponente kundenverwaltung = new KundenVerwaltungKomponente();
        IKunde kunde = kundenverwaltung.anmelden(email, passwort);

        if(kunde != null){

            KundeSessionDTO kundeSessionDTO = kunde.toSessionDTO();
            JsonNode jsonNode = Json.toJson(kundeSessionDTO);
            session(USER, jsonNode.toString());
            session(TIME,Long.toString(System.currentTimeMillis()));
            return showKundeProfil();
        }else{
            return ok(anmelden.render("<b style=\"color:red\">Email oder Passwort falsch</b>"));
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
        int plz = Integer.parseInt(registrierForm.get("plz"));
        String str = registrierForm.get("strasse");
        int hn = Integer.parseInt(registrierForm.get("hn"));
        String adzs = registrierForm.get("adresszusatz");


        if(!passwort1.equals(passwort2)) return ok(registrieren.render("Passw&ouml;rter stimmen nicht &uuml;berein"));
        IKundenVerwaltungKomponente kundenverwalter = new KundenVerwaltungKomponente();

        int result = kundenverwalter.registrieren(email, passwort1, vn, nachname, gebDatum, ort, plz, str, hn, adzs);
        if(result == 0){
            return ok(registrieren.render("Erfolgreich registriert<br> Sie k&ouml;nnen sich jetzt anmelden"));
        }else if(result == 1){
            return ok(registrieren.render("Diese Email-Adresse wird bereits verwendet, bitte nehmen sie eine andere"));
        }else if(result == 2){
            return ok(registrieren.render("Passwort zu kurz oder leer"));
        }else if(result == 3){
            return ok(registrieren.render("Email ist ung&uuml;ltig oder leer"));
        }else{
            return ok(registrieren.render("Registrieren zuzeit nicht m&ouml;glich!<br>Bitte versuchen Sie es zu einem sp&auml;teren Zeitpunkt"));
        }
    }



    public static Result doKundenProfil(){

//        JsonNode changedProfil = request().body().asJson();
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        IKundenVerwaltungKomponente verwaltung = new KundenVerwaltungKomponente();
        KundeSessionDTO sessionDTO = Json.fromJson(Json.parse(session(USER)),KundeSessionDTO.class);

        String email = dynamicForm.get("email");
        String vn    = dynamicForm.get("vorname");
        String nn    = dynamicForm.get("nachname");
        String gd    = dynamicForm.get("gebDatum");
        String ort   = dynamicForm.get("ort");
        String plz   = dynamicForm.get("plz");
        String str   = dynamicForm.get("strasse");
        String hn    = dynamicForm.get("hausnummer");
        String adzs  = dynamicForm.get("adresszusatz");

        if(
                email == null || email.isEmpty() ||
                vn == null || vn.isEmpty() ||
                nn == null || nn.isEmpty() ||
                ort == null || ort.isEmpty() ||
                plz == null || plz.isEmpty() ||
                str == null || str.isEmpty() ||
                hn == null || hn.isEmpty()){

            KundeDTO dto = verwaltung.getKundenDaten(sessionDTO);
            return ok(profil.render(dto,"Eingaben beinhalten leere oder ung&uuml;tige Inhalte"));
        }

        KundeDTO kundeDTO = new KundeDTO(sessionDTO.getId() ,email,vn,nn,gd,ort,Integer.parseInt(plz),str, Integer.parseInt(hn),adzs);
        boolean result = verwaltung.changeProfil(kundeDTO);

        if(result){
            return ok(profil.render(kundeDTO,"Erfolgreich gespeichert!"));
        }else{
            return ok(profil.render(kundeDTO,"Hat nicht funktioniert!"));
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
        else{

            String jsonString = session(USER);
            JsonNode jsonNode = Json.parse(jsonString);
            KundeSessionDTO kundeSessionDTO = Json.fromJson(jsonNode,KundeSessionDTO.class);

            System.out.println("KUNDE json  -> "+jsonNode);
            System.out.println("KUNDE class -> "+kundeSessionDTO.toString());
            if(checkAdminSession()){
                // ADMIN
                return ok(admin_account.render(kundeSessionDTO));
            }else{
                // USER
                return ok(kunde_account.render(kundeSessionDTO));
            }
        }
    }

    public static boolean checkUserSession(){
        return (session(USER) != null);
    }

    public static boolean checkAdminSession(){
        if(session(USER) != null){

            String jsonString = session(USER);
            JsonNode jsonNode = Json.parse(jsonString);
            KundeSessionDTO sessionDTO = Json.fromJson(jsonNode,KundeSessionDTO.class);

            if(sessionDTO.getTyp() == UserTyp.ADMIN.getValue()) return true;
            else return false;
        }else{
            return false;
        }
    }

    public static Result kundeProfil(){

        if(!checkUserSession()) return Verlinkung.showAnmelden();

        IKundenVerwaltungKomponente verwaltung = new KundenVerwaltungKomponente();
        KundeSessionDTO sessionDTO = Json.fromJson(Json.parse(session(USER)),KundeSessionDTO.class);
        KundeDTO dto = verwaltung.getKundenDaten(sessionDTO);

        return ok(profil.render(dto,""));
    }

    /**** ADMIN Bereich ****/

}
