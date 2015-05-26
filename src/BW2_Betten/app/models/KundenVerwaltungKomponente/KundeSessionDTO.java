package models.KundenVerwaltungKomponente;

/**
 * Created by dima on 23.05.15.
 */
public class KundeSessionDTO {

    private int id;
    private String email;
    private String vorname;
    private String nachname;

    private int typ;

//    public KundeSessionDTO(int id, String email, String vorname, String nachname, int typ) {
//        this.id = id;
//        this.email = email;
//        this.vorname = vorname;
//        this.nachname = nachname;
//        this.typ = typ;
//    }

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

    public int getTyp() {
        return typ;
    }

    /**** SETTER ****/
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

    public void setTyp(int typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "KundeSessionDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", typ=" + typ +
                '}';
    }
}
