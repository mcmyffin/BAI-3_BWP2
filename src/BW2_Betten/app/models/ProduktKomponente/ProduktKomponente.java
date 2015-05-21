package models.ProduktKomponente;

import javafx.util.Pair;
import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.IDBArtikel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 13.05.15.
 */
public class ProduktKomponente implements IProduktKomponente{

    private IDBArtikel persistenz;

    public ProduktKomponente(){
        this.persistenz = new DatenbankAdapter();
    }




    //*** implementierte Interface methoden

    @Override
    public IArtikel sucheArtikelNachArtikelID(int artikelID) {

        return buildArtikel(persistenz.getArtikelByID(artikelID));
    }

    @Override
    public List<IArtikel> sucheArtikelNachBegriff(String suchbegriff) {

        List<List<String>> artikelListe = persistenz.sucheArtikelNachBegriff(suchbegriff);
       return buildArtikelList(artikelListe);
    }

    @Override
    public List<IArtikel> getArtikel() {

        List<List<String>> artikelListe = persistenz.getArtikel();
        return buildArtikelList(artikelListe);
    }

    private List<IArtikel> buildArtikelList(List<List<String>> artikelListe){

        List<IArtikel> artikelList = new ArrayList<>();

        for(List<String> artikel: artikelListe){

            IArtikel art = buildArtikel(artikel);
            if(art == null) continue;
            artikelList.add(art);
        }

        return artikelList;
    }

    private IArtikel buildArtikel(List<String> artikel){

        if(artikel.isEmpty()) return null;

        int artID = Integer.parseInt(artikel.get(0));
        String bezeichnung = artikel.get(1);
        String beschreibung = artikel.get(2);
        ArtikelKategorie kategorie = ArtikelKategorie.getKategorieByString(artikel.get(3));
        ArtikelTyp typ = ArtikelTyp.getTypByInt(Integer.parseInt(artikel.get(4)));
        String bildURL = artikel.get(5);
        int bestand = Integer.parseInt(artikel.get(6));
        int preis = Integer.parseInt(artikel.get(7));

        return new Artikel(artID,bezeichnung,beschreibung,kategorie,typ,bildURL,bestand,preis);
    }

    private List<Pair<Integer,Integer>> getUnterArtikel(IArtikel artikel){

        List<Pair<Integer,Integer>> unterartikel = new ArrayList();
        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_0)) return unterartikel;
        return persistenz.getUnterArtikel(artikel.getArtikelID());
    }

    private List<Pair<Integer,Integer>> getUnterArtikel(int artikelnummer){
        IArtikel artikel = sucheArtikelNachArtikelID(artikelnummer);
        return getUnterArtikel(artikel);
    }

    private int calculateBestand(IArtikel artikel){

        int bestand = artikel.getBestand();
        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_0)) return bestand;



        return bestand;
    }

}
