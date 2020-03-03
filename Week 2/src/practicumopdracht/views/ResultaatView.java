package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;
import java.time.LocalDate;

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

    private VBox vBox;
    private HBox hBox;

    private GridPane view;

    public ResultaatView() {
        initLayout();
        initResultaat();
    }

    private void initLayout() {
        //Labels
        Label vakkenLabel = new Label("Vak:");
        Label studentennummerLabel = new Label("Studentennummer:");
        Label studentennaamLabel = new Label("Naam van de student:");
        Label datumLabel = new Label("Datum van toetsafname:");
        Label cijferLabel = new Label("Behaalde cijfer:");
        Label gehaaldLabel = new Label("Gehaald?");

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
        terugButton = new Button("Terug naar overzicht");

        view = new GridPane();
        view.setHgap(15);
        view.setVgap(10);
        view.setPadding(new Insets(10, 10, 10, 10));

        view.add(vakkenLabel, 0,0);
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

        //HBox
        hBox = new HBox(nieuwButton, verwijderenButton);
        view.getChildren().add(hBox);
        hBox.setSpacing(100);

        view.add(nieuwButton, 0, 8);
        nieuwButton.setPrefWidth(450);

        view.add(verwijderenButton, 1, 8);
        verwijderenButton.setPrefWidth(400);

        view.add(terugButton, 0, 9, 2, 1);
        terugButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        terugButton.setPrefWidth(500);
    }

    //test
    public void initResultaat() {
        Resultaat mijnResultaat = new Resultaat(123456789, "Testpersoon Test", LocalDate.now(), 8.9, true, new Vak("Programming", "Practicum 1", 12));
        studentennummerInvoerVeld.setText(String.valueOf(mijnResultaat.getStudentennummer()));
        volledigeNaamStudentInvoerVeld.setText(mijnResultaat.getVolledigeNaamStudent());
        datumInvoerVeld.setValue(mijnResultaat.getDatum());
        cijferInvoerVeld.setText(String.valueOf(mijnResultaat.getCijfer()));
        gehaaldInvoerVeld.setSelected(mijnResultaat.isGehaald());
        listView.getItems().add(mijnResultaat);
    }

    @Override
    public Parent getRoot() {
        return view;
    }
}
