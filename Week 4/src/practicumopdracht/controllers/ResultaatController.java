package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.FakeResultaatDAO;
import practicumopdracht.models.Resultaat;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.VakView;
import practicumopdracht.views.View;

import java.util.Optional;

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
    private VakView vakView;

    private String vakIsVerplicht = "- Vak is verplicht!";
    private String studentennummerIsVerplicht = "- Studentennummer is verplicht of ongeldig!";
    private String datumIsOngeldig = "- Datum van toetsafname is verplicht of ongeldig!";
    private String naamIsVerplicht = "- Volledige naam is verplicht of ongeldig!";
    private String cijferIsOngeldig = "- Behaalde cijfer is verplicht of ongeldig!";

    public ResultaatController() {
        resultaatView = new ResultaatView();
        vakView = new VakView();

        resultaatView.getTerugButton().setOnAction(actionEvent -> pressedTerug());
        resultaatView.getNieuwButton().setOnAction(e -> pressedNieuw());
        resultaatView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        resultaatView.getOpslaanButton().setOnAction(e -> pressedOpslaan());

        resultaatDAO = new FakeResultaatDAO();
        refreshData();
//        fillVakken();
    }

    //data verkrijgen
    private void refreshData() {
        ObservableList<Resultaat> resultaatList = FXCollections.observableList(resultaatDAO.getAll());
        resultaatView.getListView().setItems(resultaatList);


        for (int i = 0; i < vakView.getListView().getItems().size(); i++) {
            resultaatView.getVakken().getItems().add(vakView.getListView().getItems().get(i));
        }

    }
//    public void fillVakken(){
//        for (int i = 0; i < vakView.getListView().getItems().size(); i++) {
//            resultaatView.getVakken().getItems().add(vakView.getListView().getItems().get(i));
//        }
//    }


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
        if (result.get() == ButtonType.OK){
            resultaatView.getListView().getItems().clear();
            refreshFields();
        }
    }

    //item verwijderen
    public void pressedVerwijderen() {
        Resultaat selectedItem = resultaatView.getListView().getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Je hebt niks geselecteerd!");
            alert.show();
        } else {
            resultaatDAO.remove(selectedItem);
            refreshData();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Verwijderen");
            alert.setHeaderText("Je hebt dit item succesvol verwijderd!");
            alert.show();
        }
    }

    //combobox is null
    public boolean comboboxIsNull(){
        return resultaatView.getVakken().getValue() == null;
    }

    //studentennummer is empty of ongeldig
    public boolean studentennummerIsEmpty(){
        return (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty());
    }

    //naam is empty of ongeldig
    public boolean naamIsEmpty(){
        return (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty());
    }

    //datum is ongeldig
    public boolean datumIsOngeldig(){
        return (resultaatView.getDatumInvoerVeld().getValue() == null);
    }

    //cijfer is ongeldig
    public boolean cijferIsOngeldig(){
        return (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty());
    }

    //item opslaan
    public void pressedOpslaan() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Opslaan");
        alert.setHeaderText("De volgende fouten zijn gevonden: ");
        if (comboboxIsNull()
                && studentennummerIsEmpty() && naamIsEmpty() && datumIsOngeldig() && cijferIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n"+ naamIsVerplicht + "\n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);

            //4 niet ingevuld
        } else if (cijferIsOngeldig() && studentennummerIsEmpty() && naamIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(studentennummerIsVerplicht + "\n" + naamIsVerplicht +"\n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n" + naamIsVerplicht + "\n" + datumIsOngeldig);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && cijferIsOngeldig() && datumIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);
        } else if (comboboxIsNull() && naamIsEmpty() && cijferIsOngeldig() && datumIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + naamIsVerplicht + "\n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty() && cijferIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n" + naamIsVerplicht + "\n" + cijferIsOngeldig);

            //3 niet ingevuld
        } else if (comboboxIsNull() && studentennummerIsEmpty() && naamIsEmpty()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n" + naamIsVerplicht);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n" + datumIsOngeldig);
        } else if (comboboxIsNull() && studentennummerIsEmpty() && cijferIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht + "\n" + cijferIsOngeldig);
        } else if (cijferIsOngeldig() && studentennummerIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(studentennummerIsVerplicht + "\n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);
        } else if (cijferIsOngeldig() && studentennummerIsEmpty() && naamIsEmpty()) {
            alert.setContentText(studentennummerIsVerplicht + " \n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);
        } else if (cijferIsOngeldig() && naamIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(naamIsVerplicht + "\n"+ datumIsOngeldig + "\n" + cijferIsOngeldig);
        } else if (cijferIsOngeldig() && naamIsEmpty() && comboboxIsNull()) {
            alert.setContentText(vakIsVerplicht + "\n" + naamIsVerplicht +"\n" + cijferIsOngeldig);
        } else if (studentennummerIsEmpty() && naamIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(studentennummerIsVerplicht + "\n" + naamIsVerplicht + "\n"+ datumIsOngeldig);
        } else if (comboboxIsNull() && naamIsEmpty() && cijferIsOngeldig()) {
            alert.setContentText(studentennummerIsVerplicht + "\n" + naamIsVerplicht + "\n" + cijferIsOngeldig);

            //2 niet ingevuld
        } else if ((resultaatView.getVakken().getValue() == null && studentennummerIsEmpty())) {
            alert.setContentText(vakIsVerplicht + "\n" + studentennummerIsVerplicht);
        } else if (studentennummerIsEmpty() && naamIsEmpty()) {
            alert.setContentText(studentennummerIsVerplicht + "\n" + naamIsVerplicht);
        } else if (naamIsEmpty() && datumIsOngeldig()) {
            alert.setContentText(naamIsVerplicht + "\n" + datumIsOngeldig);
        } else if (comboboxIsNull() && naamIsEmpty()) {
            alert.setContentText(vakIsVerplicht + "\n" + naamIsVerplicht);
        } else if (comboboxIsNull() && datumIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n"+ datumIsOngeldig);
        } else if (comboboxIsNull() && cijferIsOngeldig()) {
            alert.setContentText(vakIsVerplicht + "\n" + cijferIsOngeldig);
        } else if (studentennummerIsEmpty() && cijferIsOngeldig()) {
            alert.setContentText(studentennummerIsVerplicht + "\n" + cijferIsOngeldig);
        } else if (naamIsEmpty() && cijferIsOngeldig()) {
            alert.setContentText(naamIsVerplicht + "\n" + cijferIsOngeldig);
        } else if (datumIsOngeldig() && cijferIsOngeldig()) {
            alert.setContentText("- Datum van toetsafname isverplicht of ongeldig! \n" + cijferIsOngeldig);
        } else if (datumIsOngeldig() && studentennummerIsEmpty()) {
            alert.setContentText(datumIsOngeldig + "\n"+ studentennummerIsVerplicht);

            //1 niet ingevuld
        } else if (comboboxIsNull()) {
            alert.setContentText(vakIsVerplicht);
        } else if (studentennummerIsEmpty()) {
            alert.setContentText(studentennummerIsVerplicht);
        } else if (naamIsEmpty()) {
            alert.setContentText(naamIsVerplicht);
        } else if (datumIsOngeldig()) {
            alert.setContentText(datumIsOngeldig);
        } else if (cijferIsOngeldig()) {
            alert.setContentText(cijferIsOngeldig);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Opslaan is gelukt!");

            Resultaat newResultaat = new Resultaat(-1, parseInt(resultaatView.getStudentennummerInvoerVeld().getText()),
                    resultaatView.getVolledigeNaamStudentInvoerVeld().getText(),
                    resultaatView.getDatumInvoerVeld().getValue(),
                    parseInt(resultaatView.getCijferInvoerVeld().getText()),
                    resultaatView.getGehaaldInvoerVeld().isSelected(),
                    resultaatView.getVakken().getValue()
            );
            alert.setContentText("Deze gegevens zijn succesvol opgeslagen: \n\n" + newResultaat);

            resultaatDAO.addOrUpdate(newResultaat);
            refreshData();
            refreshFields();
        }
        alert.show();
    }

    //fields refreshen
    public void refreshFields(){
        resultaatView.getVakken().setValue(null);
        resultaatView.getStudentennummerInvoerVeld().clear();
        resultaatView.getVolledigeNaamStudentInvoerVeld().clear();
        resultaatView.getCijferInvoerVeld().clear();
        resultaatView.getGehaaldInvoerVeld().setSelected(false);
        resultaatView.getDatumInvoerVeld().setValue(null);
    }
    @Override
    public View getView() {
        return resultaatView;
    }
}
