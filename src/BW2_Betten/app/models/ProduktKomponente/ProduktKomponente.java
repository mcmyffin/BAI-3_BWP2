package models.ProduktKomponente;


import models.DatenTypen.Pair;
import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.IDBArtikel;
import models.ProduktKomponente.DTO.ArtikelAdvancedDTO;
import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.Produkt.Artikel;
import models.ProduktKomponente.Produkt.ArtikelTyp;
import models.ProduktKomponente.Produkt.IArtikel;

import java.util.*;

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
    public IArtikel getArtikelByID(int artikelID) {

        IArtikel artikel = persistenz.getArtikelByID(artikelID);
        return artikel;
    }

    @Override
    public List<IArtikel> getArtikelByBegriff(String suchbegriff) {

        return persistenz.getArtikelByBegriff(suchbegriff);
    }

    @Override
    public List<IArtikel> getAlleArtikel() {

        return persistenz.getAlleArtikel();
    }

    @Override
    public ArtikelSimplelDTO getArtikelByIDAsDTO(int artikelID) {
        return parseArtikelToArtikelSimple(getArtikelByID(artikelID));
    }

    @Override
    public List<ArtikelSimplelDTO> getArtikelByBegriffAsDTO(String suchbegriff) {

        return parseArtikelListToArtikelSimpleList(getArtikelByBegriff(suchbegriff));
    }

    @Override
    public List<ArtikelSimplelDTO> getAlleArtikelAsDTO() {

        return parseArtikelListToArtikelSimpleList(getAlleArtikel());
    }

    @Override
    public ArtikelAdvancedDTO getAdvancedArtikelByID(int artikelID) {

        IArtikel artikel = this.getArtikelByID(artikelID);
        List<ArtikelAdvancedDTO> unterartikel = this.getUnterArtikelFrom(artikel);

        ArtikelAdvancedDTO advancedDTO = new ArtikelAdvancedDTO(artikel,unterartikel);
        return advancedDTO;
    }

    @Override
    public List<ArtikelAdvancedDTO> getAllAdvancedArtikel() {

        List<IArtikel> alleArtikel = this.getAlleArtikel();
        List<ArtikelAdvancedDTO> resultList = new LinkedList();

        for(IArtikel artikel : alleArtikel){

            if(artikel == null) continue;
            List<ArtikelAdvancedDTO> unterartikel = this.getUnterArtikelFrom(artikel);
            ArtikelAdvancedDTO advancedDTO = new ArtikelAdvancedDTO(artikel,unterartikel);
            resultList.add(advancedDTO);
        }

        return resultList;
    }


    private List<ArtikelSimplelDTO> parseArtikelListToArtikelSimpleList(List<IArtikel> artikelList){

        List<ArtikelSimplelDTO> resultListe = new ArrayList();

        for(IArtikel artikel : artikelList){

            ArtikelSimplelDTO simplelDTO = parseArtikelToArtikelSimple(artikel);
            resultListe.add(simplelDTO);
        }

        return resultListe;
    }

    private ArtikelSimplelDTO parseArtikelToArtikelSimple(IArtikel artikel){

        ArtikelSimplelDTO simplelDTO = new ArtikelSimplelDTO(artikel);
        return simplelDTO;
    }

    private List<ArtikelAdvancedDTO> getUnterArtikelFrom(IArtikel artikel){

        List<ArtikelAdvancedDTO> resultList = new ArrayList();

        for(IArtikel einArtikel : getUnterartikel(artikel,new LinkedList<IArtikel>())){

            ArtikelAdvancedDTO advancedDTO = new ArtikelAdvancedDTO(einArtikel,null);
            resultList.add(advancedDTO);
        }
        return resultList;
    }

    /**/
    private List<IArtikel> getUnterartikel(IArtikel artikel, List<IArtikel> accu){

        if(artikel == null) return accu;
        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_0)) return accu;

        List<IArtikel> untermenge = persistenz.getUnterartikelListe(artikel.getArtikelID());

        for(IArtikel einArtikel : untermenge){

            getUnterartikel(einArtikel,accu).add(einArtikel);
        }

        return accu;
    }
    /**/




    private IArtikel calculateBestand(IArtikel artikel){

        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_0)) return artikel;

        Queue<IArtikel> queue = new LinkedList();
        List<Pair<IArtikel,Integer>> unterartikel = new LinkedList();

        queue.offer(artikel);

        while (!queue.isEmpty()){

            IArtikel einArtikel = queue.poll();

            List<Pair<IArtikel,Integer>> untermenge = persistenz.getUnterArtikelByID(einArtikel.getArtikelID());
            if(untermenge == null) continue;

            for(Pair<IArtikel,Integer> eineUntermenge : untermenge){

                unterartikel.add(eineUntermenge);
                queue.offer(eineUntermenge.getKey());
            }
        }

        int bestand = Integer.MAX_VALUE;

        for(Pair<IArtikel,Integer> unterartikelPaar : unterartikel){

            IArtikel einArtikel = unterartikelPaar.getKey();
            int menge = unterartikelPaar.getValue();

            if(einArtikel.getTyp().equals(ArtikelTyp.LEVEL_0)){

                int tmpBestand = einArtikel.getBestand();

                if(tmpBestand <= 0){
                    bestand = 0;
                    break;
                }

                tmpBestand = (tmpBestand)/(menge);
                bestand = Math.min(bestand,tmpBestand);
            }
        }

        artikel.setBestand(bestand);
        return artikel;
    }

    //    private List<Pair<Integer,Integer>> getUnterArtikel(IArtikel artikel){
//
//        List<Pair<Integer,Integer>> unterartikel = new ArrayList();
//        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_0)) return unterartikel;
//        return persistenz.getUnterArtikel(artikel.getArtikelID());
//    }

//    private List<Pair<Integer,Integer>> getUnterArtikel(int artikelnummer){
//        IArtikel artikel = sucheArtikelNachArtikelID(artikelnummer);
//        return getUnterArtikel(artikel);
//    }

//    private int calculateBestand(IArtikel artikel){
//
//        int bestand = artikel.getBestand();
//
//        for(Pair<Integer,Integer> unterartikelIDPaar : getUnterArtikel(artikel)){
//
//            IArtikel unterartikel = sucheArtikelNachArtikelID(unterartikelIDPaar.getKey());
//            if(unterartikel.getTyp().equals(ArtikelTyp.LEVEL_0)){
//
//                if(unterartikel.getBestand() > 0){
//                    return (unterartikel.getBestand())/unterartikelIDPaar.getValue();
//                }else return 0;
//
//            } else {
//                return Math.min(calculateBestand(unterartikel), Integer.MAX_VALUE);
//            }
//        }
//        return bestand;
//    }


//    private List<Pair<Integer,Integer>> getAlleUnterartikelAlsListe(IArtikel artikel){
//
//        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_0)) return null;
//
//        List<Pair<Integer,Integer>> result = new ArrayList();
//
//
//        // LEVEL 1
//        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_1)){
//            return getUnterArtikel(artikel);
//        }
//
//        // LEVEL 2
//        if(artikel.getTyp().equals(ArtikelTyp.LEVEL_2)){
//
//            // LEVEL 1 & LEVEL 0
//
//        }
//
//        return null;
//
//    }

}
