package models.KundenVerwaltungKomponente.DTO;

/**
 * Created by dima on 23.05.15.
 */
public class KundeDTO {

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

    public KundeDTO(int id, String email, String vorname, String nachname, String gebDatum, String ort, int plz, String strasse, int hausnummer, String adresszusatz) {
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
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGebDatum() {
        return gebDatum;
    }

    public String getOrt() {
        return ort;
    }

    public int getPlz() {
        return plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public String getAdresszusatz() {
        return adresszusatz;
    }
}
