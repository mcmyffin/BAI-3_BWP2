package models.KundenVerwaltungKomponente;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setGebDatum(String gebDatum) {
        this.gebDatum = gebDatum;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
    }
}
