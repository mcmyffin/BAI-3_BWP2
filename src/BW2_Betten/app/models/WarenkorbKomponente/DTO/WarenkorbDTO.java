package models.WarenkorbKomponente.DTO;

import models.ProduktKomponente.DTO.ArtikelSimplelDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by dima on 20.05.15.
 */
public class WarenkorbDTO {

    private List<ArtikelSimplelDTO> artikelListe;
    private Map<Integer,Integer> artikelMenge;

    public WarenkorbDTO(List<ArtikelSimplelDTO> artikelListe,Map<Integer,Integer> artikelMenge){

        this.artikelListe = artikelListe;
        this.artikelMenge = artikelMenge;
    }

    public List<ArtikelSimplelDTO> getArtikelListe() {
        return artikelListe;
    }

    public int getArtikelMengeByID(int artikelID){

        if(artikelMenge.containsKey(artikelID)){
            return artikelMenge.get(artikelID);
        }else{
            return 0;
        }
    }

    public int getGesamtpreis() {

        int preis = 0;
        for(ArtikelSimplelDTO simplelDTO : artikelListe){

            int menge = artikelMenge.get(simplelDTO.getArtID());
            int einzelPreis = simplelDTO.getPreis();

            preis += (menge*einzelPreis);
        }
        return preis;
    }

    public Map<Integer,Integer> getArtikelMenge(){
        return this.artikelMenge;
    }
}
