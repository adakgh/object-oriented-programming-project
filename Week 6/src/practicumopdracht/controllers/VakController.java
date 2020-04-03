package practicumopdracht.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.VakNameComparator;
import practicumopdracht.comparators.VakNumberOfTestsMadeComparator;
import practicumopdracht.data.ResultaatDAO;
import practicumopdracht.data.VakDAO;
import practicumopdracht.models.Vak;
import practicumopdracht.views.VakView;
import practicumopdracht.views.View;

import java.util.Comparator;
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
    private VakDAO vakDAO;
    private ResultaatDAO resultaatDAO;

    public VakController() {
        vakView = new VakView();
        vakDAO = MainApplication.getVakDAO();
        resultaatDAO = MainApplication.getResultaatDAO();

        vakView.getTerugButton().setOnAction(e -> pressedTerug());
        vakView.getNieuwButton().setOnAction(e -> pressedNieuw());
        vakView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        vakView.getOpslaanButton().setOnAction(e -> pressedOpslaan());

        vakView.getOpslaanMenuItem().setOnAction(e -> {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Opslaan");
            alert.setHeaderText("Je hebt op de opslaan-knop gedrukt!");
            alert.setContentText("Weet je zeker dat je alle items wilt opslaan?");
            saveData();
        });
        vakView.getLaadMenuItem().setOnAction(e -> loadData());
        vakView.getSluitMenuItem().setOnAction(e -> exit());

        vakView.getSorteerNaamOplopend().setOnAction(e -> sortNameAsc());
        vakView.getSorteerNaamAflopend().setOnAction(e -> sortNameDesc());
        vakView.getSorteerAantalGemaakteToetsenOplopend().setOnAction(e -> sortTestsMadeAsc());
        vakView.getSorteerAantalGemaakteToetsenAflopend().setOnAction(e -> sortTestsMadeDesc());

        sort(new VakNameComparator.vakNaamOplopend());

        refreshData();
        pressedItem();
    }

    //data verkrijgen
    public void refreshData() {
        ObservableList<Vak> vakList = FXCollections.observableList(vakDAO.getAll());
        vakView.getListView().setItems(vakList);
    }

    //switchen van view
    public void pressedTerug() {
        if (!vakView.getListView().getSelectionModel().getSelectedItems().isEmpty()) {
            MainApplication.switchController(new ResultaatController(vakView.getListView().getSelectionModel().getSelectedItem()));
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Naar resultaten");
            alert.setHeaderText("Je hebt geen vak geselecteerd om resultaten bij te voegen!");
            alert.setContentText("Selecteer alsjeblieft een vak");
            alert.show();
        }
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


            if (!vakView.getListView().getSelectionModel().getSelectedItems().isEmpty()) {
                newVak.setId(vakView.getListView().getSelectionModel().getSelectedItem().getId());
            }

            vakDAO.addOrUpdate(newVak);
            refreshData();
            refreshFields();
        }
        alert.show();
    }

    //data opslaan
    public void saveData() {
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            vakDAO.save();
            MainApplication.getVakDAO().save();
            resultaatDAO.save();
            MainApplication.getResultaatDAO().save();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Opslaan is gelukt!");
            alert.show();
        }
    }

    //data laden
    public void loadData() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Laden");
        alert.setHeaderText("Je hebt op de laden-knop gedrukt!");
        alert.setContentText("Weet je zeker dat je alle items wilt laden?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            vakDAO.load();
            MainApplication.getVakDAO().load();

            resultaatDAO.load();
            MainApplication.getResultaatDAO().load();

            refreshData();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Laden is gelukt!");
            alert.show();
        }
    }

    //afsluiten
    public void exit() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Laden");
        alert.setHeaderText("Je hebt op de sluit-knop gedrukt!");
        alert.setContentText("Weet je zeker dat je de applicatie wilt sluiten?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Opslaan");
            alert.setHeaderText("Sla je gegevens op anders gaan ze verloren!");
            alert.setContentText("Wil je je gegevens opslaan?");

            saveData();
            alert.show();
        }
        Platform.exit();
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
                    if (!vakView.getListView().getSelectionModel().getSelectedItems().isEmpty()) {
                        vakView.getVak().setText(vakView.getListView().getSelectionModel().getSelectedItem().getVakNaam());
                        vakView.getToetsNaamInvoerVeld().setText(vakView.getListView().getSelectionModel().getSelectedItem().getToetsNaam());
                        vakView.getAantalGemaakteToetsenInvoerVeld().setText(String.valueOf(vakView.getListView().getSelectionModel().getSelectedItem().getAantalGemaakteToetsen()));
                    }
                }
        });
    }

    //sortering ophalen
    private void sort(Comparator<Vak> comparator) {
        FXCollections.sort(vakView.getListView().getItems(), comparator);
    }

    //naam oplopend sorteren
    public void sortNameAsc(){
        sort(new VakNameComparator.vakNaamOplopend());
    }

    //naam aflopend sorteren
    public void sortNameDesc(){
        sort(new VakNameComparator.vakNaamAflopend());
    }

    //aantal gemaakte toetsen oplopend sorteren
    public void sortTestsMadeAsc(){
        sort(new VakNumberOfTestsMadeComparator.vakAantalOplopend());
    }

    //aantal gemaakte toetsen aflopend sorteren
    public void sortTestsMadeDesc(){
        sort(new VakNumberOfTestsMadeComparator.vakAantalAflopend());
    }

    @Override
    public View getView() {
        return vakView;
    }
}
