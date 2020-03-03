package practicumopdracht.controllers;

import practicumopdracht.models.Vak;
import practicumopdracht.views.VakView;

public class VakController extends Controller {
    private VakView vakView;
    private Vak vak;


    public VakController() {
        vakView = new VakView();
        vak = new Vak("Programming", "abc", 13);

        vakView.getTerugButton().setOnAction(e -> Terug());
    }

    public void Terug() {

    }

    public VakView getView() {
        return new VakView();
    }
}
