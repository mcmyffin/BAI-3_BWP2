package models.DatenbankAdapter;

import models.DatenbankAdapter.Exception.ServerDown;

import java.util.List;

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
     */
    public List<String> anmelden(String email, String passwort);


    public List<String> getKundenDaten(int kID, String email, String vn, String nn);
    /**
     * Get Kunden ID by Email
     * Versucht die Kundennummer der zugehoerigen Email zu finden
     * @param email
     * @return int
     *  Legende:
     *      int > 0 := KundenID
     *      int = 0 := Kunde nicht gefunden
     *      int < 0 := Datenbank-Problem,
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
     * @param plz
     * @param str
     * @param hn
     * @param adzs
     *
     * @return true, wenn Registration erfolgreich war
     */
    public boolean registrieren(String email, String passwort, String vn, String nachname, String gebD,
                                                               String ort, int plz, String str, int hn, String adzs);

    /**
     * Change Profil
     * Speichert die Aenderungen des Profils mittels der KundenID
     * Wenn KundenID ungueltig oder Datenbank unerreichbar, dann false.
     * @param kID
     * @param email
     * @param vn
     * @param nn
     * @param gebD
     * @param ort
     * @param plz
     * @param str
     * @param hn
     * @param adzs
     * @return wenn erfolgreich gespeichert, dann true.
     */
    public boolean changeProfil(int kID, String email, String vn, String nn,String gebD, String ort, int plz, String str,
                                                                int hn, String adzs);
}
