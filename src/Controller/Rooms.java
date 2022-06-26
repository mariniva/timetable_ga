package Controller;

import Model.DatabaseHandler;
import Model.GroupsModel;
import Model.RoomModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Rooms implements Initializable {

    @FXML
    private TextArea newRoomId;

    @FXML
    private TextArea newRoomNumber;

    @FXML
    private TextArea newRoomCapacity;

    @FXML
    private TableColumn<RoomModel, Integer> roomCapacityCol;

    @FXML
    private TableColumn<RoomModel, Integer> roomIDCol;

    @FXML
    private TableColumn<RoomModel, String> roomNumberCol;

    @FXML
    private TableView<RoomModel> roomTable;

    @FXML
    void generateTable(ActionEvent event) {
        System.out.println("goToTimetable");

        PaneNavigator.loadPane(PaneNavigator.TIMETABLE_PANE);
    }

    @FXML
    void handleDeleteRoom(ActionEvent event) {
        //добавить удаление из БД
        int selectedIndex = roomTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            roomTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Room Selected");
            alert.setContentText("Please select a room in the table.");

            alert.showAndWait();
        }
    }

    ObservableList<RoomModel> listG;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    private DatabaseHandler mysqlconnect;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roomIDCol.setCellValueFactory(new PropertyValueFactory<RoomModel, Integer>("roomId"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("number"));
        roomCapacityCol.setCellValueFactory(new PropertyValueFactory<RoomModel, Integer>("capacity"));

        listG = mysqlconnect.getDataRoom();
        roomTable.setItems(listG);

    }


    @FXML
    void handleNewRoom(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into rooms (roomID,number,capacity)values(?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(newRoomId.getText()));
            pst.setInt(2, Integer.parseInt(newRoomNumber.getText()));
            pst.setInt(3, Integer.parseInt(newRoomCapacity.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Group Add succes");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
