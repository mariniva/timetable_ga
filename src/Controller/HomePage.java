package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomePage {
	
	
    @FXML
    private void goToTrainers(ActionEvent event) {
        System.out.println("goToTrainers");
        PaneNavigator.loadPane(PaneNavigator.TRAINERS_PANE);
    }
}
