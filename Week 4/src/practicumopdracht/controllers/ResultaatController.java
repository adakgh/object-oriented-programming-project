package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import practicumopdracht.MainApplication;
import practicumopdracht.data.FakeResultaatDAO;
import practicumopdracht.data.FakeVakDAO;
import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.View;

import java.util.List;
import java.util.Optional;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Detail controller voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class ResultaatController extends Controller {
    private ResultaatView resultaatView;
    private Alert alert;
    private FakeResultaatDAO resultaatDAO;
    private FakeVakDAO vakDAO;

    private final String VAK_IS_VERPLICHT = "- Vak is verplicht!";
    private final String STUDENTENNUMMER_IS_VERPLICHT = "- Studentennummer is verplicht of ongeldig!";
    private final String DATUM_IS_ONGELDIG = "- Datum van toetsafname is verplicht of ongeldig!";
    private final String NAAM_IS_VERPLICHT = "- Volledige naam is verplicht of ongeldig!";
    private final String CIJFER_IS_ONGELDIG = "- Behaalde cijfer is verplicht of ongeldig!";

    public ResultaatController(Vak v) {
        resultaatView = new ResultaatView();
        vakDAO = new FakeVakDAO();

        resultaatView.getTerugButton().setOnAction(e -> pressedTerug());
        resultaatView.getNieuwButton().setOnAction(e -> pressedNieuw());
        resultaatView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        resultaatView.getOpslaanButton().setOnAction(e -> pressedOpslaan());
        resultaatView.getVakken().setOnAction(e -> refreshBox());

        resultaatDAO = new FakeResultaatDAO();
        refreshData(v);
        fillVakken();
        pressedItem();
    }

    //data verkrijgen
    private void refreshData(Vak v) {
        ObservableList<Resultaat> resultaatList = FXCollections.observableList(resultaatDAO.getAllFor(v));
        resultaatView.getListView().setItems(resultaatList);
        resultaatView.getVakken().setValue(v);
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
                refreshData(resultaatView.getVakken().getValue());
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
        return (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty() || Double.parseDouble(resultaatView.getCijferInvoerVeld().getText()) > 10);
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

            Resultaat newResultaat = new Resultaat(resultaatView.getVakken().getValue().getId(), parseInt(resultaatView.getStudentennummerInvoerVeld().getText()),
                    resultaatView.getVolledigeNaamStudentInvoerVeld().getText(),
                    resultaatView.getDatumInvoerVeld().getValue(),
                    parseDouble(resultaatView.getCijferInvoerVeld().getText()),
                    resultaatView.getGehaaldInvoerVeld().isSelected(), resultaatView.getVakken().getValue());
            alert.setContentText("Deze gegevens zijn succesvol opgeslagen: \n\n" + newResultaat);

            if (!resultaatView.getListView().getSelectionModel().getSelectedItems().isEmpty()) {
                newResultaat.setId(resultaatView.getListView().getSelectionModel().getSelectedItem().getId());
            }

            resultaatDAO.addOrUpdate(newResultaat);
            fillVakken();
            refreshFields();
            refreshData(vakDAO.get(newResultaat.getMasterId()));
        }
        alert.show();
    }

    //fields refreshen
    public void refreshFields() {
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
                if (!resultaatView.getListView().getSelectionModel().getSelectedItems().isEmpty()) {
                    Resultaat resultaat = resultaatView.getListView().getSelectionModel().getSelectedItem();
                    resultaatView.getVakken().setValue(vakDAO.get(resultaat.getMasterId()));
                    resultaatView.getStudentennummerInvoerVeld().setText(String.valueOf(resultaat.getStudentennummer()));
                    resultaatView.getVolledigeNaamStudentInvoerVeld().setText(resultaat.getVolledigeNaamStudent());
                    resultaatView.getDatumInvoerVeld().setValue(resultaat.getDatum());
                    resultaatView.getCijferInvoerVeld().setText(String.valueOf(resultaat.getCijfer()));
                    resultaatView.getGehaaldInvoerVeld().setSelected(resultaat.isGehaald());
                }
            }
        });
    }

    //resultaten met combobox mee veranderen
    private void refreshBox() {
        if (!resultaatView.getVakken().getSelectionModel().isEmpty()){
            refreshData(resultaatView.getVakken().getValue());
        }
    }

    @Override
    public View getView() {
        return resultaatView;
    }
}