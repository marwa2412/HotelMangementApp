package controllers;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
import javafx.util.Duration;
import models.*;
public class SceneController {
	

 private Stage stage;
 private Scene scene;
 private Parent root;
 


 
 

 
 @FXML
 private PasswordField password;

 @FXML
 private TextField username;
 

 
 @FXML
 /**
  * This is a Java method called "loginFct" that is triggered by an ActionEvent. It takes the text input from two text fields,
    "username" and "password," and uses them to check if they match any records in the "users" table in a database. 
    If the username and password match a record, it shows a message saying the user is connected and calls another 
    method "switchToDashboard" with the event as its parameter. If the username and password do not match any records 
    or if either text field is empty, it shows a message saying the connection failed. If there is an error in connecting 
    to the database, it catches the exception and prints an error message.
  * @param event
  */
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
		 		 JOptionPane.showMessageDialog(null,"Connection failed. Try again.");
		 	  }
	 	   } 
	 	  }catch(Exception e){
	 	    System.out.println("Error in connection");
	 	e.printStackTrace();
	 	}
	 
 }
 
 /**
  * This method is used to switch to the dashboard scene when the user clicks on a button or takes some other action that 
    triggers the "switchToDashboard" method. The method first loads the FXML file for the dashboard scene, then sets the 
    stage and scene to the new dashboard scene and shows it to the user. It also resets the position of the dashboard.
  * @param event
  * @throws IOException
  */
 public void switchToDashboard(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
 @FXML
 private Button add;

 @FXML
 private Button dashboard;

 @FXML
 private Button modify;

 @FXML
 private Button search;

 @FXML
 private TextField serchBar;

 @FXML
 private AnchorPane sliderAdd;

 @FXML
 private AnchorPane sliderDashboard;

 @FXML
 private AnchorPane sliderModify;

 @FXML
 private Button upload;

 @FXML
 private TableView<?> sliderTable;
 
 @FXML
 private Label addLabel;
 
 @FXML
 private Label menuLabel;
 
 @FXML
 private Label modifyLabel;

 @FXML
 private Label reloadLabel;
 
 @FXML
 private Label serachLabel;
 
 /**
  * This is a method called "modifyS" that is triggered by an ActionEvent. It creates an animation 
    effect using the FadeTransition class, which makes the node "sliderModify" fade in over a duration of 7 seconds.
    It also sets the visibility of the "modifyLabel" to true, making it visible on the UI. The animation effect will 
    make the "sliderModify" node gradually appear on the screen and the "modifyLabel" will be displayed as well.
  * @param event
  * @throws IOException
  */
 @FXML
 public void modifyS(ActionEvent event) throws IOException {
	 FadeTransition fade = new FadeTransition();
     fade.setNode(sliderModify);
     fade.setDuration(Duration.millis(7000));
     fade.setFromValue(0);
     fade.setToValue(1);
     fade.setByValue(0.4);
     fade.play();
     modifyLabel.setVisible(true);
 }
 
/**
 * This is a Java method called "searchS" that is triggered by an ActionEvent. It creates an animation
   effect using the FadeTransition class, which makes the node "serchBar" fade in over a duration of 7 seconds. 
   It also sets the visibility of the "serachLabel" to true, making it visible on the UI. The animation effect 
   will make the "serchBar" node gradually appear on the screen and the "serachLabel" will be displayed as well.
   It is similar to the previous method 'modifyS' but the nodes and the labels are different.
 * @param event
 * @throws IOException
 */
 @FXML
 public void searchS(ActionEvent event) throws IOException{
	 FadeTransition fade = new FadeTransition();
     fade.setNode(serchBar);
     fade.setDuration(Duration.millis(7000));
     fade.setFromValue(0);
     fade.setToValue(1);
     fade.setByValue(0.4);
     fade.play();
     serachLabel.setVisible(true);
 }
 /**
  * This is a Java method called "dashboardS" that is triggered by an ActionEvent. It creates an animation
     effect using the FadeTransition class, which makes the node "sliderDashboard" fade in over a duration of 7 seconds. 
     It also sets the visibility of the "menuLabel" to true, making it visible on the UI. The animation effect will 
     make the "sliderAdd" node gradually appear on the screen and the "menuLabel" will be displayed as well. 
     It is similar to the previous methods 'modifyS' and 'searchS' but the nodes and the labels are different.
  * @param event
  * @throws IOException
  */
 @FXML
 public void dashboardS(ActionEvent event) throws IOException{
	 FadeTransition fade = new FadeTransition();
     fade.setNode(sliderDashboard);
     fade.setDuration(Duration.millis(7000));
     fade.setFromValue(0);
     fade.setToValue(1);
     fade.setByValue(0.4);
     fade.play();
     menuLabel.setVisible(true);
 }
 @FXML
 /**
  * This is a Java method called "uploadS" that is triggered by an ActionEvent. It creates an animation effect
     using the FadeTransition class, which makes the node "sliderTable" fade in over a duration of 7 seconds. 
     It also sets the visibility of the "reloadLabel" to true, making it visible on the UI. The animation effect
     will make the "sliderTable" node gradually appear on the screen and the "reloadLabel" will be displayed as well. 
     It is similar to the previous methods 'modifyS', 'searchS' and 'dashboardS' but the nodes and the labels are different.
  * @param event
  * @throws IOException
  */
 public void uploadS(ActionEvent event) throws IOException{
     FadeTransition fade = new FadeTransition();
     fade.setNode(sliderTable);
     fade.setDuration(Duration.millis(7000));
     fade.setFromValue(0);
     fade.setToValue(1);
     fade.setByValue(0.4);
     fade.play();
     reloadLabel.setVisible(true);
 }
 /**
  * This is a Java method called "addS" that is triggered by an ActionEvent. It creates an animation 
    effect using the FadeTransition class, which makes the node "sliderAdd" fade in over a duration of 7 seconds. 
    It also sets the visibility of the "addLabel" to true, making it visible on the UI. The animation effect will 
    make the "sliderAdd" node gradually appear on the screen and the "addLabel" will be displayed as well.
	It appears to be doing the same as the 'dashboardS' method but the nodes and the labels are different.
  * @param event
  * @throws IOException
  */
 public void addS(ActionEvent event) throws IOException {
     FadeTransition fade = new FadeTransition();
     fade.setNode(sliderAdd);
     fade.setDuration(Duration.millis(7000));
     fade.setFromValue(0);
     fade.setToValue(1);
     fade.setByValue(0.4);
     fade.play();
     addLabel.setVisible(true);
     
 }




	 
}













