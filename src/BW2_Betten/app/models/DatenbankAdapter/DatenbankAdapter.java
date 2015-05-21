package models.DatenbankAdapter;

import javafx.util.Pair;
import models.DatenbankAdapter.Exception.ServerDown;
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
    public boolean anmelden(String email, String passwort) throws ServerDown {

        boolean result = false;

        try{
            Statement stmt = con.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT U_ID FROM User WHERE U_Email = '"+email+"' AND  U_Passwort = '"+passwort+"' ;");

            if(rs.next()){
                result = true;
            }
            rs.close();

        }catch (SQLException ex){
            throw new ServerDown("Code "+ex.getErrorCode());
        }

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

        }

        return result;
    }

    @Override
    public boolean registrieren(String email, String passwort, String vn, String nachname, String gebD, int ort, String str, int hn, String adzs) {


        return false;
    }
}
