package models.WarenkorbKomponente;

import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.Produkt.IArtikel;
import models.WarenkorbKomponente.DTO.WarenkorbDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 27.05.15.
 */
public class Warenkorb implements IWarenkorb {

    private List<IArtikel> artikelListe;
    private Map<Integer,Integer> artikelMenge;


    private Warenkorb(List<IArtikel> artikelListe, Map<Integer,Integer> artikelMenge){
        this.artikelListe = artikelListe;
        this.artikelMenge = artikelMenge;
    }

    static IWarenkorb createWarenkorb(List<IArtikel> artikelListe, Map<Integer,Integer> artikelMenge){
        return (IWarenkorb) (new Warenkorb(artikelListe,artikelMenge));
    }


    @Override
    public List<IArtikel> getArtikelListe() {
        return artikelListe;
    }

    @Override
    public Map<Integer, Integer> getArtikelMenge() {
        return artikelMenge;
    }

    @Override
    public int getGesamtPreis() {

        int preis = 0;

        for(IArtikel einArtikel: artikelListe){

            int wert = einArtikel.getPreis();
            int menge = artikelMenge.get(einArtikel.getArtikelID());

            preis += (wert*menge);
        }

        return preis;
    }

    @Override
    public boolean addArtikel(IArtikel artikel) {

        // pruefe auf NULL
        if(artikel == null)  return false;

        // precondition über den Artikel bestand
        if(artikel.getBestand() <= 0) return false;
        if(artikelListe.contains(artikel)){

            int menge = artikelMenge.get(artikel.getArtikelID());
            if(menge+1 > artikel.getBestand()) return false;

            artikelMenge.put(artikel.getArtikelID(),menge+1);

        }else{

            artikelMenge.put(artikel.getArtikelID(),1);
            artikelListe.add(artikel);
        }

        // TODO im zusamenhang pruefen auf Bestand

        return true;
    }

    @Override
    public boolean setArtikel(IArtikel artikel, int menge) {

        // pruefe auf NULL
        if(artikel == null)  return false;

        // precondition über den Artikel bestand
        if(artikel.getBestand() <= 0) return false;

        if(menge <= 0) removeArtikel(artikel);
        if(artikelListe.contains(artikel)){

            if(menge > artikel.getBestand()) return false;
            artikelMenge.put(artikel.getArtikelID(),menge);


            return true;
        }else{

            artikelListe.add(artikel);
            artikelMenge.put(artikel.getArtikelID(),menge);

            return true;
        }

    }

    @Override
    public boolean removeArtikel(IArtikel artikel){

        // pruefe auf NULL
        if(artikel == null) return false;

        // preconditions
        if(artikelListe.contains(artikel)){
            artikelListe.remove(artikel);
        }

        if(artikelMenge.containsKey(artikel.getArtikelID())){
            artikelMenge.remove(artikel.getArtikelID());
        }


        return true;
    }

    @Override
    public WarenkorbDTO toDTO() {

        pruefeAufUngueltigeMengen();

        List<ArtikelSimplelDTO> artikelSimplelDTOs = new ArrayList();

        for(IArtikel einArtikel : getArtikelListe()){

            ArtikelSimplelDTO simplelDTO = einArtikel.toSimpleDTO();
            artikelSimplelDTOs.add(simplelDTO);
        }

        WarenkorbDTO warenkorbDTO = new WarenkorbDTO(artikelSimplelDTOs,getArtikelMenge());
        return warenkorbDTO;
    }

    private void pruefeAufUngueltigeMengen(){

        List<IArtikel> ungueltigeArtikel = new LinkedList();

        for(IArtikel artikel : artikelListe){

            int artID = artikel.getArtikelID();

            if(!artikelMenge.containsKey(artID)){
                ungueltigeArtikel.add(artikel);
            }else{

                int menge = artikelMenge.get(artikel);
                if(menge <= 0) ungueltigeArtikel.add(artikel);
            }



            artikelListe.removeAll(ungueltigeArtikel);
        }
    }

}
