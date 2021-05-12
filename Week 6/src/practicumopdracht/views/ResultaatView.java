package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;

/**
 * Detail view voor OOP2 practicumopdracht.
 *
 */
public class ResultaatView extends View {
    private TextField studentennummerInvoerVeld;
    private TextField volledigeNaamStudentInvoerVeld;
    private DatePicker datumInvoerVeld;
    private TextField cijferInvoerVeld;
    private CheckBox gehaaldInvoerVeld;
    private ListView<Resultaat> listView;
    private Button opslaanButton;
    private Button nieuwButton;
    private Button verwijderenButton;
    private Button terugButton;
    private ComboBox<Vak> vakken;
    private GridPane view;
    private VBox vBox;
    private HBox hBox;

    private ToggleGroup toggleGroup;
    private RadioButton sorteerDatumOplopend;
    private RadioButton sorteerDatumAflopend;
    private RadioButton sorteerStudentennummerOplopend;
    private RadioButton sorteerStudentennummerAflopend;

    public ResultaatView() {
        initLayout();
    }

    private void initLayout() {
        //Labels
        Label vakkenLabel = new Label("Vak:");
        Label studentennummerLabel = new Label("Studentennummer:");
        Label studentennaamLabel = new Label("Naam van de student:");
        Label datumLabel = new Label("Datum van toetsafname:");
        Label cijferLabel = new Label("Behaalde cijfer:");
        Label gehaaldLabel = new Label("Gehaald?");
        Label sorteerLabel = new Label("Sortering:");

        vakken = new ComboBox<>();
        studentennummerInvoerVeld = new TextField();
        volledigeNaamStudentInvoerVeld = new TextField();
        datumInvoerVeld = new DatePicker();
        cijferInvoerVeld = new TextField();
        gehaaldInvoerVeld = new CheckBox();
        listView = new ListView<>();
        opslaanButton = new Button("Opslaan");
        nieuwButton = new Button("Nieuw");
        verwijderenButton = new Button("Verwijderen");
        terugButton = new Button("Terug naar vak");
        toggleGroup = new ToggleGroup();
        sorteerStudentennummerOplopend = new RadioButton("Studentennummer (0-9)");
        sorteerStudentennummerAflopend = new RadioButton("Studentennummer (9-0)");
        sorteerDatumOplopend = new RadioButton("Datum, nieuw naar oud");
        sorteerDatumAflopend = new RadioButton("Datum, oud naar nieuw");

        view = new GridPane();
        view.setHgap(15);
        view.setVgap(10);
        view.setPadding(new Insets(10, 10, 10, 10));

        view.add(vakkenLabel, 0, 0);
        view.add(vakken, 1, 0);
        vakken.setPrefWidth(500);

        view.add(studentennummerLabel, 0, 1);
        view.add(studentennummerInvoerVeld, 1, 1);

        view.add(studentennaamLabel, 0, 2);
        view.add(volledigeNaamStudentInvoerVeld, 1, 2);

        view.add(datumLabel, 0, 3);
        view.add(datumInvoerVeld, 1, 3);
        datumInvoerVeld.setPrefWidth(500);

        view.add(cijferLabel, 0, 4);
        view.add(cijferInvoerVeld, 1, 4);

        view.add(gehaaldLabel, 0, 5);
        view.add(gehaaldInvoerVeld, 1, 5);

        //VBox
        vBox = new VBox(opslaanButton, listView);
        view.getChildren().add(vBox);
        view.add(opslaanButton, 0, 6, 2, 1);
        opslaanButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        opslaanButton.setPrefWidth(300);

        view.add(listView, 0, 7, 2, 1);
        listView.setPrefHeight(200);
        vBox.setSpacing(10);

        //sorterenHBox
        HBox sorteerKnoppen = new HBox();
        view.add(sorteerKnoppen, 0, 8, 2, 1);

        sorteerStudentennummerOplopend.setToggleGroup(toggleGroup);
        sorteerStudentennummerAflopend.setToggleGroup(toggleGroup);
        sorteerDatumOplopend.setToggleGroup(toggleGroup);
        sorteerDatumAflopend.setToggleGroup(toggleGroup);

        sorteerKnoppen.setSpacing(10);
        sorteerKnoppen.getChildren().addAll(sorteerLabel, sorteerStudentennummerOplopend, sorteerStudentennummerAflopend, sorteerDatumOplopend, sorteerDatumAflopend);

        //HBox
        hBox = new HBox(nieuwButton, verwijderenButton);
        view.getChildren().add(hBox);
        hBox.setSpacing(100);

        view.add(nieuwButton, 0, 9);
        nieuwButton.setPrefWidth(450);

        view.add(verwijderenButton, 1, 9);
        verwijderenButton.setPrefWidth(400);

        view.add(terugButton, 0, 10, 2, 1);
        terugButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        terugButton.setPrefWidth(500);
    }

    //getters
    public Button getOpslaanButton() {
        return opslaanButton;
    }

    public Button getNieuwButton() {
        return nieuwButton;
    }

    public Button getVerwijderenButton() {
        return verwijderenButton;
    }

    public Button getTerugButton() {
        return terugButton;
    }

    public TextField getStudentennummerInvoerVeld() {
        return studentennummerInvoerVeld;
    }

    public TextField getVolledigeNaamStudentInvoerVeld() {
        return volledigeNaamStudentInvoerVeld;
    }

    public DatePicker getDatumInvoerVeld() {
        return datumInvoerVeld;
    }

    public TextField getCijferInvoerVeld() {
        return cijferInvoerVeld;
    }

    public CheckBox getGehaaldInvoerVeld() {
        return gehaaldInvoerVeld;
    }

    public ComboBox<Vak> getVakken() {
        return vakken;
    }

    public ListView<Resultaat> getListView() {
        return listView;
    }

    public RadioButton getSorteerDatumOplopend() {
        return sorteerDatumOplopend;
    }

    public RadioButton getSorteerDatumAflopend() {
        return sorteerDatumAflopend;
    }

    public RadioButton getSorteerStudentennummerOplopend() {
        return sorteerStudentennummerOplopend;
    }

    public RadioButton getSorteerStudentennummerAflopend() {
        return sorteerStudentennummerAflopend;
    }

    @Override
    public Parent getRoot() {
        return view;
    }
}
