package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Practicumopdracht OOP2");
        stage.setWidth(640);
        stage.setHeight(480);

        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("Klik mij!"));
        Scene scene = new Scene(pane, 200, 50);
        stage.setScene(scene);

        stage.show();
    }
}
