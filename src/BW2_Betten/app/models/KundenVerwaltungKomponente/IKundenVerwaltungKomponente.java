package models.KundenVerwaltungKomponente;

import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.KundenVerwaltungKomponente.Benutzer.KundeRegistrierenResult;
import models.KundenVerwaltungKomponente.DTO.KundeDTO;
import models.KundenVerwaltungKomponente.DTO.KundeSessionDTO;
import models.KundenVerwaltungKomponente.Exceptions.AnmeldungNichtMoeglichException;
import models.KundenVerwaltungKomponente.Exceptions.KundeNichtGefundenException;
import models.KundenVerwaltungKomponente.Exceptions.SessionParseException;

/**
 * Created by dima on 13.05.15.
 */
public interface IKundenVerwaltungKomponente {

    /**
     * Anmelden
     * Versucht User anzumelden
     * @param email
     * @param passwort
     * @return IKunde Objekt wenn anmelden erfolgreich, sont null
     */
    public IKunde anmelden(String email, String passwort) throws AnmeldungNichtMoeglichException;


    public IKunde getKundenDaten(KundeSessionDTO sessionDTO) throws KundeNichtGefundenException;

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
     * Passwort muss mindestens laenge 6 haben
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
     public KundeRegistrierenResult registrieren(String email, String passwort, String vn, String nachname, String gebD, String ort,int plz, String str, int hn, String adzs);

    /**
     * Change Profil
     * Wenn gueltige Aenderungen im DTO eingetragen, werden diese gespeichert.
     * @param kundeDTO
     * @return true wenn erfolgreich gespeichert
     */
    public boolean changeProfil(KundeSessionDTO sessionDTO , KundeDTO kundeDTO);


    /**
     * From Kunde Session DTO
     * Parst den Session DTO zu IKunde
     * @param kundeSessionDTO
     * @return IKunde
     */
    public IKunde fromKundeSessionDTO(KundeSessionDTO kundeSessionDTO)  throws SessionParseException, KundeNichtGefundenException;
}
