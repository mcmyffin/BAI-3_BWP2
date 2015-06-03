package controllers;

import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.IBestellung;
import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.WarenkorbKomponente.IWarenkorb;

/**
 * Created by dima on 02.06.15.
 */
public class Bestellung {

    static boolean doBestellung(IKunde kunde, IWarenkorb warenkorb){

        if(kunde == null || warenkorb == null) return false;

        IBestellung bestellPersistenz = new DatenbankAdapter();

        return bestellPersistenz.makeBestellung(kunde,warenkorb);
    }
}
