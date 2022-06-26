package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML private StackPane paneHolder;
    @FXML private ToggleButton homepageTab;
    @FXML private ToggleButton stylesTab;
    @FXML private ToggleButton trainersTab;
    @FXML private ToggleButton groupsTab;
    @FXML private ToggleButton timeslotsTab;
    @FXML private ToggleButton roomsTab;
    @FXML private ToggleButton timetableTab;

    private HashMap<String,ToggleButton> tabs =  new HashMap<>();
    private ToggleButton currentTab;
    public void setCurrentTab(String tabID){
        currentTab = tabs.get(tabID);
        currentTab.setSelected(true);
    }

    public void setPane(Node node) {
        paneHolder.getChildren().setAll(node);
    }


    public void navigateTab(ActionEvent event) {
        ToggleButton btn = (ToggleButton) event.getSource();
        btn.setSelected(true);
        currentTab = btn;
        String tab = btn.getText();
        System.out.println(tab);
        if(tab.equals("—“¿–“")){
            PaneNavigator.loadPane(PaneNavigator.HOMEPAGE_PANE);
        }else if(tab.equals("—“»À»")){
            PaneNavigator.loadPane(PaneNavigator.STYLES_PANE);
        }else if(tab.equals("“–≈Õ≈–¿")){
        PaneNavigator.loadPane(PaneNavigator.TRAINERS_PANE);
        }else if(tab.equals("√–”œœ€")){
            PaneNavigator.loadPane(PaneNavigator.GROUPS_PANE);
        }else if(tab.equals("«¿À€")){
            PaneNavigator.loadPane(PaneNavigator.ROOMS_PANE);
        }else if(tab.equals("–¿—œ»—¿Õ»≈")){
           PaneNavigator.loadPane(PaneNavigator.TIMETABLE_PANE);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.put("HomePage",homepageTab);
        tabs.put("Styles",stylesTab);
        tabs.put("Trainers",trainersTab);
        tabs.put("Groups",groupsTab);
        tabs.put("Timeslots",timeslotsTab);
        tabs.put("Rooms",roomsTab);
        tabs.put("Timetable",timetableTab);
    }
}