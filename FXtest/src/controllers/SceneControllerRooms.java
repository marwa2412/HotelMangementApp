package controllers;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.hotelModel;
import models.rooms;

public class SceneControllerRooms implements Initializable{
	 private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	

	@FXML
	 private TableColumn<rooms, Boolean> cleaningClm = new TableColumn<rooms, Boolean>() ;

	 @FXML
	 private TableColumn<rooms, Integer> floorClm = new TableColumn<rooms, Integer>();

	 @FXML
	 private TableColumn<rooms,Integer> priceClm = new TableColumn<rooms, Integer>();

	 @FXML
	 private TableColumn<rooms,Integer> roomClm = new TableColumn<rooms, Integer>();

	 @FXML
	 private TableColumn<rooms,String> statueClm = new TableColumn<rooms, String>();

	 @FXML
	 private TableView<rooms> tableRoom=new TableView<rooms>();


	 @FXML
	 private TableColumn<rooms,String> techClm = new TableColumn<rooms, String>();

	 @FXML
	 private TableColumn<rooms,String> typeClm = new TableColumn<rooms, String>();

	 @FXML
	 private TableColumn<rooms,String> viewClm = new TableColumn<rooms, String>();
	 
	 
	 
	 @Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
	 	tableRoom.setItems(model.getRoomsList());
	 	floorClm.setCellValueFactory(new PropertyValueFactory<>("numFloor"));
	 	priceClm.setCellValueFactory(new PropertyValueFactory<>("priceRoom"));
	 	roomClm.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
	 	statueClm.setCellValueFactory(new PropertyValueFactory<>("statueRoom"));
	 	techClm.setCellValueFactory(new PropertyValueFactory<>("techProbs"));
	 	typeClm.setCellValueFactory(new PropertyValueFactory<>("typeRoom"));
	 	viewClm.setCellValueFactory(new PropertyValueFactory<>("viewRoom"));
	 	cleaningClm.setCellValueFactory(new PropertyValueFactory<>("cleaningRoom"));
	 	
	 }

	 
	 @FXML
	    private TextField CleanigStatue;

	    @FXML
	    private TextField Floor;

	    @FXML
	    private TextField NumRoom;

	    @FXML
	    private TextField PriceRoom;

	    @FXML
	    private TextField StatueRoom;

	    @FXML
	    private TextField TechPrbs;

	    @FXML
	    private TextField TypeRoom;


	 @FXML
	 void SaveNewRoom(ActionEvent event) throws IOException {
		 int numRoom = Integer.parseInt(NumRoom.getText());
		 int floor =  Integer.parseInt(Floor.getText());
		 int priceRoom =  Integer.parseInt(PriceRoom.getText());
		 String statueRoom = StatueRoom.getText();
		 String techPrbs = TechPrbs.getText();
		 String typeRoom = TypeRoom.getText();
		 String viewRoom=null ;
		 boolean CleanigStatue = true;
		 rooms Roomobj = new rooms(numRoom, floor, typeRoom, viewRoom,statueRoom,CleanigStatue, techPrbs, priceRoom) ;
		 rooms.saveNewRoomM(Roomobj);	
		 }
	 
	 void ModifyRoom(ActionEvent event) {
		 int numRoom = Integer.parseInt(NumRoom.getText());
		 int floor =  Integer.parseInt(Floor.getText());
		 int priceRoom =  Integer.parseInt(PriceRoom.getText());
		 String statueRoom = StatueRoom.getText();
		 String techPrbs = TechPrbs.getText();
		 String typeRoom = TypeRoom.getText();
		 String viewRoom=null ;
		 boolean CleanigStatue = true ;
		 rooms Roomobj = new rooms(numRoom, floor, typeRoom, viewRoom,statueRoom,CleanigStatue, techPrbs, priceRoom) ;
		 rooms.ModifyRoomM(Roomobj);		 
		 }
	
	 public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToAddNewRoom(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/newRoom.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToRooms(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/rooms.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToModifyRoom(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/modifyRoom.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	
}
