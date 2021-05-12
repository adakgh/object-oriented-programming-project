package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Resultaat;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.View;

import static java.lang.Integer.parseInt;

/**
 * Detail controller voor OOP2 practicumopdracht.
 *
 */
public class ResultaatController extends Controller {
    private ResultaatView resultaatView;
    private Alert alert;


    public ResultaatController() {
        resultaatView = new ResultaatView();
        resultaatView.getTerugButton().setOnAction(actionEvent -> pressedTerug());
        resultaatView.getNieuwButton().setOnAction(e -> pressedNieuw());
        resultaatView.getVerwijderenButton().setOnAction(e -> pressedVerwijderen());
        resultaatView.getOpslaanButton().setOnAction(e -> pressedOpslaan());
    }

    public void pressedTerug() {
        MainApplication.switchController(new VakController());
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
        if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");

            //4 niet ingevuld
        } else if ((resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Vak is verplicht! \n- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");

            //3 niet ingevuld
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVakken().getValue() == null)) {
            alert.setContentText("- Vak is verplicht! \n- Volledige naam is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");

            //2 niet ingevuld
        } else if ((resultaatView.getVakken().getValue() == null
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty()))) {
            alert.setContentText("- Vak is verplicht! \n- Studentennummer is verplicht of ongeldig!");
        } else if ((resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Volledige naam is verplicht of ongeldig!");
        } else if ((resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Volledige naam is verplicht of ongeldig! \n- Datum van toetsafname is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Vak is verplicht! \n- Volledige naam is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Vak is verplicht! \n- Datum van toetsafname is verplicht of ongeldig!");
        } else if ((resultaatView.getVakken().getValue() == null)
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Vak is verplicht! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Volledige naam is verplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getDatumInvoerVeld().getValue() == null)
                && (resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Datum van toetsafname isverplicht of ongeldig! \n- Behaalde cijfer is verplicht of ongeldig!");
        } else if ((resultaatView.getDatumInvoerVeld().getValue() == null)
                && (resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Datum van toetsafname is verplicht of ongeldig! \n- Studentennummer is verplicht of ongeldig!");

            //1 niet ingevuld
        } else if (resultaatView.getVakken().getValue() == null) {
            alert.setContentText("- Vak is verplicht!");
        } else if ((resultaatView.getStudentennummerInvoerVeld().getText().isEmpty() || resultaatView.getStudentennummerInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getStudentennummerInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Studentennummer is verplicht of ongeldig!");
        } else if ((resultaatView.getVolledigeNaamStudentInvoerVeld().getText().isEmpty() || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().matches("[^0-9]") || resultaatView.getVolledigeNaamStudentInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Volledige naam is verplicht of ongeldig!");
        } else if ((resultaatView.getDatumInvoerVeld().getValue() == null)) {
            alert.setContentText("- Datum van toetsafname is verplicht of ongeldig!");
        } else if ((resultaatView.getCijferInvoerVeld().getText().isEmpty() || resultaatView.getCijferInvoerVeld().getText().matches("[A-Za-z]") || resultaatView.getCijferInvoerVeld().getText().trim().isEmpty())) {
            alert.setContentText("- Behaalde cijfer is verplicht of ongeldig!");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Opslaan is gelukt!");

            Resultaat newResultaat = new Resultaat(parseInt(resultaatView.getStudentennummerInvoerVeld().getText()),
                    resultaatView.getVolledigeNaamStudentInvoerVeld().getText(),
                    resultaatView.getDatumInvoerVeld().getValue(),
                    parseInt(resultaatView.getCijferInvoerVeld().getText()),
                    resultaatView.getGehaaldInvoerVeld().isSelected(),
                    resultaatView.getVakken().getValue()
            );
            alert.setContentText("Deze gegevens zijn succesvol opgeslagen: \n\n" + newResultaat);

            resultaatView.getVakken().setValue(null);
            resultaatView.getStudentennummerInvoerVeld().clear();
            resultaatView.getVolledigeNaamStudentInvoerVeld().clear();
            resultaatView.getCijferInvoerVeld().clear();
            resultaatView.getGehaaldInvoerVeld().setSelected(false);
            resultaatView.getDatumInvoerVeld().setValue(null);
        }
        alert.show();
    }

    @Override
    public View getView() {
        return resultaatView;
    }
}
