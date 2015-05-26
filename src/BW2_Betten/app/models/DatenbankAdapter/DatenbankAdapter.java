package models.DatenbankAdapter;

import models.DatenbankAdapter.Exception.ServerDown;
import models.ProduktKomponente.Pair;
import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public List<List<String>> getArtikel(){

        String statement = "SELECT ART_ID,ART_Bezeichnung,ART_Beschreibung, ART_Kategorie,ART_Typ, " +
                                             "ART_BildURL, ART_Bestand, ART_Preis FROM `Artikel`; ";

        return getArtikelNachStatement(statement);
    }

    @Override
    public List<List<String>> sucheArtikelNachBegriff(String begriff) {

        String statement = "SELECT * FROM Artikel WHERE ART_Bezeichnung LIKE '%"+begriff+"%' " +
                                             "OR ART_Beschreibung LIKE '%"+begriff+"%' OR ART_Kategorie LIKE '%"+begriff+"%' " +
                                             "OR ART_ID LIKE '%"+begriff+"%';";

        return getArtikelNachStatement(statement);
    }

    @Override
    public List<String> getArtikelByID(int artikelnummer) {
        String statement = "SELECT * FROM Artikel WHERE ART_ID = "+artikelnummer+";";
        return getEinArtikelNachStatement(statement);
    }

    @Override
    public List<Pair<Integer, Integer>> getUnterArtikel(int artikelnummer) {
        List<Pair<Integer,Integer>> unterartikel = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT UPR_UnterArtID, UPR_Menge FROM Unterprodukt WHERE UPR_OberArtID = '"+artikelnummer+"' ;");

            while(rs.next()){
                int unterProdID = rs.getInt("UPR_UnterArtID");
                int menge = rs.getInt("UPR_Menge");

                Pair<Integer,Integer> unterprodukt = new Pair<>(unterProdID,menge);
                unterartikel.add(unterprodukt);
            }

            rs.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return unterartikel;
    }


    private List<List<String>> getArtikelNachStatement(String statement){

        List<List<String>> artikelListe = new ArrayList<>();

        try{
            Statement stmt= con.createStatement();
            ResultSet rs = stmt.executeQuery(statement);

            while(rs.next()){

                List<String> artikel = new ArrayList<>();
                artikel.add(rs.getString("ART_ID"));
                artikel.add(rs.getString("ART_Bezeichnung"));
                artikel.add(rs.getString("ART_Beschreibung"));
                artikel.add(rs.getString("ART_Kategorie"));
                artikel.add(rs.getString("ART_Typ"));
                artikel.add(rs.getString("ART_BildURL"));
                artikel.add(rs.getString("ART_Bestand"));
                artikel.add(rs.getString("ART_Preis"));

                artikelListe.add(artikel);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artikelListe;
    }

    private List<String> getEinArtikelNachStatement(String statement){

        List<String> artikel = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery(statement);

            if(rs.next()){
                artikel.add(rs.getString("ART_ID"));
                artikel.add(rs.getString("ART_Bezeichnung"));
                artikel.add(rs.getString("ART_Beschreibung"));
                artikel.add(rs.getString("ART_Kategorie"));
                artikel.add(rs.getString("ART_Typ"));
                artikel.add(rs.getString("ART_BildURL"));
                artikel.add(rs.getString("ART_Bestand"));
                artikel.add(rs.getString("ART_Preis"));
            }

            rs.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return artikel;
    }

    /*** IDBKunde ***/

    @Override
    public List<String> anmelden(String email, String passwort){

        List<String> result = null;

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT U_ID,U_Email,U_Vorname,U_Nachname,U_GebDatum,U_Ort,U_PLZ,U_Strasse,U_Hausnummer,U_Adresszusatz,U_Typ " +
                    "FROM User " +
                    "WHERE U_Email = '"+email+"' AND U_Passwort = '"+passwort+"' ;");

            if(rs.next()){

                result = new ArrayList<>();
                result.add(rs.getString("U_ID"));
                result.add(rs.getString("U_Email"));
                result.add(rs.getString("U_Vorname"));
                result.add(rs.getString("U_Nachname"));
                result.add(rs.getString("U_GebDatum"));
                result.add(rs.getString("U_Ort"));
                result.add(rs.getString("U_PLZ"));
                result.add(rs.getString("U_Strasse"));
                result.add(rs.getString("U_Hausnummer"));
                result.add(rs.getString("U_Adresszusatz"));
                result.add(rs.getString("U_Typ"));

            }
            rs.close();

        }catch (SQLException ex){}

        return result;
    }

    @Override
    public List<String> getKundenDaten(int kID, String email, String vn, String nn) {

        List<String> result = null;

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT U_ID,U_Email,U_Vorname,U_Nachname,U_GebDatum,U_Ort,U_PLZ,U_Strasse,U_Hausnummer,U_Adresszusatz,U_Typ " +
                    "FROM User " +
                    "WHERE U_Email = '"+email+"' AND U_ID = '"+kID+"' AND U_Vorname = '"+vn+"' AND U_Nachname = '"+nn+"' ;");

            if(rs.next()){

                result = new ArrayList<>();
                result.add(rs.getString("U_ID"));
                result.add(rs.getString("U_Email"));
                result.add(rs.getString("U_Vorname"));
                result.add(rs.getString("U_Nachname"));
                result.add(rs.getString("U_GebDatum"));
                result.add(rs.getString("U_Ort"));
                result.add(rs.getString("U_PLZ"));
                result.add(rs.getString("U_Strasse"));
                result.add(rs.getString("U_Hausnummer"));
                result.add(rs.getString("U_Adresszusatz"));
                result.add(rs.getString("U_Typ"));

            }
            rs.close();

        }catch (SQLException ex){}

        return result;

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
            return true;


        } catch (SQLException e) {
            return false;
        }
    }
}
