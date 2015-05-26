package models.KundenVerwaltungKomponente;

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

    public Kunde(int id, String email, String vorname, String nachname, String gebDatum, String ort, int plz, String strasse, int hausnummer, String adresszusatz, UserTyp typ) {
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

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public KundeSessionDTO toSessionDTO() {
        KundeSessionDTO dto = new KundeSessionDTO();
        dto.setId(this.id);
        dto.setEmail(this.email);
        dto.setVorname(this.vorname);
        dto.setNachname(this.nachname);
        dto.setTyp(this.typ.getValue());

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

    @Override
    public boolean setEmail(String newEmail) {
        if(newEmail == null) return false;
        if(newEmail.isEmpty()) return false;

        this.email = newEmail;
        return true;
    }

    @Override
    public boolean setVorname(String newVorname) {
        if(newVorname == null) return false;
        if(newVorname.isEmpty()) return false;

        this.vorname = newVorname;
        return true;
    }

    @Override
    public boolean setNachname(String newNachname) {
        if(newNachname == null) return false;
        if(newNachname.isEmpty()) return false;

        this.nachname = newNachname;
        return true;
    }

    @Override
    public boolean setGebDatum(String newGebDatum) {
        if(newGebDatum == null) return false;
        if(newGebDatum.isEmpty()) return false;

        this.gebDatum = newGebDatum;
        return true;
    }

    @Override
    public boolean setOrt(String newOrt) {
        if(newOrt== null) return false;
        if(newOrt.isEmpty()) return false;

        this.ort = newOrt;
        return true;
    }

    @Override
    public boolean setPLZ(int newPLZ) {
        if(newPLZ <= 0) return false;

        this.plz = newPLZ;
        return true;
    }

    @Override
    public boolean setStrasse(String newStrasse) {
        if(newStrasse== null) return false;
        if(newStrasse.isEmpty()) return false;

        this.strasse = newStrasse;
        return true;
    }

    @Override
    public boolean setHausnummer(int newHausnummer) {
        if(newHausnummer < 0) return false;

        this.hausnummer = newHausnummer;
        return true;
    }

    @Override
    public boolean setAdresszusatz(String newAdresszusatz) {
        if(newAdresszusatz == null) return false;
        if(newAdresszusatz.isEmpty()) return false;

        this.adresszusatz = newAdresszusatz;
        return true;
    }
}
