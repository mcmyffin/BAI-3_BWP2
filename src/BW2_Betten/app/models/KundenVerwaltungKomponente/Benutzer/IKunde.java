package models.KundenVerwaltungKomponente.Benutzer;

import models.KundenVerwaltungKomponente.DTO.KundeDTO;
import models.KundenVerwaltungKomponente.DTO.KundeSessionDTO;

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

}
