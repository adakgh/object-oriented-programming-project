package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import practicumopdracht.MainApplication;
import practicumopdracht.data.ResultaatDAO;
import practicumopdracht.data.VakDAO;
import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.View;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

/**
 * Detail controller voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class ResultaatController extends Controller {
    private final String VAK_IS_VERPLICHT = "- Vak is verplicht!";
    private final String STUDENTENNUMMER_IS_VERPLICHT = "- Studentennummer is verplicht of ongeldig!";
    private final String DATUM_IS_ONGELDIG = "- Datum van toetsafname is verplicht of ongeldig!";
    private final String NAAM_IS_VERPLICHT = "- Volledige naam is verplicht of ongeldig!";
    private final String CIJFER_IS_ONGELDIG = "- Behaalde cijfer is verplicht of ongeldig!";
    private ResultaatView resultaatView;
    private Alert alert;
    private ResultaatDAO resultaatDAO;
    private VakDAO vakDAO;

    public ResultaatController() {
        resultaatView = new ResultaatView();
        vakDAO = MainApplication.getVakDAO();
        resultaatDAO = MainApplication.getResultaatDAO();

        resultaatView.getTerugButton().setOnAction(e -> pressedTerug());
        resultaatView.getNieuwButton().setOnAction(e -> pressedNieuw());
        resultaatView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        resultaatView.getOpslaanButton().setOnAction(e -> pressedOpslaan());

        refreshData();
        fillVakken();
        pressedItem();
    }

    //data verkrijgen
    private void refreshData() {
        ObservableList<Resultaat> resultaatList = FXCollections.observableList(resultaatDAO.getAll());
        resultaatView.getListView().setItems(resultaatList);
    }

    //combobox met vakken vullen
    public void fillVakken() {
        List<Vak> vaklist = vakDAO.getAll();
        ObservableList<Vak> observableVakList = FXCollections.observableList(vaklist);
        resultaatView.getVakken().setItems(observableVakList);
    }

    //switchen van view
    public void pressedTerug() {
        MainApplication.switchController(new VakController());
    }

    //listview en fields legen
    public void pressedNieuw() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nieuw");
        alert.setHeaderText("Je hebt op de nieuw-knop gedrukt!");
        alert.setContentText("Weet je zeker dat je alle items wilt verwijderen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            resultaatView.getListView().getItems().clear();
            refreshFields();
        }
    }

    //item verwijderen
    public void pressedVerwijderen() {
        Resultaat selectedItem = resultaatView.getListView().getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Je hebt geen item geselecteerd om te verwijderen!");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Verwijderen");
            alert.setHeaderText("Je hebt op de verwijder-knop gedrukt!");
            alert.setContentText("Weet je zeker dat je deze item wilt verwijderen?");
            Optional<ButtonType> resultverwijderen = alert.showAndWait();

            if (resultverwijderen.get() == ButtonType.OK) {
                resultaatDAO.remove(selectedItem);
                refreshData();
                refreshFields();
            }
        }
    }

    //combobox is null
    public boolean comboboxIsNull() {
        return resultaatView.getVakken().getValue() == null;
    }

    //studentennummer is empty of ongeldig
    public boolean studentennummerIsEmpty() {
        return (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty());
    }

    //naam is empty of ongeldig
    public boolean naamIsEmpty() {
        return (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty());
    }

    //datum is ongeldig
    public boolean datumIsEmpty() {
        return (resultaatView.getDatumInvoerVeld().getValue() == null);
    }

    //cijfer is ongeldig
    public boolean cijferIsEmpty() {
        return (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty());
    }

    //item opslaan
    public void pressedOpslaan() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Opslaan");
        alert.setHeaderText("De volgende fouten zijn gevonden: ");
        if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty() && datumIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);

            //4 niet ingevuld
        } else if (cijferIsEmpty() && studentennummerIsEmpty() && naamIsEmpty() && datumIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty() && datumIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && cijferIsEmpty() && datumIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (comboboxIsNull() && naamIsEmpty() && cijferIsEmpty() && datumIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);

            //3 niet ingevuld
        } else if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && datumIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);
        } else if (cijferIsEmpty() && studentennummerIsEmpty() && datumIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (cijferIsEmpty() && studentennummerIsEmpty() && naamIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + " \n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (cijferIsEmpty() && naamIsEmpty() && datumIsEmpty()) {
            alert.setContentText(NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (cijferIsEmpty() && naamIsEmpty() && comboboxIsNull()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);
        } else if (studentennummerIsEmpty() && naamIsEmpty() && datumIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG);
        } else if (comboboxIsNull() && naamIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);

            //2 niet ingevuld
        } else if (comboboxIsNull() && studentennummerIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + STUDENTENNUMMER_IS_VERPLICHT);
        } else if (studentennummerIsEmpty() && naamIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT);
        } else if (naamIsEmpty() && datumIsEmpty()) {
            alert.setContentText(NAAM_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG);
        } else if (comboboxIsNull() && naamIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + NAAM_IS_VERPLICHT);
        } else if (comboboxIsNull() && datumIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + DATUM_IS_ONGELDIG);
        } else if (comboboxIsNull() && cijferIsEmpty()) {
            alert.setContentText(VAK_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);
        } else if (studentennummerIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);
        } else if (naamIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(NAAM_IS_VERPLICHT + "\n" + CIJFER_IS_ONGELDIG);
        } else if (datumIsEmpty() && cijferIsEmpty()) {
            alert.setContentText(DATUM_IS_ONGELDIG + "\n" + CIJFER_IS_ONGELDIG);
        } else if (datumIsEmpty() && studentennummerIsEmpty()) {
            alert.setContentText(DATUM_IS_ONGELDIG + "\n" + STUDENTENNUMMER_IS_VERPLICHT);

            //1 niet ingevuld
        } else if (comboboxIsNull()) {
            alert.setContentText(VAK_IS_VERPLICHT);
        } else if (studentennummerIsEmpty()) {
            alert.setContentText(STUDENTENNUMMER_IS_VERPLICHT);
        } else if (naamIsEmpty()) {
            alert.setContentText(NAAM_IS_VERPLICHT);
        } else if (datumIsEmpty()) {
            alert.setContentText(DATUM_IS_ONGELDIG);
        } else if (cijferIsEmpty()) {
            alert.setContentText(CIJFER_IS_ONGELDIG);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Opslaan is gelukt!");

            Resultaat newResultaat = new Resultaat(resultaatView.getVakken().getSelectionModel().getSelectedItem().getId(), parseInt(resultaatView.getStudentennummerInvoerVeld().getText()),
                    resultaatView.getVolledigeNaamStudentInvoerVeld().getText(),
                    resultaatView.getDatumInvoerVeld().getValue(),
                    parseInt(resultaatView.getCijferInvoerVeld().getText()),
                    resultaatView.getGehaaldInvoerVeld().isSelected()
            );

            alert.setContentText("Deze gegevens zijn succesvol opgeslagen: \n\n" + newResultaat);

            resultaatDAO.addOrUpdate(newResultaat);
            refreshData();
            refreshFields();
        }
        alert.show();
    }

    //fields refreshen
    public void refreshFields() {
        resultaatView.getVakken().setValue(null);
        resultaatView.getStudentennummerInvoerVeld().clear();
        resultaatView.getVolledigeNaamStudentInvoerVeld().clear();
        resultaatView.getCijferInvoerVeld().clear();
        resultaatView.getGehaaldInvoerVeld().setSelected(false);
        resultaatView.getDatumInvoerVeld().setValue(null);
    }

    //listview item onclick event
    public void pressedItem() {
        resultaatView.getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resultaatView.getOpslaanButton().setOnAction(e -> pressedBewerken());
                for (int i = 0; i < 30; i++) {
                    if (resultaatView.getListView().getSelectionModel().getSelectedItem().getId() == i) {
                        resultaatView.getVakken().setValue(resultaatView.getListView().getSelectionModel().getSelectedItem().getHoortBijVak());
                        resultaatView.getStudentennummerInvoerVeld().setText(String.valueOf(resultaatView.getListView().getSelectionModel().getSelectedItem().getStudentennummer()));
                        resultaatView.getVolledigeNaamStudentInvoerVeld().setText(resultaatView.getListView().getSelectionModel().getSelectedItem().getVolledigeNaamStudent());
                        resultaatView.getDatumInvoerVeld().setValue(resultaatView.getListView().getSelectionModel().getSelectedItem().getDatum());
                        resultaatView.getCijferInvoerVeld().setText(String.valueOf(resultaatView.getListView().getSelectionModel().getSelectedItem().getCijfer()));
                        resultaatView.getGehaaldInvoerVeld().setSelected(resultaatView.getListView().getSelectionModel().getSelectedItem().getGehaald());
                    }
                }
            }
        });
    }

    //listview item bewerken
    public void pressedBewerken() {
        Resultaat bewerkteItem = resultaatView.getListView().getSelectionModel().getSelectedItem();
        if (bewerkteItem.getId() == bewerkteItem.getId()) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Bewerken is gelukt!");

            bewerkteItem.setHoortBijVak(resultaatView.getVakken().getValue());
            bewerkteItem.setStudentennummer(Integer.parseInt(resultaatView.getStudentennummerInvoerVeld().getText()));
            bewerkteItem.setVolledigeNaamStudent(resultaatView.getVolledigeNaamStudentInvoerVeld().getText());
            bewerkteItem.setDatum(resultaatView.getDatumInvoerVeld().getValue());
            bewerkteItem.setCijfer(Double.parseDouble(resultaatView.getCijferInvoerVeld().getText()));
            bewerkteItem.setGehaald(resultaatView.getGehaaldInvoerVeld().isSelected());

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
        return resultaatView;
    }
}
