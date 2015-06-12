package models.KundenVerwaltungKomponente.Benutzer;

/**
 * Created by dima on 29.05.15.
 */
public enum KundeRegistrierenResult {

    OK("Erfolgreich registriert"),
    FAIL_EMAIL_VERGEBEN("Diese Email wird bereits vergeben"),
    FAIL_EMAIL_FALSCH("Email ist falsch oder leer"),
    FAIL_PASSWORT_ZUKURZ("Passwort ist zu kurz"),
    FAIL_PASSWORT_LEER_UNGUELTIG("Passwort ist leer oder ungueltig"),
    ERROR_SERVER("Registrieren ist zurzeit nicht moeglich"),
    ERROR_UNDEFINIERT("Ein Fehler ist aufgetreten, bitte Kontaktieren Sie den Administrator");


    private String value;

    private KundeRegistrierenResult(String value){
        this.value = value;
    }

    public String getMessage(){
        return this.getMessage();
    }
}
