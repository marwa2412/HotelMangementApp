package controllers;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 public void switchToDashboard(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
 



	 
}













