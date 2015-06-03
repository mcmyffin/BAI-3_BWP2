package models.KundenVerwaltungKomponente.Benutzer;

import models.KundenVerwaltungKomponente.DTO.KundeSessionDTO;
import models.KundenVerwaltungKomponente.Exceptions.KundeCreateException;

/**
 * Created by dima on 28.05.15.
 */
public class KundenCreator {

    public static IKunde createKunde(int id, String email, String vorname, String nachname, String gebDatum, String ort, int plz,
                                     String strasse, int hausnummer, String adresszusatz, int typInt) throws KundeCreateException {

        UserTyp typ = UserTyp.getUserTypByInt(typInt);
        return Kunde.createKunde(id,email,vorname,nachname,gebDatum,ort,plz,strasse,hausnummer,adresszusatz,typ);
    }
}
