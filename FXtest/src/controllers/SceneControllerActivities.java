package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.activities;
import models.hotelModel;

public class SceneControllerActivities implements Initializable {
	 private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	 private TableColumn<activities, String> dateActivity = new TableColumn<activities, String>(); 

	 @FXML
	 private TableColumn<activities, Integer> idActivity = new TableColumn<activities, Integer>();

	 @FXML
	 private TableColumn<activities, Integer> personsActivity = new TableColumn<activities, Integer>(); 

	 @FXML
	 private TableColumn<activities, Integer> priceActivity = new TableColumn<activities, Integer>(); 

	 @FXML
	 private TableColumn<activities, String> spotActivity = new TableColumn<activities, String>(); 

	 @FXML
	 private TableView<activities> tableActivity = new TableView<activities>(); 

	 @FXML
	 private TableColumn<activities, Integer> timeActivity = new TableColumn<activities, Integer>(); 

	 @FXML
	 private TableColumn<activities, String> typeActivity = new TableColumn<activities, String>(); 
	 
	 @FXML
	 private TextField textFieldDate;
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tableActivity.setItems(model.getActivitiesList());
		idActivity.setCellValueFactory(new PropertyValueFactory<>("ID"));
		personsActivity.setCellValueFactory(new PropertyValueFactory<>("persons"));
		priceActivity.setCellValueFactory(new PropertyValueFactory<>("price"));
		spotActivity.setCellValueFactory(new PropertyValueFactory<>("spot"));
		timeActivity.setCellValueFactory(new PropertyValueFactory<>("time"));
		typeActivity.setCellValueFactory(new PropertyValueFactory<>("type"));
		dateActivity.setCellValueFactory(new PropertyValueFactory<>("date"));

		// TODO Auto-generated method stub
		
	}
	 @FXML
	 private DatePicker Date;

	 @FXML
	 private TextField Persons;

	 @FXML
	 private TextField PriceA;

	 @FXML
	 private TextField Spot;

	 @FXML
	 private TextField Time;

	 @FXML
	 private TextField Type;
	 
	

	 
	 

	 @FXML
	 void saveNewActivity(ActionEvent event) {
		 	String  type = Type.getText();
			String date= Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")) ;
		 	int  time = Integer.parseInt(Time.getText());
		 	int  persons = Integer.parseInt(Persons.getText());
		 	String  spot = Spot.getText();
		 	int  price = Integer.parseInt(PriceA.getText());
		 	activities activityObj = new activities(type,  date,  time,  persons,  spot, price);
			activities.saveNewActivityM(activityObj);	


	 }
	 
	 
	 void ModifyActivity(ActionEvent event) {
		 	String  type = Type.getText();
			String date= Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")) ;
		 	int  time = Integer.parseInt(Time.getText());
		 	int  persons = Integer.parseInt(Persons.getText());
		 	String  spot = Spot.getText();
		 	int  price = Integer.parseInt(PriceA.getText());
		 	activities activityObj = new activities(type,  date,  time,  persons,  spot, price);
		 	activities.ModifyActivityM(activityObj);	


	}
	 
	 public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToActivity(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/activity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToModifyActivity(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/modifyActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToAddNewActivity(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/newActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
}
