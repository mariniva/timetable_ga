package Controller;

import View.App;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class PaneNavigator {
    public static final String HOMEPAGE_PANE   = "HomePage.fxml";
    public static final String STYLES_PANE = "Styles.fxml";
    public static final String TRAINERS_PANE = "Trainers.fxml";
    public static final String ROOMS_PANE = "Rooms.fxml";
    public static final String GROUPS_PANE = "Groups.fxml";
    public static final String TIMETABLE_PANE = "Timetable.fxml";
    public static final String MAIN_PANE = "Main.fxml";

    private static MainController mainController;
    private static App mainApp;

    public static void setMainController(MainController mainController) {
        PaneNavigator.mainController = mainController;
    }

    public static void setMainApp(App mainApp){
        PaneNavigator.mainApp = mainApp;
    }

    public static App getMainApp(){
        return mainApp;
    }

    public static void loadPane(String fxml) {
        try {
            String[] dir = fxml.split("\\.");
            String tabID = dir[dir.length - 2];
            mainController.setCurrentTab(tabID);
            //getClass().getResourceAsStream("/resources/fxml/" + fxml);
            mainController.setPane(FXMLLoader.load( new URL("file:resources/" + fxml)));
            //mainController.setPane(FXMLLoader.load(PaneNavigator.class.getResource("/resources/fxml/" + fxml)));
            //mainController.setPane(FXMLLoader.load(this.getClass().getResource("/resources/fxml/" + fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
