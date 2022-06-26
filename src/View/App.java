package View;

import Controller.MainController;
import Controller.PaneNavigator;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;

import static Model.DatabaseHandler.*;

public class App extends Application {

	private Stage primaryStage;
	private DatabaseHandler mysqlconnect;

	private PreparedStatement pst;

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		primaryStage.setTitle("Timetable");
		primaryStage.setResizable(false);
//        primaryStage.getIcons().add(new Image("file:resources/images/logo.png"));
		primaryStage.setScene(createScene(loadMainPane()));
		primaryStage.show();
		Timetable timetable = new Timetable(getDataRoom(),getDataTrainers(),getDataStyles(),getDataGroup(),getDataTimeslots());
		timetable.generatedTimetable();


	}




	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Timetable initialize(){
		//добавить БД
//		System.out.println("init");
//		RoomModel PART2LAB = new RoomModel(1, "A1", 16);
//		RoomModel FD56 = new RoomModel(2, "B1", 20);
//		RoomModel FD99 = new RoomModel(3, "C1", 18);
//		RoomModel FD60 = new RoomModel(4, "D1", 20);
//		RoomModel SLT01 = new RoomModel(5, "E1", 19);
//		RoomModel GD40 = new RoomModel(6, "F1", 16);
//		RoomModel GD90 = new RoomModel(7, "G1", 20);
//		RoomData.addAll(PART2LAB,FD56,FD99,FD60,GD90,SLT01,GD40);
//
//		GroupsModel AA11 = new GroupsModel(1, 1, 16, new int[] { 1});
//		GroupsModel AA22 = new GroupsModel(2, 2, 20, new int[] { 2});
//		GroupsModel AA33 = new GroupsModel(3, 3, 18, new int[] { 3});
//		GroupsModel AA44 = new GroupsModel(4, 1, 20, new int[] { 4});
//		GroupsModel AA55 = new GroupsModel(5, 2, 19, new int[] { 5});
//		GroupsModel AA66 = new GroupsModel(6, 3, 16, new int[] { 6});
//		GroupsModel AA77 = new GroupsModel(7, 1, 15, new int[] { 7});
//		GroupsModel AA88 = new GroupsModel(8, 2, 19, new int[] { 1});
//		GroupsModel AA99 = new GroupsModel(9, 3, 17, new int[] { 2});
//		GroupsModel BB11 = new GroupsModel(10, 1, 19, new int[] { 3});
//		GroupsModel BB22 = new GroupsModel(11, 2, 16, new int[] { 4});
//		GroupsModel BB33 = new GroupsModel(12, 3, 19, new int[] { 5});
//		GroupData.addAll(AA11,AA22,AA33,AA44,AA55,AA66,AA77,AA88,AA99,BB11,BB22,BB33);
//		//Compscience part1
//		StylesModel firstSCS1202 = new StylesModel(1, "br1", "Брейкинг", new int[] { 1, 5, 7 });
//		StylesModel firstSCS1204 = new StylesModel(2, "hh", "Хип Хоп", new int[] { 4 });
//		StylesModel firstSCS1205 = new StylesModel(3, "st1", "Современные танцы", new int[] { 2,3 });
//		StylesModel firstSCS1206 = new StylesModel(4, "akr1", "Акробатика", new int[] { 5 });
//		StylesModel firstSCS1207 = new StylesModel(5, "str1", "Стретчинг", new int[] { 3 });
//		StylesModel firstPLC1201 = new StylesModel(6, "ft1", "Фитнес Танцы", new int[] {3 });
//		StylesModel firstSCI1201 = new StylesModel(7, "am1", "Актерское мастерство", new int[] { 6 });
//		StyleData.addAll( firstSCS1202, firstSCS1205, firstSCS1204, firstSCS1206, firstSCS1207, firstPLC1201,firstSCI1201);
//
//		TrainersModel MrKMzelikahle = new TrainersModel(1, "Дмитрий Корляков");
//		TrainersModel MrKChilumani = new TrainersModel(2, "Ксения Журавлева");
//		TrainersModel MrsSDube = new TrainersModel(3, "Любовь Милованова");
//		TrainersModel MrsSSDube = new TrainersModel(4, "Ольга Мисухина");
//		TrainersModel MrsSMoyo = new TrainersModel(5, "Алексей Гавришев");
//		TrainersModel MrTNyathi = new TrainersModel(6, "Ангелина Лоскутова");
//		TrainersModel MrKSibanda = new TrainersModel(7, "Павел Мороз");
//		TrainerData.addAll(MrKMzelikahle,MrKChilumani,MrsSDube,MrTNyathi,MrsSSDube,MrsSMoyo,MrKSibanda);
//
		return null;
	}

	private Pane loadMainPane() throws IOException {
		FXMLLoader loader = new FXMLLoader(new URL("file:resources/" + PaneNavigator.MAIN_PANE));
		//loader.setLocation(new URL("file:resources/fxml/" + PaneNavigator.MAIN_PANE));
		//Pane mainPane = loader.load();
		Pane mainPane = loader.load();
		MainController mainController = loader.getController();
		PaneNavigator.setMainApp(this);
		PaneNavigator.setMainController(mainController);
		PaneNavigator.loadPane(PaneNavigator.HOMEPAGE_PANE);

		return mainPane;
	}
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);
		return scene;
	}



	}

