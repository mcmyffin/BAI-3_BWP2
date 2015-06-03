package models.KundenVerwaltungKomponente.Benutzer;

import models.KundenVerwaltungKomponente.DTO.KundeDTO;
import models.KundenVerwaltungKomponente.DTO.KundeSessionDTO;
import models.KundenVerwaltungKomponente.Exceptions.KundeCreateException;

/**
 * Created by dima on 23.05.15.
 */
public class Kunde implements IKunde{

    private int id;
    private String email;
    private String vorname;
    private String nachname;
    private String gebDatum;

    private String ort;
    private int plz;
    private String strasse;
    private int hausnummer;
    private String adresszusatz;

    private UserTyp typ;

    private Kunde(int id, String email, String vorname, String nachname, String gebDatum, String ort, int plz, String strasse, int hausnummer, String adresszusatz, UserTyp typ) {
        this.id = id;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.gebDatum = gebDatum;
        this.ort = ort;
        this.plz = plz;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.adresszusatz = adresszusatz;
        this.typ = typ;
    }

    static IKunde createKunde(int id, String email, String vorname, String nachname, String gebDatum, String ort, int plz,
                             String strasse, int hausnummer, String adresszusatz, UserTyp typ) throws KundeCreateException{

        // preconditions
        if(id <= 0) throw new KundeCreateException("KundenID falsch! ");
        if(email == null) throw new KundeCreateException("Email darf nicht NULL sein!" );
        if(vorname == null) throw new KundeCreateException("Vorname darf nicht NULL sein! ");
        if(nachname== null) throw new KundeCreateException("Nachname darf nicht NULL sein! ");
        if(gebDatum == null) throw new KundeCreateException("Geburtsdatum darf nicht NULL sein! ");
        if(ort == null) throw new KundeCreateException("Ort darf nicht NULL sein! ");
        if(strasse == null) throw new KundeCreateException("Strasse daft nicht NULL sein! ");
        if(plz <= 0) throw new KundeCreateException("plz falsch! ");
        if(hausnummer <= 0) throw new KundeCreateException("hausnummer falsch! ");
        if(typ == null) throw new KundeCreateException("UserTyp darf nicht NULL sein! ");

        return ((IKunde) (new Kunde(id,email,vorname,nachname,gebDatum,ort,plz,strasse,hausnummer,adresszusatz,typ)));
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public KundeSessionDTO toSessionDTO() {
        KundeSessionDTO dto = new KundeSessionDTO(
                id,
                email,
                vorname,
                nachname,
                typ.getValue()
        )
                ;
        return dto;
    }

    @Override
    public KundeDTO toDTO() {

        return new KundeDTO(
                this.getID(),
                this.getEmail(),
                this.getVorname(),
                this.getNachname(),
                this.getGebDatum(),
                this.getOrt(),
                this.getPLZ(),
                this.getStrasse(),
                this.getHausnummer(),
                this.getAdresszusatz()
        );
    }


    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getVorname() {
        return this.vorname;
    }

    @Override
    public String getNachname() {
        return this.nachname;
    }

    @Override
    public String getGebDatum() {
        return this.gebDatum;
    }

    @Override
    public String getOrt() {
        return this.ort;
    }

    @Override
    public int getPLZ() {
        return this.plz;
    }

    @Override
    public String getStrasse() {
        return this.strasse;
    }

    @Override
    public int getHausnummer() {
        return this.hausnummer;
    }

    @Override
    public String getAdresszusatz() {
        return this.adresszusatz;
    }

    @Override
    public UserTyp getUserTyp() {
        return this.typ;
    }

}
