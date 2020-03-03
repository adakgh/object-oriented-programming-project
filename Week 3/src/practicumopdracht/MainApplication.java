package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.VakController;
import practicumopdracht.views.VakView;

public class MainApplication extends Application {
    private String TITLE = "Practicumopdracht OOP2 - Ghizlane el Adak";
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private static Stage stage;

    public void start(Stage stage) {
        this.stage = stage;

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        VakController controller = new VakController();
        VakView view = controller.getView();

        Scene mainScene = new Scene(
                view.getRoot(),
                300,
                200
        );

        switchController(new VakController());

        stage.setScene(mainScene);
        stage.show();
    }

    public static void switchController(Controller controller){
        Scene vakScene = new Scene(
                controller.getView().getRoot());

        stage.setScene(vakScene);
        stage.show();
    }
}

