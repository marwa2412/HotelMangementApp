package controllers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.*;
public class SceneController {
	

 private Stage stage;
 private Scene scene;
 private Parent root;
 


 
 

 /**
  * login function
  * @parametres
  * get the input password and username from the login page and compare with the DB il there is a match it logs, 
  * else if shows a dialog box
  * 
  */
 @FXML
 private PasswordField password;

 @FXML
 private TextField username;

 @FXML
 void loginFct(ActionEvent event)   {
	 String user = username.getText();
	 String pswd = password.getText();
	 String query1 = "SELECT * FROM users";
	 try { 
	 	 PreparedStatement preparedStmt1 = hotelModel.connectionToDB().prepareStatement(query1);
	 	 ResultSet rs1 = preparedStmt1.executeQuery();
	 	 int i= 0 ;
	 	 if(user.equals("") || pswd.equals("")) {
	 		JOptionPane.showMessageDialog(null,"Fill in all fields!");	
	 	 }else {
	 		 while(rs1.next()) {
	 		 String username1 = rs1.getString("username");
	 		 String password1 = rs1.getString("password");
	 		 	if(username1.equals(user) && password1.equals(pswd)) {
		 			i=1 ;
		 			JOptionPane.showMessageDialog(null,"You are connected. Welcome..!");
		 			
		 			
		 			
		 			switchToDashboard(event);
		 			break ;
	 		 	}
	 	     }
		 	  if(i==0) {
		 		 JOptionPane.showMessageDialog(null,"Failed connection, Wrong information!!");
		 	  }
	 	   } 
	 	  }catch(Exception e){
	 	    System.out.println("Error in connection");
	 	e.printStackTrace();
	 	}
	 
 }
 

// Functions for switching between interfaces

// help and documentation interface
public void switchToHelpAndDoc(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/helpAndDoc.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
//***********************************************
//activities
//switch to the list of activities
public void switchToActivity(ActionEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("../application/activity.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
//switch to the list of activities already booked
public void switchToBookingActivity(ActionEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("../application/bookingActivity.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }

// new booking for an activity
public void switchToAddNewBookingActivity(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/addBookingActivity.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }


//clients
//switch to the list of clients
public void switchToClient(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }


//rooms
//Rooms
public void switchToRooms(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/rooms.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 
	 }
//switch to the list of rooms already booked
public void switchToBookingRoom(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
//new booking for a room
/*public void switchToAddNewBooking(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/addBookingRoom.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }*/
//modify a previous booking for a room
public void switchToModifyBookingRoom(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/modifyBookingRoom.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }


//dashboard

//switch to bashboard
public void switchToDashboard(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }

	 
}