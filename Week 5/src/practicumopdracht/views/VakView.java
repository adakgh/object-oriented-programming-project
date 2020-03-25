package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Vak;


/**
 * Master view voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class VakView extends View {
    //Attributen
    private TextArea toetsNaamInvoerVeld;
    private TextField aantalGemaakteToetsenInvoerVeld;
    private TextField vak;
    private Button opslaanButton;
    private ListView<Vak> listView;
    private Button nieuwButton;
    private Button verwijderenButton;
    private Button terugButton;
    private GridPane view;
    private VBox vBox;
    private HBox hBox;

    private BorderPane borderPane;
    private Menu menu;
    private MenuItem laadMenuItem;
    private MenuItem opslaanMenuItem;
    private MenuItem sluitMenuItem;
    private SeparatorMenuItem separator;


    public VakView() {
        initLayout();
    }

    private void initLayout() {
        //Labels
        Label vakcomboBoxLabel = new Label("Naam van het vak:");
        Label naamLabel = new Label("Naam van de toets:");
        Label aantalToetsenLabel = new Label("Aantal gemaakte toetsen:");

        vak = new TextField();
        opslaanButton = new Button("Opslaan");
        listView = new ListView<>();
        toetsNaamInvoerVeld = new TextArea();
        aantalGemaakteToetsenInvoerVeld = new TextField();
        nieuwButton = new Button("Nieuw");
        verwijderenButton = new Button("Verwijderen");
        terugButton = new Button("Ga naar resultaat");
        borderPane = new BorderPane();
        menu = new Menu("Bestand");
        separator = new SeparatorMenuItem();
        laadMenuItem = new MenuItem("Laden");
        opslaanMenuItem = new MenuItem("Opslaan");
        sluitMenuItem = new MenuItem("Afsluiten");

        view = new GridPane();
        view.setHgap(15);
        view.setVgap(10);
        view.setPadding(new Insets(10, 10, 10, 10));

        view.add(vakcomboBoxLabel, 0, 2);
        vakcomboBoxLabel.setPrefWidth(300);
        view.add(vak, 1, 2);
        vak.setPrefWidth(500);

        view.add(naamLabel, 0, 3);
        view.add(toetsNaamInvoerVeld, 1, 3);
        toetsNaamInvoerVeld.setPrefHeight(5);

        view.add(aantalToetsenLabel, 0, 4);
        view.add(aantalGemaakteToetsenInvoerVeld, 1, 4);

        //VBox
        vBox = new VBox(opslaanButton, listView);
        view.getChildren().add(vBox);
        view.add(opslaanButton, 0, 5, 2, 1);
        opslaanButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        opslaanButton.setPrefWidth(400);

        view.add(listView, 0, 6, 2, 1);
        listView.setPrefHeight(180);
        vBox.setSpacing(10);

        //HBox
        hBox = new HBox(nieuwButton, verwijderenButton);
        view.getChildren().add(hBox);
        hBox.setSpacing(100);

        view.add(nieuwButton, 0, 7);
        nieuwButton.setPrefWidth(450);

        view.add(verwijderenButton, 1, 7);
        verwijderenButton.setPrefWidth(400);

        view.add(terugButton, 0, 8, 2, 1);
        terugButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        terugButton.setPrefWidth(500);

        //BorderPane
        view.add(borderPane,0,0,2,1);
        borderPane.setPadding(new Insets(-10, -10, -10,-10));
        menu.getItems().addAll(laadMenuItem, opslaanMenuItem, separator, sluitMenuItem);
        MenuBar menuBar = new MenuBar(menu);
        borderPane.setTop(menuBar);
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

    public TextArea getToetsNaamInvoerVeld() {
        return toetsNaamInvoerVeld;
    }

    public TextField getAantalGemaakteToetsenInvoerVeld() {
        return aantalGemaakteToetsenInvoerVeld;
    }

    public TextField getVak() {
        return vak;
    }

    public ListView<Vak> getListView() {
        return listView;
    }

    public MenuItem getLaadMenuItem() {
        return laadMenuItem;
    }

    public MenuItem getOpslaanMenuItem() {
        return opslaanMenuItem;
    }

    public MenuItem getSluitMenuItem() {
        return sluitMenuItem;
    }

    @Override
    public Parent getRoot() {
        return view;
    }

}
