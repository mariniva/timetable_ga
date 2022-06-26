package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Model.*;
import Controller.PaneNavigator;
import View.App;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.ClassModel;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

public class Timetable implements Initializable {
    @FXML
    private TableColumn<GeneratorTimetable, Integer> tableGroupCol;

    @FXML
    private TableColumn<GeneratorTimetable, Integer> tableIdCol;

    @FXML
    private TableColumn<GeneratorTimetable, String> tableRoomCol;

    @FXML
    private TableColumn<GeneratorTimetable, Integer> tableShiftCol;

    @FXML
    private TableColumn<GeneratorTimetable, String> tableStyleCol;

    @FXML
    private TableColumn<GeneratorTimetable, String> tableTimeCol;

    @FXML
    private TableColumn<GeneratorTimetable, String> tableTrainerCol;

    @FXML
    private TableView<GeneratorTimetable> timetable;

    ObservableList<GeneratorTimetable> listG;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    private DatabaseHandler mysqlconnect;
    private PreparedStatement pst;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableIdCol.setCellValueFactory(new PropertyValueFactory<GeneratorTimetable, Integer>("generatorID"));
        tableRoomCol.setCellValueFactory(new PropertyValueFactory<GeneratorTimetable, String>("room"));
        tableShiftCol.setCellValueFactory(new PropertyValueFactory<GeneratorTimetable, Integer>("shift"));
        tableStyleCol.setCellValueFactory(new PropertyValueFactory<GeneratorTimetable, String>("styleName"));
        tableTimeCol.setCellValueFactory(new PropertyValueFactory<GeneratorTimetable, String>("timeslot"));
        tableTrainerCol.setCellValueFactory(new PropertyValueFactory<GeneratorTimetable, String>("trainer"));

        listG = mysqlconnect.getDataGenerator();
        timetable.setItems(listG);


    }
}
