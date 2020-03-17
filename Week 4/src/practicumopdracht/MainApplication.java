package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.VakController;
import practicumopdracht.data.ResultaatDAO;
import practicumopdracht.data.VakDAO;

/**
 * Mainapplicarion class voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class MainApplication extends Application {
    private String TITLE = "Practicumopdracht OOP2 - Ghizlane el Adak";
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private static Stage stage;

    private static VakDAO vakDAO;
    private static ResultaatDAO resultaatDAO;


    @Override
    public void start(Stage stage) {
        this.stage = stage;

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        switchController(new VakController());
        stage.show();
    }

    public static void switchController(Controller controller){
       stage.setScene(new Scene(controller.getView().getRoot()));
    }

    public static VakDAO getVakDAO() {
       return vakDAO;
    }

    public static ResultaatDAO getResultaatDAO() {
        return getResultaatDAO();
    }
}

