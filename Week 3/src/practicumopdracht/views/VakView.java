package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Vak;

public class VakView extends View {
    //Attributen
    private TextArea toetsNaamInvoerVeld;
    private TextField aantalGemaakteToetsenInvoerVeld;
    private ComboBox vakComboBox;
    private Button opslaanButton;
    private ListView<Vak> listView;
    private Button nieuwButton;
    private Button verwijderenButton;
    private Button terugButton;

    private GridPane view;
    private VBox vBox;
    private HBox hBox;

    public VakView() {
        initLayout();
        initVak();
    }

    private void initLayout() {
        //Labels
        Label vakcomboBoxLabel = new Label("Naam van het vak:");
        Label naamLabel = new Label("Naam van de toets:");
        Label aantalToetsenLabel = new Label("Aantal gemaakte toetsen:");

        vakComboBox = new ComboBox();
        opslaanButton = new Button("Opslaan");
        listView = new ListView<>();
        toetsNaamInvoerVeld = new TextArea();
        aantalGemaakteToetsenInvoerVeld = new TextField();
        nieuwButton = new Button("Nieuw");
        verwijderenButton = new Button("Verwijderen");
        terugButton = new Button("Terug naar overzicht");

        view = new GridPane();
        view.setHgap(15);
        view.setVgap(10);
        view.setPadding(new Insets(10, 10, 10, 10));

        view.add(vakcomboBoxLabel, 0, 0);
        vakcomboBoxLabel.setPrefWidth(300);
        view.add(vakComboBox, 1, 0);
        vakComboBox.setPrefWidth(500);
        vakComboBox.getItems().add("Programming");
        vakComboBox.getItems().add("OOP1");
        vakComboBox.getItems().add("OOP2");
        vakComboBox.getItems().add("Databases");

        view.add(naamLabel, 0, 1);
        view.add(toetsNaamInvoerVeld, 1, 1);
        toetsNaamInvoerVeld.setPrefHeight(5);

        view.add(aantalToetsenLabel, 0, 2);
        view.add(aantalGemaakteToetsenInvoerVeld, 1, 2);

        //VBox
        vBox = new VBox(opslaanButton, listView);
        view.getChildren().add(vBox);
        view.add(opslaanButton, 0, 4, 2, 1);
        opslaanButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        opslaanButton.setPrefWidth(400);

        view.add(listView, 0, 5, 2, 1);
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
    private void initVak() {
        Vak mijnVak = new Vak("Programming", "1e toets", 12);
        vakComboBox.setPromptText(mijnVak.getVakNaam());
        toetsNaamInvoerVeld.setText(mijnVak.getToetsNaam());
        aantalGemaakteToetsenInvoerVeld.setText(String.valueOf(mijnVak.getAantalGemaakteToetsen()));
        listView.getItems().add(mijnVak);
    }

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

    @Override
    public Parent getRoot() {
        return view;
    }

}
