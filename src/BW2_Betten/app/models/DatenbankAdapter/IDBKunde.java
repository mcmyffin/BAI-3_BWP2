package models.DatenbankAdapter;

import models.DatenbankAdapter.Exception.ServerDown;

/**
 * Created by dima on 20.05.15.
 */
public interface IDBKunde {

    /**
     * Anmelden
     * Versucht den User in der Datenbank zu identifizieren
     * @param email
     * @param passwort
     * @return true, wenn indetifizierung erfolgreich war
     * @throws ServerDown
     */
    public boolean anmelden(String email, String passwort) throws ServerDown;


    /**
     * Get Kunden ID by Email
     * Versucht die Kundennummer der zugehoerigen Email zu finden
     * @param email
     * @return kundenID > 0 Wenn gefunden, sonst 0
     */
    public int getKundenIDByEmail(String email);

    /**
     * Registrieren
     * Versucht den User zu registrieren mit den folgenden Parametern
     * @param email
     * @param passwort
     * @param vn
     * @param nachname
     * @param gebD
     * @param ort
     * @param str
     * @param hn
     * @param adzs
     *
     * @return true, wenn Registration erfolgreich war
     */
    public boolean registrieren(String email, String passwort, String vn, String nachname, String gebD,
                                                               int ort, String str, int hn, String adzs);
}
