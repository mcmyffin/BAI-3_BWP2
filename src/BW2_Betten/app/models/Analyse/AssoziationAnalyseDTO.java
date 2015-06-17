package models.Analyse;

import models.ProduktKomponente.DTO.ArtikelSimplelDTO;
import models.ProduktKomponente.Produkt.IArtikel;

import java.util.List;
import java.util.Map;

/**
 * Created by dima on 03.06.15.
 */
public class AssoziationAnalyseDTO {

    private List<ArtikelSimplelDTO> uebergeordnet;
    private List<ArtikelSimplelDTO> untergeordnet;

    // ueberzugeordnete ArtikelID -> untergeordnete ArtikelID
    private Map<Integer,Integer> zuordnung;
    // uebergeordnete ArtikelID -> Wahrscheinlichkeit in Prozent
    private Map<Integer,Double> zuordnungWahrscheinlichkeit;
}
