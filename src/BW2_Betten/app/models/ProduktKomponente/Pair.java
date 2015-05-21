package models.ProduktKomponente;

/**
 * Created by dima on 21.05.15.
 */
public class Pair<K,V> {

    private K key;
    private V value;

    /**
     * Pair<K,V>
     *     Erstellt ein Pair Objekt mit K dem eindeutigen key
     *     und V als value
     * @param key
     * @param value
     * @throws NullPointerException
     *      Wirft eine NullpointerException, wenn Key oder Value null sind
     */
    public Pair(K key,V value) throws NullPointerException{
        boolean precheck = initPair(key,value);
        if(!precheck) throw new NullPointerException("Key und Value darf nicht null sein !");
    }

    private boolean initPair(K key,V value){
        if(key == null) return false;
        if(value == null) return false;
        else{
            this.key = key;
            this.value = value;
            return true;
        }

    }

    public K getKey(){
        return this.key;
    }

    public V getValue(){
        return this.value;
    }
}
