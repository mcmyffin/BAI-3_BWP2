package controllers;

import models.DatenTypen.Pair;
import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.IAnalyse;
import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.IProduktKomponente;
import models.ProduktKomponente.Produkt.IArtikel;
import models.ProduktKomponente.ProduktKomponente;
import play.Logger;

import java.util.*;

/**
 * Created by dima on 02.06.15.
 */
public class Analyse {

    static List<List<ArtikelSimplelDTO>> doABC_analyse(){

        List<List<ArtikelSimplelDTO>> analyseResult = new ArrayList();
        List<IArtikel> artikelList = new LinkedList();
        IAnalyse analysePersistenz = new DatenbankAdapter();

        List<Pair<IArtikel,Integer>> verkaufteArtikelAlsMenge = analysePersistenz.getAlleVerkaufteArtikel();
        List<Pair<IArtikel,Integer>> verkaufteArtikelAlsSumme = new LinkedList();

        double gesamtVerkaufsSumme = 0.0;

        for(Pair<IArtikel,Integer> artikelPaar : verkaufteArtikelAlsMenge){

            IArtikel einArtikel = artikelPaar.getKey();
            int menge = artikelPaar.getValue();

            int verkaufsSumme = (einArtikel.getPreis() * menge);

            artikelList.add(einArtikel);
            Pair<IArtikel,Integer> artikelVerkaufsSummePaar = new Pair(einArtikel,verkaufsSumme);
            verkaufteArtikelAlsSumme.add(artikelVerkaufsSummePaar);
            gesamtVerkaufsSumme += (verkaufsSumme);
        }


        Map<IArtikel,Double> artikelVerkaufsAnteilInProzent = new LinkedHashMap();
        for(Pair<IArtikel,Integer> artikelPaar : verkaufteArtikelAlsSumme){

            IArtikel artikel = artikelPaar.getKey();
            int verkaufsSumme = artikelPaar.getValue();

            artikelVerkaufsAnteilInProzent.put(artikel,((verkaufsSumme/gesamtVerkaufsSumme)*100.0));
        }

        final Map<IArtikel,Double> finalArtikeVerkaufsAnteilInProzent = new LinkedHashMap(artikelVerkaufsAnteilInProzent);
        Collections.sort(artikelList, new Comparator<IArtikel>() {
            @Override
            public int compare(IArtikel o1, IArtikel o2) {
                double artikel1Prozent = finalArtikeVerkaufsAnteilInProzent.get(o1);
                double artikel2Prozent = finalArtikeVerkaufsAnteilInProzent.get(o2);

                return Double.compare(artikel2Prozent,artikel1Prozent);
            }
        });


        List<ArtikelSimplelDTO> aObjekte = new LinkedList();
        List<ArtikelSimplelDTO> bObjekte = new LinkedList();
        List<ArtikelSimplelDTO> cObjekte = new LinkedList();

        double prozentSumme = 0.0;
        for(IArtikel artikel : artikelList){


            double artikelProzent = finalArtikeVerkaufsAnteilInProzent.get(artikel);
            prozentSumme += artikelProzent;
            if(prozentSumme <= 80.0){

                ArtikelSimplelDTO simplelDTO = artikel.toSimpleDTO();
                aObjekte.add(simplelDTO);

            }else if(prozentSumme <= 95.0){

                ArtikelSimplelDTO simplelDTO = artikel.toSimpleDTO();
                bObjekte.add(simplelDTO);

            }else{

                ArtikelSimplelDTO simplelDTO = artikel.toSimpleDTO();
                cObjekte.add(simplelDTO);

            }
        }

        analyseResult.add(aObjekte);
        analyseResult.add(bObjekte);
        analyseResult.add(cObjekte);

        return analyseResult;
    }
}
