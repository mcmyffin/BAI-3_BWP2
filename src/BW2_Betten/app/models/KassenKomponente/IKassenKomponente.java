package models.KassenKomponente;

import models.BestellKomponente.IBestellung;

/**
 * Created by dima on 13.05.15.
 */
public interface IKassenKomponente {

    public IRechnung erstelleRechnung(IBestellung bestellung);
}
