package models.WarenkorbKomponente;


import java.util.Map;

/**
 * Created by dima on 13.05.15.
 */
public interface IWarenkorbKomponente {



    public IWarenkorb fromSession(Map<Integer,Integer> artikel);

    public IWarenkorb createEmtyWarenkorb();



}
