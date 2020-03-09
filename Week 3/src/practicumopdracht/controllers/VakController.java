package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Vak;
import practicumopdracht.views.VakView;
import practicumopdracht.views.View;

/**
 * Master controller voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class VakController extends Controller {
    private VakView vakView;
    private Alert alert;

    public VakController() {
        vakView = new VakView();
        vakView.getTerugButton().setOnAction(actionEvent -> pressedTerug());
        vakView.getNieuwButton().setOnAction(e -> pressedNieuw());
        vakView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        vakView.getOpslaanButton().setOnAction(e -> pressedOpslaan());
    }

    public void pressedTerug() {
        MainApplication.switchController(new ResultaatController());
    }

    public void pressedNieuw() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nieuw");
        alert.setHeaderText("Je hebt op de nieuw-knop gedrukt!");
        alert.show();
    }

    public void pressedVerwijderen() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Verwijderen");
        alert.setHeaderText("Je hebt op de verwijder-knop gedrukt!");
        alert.show();
    }

    public void pressedOpslaan() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Opslaan");
        alert.setHeaderText("De volgende fouten zijn gevonden: ");
        if ((vakView.getVak().getText().isEmpty() || vakView.getVak().getText().trim().isEmpty())
                && (vakView.getAantalGemaakteToetsenInvoerVeld().getText().isEmpty() || vakView.getAantalGemaakteToetsenInvoerVeld().getText().matches("^[a-zA-Z]+$"))
                && (vakView.getToetsNaamInvoerVeld().getText().isEmpty() || vakView.getToetsNaamInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Naam van het vak is verplicht! \n- Naam van de toets is verplicht! \n- Aantal gemaakte toetsen is verplicht of ongeldig!");

        } else if ((vakView.getVak().getText().isEmpty() || vakView.getVak().getText().trim().isEmpty()) && (vakView.getAantalGemaakteToetsenInvoerVeld().getText().isEmpty() || vakView.getAantalGemaakteToetsenInvoerVeld().getText().matches("^[a-zA-Z]+$"))) {
            alert.setContentText("- Naam van het vak is verplicht! \n- Aantal gemaakte toetsen is verplicht of ongeldig!");
        } else if ((vakView.getAantalGemaakteToetsenInvoerVeld().getText().isEmpty() || vakView.getAantalGemaakteToetsenInvoerVeld().getText().matches("^[a-zA-Z]+$")) && (vakView.getToetsNaamInvoerVeld().getText().isEmpty() || vakView.getToetsNaamInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Naam van de toets is verplicht! \n- Aantal gemaakte toetsen is verplicht of ongeldig!");
        } else if ((vakView.getVak().getText().isEmpty() || vakView.getVak().getText().trim().isEmpty()) && (vakView.getToetsNaamInvoerVeld().getText().isEmpty() || vakView.getToetsNaamInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Naam van het vak is verplicht! \n- Naam van de toets is verplicht!");
        } else if ((vakView.getVak().getText().isEmpty() || vakView.getVak().getText().trim().isEmpty())) {
            alert.setContentText("- Naam van het vak is verplicht!");
        } else if (vakView.getAantalGemaakteToetsenInvoerVeld().getText().isEmpty() || vakView.getAantalGemaakteToetsenInvoerVeld().getText().matches("^[a-zA-Z]+$")) {
            alert.setContentText("- Aantal gemaakte toetsen is verplicht of ongeldig!");
        } else if ((vakView.getToetsNaamInvoerVeld().getText().isEmpty() || vakView.getToetsNaamInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Naam van de toets is verplicht!");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Opslaan is gelukt!");

            Vak newVak = new Vak(vakView.getVak().getText(),
                    vakView.getToetsNaamInvoerVeld().getText(),
                    vakView.getAantalGemaakteToetsenInvoerVeld().getLength());
            alert.setContentText("Deze gegevens zijn succesvol opgeslagen: \n\n" + newVak);

            vakView.getVak().clear();
            vakView.getToetsNaamInvoerVeld().clear();
            vakView.getAantalGemaakteToetsenInvoerVeld().clear();
        }
        alert.show();
    }

    @Override
    public View getView() {
        return vakView;
    }
}
