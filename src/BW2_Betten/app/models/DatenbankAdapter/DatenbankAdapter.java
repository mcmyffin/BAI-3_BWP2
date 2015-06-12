package models.DatenbankAdapter;

import models.BestellKomponente.BestellStatus;
import models.DatenTypen.Pair;
import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.KundenVerwaltungKomponente.Benutzer.KundenCreator;
import models.KundenVerwaltungKomponente.Exceptions.KundeCreateException;
import models.ProduktKomponente.Exceptions.ArtikelCreateException;
import models.ProduktKomponente.Produkt.ArtikelCreator;
import models.ProduktKomponente.Produkt.IArtikel;
import models.WarenkorbKomponente.IWarenkorb;
import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by dima on 13.05.15.
 */
public class DatenbankAdapter implements IDatenbankAdapter{

    Connection con;

    public DatenbankAdapter(){
        con = DB.getConnection();
    }


    /*** IDBArtikel implementierung ***/

    @Override
    public List<IArtikel> getAlleArtikel(){

        String statement = "SELECT * FROM `Artikel`; ";

        return getArtikelNachStatement(statement);
    }

    @Override
    public List<IArtikel> getArtikelByBegriff(String begriff) {

        String statement = "SELECT * FROM Artikel WHERE ART_Bezeichnung LIKE '%"+begriff+"%' " +
                                             "OR ART_Beschreibung LIKE '%"+begriff+"%' OR ART_Kategorie LIKE '%"+begriff+"%' " +
                                             "OR ART_ID LIKE '%"+begriff+"%';";

        return getArtikelNachStatement(statement);
    }

    @Override
    public IArtikel getArtikelByID(int artikelnummer) {
        String statement = "SELECT * FROM Artikel WHERE ART_ID = "+artikelnummer+";";
        return getEinArtikelNachStatement(statement);
    }

    @Override
    public List<Pair<IArtikel, Integer>> getUnterArtikelByID(int artikelnummer) {
        List<Pair<IArtikel,Integer>> unterartikel = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT UPR_UnterArtID, UPR_Menge FROM Unterprodukt WHERE UPR_OberArtID = '"+artikelnummer+"' ;");

            while(rs.next()){
                int unterProdID = rs.getInt("UPR_UnterArtID");
                int menge = rs.getInt("UPR_Menge");

                IArtikel artikel = this.getArtikelByID(unterProdID);

                Pair<IArtikel,Integer> unterprodukt = new Pair<>(artikel,menge);
                unterartikel.add(unterprodukt);
            }

            rs.close();
            con.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return unterartikel;
    }

    @Override
    public List<IArtikel> getUnterartikelListe(int artikelnummer) {

        List<IArtikel> unterartikelListe = new LinkedList();

        try {

            if(con.isClosed()) con = DB.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT UPR_UnterArtID FROM Unterprodukt WHERE UPR_OberArtID = '"+artikelnummer+"' ;");

            while(rs.next()){

                int unterID = rs.getInt("UPR_UnterArtID");

                IArtikel artikel = getArtikelByID(unterID);

                if(artikel != null){
                    unterartikelListe.add(artikel);
                }
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unterartikelListe;
    }


    private List<IArtikel> getArtikelNachStatement(String statement){

        List<IArtikel> artikelListe = new ArrayList<>();

        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(statement);

            while(rs.next()){


                int id = rs.getInt("ART_ID");
                String bezeichnung = rs.getString("ART_Bezeichnung");
                String beschreibung = rs.getString("ART_Beschreibung");
                String kategorieString = rs.getString("ART_Kategorie");
                int typInt = rs.getInt("ART_Typ");
                String bildURL = rs.getString("ART_BildURL");
                int bestand = rs.getInt("ART_Bestand");
                int preis = rs.getInt("ART_Preis");


                artikelListe.add(ArtikelCreator.createArtikel(id, bezeichnung, beschreibung, kategorieString, typInt, bildURL, bestand, preis));

            }
            rs.close();
            con.close();

        } catch (SQLException|ArtikelCreateException e) {
            e.printStackTrace();
        }

        return artikelListe;
    }

    private IArtikel getEinArtikelNachStatement(String statement){

        IArtikel artikel = null;

        try{
            if(con.isClosed()) con = DB.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(statement);

            if(rs.next()){
                int id = rs.getInt("ART_ID");
                String bezeichnung = rs.getString("ART_Bezeichnung");
                String beschreibung = rs.getString("ART_Beschreibung");
                String kategorieString = rs.getString("ART_Kategorie");
                int typInt = rs.getInt("ART_Typ");
                String bildURL = rs.getString("ART_BildURL");
                int bestand = rs.getInt("ART_Bestand");
                int preis = rs.getInt("ART_Preis");

                artikel = ArtikelCreator.createArtikel(id, bezeichnung, beschreibung, kategorieString, typInt, bildURL, bestand, preis);
            }

            rs.close();
            con.close();
        }catch (SQLException|ArtikelCreateException ex){
            ex.printStackTrace();
        }
        return artikel;
    }

    /****************/
    /*** IDBKunde ***/

    @Override
    public IKunde anmelden(String email, String passwort){

        String statement = "SELECT * FROM User WHERE U_Email = '"+email+"' AND U_Passwort = '"+passwort+"' ;";
        return this.getKundeByStatement(statement);
    }

    @Override
    public IKunde getKundeByID(int kID) {

        String statement = "SELECT * FROM User WHERE U_ID = '"+kID+"'  ;";
        return this.getKundeByStatement(statement);
    }

    @Override
    public int getKundenIDByEmail(String email) {
        int result = 0;

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT U_ID FROM User WHERE U_Email = '"+email+"' ;");

            if(rs.next()){
                result = Integer.parseInt(rs.getString("U_ID"));
            }
            rs.close();
            con.close();

        }catch (SQLException ex){
            return -1;
        }
        return result;
    }

    @Override
    public boolean registrieren(String email, String passwort, String vn, String nachname, String gebD, String ort, int plz, String str, int hn, String adzs) {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery("INSERT INTO User VALUES(" +
                    "''," +
                    "'"+email+"'," +
                    "'"+passwort+"'," +
                    "'"+vn+"'," +
                    "'"+nachname+"'," +
                    "'"+gebD+"'," +
                    "'"+ort+"'," +
                    "'"+plz+"'," +
                    "'"+str+"'," +
                    "'"+hn+"'," +
                    "'"+adzs+"'," +
                    "'');");


            rs.close();
            con.close();

            return true;


        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean changeProfil(int kID, String email, String vn, String nn, String gebD, String ort, int plz, String str, int hn, String adzs) {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery("UPDATE User SET " +
                    "U_Email = '"+email+"'," +
                    "U_Vorname = '"+vn+"'," +
                    "U_Nachname = '"+nn+"'," +
                    "U_GebDatum = '"+gebD+"'," +
                    "U_Ort = '"+ort+"'," +
                    "U_Plz = '"+plz+"'," +
                    "U_Strasse = '"+str+"'," +
                    "U_Hausnummer = '"+hn+"'," +
                    "U_Adresszusatz = '"+adzs+" "+
                    "WHERE U_ID = '"+kID+"' ;");


            rs.close();
            con.close();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }


    private IKunde getKundeByStatement(String statement){

        IKunde kunde = null;

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(statement);

            if(rs.next()){

                int id = rs.getInt("U_ID");
                String email = rs.getString("U_Email");
                String vorname = rs.getString("U_Vorname");
                String nachname = rs.getString("U_Nachname");
                String gebDatum = rs.getString("U_GebDatum");
                String ort = rs.getString("U_Ort");
                int plz = rs.getInt("U_PLZ");
                String strasse = rs.getString("U_Strasse");
                int hausnummer = rs.getInt("U_Hausnummer");
                String adrZS = rs.getString("U_Adresszusatz");
                int typInt = rs.getInt("U_Typ");

                kunde = KundenCreator.createKunde(id,email,vorname,nachname,gebDatum,ort,plz,strasse,hausnummer,adrZS,typInt);

            }
            rs.close();
            con.close();

        }catch (SQLException|KundeCreateException ex){
            ex.printStackTrace();
        }

        return kunde;
    }

    /*******************/
    /*** IBestellung ***/

    @Override
    public boolean makeBestellung(IKunde kunde, IWarenkorb warenkorb) {

        if(kunde == null || warenkorb == null) return false;

        int userID = kunde.getID();

        Calendar calendar = Calendar.getInstance();
        String date = calendar.getTime().toString();

        // Schreibsperre setzten
        setBestellvorgangLock();

        // Bestellung erstellen
        boolean bestellungErstellen = createBestellung(date, BestellStatus.IN_BEARBEITUNG.getValue());

        // Auftrag erstellen
        boolean auftragErstellen = createAuftrag(userID);

        // Warenkorb uebertragen in die Bestellliste
        boolean bestellliste = true;
        for(IArtikel artikel : warenkorb.getArtikelListe()){

            int artikelID = artikel.getArtikelID();
            int menge = warenkorb.getArtikelMenge().get(artikelID);

            boolean result = createBestellEintrag(artikelID,menge);

            if(!result){
                bestellliste = result;
            }
        }

        // Schreibsperre aufheben
        unsetBestellvorgangLock();

        // Connection beenden
        try {
            con.close();
        } catch (SQLException e) {
            return false;
        }

        return bestellungErstellen && auftragErstellen && bestellliste;
    }

    private void setBestellvorgangLock(){

        String lockSetzen = "LOCK TABLES online_shop.Bestellung WRITE, online_shop.Auftrag WRITE, Bestellliste WRITE ;";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(lockSetzen);

            rs.close();
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private boolean createBestellung(String datum, int status){

        String bestellungErstellen = "INSERT INTO online_shop.Bestellung (B_Datum , B_Status) VALUES('"+datum+"',"+status+");";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(bestellungErstellen);

            rs.close();
            stmt.close();
        }catch (SQLException ex){
            return false;
        }

        return true;
    }

    private boolean createAuftrag(int userID){

        String auftragErstellen = "INSERT INTO online_shop.Auftrag (A_UserID , A_BestellID) VALUES("+userID+", (SELECT MAX(B_ID) FROM Bestellung) ) ;";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(auftragErstellen);

            rs.close();
            stmt.close();
        }catch (SQLException ex){
            return false;
        }
        return true;
    }

    private boolean createBestellEintrag(int artID, int menge){

        String bestelllisteEintrage = "INSERT INTO online_shop.Bestellliste (BEST_BestellID , BEST_ArtikelID , BEST_Menge) VALUES((SELECT MAX(B_ID) FROM Bestellung) , "+artID+" ,"+menge+");";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(bestelllisteEintrage);

            rs.close();
            stmt.close();

        }catch (SQLException ex){
            return false;
        }
        return true;
    }


    private void unsetBestellvorgangLock(){

        String lockAufheben = "UNLOCK TABLES;";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(lockAufheben);

            rs.close();
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    /******************/
    /*** IAnalyse ***/
    @Override
    public List<Pair<IArtikel, Integer>> getAlleVerkaufteArtikel() {

        List<Pair<IArtikel,Integer>> verkaufteArtikel = new LinkedList();
        String statement = "SELECT BEST_ArtikelID, (SUM(BEST_Menge)) AS menge FROM Bestellliste GROUP BY BEST_ArtikelID";

        try{
            if(con.isClosed()) con = DB.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(statement);

            while (rs.next()){

                int artID = rs.getInt("BEST_ArtikelID");
                int menge = rs.getInt("menge");

                IArtikel artikel = this.getArtikelByID(artID);

                if(artikel != null){

                    Pair<IArtikel,Integer> artikelMengePaar = new Pair(artikel,menge);
                    verkaufteArtikel.add(artikelMengePaar);
                }
            }


            rs.close();
            stmt.close();
            con.close();
        }catch (SQLException ex){
            return null;
        }

        return verkaufteArtikel;
    }
}
