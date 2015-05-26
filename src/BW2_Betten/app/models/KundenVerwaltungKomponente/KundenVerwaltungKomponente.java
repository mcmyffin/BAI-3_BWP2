package models.KundenVerwaltungKomponente;

import models.DatenbankAdapter.DatenbankAdapter;
import models.DatenbankAdapter.Exception.ServerDown;
import models.DatenbankAdapter.IDBKunde;

import java.util.List;

/**
 * Created by dima on 13.05.15.
 */
public class KundenVerwaltungKomponente implements IKundenVerwaltungKomponente {

    private IDBKunde persistenz = new DatenbankAdapter();

    @Override
    public IKunde anmelden(String email, String passwort) {

        List<String> kunde = persistenz.anmelden(email, passwort);
        if(kunde == null) return null;

        int id = Integer.parseInt(kunde.get(0));
        String kundenemail = kunde.get(1);
        String vorname = kunde.get(2);
        String nachname = kunde.get(3);
        String gebDatum = kunde.get(4);

        String ort = kunde.get(5);
        int plz = Integer.parseInt(kunde.get(6));
        String strasse = kunde.get(7);
        int hausnummer = Integer.parseInt(kunde.get(8));
        String adresszusatz = kunde.get(9);
        UserTyp typ = UserTyp.getUserTypByInt(Integer.parseInt(kunde.get(10)));

        IKunde result = new Kunde(id,kundenemail,vorname,nachname,gebDatum,ort,plz,strasse,hausnummer,adresszusatz,typ);
        return result;
    }

    @Override
    public KundeDTO getKundenDaten(KundeSessionDTO sessionDTO) {

        List<String> kunde = persistenz.getKundenDaten(sessionDTO.getId(),sessionDTO.getEmail(),sessionDTO.getVorname(),sessionDTO.getNachname());
        if(kunde == null) return null;

        int id = Integer.parseInt(kunde.get(0));
        String kundenemail = kunde.get(1);
        String vorname = kunde.get(2);
        String nachname = kunde.get(3);
        String gebDatum = kunde.get(4);

        String ort = kunde.get(5);
        int plz = Integer.parseInt(kunde.get(6));
        String strasse = kunde.get(7);
        int hausnummer = Integer.parseInt(kunde.get(8));
        String adresszusatz = kunde.get(9);
        UserTyp typ = UserTyp.getUserTypByInt(Integer.parseInt(kunde.get(10)));

        IKunde result = new Kunde(id,kundenemail,vorname,nachname,gebDatum,ort,plz,strasse,hausnummer,adresszusatz,typ);
        return result.toDTO();

    }

    /*      -1 := zurzeit nicht moeglich
     *       0 := erfolgreich registriert
     *       1 := email bereits registriert
     *       2 := passwort zu kurz oder leer
     *       3 := email ungueltig oder leer
     */
    @Override
    public int registrieren(String email, String passwort, String vn, String nachname, String gebD, String ort,int plz, String str, int hn, String adzs) {

        if(email == null || email.isEmpty() || !email.contains("@")) return 3;
        if(passwort == null || passwort.isEmpty() || passwort.length() < 6) return 2;

        int id = persistenz.getKundenIDByEmail(email);
        if(id > 0) return 1;
        if(id == 0){
            if(persistenz.registrieren(email,passwort,vn,nachname,gebD,ort,plz,str,hn,adzs)) return 0;
            else return -1;

        }else return -1;
    }

    @Override
    public boolean changeProfil(KundeDTO kundeDTO) {
        return persistenz.changeProfil(kundeDTO.getId(),kundeDTO.getEmail(),kundeDTO.getVorname(),kundeDTO.getNachname(),kundeDTO.getGebDatum(),
                               kundeDTO.getOrt(),kundeDTO.getPlz(),kundeDTO.getStrasse(),kundeDTO.getHausnummer(),kundeDTO.getAdresszusatz());
    }
}
