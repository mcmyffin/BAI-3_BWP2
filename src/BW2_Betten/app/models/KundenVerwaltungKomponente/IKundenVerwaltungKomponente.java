package models.KundenVerwaltungKomponente;

/**
 * Created by dima on 13.05.15.
 */
public interface IKundenVerwaltungKomponente {

    /**
     * Anmelden
     * Versucht User anzumelden
     * @param email
     * @param passwort
     * @return Wenn erfolgreich, dann true.
     */
    public boolean anmelden(String email, String passwort);


    /**
     * Registrieren
     * Versucht den User zu registrieren, hierzu sind folgende
     * Informationen notwendig
     *
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
     * Rueckgabewert Legede:
     *      -1 := zurzeit nicht moeglich
     *       0 := erfolgreich registriert
     *       1 := email bereits registriert
     *       2 := passwort zu kurz oder leer
     *       3 := email ungueltig oder leer
     *
     * @return int-Meldung
     */
     public int registrieren(String email, String passwort, String vn, String nachname, String gebD, int ort, String str, int hn, String adzs);
}
