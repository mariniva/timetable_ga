package Controller;

import Model.DatabaseHandler;
import Model.RoomModel;
import Model.TrainersModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Trainers implements Initializable {

    @FXML
    private TextArea newTrainersId;

    @FXML
    private TextArea newTrainersName;

    @FXML
    private TableColumn<TrainersModel, Integer> trainersIdCol;

    @FXML
    private TableColumn<TrainersModel, String> trainersNameCol;

    @FXML
    private TableView<TrainersModel> trainersTable;

    @FXML
    void handleDeleteTrainers(ActionEvent event) {
        //добавить удаление из БД
        int selectedIndex = trainersTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            trainersTable.getItems().remove(selectedIndex);
//            PaneNavigator.getMainApp().getClassData().clear();
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

    @FXML
    void handleNewTrainer(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into trainers (trainerID,name)values(?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(newTrainersId.getText()));
            pst.setInt(2, Integer.parseInt(newTrainersName.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Group Add succes");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    ObservableList<TrainersModel> listG;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    private DatabaseHandler mysqlconnect;
    private PreparedStatement pst;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trainersIdCol.setCellValueFactory(new PropertyValueFactory<TrainersModel, Integer>("trainerID"));
        trainersNameCol.setCellValueFactory(new PropertyValueFactory<TrainersModel, String>("name"));

        listG = mysqlconnect.getDataTrainers();
        trainersTable.setItems(listG);

    }

}
