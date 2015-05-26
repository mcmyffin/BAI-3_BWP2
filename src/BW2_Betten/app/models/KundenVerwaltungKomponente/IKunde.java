package models.KundenVerwaltungKomponente;

/**
 * Created by dima on 23.05.15.
 */
public interface IKunde {

    public KundeSessionDTO toSessionDTO();

    public KundeDTO toDTO();

    /****** GETTER *****/
    public int getID();

    public String getEmail();

    public String getVorname();

    public String getNachname();

    public String getGebDatum();

    public String getOrt();

    public int getPLZ();

    public String getStrasse();

    public int getHausnummer();

    public String getAdresszusatz();

    public UserTyp getUserTyp();

    /***** SETTER ******/

    public boolean setEmail(String newEmail);

    public boolean setVorname(String newVorname);

    public boolean setNachname(String newNachname);

    public boolean setGebDatum(String newGebDatum);

    public boolean setOrt(String newOrt);

    public boolean setPLZ(int newPLZ);

    public boolean setStrasse(String newStrasse);

    public boolean setHausnummer(int newHausnummer);

    public boolean setAdresszusatz(String newAdresszusatz);
}
