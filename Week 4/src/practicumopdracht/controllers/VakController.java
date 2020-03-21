package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import practicumopdracht.MainApplication;
import practicumopdracht.data.FakeVakDAO;
import practicumopdracht.models.Vak;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.VakView;
import practicumopdracht.views.View;

import java.util.Optional;

import static java.lang.Integer.parseInt;

/**
 * Master controller voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class VakController extends Controller {
    private final String VAK_IS_VERPLICHT = "- Naam van het vak is verplicht!";
    private final String NAAM_TOETS_IS_VERPLICHT = "- Naam van de toets is verplicht!";
    private final String AANTAL_GEMAAKTE_TOETSEN_IS_VERPLICHT = "- Aantal gemaakte toetsen is verplicht of ongeldig!";
    private VakView vakView;
    private Alert alert;
    private FakeVakDAO vakDAO;

    public VakController() {
        vakView = new VakView();

        vakView.getTerugButton().setOnAction(e -> pressedTerug());
        vakView.getNieuwButton().setOnAction(e -> pressedNieuw());
        vakView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        vakView.getOpslaanButton().setOnAction(e -> pressedOpslaan());

        vakDAO = new FakeVakDAO();
        refreshData();
        pressedItem();
    }

    //data verkrijgen
    private void refreshData() {
        ObservableList<Vak> vakList = FXCollections.observableList(vakDAO.getAll());
        vakView.getListView().setItems(vakList);
    }

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
            alert.setTitle("Verwijderen");
            alert.setContentText("Je hebt geen item geselecteerd om te verwijderen!");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Verwijderen");
            alert.setHeaderText("Je hebt op de verwijder-knop gedrukt!");
            alert.setContentText("Weet je zeker dat je deze item wilt verwijderen?");
            Optional<ButtonType> resultverwijderen = alert.showAndWait();

            if (resultverwijderen.get() == ButtonType.OK) {
                vakDAO.remove(selectedItem);
                refreshData();
                refreshFields();
            }
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
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + NAAM_TOETS_IS_VERPLICHT + "\n" + AANTAL_GEMAAKTE_TOETSEN_IS_VERPLICHT);
        } else if (vakIsEmpty() && aantalToetsenIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + AANTAL_GEMAAKTE_TOETSEN_IS_VERPLICHT);
        } else if (aantalToetsenIsEmpty() && naamToetsIsEmpty()) {
            alert.setContentText(NAAM_TOETS_IS_VERPLICHT + "\n" + AANTAL_GEMAAKTE_TOETSEN_IS_VERPLICHT);
        } else if (vakIsEmpty() && naamToetsIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + NAAM_TOETS_IS_VERPLICHT);
        } else if (vakIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT);
        } else if (aantalToetsenIsEmpty()) {
            alert.setContentText(AANTAL_GEMAAKTE_TOETSEN_IS_VERPLICHT);
        } else if (naamToetsIsEmpty()) {
            alert.setContentText(NAAM_TOETS_IS_VERPLICHT);
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

    //listview item onclick event
    public void pressedItem() {
        vakView.getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vakView.getOpslaanButton().setOnAction(e -> pressedBewerken());
                for (int i = 0; i < 10; i++) {
                    if (vakView.getListView().getSelectionModel().getSelectedItem().getId() == i) {
                        vakView.getVak().setText(vakView.getListView().getSelectionModel().getSelectedItem().getVakNaam());
                        vakView.getToetsNaamInvoerVeld().setText(vakView.getListView().getSelectionModel().getSelectedItem().getToetsNaam());
                        vakView.getAantalGemaakteToetsenInvoerVeld().setText(String.valueOf(vakView.getListView().getSelectionModel().getSelectedItem().getAantalGemaakteToetsen()));
                    }
                }
            }
        });
    }

    //listview item bewerken
    public void pressedBewerken() {
        Vak bewerkteItem = vakView.getListView().getSelectionModel().getSelectedItem();
        if (vakView.getListView().getSelectionModel().getSelectedItem().getId() == 1) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Bewerken is gelukt!");

            bewerkteItem.setVakNaam(vakView.getVak().getText());
            bewerkteItem.setToetsNaam(vakView.getToetsNaamInvoerVeld().getText());
            bewerkteItem.setAantalGemaakteToetsen(Integer.valueOf(vakView.getAantalGemaakteToetsenInvoerVeld().getText()));

            refreshData();
            refreshFields();
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Je hebt niks geselecteerd om te bewerken!");
            alert.show();
        }
    }

    @Override
    public View getView() {
        return vakView;
    }
}
