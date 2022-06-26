package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;


public class DatabaseHandler extends Configs {
    //    Connection dbConnection;
//
//    public Connection getDbConnection()
//        throws ClassNotFoundException, SQLException {
//        String connectionString = "jdbc:mysql://"+ dbHost + ":" + dbPort + "/" +dbName;
//        Class.forName("com.mysql.jdbc.Driver");
//        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
//        return dbConnection;
//    }
    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/timetable", "root", "12345");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<GroupsModel> getDataGroup() {
        Connection conn = ConnectDb();
        ObservableList<GroupsModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from groups");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int[] stylesIds = parseStringToInt(rs.getString("styleId"));
                list.add(new GroupsModel(rs.getInt("groupID"), rs.getInt("groupShift"), rs.getInt("groupSize"),stylesIds));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static int[] parseStringToInt(String stringInt){
        return Arrays.stream(stringInt.split(",")).mapToInt(i-> Integer.parseInt(i)).toArray();
    }



//    таблица стили группы
//    public static ObservableList<GroupsModel> getDataGroupStyle() {
//        Connection conn = ConnectDb();
//        ObservableList<GroupsModel> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from groupStyle");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new GroupsModel(Integer.parseInt(rs.getString("groupId")), rs.getInt("groupStyle"), rs.getInt("groupSize")));
//            }
//        } catch (Exception e) {
//
//        }
//        return list;
//    }

    public static ObservableList<ClassModel> getDataClass() {
        Connection conn = ConnectDb();
        ObservableList<ClassModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from classes");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ClassModel(rs.getInt("classID"), rs.getInt("groupID"), rs.getInt("styleID")));
            }
        } catch (Exception e) {

        }
        return list;
    }
    public static ObservableList<GeneratorTimetable> getDataGenerator() {
        Connection conn = ConnectDb();
        ObservableList<GeneratorTimetable> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from generatedtimetable");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new GeneratorTimetable(rs.getInt("generatorID"),rs.getString("styleName"), rs.getInt("shift"), rs.getInt("room"),rs.getString("trainer"), rs.getString("timeslot"), rs.getString("timeslot")));
            }
            } catch (Exception e) {

        }
        return list;
    }
    public static ObservableList<RoomModel> getDataRoom() {
        Connection conn = ConnectDb();
        System.out.println(" connected");
        ObservableList<RoomModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from rooms");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomModel(rs.getInt("roomID"), rs.getString("number"), rs.getInt("capacity")));
            }
        } catch (Exception e) {

            System.out.println(" error" + e);
        }
        return list;
    }
    public static ObservableList<StylesModel> getDataStyles() {
        Connection conn = ConnectDb();
        ObservableList<StylesModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from styles");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int[] trainersIds = parseStringToInt(rs.getString("trainersID"));
                list.add(new StylesModel(rs.getInt("styleID"), rs.getString("code"), rs.getString("name"),trainersIds));
            }
        } catch (Exception e) {
            System.out.println(" error" + e);
        }
        return list;
    }
    public static ObservableList<TimeslotsModel> getDataTimeslots() {
        Connection conn = ConnectDb();
        ObservableList<TimeslotsModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from timeslots");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TimeslotsModel(rs.getInt("timeslotID"), rs.getInt("shift"), rs.getString("timeslot")));
            }
        } catch (Exception e) {
            System.out.println(" error" + e);
        }
        return list;
    }
    public static ObservableList<TrainersModel> getDataTrainers() {
        Connection conn = ConnectDb();
        ObservableList<TrainersModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from trainers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TrainersModel(rs.getInt("trainerID"), rs.getString("name")));
            }
        } catch (Exception e) {
            System.out.println(" error" + e);
        }
        return list;
    }
}
