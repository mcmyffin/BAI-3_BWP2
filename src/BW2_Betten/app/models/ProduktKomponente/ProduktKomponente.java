package models.ProduktKomponente;


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

        IArtikel einArtikel = new Artikel(artID,bezeichnung,beschreibung,kategorie,typ,bildURL,bestand,preis);
        System.out.println("Artikel : "+einArtikel.getBezeichnung()+" mit LVL "+einArtikel.getTyp());
        System.out.println("hat den bestand: "+einArtikel.getBestand());
        if(einArtikel.getTyp().getValue() != (ArtikelTyp.LEVEL_0.getValue())){
            System.out.println("->Artikel : " + einArtikel.getBezeichnung() + " geht in die bereichnung");
            einArtikel.setBestand(calculateBestand(einArtikel));
            System.out.println("Artikel : " + einArtikel.getBezeichnung()+" hat jetzt den bestand: "+einArtikel.getBestand());
        }
        return einArtikel;
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

        for(Pair<Integer,Integer> unterartikelIDPaar : getUnterArtikel(artikel)){

            IArtikel unterartikel = sucheArtikelNachArtikelID(unterartikelIDPaar.getKey());
            if(unterartikel.getTyp().equals(ArtikelTyp.LEVEL_0)){
                System.out.println("UNTERARTIKEL: "+unterartikel.getBezeichnung()+" | bestand = "+unterartikel.getBestand()+" /  menge = "+unterartikelIDPaar.getValue() +" = ");

                if(unterartikel.getBestand() > 0){
                    return (unterartikel.getBestand())/unterartikelIDPaar.getValue();
                }else return 0;


            } else {
                System.out.println("min("+calculateBestand(unterartikel) +" , "+ bestand+")");
                return Math.min(calculateBestand(unterartikel), bestand);
            }
        }
        System.out.println(artikel.getBezeichnung()+" hat keine unterartikel");
        return bestand;
    }

}
