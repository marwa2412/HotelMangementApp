package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.activities;
import models.bookingActivities;
import models.hotelModel;

public class SceneControllerBookingActivities implements Initializable {
	
	 private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 

	    @FXML
	    private TableColumn<bookingActivities, String> Activityclm = new TableColumn<bookingActivities, String>(); ;

	    @FXML
	    private TableColumn<bookingActivities, Integer> Booknumclm = new TableColumn<bookingActivities, Integer>(); 

	    @FXML
	    private TableColumn<bookingActivities, String> clientclm = new TableColumn<bookingActivities, String>(); 

	    @FXML
	    private TableColumn<bookingActivities, String> dateclm= new TableColumn<bookingActivities, String>(); 


	    @FXML
	    private TableView<bookingActivities> tablebookingact  = new TableView<bookingActivities>(); 
	
	 @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		 tablebookingact.setItems(model.getBookingActivitiesList());
		 Booknumclm.setCellValueFactory(new PropertyValueFactory<>("ID"));
		 Activityclm.setCellValueFactory(new PropertyValueFactory<>("activity"));
		 clientclm.setCellValueFactory(new PropertyValueFactory<>("client"));
		 dateclm.setCellValueFactory(new PropertyValueFactory<>("now"));
		}
	

	
	 @FXML
	 private ComboBox<?> activity;

	 @FXML
	 private ComboBox<?> client;

	 @FXML
	 void saveNewBookingActivity(ActionEvent event) {
		 String activityList = (String) activity.getValue();
		 String clientList = (String) client.getValue();
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			String now =dateFormat.format(date);
		 bookingActivities bookingActivitiesObj = new bookingActivities(activityList, clientList,now);
		 bookingActivities.saveNewBookingActivityM(bookingActivitiesObj);
	 }
	 
	 void ModifyBookingActivity(ActionEvent event) {
		 String activityList = (String) activity.getValue();
		 String clientList = (String) client.getValue();
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			String now =dateFormat.format(date);
		 bookingActivities bookingActivitiesObj = new bookingActivities(activityList, clientList,now);
		 bookingActivities.ModifyBookingActivityM(bookingActivitiesObj);
	 }
	 
	 
	 public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }

	 public void switchToAddNewBookingActivity(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/addBookingActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToModifyBookingActivity(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/modifyBookingActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToBookingActivity(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../application/bookingActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	


}
