package models.KundenVerwaltungKomponente;

import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.Exception.ServerDown;
import models.DatenbankAdapter.IDBKunde;

/**
 * Created by dima on 13.05.15.
 */
public class KundenVerwaltungKomponente implements IKundenVerwaltungKomponente {
    @Override
    public boolean anmelden(String email, String passwort) {

        IDBKunde db = new DatenbankAdapter();

        try {
            return db.anmelden(email,passwort);
        } catch (ServerDown serverDown) {
            serverDown.printStackTrace();
        }
        return false;
    }

    @Override
    public int registrieren(String email, String passwort, String vn, String nachname, String gebD, int ort, String str, int hn, String adzs) {
        return 0;
    }
}
