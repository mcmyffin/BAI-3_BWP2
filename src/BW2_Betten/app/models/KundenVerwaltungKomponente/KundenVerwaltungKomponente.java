package models.KundenVerwaltungKomponente;

import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.IDBKunde;
import models.KundenVerwaltungKomponente.Benutzer.IKunde;
import models.KundenVerwaltungKomponente.Benutzer.Kunde;
import models.KundenVerwaltungKomponente.Benutzer.KundeRegistrierenResult;
import models.KundenVerwaltungKomponente.Benutzer.UserTyp;
import models.KundenVerwaltungKomponente.DTO.KundeDTO;
import models.KundenVerwaltungKomponente.DTO.KundeSessionDTO;
import models.KundenVerwaltungKomponente.Exceptions.AnmeldungNichtMoeglichException;
import models.KundenVerwaltungKomponente.Exceptions.KundeNichtGefundenException;
import models.KundenVerwaltungKomponente.Exceptions.SessionParseException;

import java.util.List;

/**
 * Created by dima on 13.05.15.
 */
public class KundenVerwaltungKomponente implements IKundenVerwaltungKomponente {

    private IDBKunde persistenz = new DatenbankAdapter();

    @Override
    public IKunde anmelden(String email, String passwort) throws AnmeldungNichtMoeglichException{

        IKunde kunde = persistenz.anmelden(email, passwort);

        // precondition
        if(kunde == null) throw new AnmeldungNichtMoeglichException("Email oder Passwort falsch!");
        return kunde;
    }

    @Override
    public IKunde getKundenDaten(KundeSessionDTO sessionDTO) throws KundeNichtGefundenException{

        IKunde kunde = persistenz.getKundeByID(sessionDTO.getId());

        // precondition
        if(sessionDTO == null) throw new KundeNichtGefundenException("SessionDTO darf nicht NULL sein!");
        if(kunde == null) throw new KundeNichtGefundenException("Kunde nicht gefunden ");


        return kunde;

    }


    @Override
    public KundeRegistrierenResult registrieren(String email, String passwort, String vn, String nachname, String gebD, String ort,int plz, String str, int hn, String adzs) {

        // precondition
        if(email == null || email.isEmpty() || !email.contains("@")) return KundeRegistrierenResult.FAIL_EMAIL_FALSCH;
        if(passwort == null || passwort.isEmpty()) return KundeRegistrierenResult.FAIL_PASSWORT_LEER_UNGUELTIG;
        if(passwort.length() < 6) return KundeRegistrierenResult.FAIL_PASSWORT_ZUKURZ;

        int id = persistenz.getKundenIDByEmail(email);
        if(id > 0) return KundeRegistrierenResult.FAIL_EMAIL_VERGEBEN;
        if(id <= 0){
            if(persistenz.registrieren(email,passwort,vn,nachname,gebD,ort,plz,str,hn,adzs)) return KundeRegistrierenResult.OK;
            else return KundeRegistrierenResult.ERROR_SERVER;

        }else return KundeRegistrierenResult.ERROR_UNDEFINIERT;
    }

    @Override
    public boolean changeProfil(KundeSessionDTO sessionDTO , KundeDTO kundeDTO) {

        // precondition
        if(sessionDTO == null) return false;
        if(kundeDTO == null) return false;


        return persistenz.changeProfil(
                sessionDTO.getId(),
                kundeDTO.getEmail(),
                kundeDTO.getVorname(),
                kundeDTO.getNachname(),
                kundeDTO.getGebDatum(),
                kundeDTO.getOrt(),
                kundeDTO.getPlz(),
                kundeDTO.getStrasse(),
                kundeDTO.getHausnummer(),
                kundeDTO.getAdresszusatz());
    }

    @Override
    public IKunde fromKundeSessionDTO(KundeSessionDTO kundeSessionDTO) throws SessionParseException, KundeNichtGefundenException{

        // precondition
        if(kundeSessionDTO == null) throw new SessionParseException("KundeSession darf nicht NULL sein!");

        IKunde kunde = persistenz.getKundeByID(kundeSessionDTO.getId());

        if(kunde == null) throw new KundeNichtGefundenException("Kunde nicht gefunden");

        return kunde;
    }
}
