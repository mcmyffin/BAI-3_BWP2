package models.DatenbankAdapter;

import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.WarenkorbKomponente.IWarenkorb;

/**
 * Created by dima on 02.06.15.
 */
public interface IBestellung {


    public boolean makeBestellung(IKunde kunde, IWarenkorb warenkorb);
}
