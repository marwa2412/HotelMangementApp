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
import models.clients;
import models.hotelModel;

public class SceneControllerClients implements Initializable {
	private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 
	 @FXML
	 private TableColumn<clients, String> CINm = new TableColumn<clients, String>() ;

	 @FXML
	 private TableColumn<clients, String> FullNamem = new TableColumn<clients, String>() ;

	 @FXML
	 private TableColumn<clients, String> email = new TableColumn<clients, String>() ;

	 @FXML
	 private TableColumn<clients, String> idClient = new TableColumn<clients, String>() ;

	 @FXML
	 private TableColumn<clients, String> nationality = new TableColumn<clients, String>() ;

	 @FXML
	 private TableColumn<clients, String> phone = new TableColumn<clients, String>() ;

	 @FXML
	 private TableColumn<clients, String> sexe = new TableColumn<clients, String>() ;

	 @FXML
	 private TableView<clients> tableClients = new TableView<clients>() ;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableClients.setItems(model.getClientsList());
		idClient.setCellValueFactory(new PropertyValueFactory<>("ID"));
		FullNamem.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		CINm.setCellValueFactory(new PropertyValueFactory<>("cin"));
		sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
		nationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
	}
	
	

	 @FXML
	 private TextField CIN;

	 @FXML
	 private TextField Email;

	 @FXML
	 private TextField FullName;

	 @FXML
	 private TextField Nationality;

	 @FXML
	 private TextField Phone;

	 @FXML
	 private TextField Sexe;
	 
	 @FXML
	 private TextField idC;

	 @FXML
	 void saveNewClient(ActionEvent event) throws IOException{
		 	String  fullName = FullName.getText();
		 	String  cin = CIN.getText();
		 	String  sexe = Sexe.getText();
		 	String  nationality = Nationality.getText();
		 	String  phone = Phone.getText();
		 	String  email = Email.getText();

		 	
			clients Client = new clients(fullName,  cin,  sexe,  nationality,  phone,email) ;
			clients.saveNewClientM(Client);	
			switchToClients(event);
	 }
	 
	 void ModifyClient(ActionEvent event) {
		 	String  fullName = FullName.getText();
		 	String  cin = CIN.getText();
		 	String  sexe = Sexe.getText();
		 	String  nationality = Nationality.getText();
		 	String  phone = Phone.getText();
		 	String  email = Email.getText();

		 	
			clients Client = new clients(fullName,  cin,  sexe,  nationality,  phone,email) ;
			clients.ModifyClientM(Client);	
	}
	 

	public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	public void switchToAddNewClient(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/newClient.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	public void switchToModifyClient(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/modifyClient.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	public void switchToClients(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
}
