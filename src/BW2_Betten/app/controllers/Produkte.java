package controllers;

import models.ProduktKomponente.IArtikel;
import models.ProduktKomponente.IProduktKomponente;
import models.ProduktKomponente.ProduktKomponente;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.produkte;

import java.util.List;

/**
 * Created by dima on 15.05.15.
 */

public class Produkte extends Controller {

    public static Result sucheArtikel(){

        DynamicForm suche = Form.form().bindFromRequest();
        String begriff = suche.get("suchbegriff");


        IProduktKomponente produktKomponente = new ProduktKomponente();
        if(begriff.isEmpty() || begriff == null) return ok(produkte.render(produktKomponente.getArtikel()));
        return ok(produkte.render(produktKomponente.sucheArtikelNachBegriff(begriff)));
    }
}
