package controllers;

import models.DatenTypen.Pair;
import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.Produkt.IArtikel;
import models.WarenkorbKomponente.DTO.WarenkorbDTO;
import models.WarenkorbKomponente.IWarenkorb;
import models.WarenkorbKomponente.IWarenkorbKomponente;
import models.WarenkorbKomponente.WarenkorbKomponente;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.warenkorb;

import java.util.*;

/**
 * Created by dima on 18.05.15.
 */
public class WarenkorbPersistenz extends Controller {

    public static final String WARENKORB_ACTION_NAME = "warenkorb aktion";
    public static final String WARENKORB_ACTION_ARTIKEL_REMOVE = "Artikel loeschen";
    public static final String WARENKORB_ACTION_ARTIKEL_ADD = "Artikel hinzufuegen";
    public static final String WARENKORB_ACTION_ARTIKEL_REFRESH = "Artikelmenge aktualisieren";

    private static IWarenkorbKomponente warenkorbKomponente;

    static boolean addArtikelToWarenkorb(int artikelNummer){

        IArtikel artikel = Produkte.getArtikelByID(artikelNummer);
        Pair<Integer,Integer> artikelSession = getArtikelAusSessionByInt(artikelNummer);

        // precondition
        if(artikel == null) return false;
        if(artikel.getBestand() <= 0) return false;

        if(artikelSession != null){

            if((artikelSession.getValue()+1) > artikel.getBestand()) return false;
            Pair<String,String> artikelSessionNeu = new Pair(artikelNummer+"",(artikelSession.getValue()+1)+"");
            setNewSessionElem(artikelSessionNeu);
        }else{

            Pair<String,String> artikelSessionNeu = new Pair(artikelNummer+"",1+"");
            setNewSessionElem(artikelSessionNeu);
        }

        return true;
    }

    static void removeArtikelFromWarenkorb(int artikelNummer){

        session().remove(artikelNummer+"");
    }

    static boolean setArtikelMengeInWarenkorb(int artikelNummer, int menge){

        IArtikel artikel = Produkte.getArtikelByID(artikelNummer);
        Pair<Integer,Integer> artikelSession = getArtikelAusSessionByInt(artikelNummer);

        // precondition
        if(artikel == null) return false;
        if(artikel.getBestand() <= 0) return false;
        if(artikel.getBestand() < menge) return false;
        if(menge <= 0){
            removeArtikelFromWarenkorb(artikelNummer);
            return true;
        }

        if(artikelSession != null){

            Pair<String,String> artikelSessionNeu = new Pair(artikelNummer+"",menge+"");
            setNewSessionElem(artikelSessionNeu);
        }else{

            Pair<String,String> artikelSessionNeu = new Pair(artikelNummer+"",menge+"");
            setNewSessionElem(artikelSessionNeu);
        }

        return true;
    }



    public static Result doWarenkorb(){

        DynamicForm artForm = Form.form().bindFromRequest();

        String actionName = artForm.get(WARENKORB_ACTION_NAME);
        boolean actionResult = false;


        if(actionName.equals(WARENKORB_ACTION_ARTIKEL_REMOVE)){

            int artikelnummer = Integer.parseInt(artForm.get("artikelNummer"));
            removeArtikelFromWarenkorb(artikelnummer);
            actionResult = true;

        }else if(actionName.equals(WARENKORB_ACTION_ARTIKEL_REFRESH)){

            int artikelnummer = Integer.parseInt(artForm.get("artikelNummer"));
            int menge = Integer.parseInt(artForm.get("menge"));

            actionResult = setArtikelMengeInWarenkorb(artikelnummer,menge);
        }

        if(actionResult){
            return Verlinkung.showWarenkorb();
        }else{
            return internalServerError("UUPS es ist etwas schief gegangen!");
        }
    }


    static WarenkorbDTO getWarenkorbDTO(){

        Pair<List<ArtikelSimplelDTO>,Map<Integer,Integer>> warenkorbSession = getWarenKorbAsPair();
        WarenkorbDTO warenkorbDTO = new WarenkorbDTO(warenkorbSession.getKey(),warenkorbSession.getValue());

        return warenkorbDTO;
    }

    /*** Hilfsmethoden ***/

    // gibt den gesamten Warenkorb aus der Session
    private static Pair<List<ArtikelSimplelDTO>,Map<Integer,Integer>> getWarenKorbAsPair() {

        Map<Integer,Integer> artikelMenge = new HashMap();
        List<ArtikelSimplelDTO> simplelDTOList = new LinkedList();

        for(String artikelNummerString : session().keySet()){

            if(artikelNummerString.equals(Account.USER) || artikelNummerString.equals(Account.TIME)) continue;

            int artikelNummer = Integer.parseInt(artikelNummerString);
            int menge = Integer.parseInt(session(artikelNummerString));

            ArtikelSimplelDTO simplelDTO = Produkte.getArtikelByIDAsDTO(artikelNummer);

            artikelMenge.put(artikelNummer, menge);
            simplelDTOList.add(simplelDTO);

        }

        return new Pair<List<ArtikelSimplelDTO>,Map<Integer,Integer>> (simplelDTOList,artikelMenge);
    }





    /**
     * Get Artikel aus Session
     * Sucht nach dem Artikel in der Session des Klients
     *
     * @return Pair<artikelnummer,menge>
     *     Wenn nicht gefunden, dann NULL
     */
    private static Pair<Integer,Integer> getArtikelAusSessionByString(String artikelNummerString){

        Pair<Integer,Integer> artikelSession = null;

        if(session().containsKey(artikelNummerString)){

            String mengeString = session(artikelNummerString);

            int artikelNummer = Integer.parseInt(artikelNummerString);
            int menge = Integer.parseInt(mengeString);
            artikelSession = new Pair(artikelNummer,menge);
        }
        return artikelSession;
    }


    /**
     * Get Artikel aus Session
     * Sucht nach dem Artikel in der Session des Klients
     *
     * @return Pair<artikelnummer,menge>
     *     Wenn nicht gefunden, dann NULL
     */
    private static Pair<Integer,Integer> getArtikelAusSessionByInt(int artikelNummer){

        Pair<Integer,Integer> artikelSession = null;

        if(session().containsKey(artikelNummer+"")){

            String mengeString = session(artikelNummer + "");

            int menge = Integer.parseInt(mengeString);
            artikelSession = new Pair(artikelNummer,menge);
        }
        return artikelSession;
    }

    private static void setNewSessionElem(Pair<String,String> sessionElem){
        session(sessionElem.getKey(),sessionElem.getValue());
    }

    static IWarenkorb getIWarenkorbFromSession(){

        WarenkorbDTO warenkorbDTO = getWarenkorbDTO();
        IWarenkorbKomponente warenkorbKomponente = new WarenkorbKomponente();

        IWarenkorb warenkorb = warenkorbKomponente.fromSession(warenkorbDTO.getArtikelMenge());

        return warenkorb;
    }


}
