package models.WarenkorbKomponente;

import models.ProduktKomponente.ArtikelDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 20.05.15.
 */
public class WarenkorbDTO {

    private Map<ArtikelDTO,Integer> warenkorb;
    private int gesamtpreis = 0;

    public WarenkorbDTO(){
        warenkorb = new HashMap<>();
    }

    public boolean addArtikelDTO(ArtikelDTO artikelDTO, Integer menge){

        if(artikelDTO == null || menge == 0) return false;
        if(warenkorb.containsKey(artikelDTO)) return false;

        warenkorb.put(artikelDTO,menge);

        return true;
    }

    public Map<ArtikelDTO,Integer> getWarenkorb(){
        return warenkorb;
    }

    public int calculatePreis(){
        for(ArtikelDTO artikelDTO: warenkorb.keySet()){
            gesamtpreis+= (artikelDTO.getPreis() * warenkorb.get(artikelDTO));
        }
        return gesamtpreis;
    }


}
