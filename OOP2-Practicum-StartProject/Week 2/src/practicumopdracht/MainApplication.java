package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import practicumopdracht.views.ResultaatView;
import practicumopdracht.views.VakView;

public class MainApplication extends Application {
    private String TITLE = "Practicumopdracht OOP2 - Ghizlane el Adak";
    private int WIDTH = 640;
    private int HEIGHT = 480;

    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        VakView vakView = new VakView();
        ResultaatView resultaatView = new ResultaatView();

        Scene mainScene = new Scene(
                vakView.getRoot()
        );

        stage.setScene(mainScene);
        stage.show();
    }
}

