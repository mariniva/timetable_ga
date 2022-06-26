package Controller;

import Model.DatabaseHandler;
import Model.GroupsModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Groups implements Initializable {

    @FXML
    private TableColumn<GroupsModel, Integer> groupIDCol;

    @FXML
    private TableColumn<GroupsModel, Integer> groupShiftCol;

    @FXML
    private TableColumn<GroupsModel, Integer> groupSizeCol;

    @FXML
    private TableColumn<GroupsModel, String> groupStyleCol;

    @FXML
    private TableView<GroupsModel> groupTable;

    @FXML
    private TextArea newGroupId;

    @FXML
    private TextArea newGroupShift;

    @FXML
    private TextArea newGroupSize;

    @FXML
    private TextArea newGroupStyles;


    ObservableList<GroupsModel> listG;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    private DatabaseHandler mysqlconnect;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        groupIDCol.setCellValueFactory(new PropertyValueFactory<GroupsModel, Integer>("groupId"));
        groupShiftCol.setCellValueFactory(new PropertyValueFactory<GroupsModel, Integer>("groupShift"));
        groupSizeCol.setCellValueFactory(new PropertyValueFactory<GroupsModel, Integer>("groupSize"));
        groupStyleCol.setCellValueFactory(new PropertyValueFactory<GroupsModel, String>("groupStyle"));

        listG = mysqlconnect.getDataGroup();
        groupTable.setItems(listG);

    }


    @FXML
    void handleNewGroup(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into groups (groupId,groupShift,groupSize,groupStyle)values(?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(newGroupId.getText()));
            pst.setInt(2, Integer.parseInt(newGroupShift.getText()));
            pst.setInt(3, Integer.parseInt(newGroupSize.getText()));
            pst.setInt(4, Integer.parseInt(newGroupStyles.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Group Add succes");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
// 		добавить новые стили группу в базу
//		String sql2 = "insert into groupStyle (groupId,groupStyle)values(?,?)";
//		try{
//			pst = conn.prepareStatement(sql2);
//			pst.setInt(1, Integer.parseInt(newGroupId.getText()));
//			pst.setInt(2, Integer.parseInt(.getText()));
//			pst.execute();
//			JOptionPane.showMessageDialog(null,"Group Add succes");
//		}catch (Exception e){
//			JOptionPane.showMessageDialog(null,e);
//		}
    }

    @FXML
    void handleDeleteGroup(ActionEvent event) {
        //добавить удаление из БД
        int selectedIndex = groupTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            groupTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Group Selected");
            alert.setContentText("Please select a group in the table.");

            alert.showAndWait();
        }
    }
}
