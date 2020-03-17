package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.FakeResultaatDAO;
import practicumopdracht.data.FakeVakDAO;
import practicumopdracht.data.ResultaatDAO;
import practicumopdracht.data.VakDAO;
import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.VakView;
import practicumopdracht.views.View;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Integer.parseInt;

/**
 * Master controller voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class VakController extends Controller {
    private VakView vakView;
    private Alert alert;
    private FakeVakDAO vakDAO;
    private ResultaatView resultaatView;

    private String vakIsVerplicht = "- Naam van het vak is verplicht!";
    private String naamToetsIsVerplicht ="- Naam van de toets is verplicht!";
    private String aantalGemaakteToetsenIsVerplicht = "- Aantal gemaakte toetsen is verplicht of ongeldig!";

    public VakController() {
        vakView = new VakView();
        resultaatView = new ResultaatView();

        vakView.getTerugButton().setOnAction(actionEvent -> pressedTerug());
        vakView.getNieuwButton().setOnAction(e -> pressedNieuw());
        vakView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        vakView.getOpslaanButton().setOnAction(e -> pressedOpslaan());

        vakDAO = new FakeVakDAO();
        refreshData();
//        fillVakken();
    }

    //data verkrijgen
    private void refreshData() {
        ObservableList<Vak> vakList = FXCollections.observableList(vakDAO.getAll());
        vakView.getListView().setItems(vakList);

        for (int i = 0; i < vakView.getListView().getItems().size(); i++) {
            resultaatView.getVakken().getItems().add(vakList.get(i));
        }
    }

    //combobox met vakken vullen
//    public void fillVakken(){
//        for (int i = 0; i < vakView.getListView().getItems().size(); i++) {
//                resultaatView.getVakken().getItems().add(vakView.getListView().getItems().get(i));
//        }
//    }

    //switchen van view
    public void pressedTerug() {
        MainApplication.switchController(new ResultaatController());
    }

    //listview en fields legen
    public void pressedNieuw() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nieuw");
        alert.setHeaderText("Je hebt op de nieuw-knop gedrukt!");
        alert.setContentText("Weet je zeker dat je alle items wilt verwijderen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            vakView.getListView().getItems().clear();
            refreshFields();
        }
    }

    //item verwijderen
    public void pressedVerwijderen() {
        Vak selectedItem = vakView.getListView().getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Je hebt niks geselecteerd!");
            alert.show();
        } else {
            vakDAO.remove(selectedItem);
            refreshData();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Verwijderen");
            alert.setHeaderText("Je hebt dit item succesvol verwijderd!");
            alert.show();
        }
    }

    //vak is empty
    public boolean vakIsEmpty() {
        return vakView.getVak().getText().isEmpty() || vakView.getVak().getText().trim().isEmpty();
    }

    //naam van toets is empty
    public boolean naamToetsIsEmpty() {
    return vakView.getToetsNaamInvoerVeld().getText().isEmpty() || vakView.getToetsNaamInvoerVeld().getText().trim().isEmpty();
    }

    //aantal gemaakt toetsen is empty of getallen
    public boolean aantalToetsenIsEmpty() {
        return (vakView.getAantalGemaakteToetsenInvoerVeld().getText().isEmpty() || vakView.getAantalGemaakteToetsenInvoerVeld().getText().matches("[A-Za-z]"));
    }

    //item opslaan
    public void pressedOpslaan() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Opslaan");
        alert.setHeaderText("De volgende fouten zijn gevonden: ");
        if (vakIsEmpty() && aantalToetsenIsEmpty() && naamToetsIsEmpty()) {
            alert.setContentText(vakIsVerplicht + "\n" + naamToetsIsVerplicht + "\n" + aantalGemaakteToetsenIsVerplicht);
        } else if (vakIsEmpty() && aantalToetsenIsEmpty()) {
            alert.setContentText(vakIsVerplicht + "\n" + aantalGemaakteToetsenIsVerplicht);
        } else if (aantalToetsenIsEmpty() && naamToetsIsEmpty()) {
            alert.setContentText(naamToetsIsVerplicht + "\n" + aantalGemaakteToetsenIsVerplicht);
        } else if (vakIsEmpty() && naamToetsIsEmpty()) {
            alert.setContentText(vakIsVerplicht + "\n" + naamToetsIsVerplicht);
        } else if (vakIsEmpty()) {
            alert.setContentText(vakIsVerplicht);
        } else if (aantalToetsenIsEmpty()) {
            alert.setContentText(aantalGemaakteToetsenIsVerplicht);
        } else if (naamToetsIsEmpty()) {
            alert.setContentText(naamToetsIsVerplicht);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Opslaan is gelukt!");

            Vak newVak = new Vak(vakView.getVak().getText(),
                    vakView.getToetsNaamInvoerVeld().getText(),
                    parseInt(vakView.getAantalGemaakteToetsenInvoerVeld().getText()));
            alert.setContentText("Deze gegevens zijn succesvol opgeslagen: \n\n" + newVak);

            vakDAO.addOrUpdate(newVak);
            refreshData();

            refreshFields();
        }
        alert.show();
    }

    //fields refreshen
    public void refreshFields() {
        vakView.getVak().clear();
        vakView.getToetsNaamInvoerVeld().clear();
        vakView.getAantalGemaakteToetsenInvoerVeld().clear();
    }

    @Override
    public View getView() {
        return vakView;
    }
}
