package controllers;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.activities;
import models.clients;
import models.hotelModel;

public class SceneControllerClients implements Initializable {
	private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;
	 
	 
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
		
		
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 Date date = new Date();
		 String now =dateFormat.format(date);
		currentDate.setText(now);
		
		
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
		    Date date1 = new Date();
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		    currentTime.setText(simpleDateFormat.format(date1));
		}), new KeyFrame(Duration.seconds(1)));

		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
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
	 public boolean isInt(String s)
	    {
	        try
	        {
	            Integer.parseInt(s);
	            return true;
	        } catch (NumberFormatException ex)
	        {
	            return false;
	        }
	    }
	 @FXML
	 void saveNewClient(ActionEvent event) throws IOException{
		 	String  fullName;
		 	 if(FullName.getText().isEmpty() ) {
		 		fullName="0";
			 }else {
				 fullName = FullName.getText();
	
			 }
		 	String  cin;
		 	 if(CIN.getText().isEmpty() ) {
		 		cin="0";
			 }else {
				 cin = CIN.getText();
	
			 }
		 	String  sexe;
		 	 if(Sexe.getText().isEmpty() ) {
		 		sexe="0";
			 }else {
				 sexe = Sexe.getText();
	
			 }
		 	String  nationality;
		 	 if(Nationality.getText().isEmpty() ) {
		 		nationality="0";
			 }else {
				 nationality = Nationality.getText();
	
			 }
		 	String  phone;
		 	 if(Phone.getText().isEmpty() ) {
		 		phone="0";
			 }else {
				 phone = Phone.getText();
	
			 }
		 	String  email;
		 	 if(Email.getText().isEmpty() ) {
		 		 	email="0";
				 }else {
					 email = Email.getText();
		
				 }
		 	Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to add the activity?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
		    	clients Client = new clients(fullName,  cin,  sexe,  nationality,  phone,email) ;
				clients.saveNewClientM(Client);	
				Alert alertYES = new Alert(AlertType.INFORMATION);
				alertYES.setTitle("Information Dialog");
				alertYES.setHeaderText("");
				alertYES.setContentText("Client added successfully");
				alertYES.showAndWait();
		    } else {	
		    	Alert alertNO = new Alert(AlertType.INFORMATION);
		    	alertNO.setTitle("Information Dialog");
		    	alertNO.setHeaderText("");
		    	alertNO.setContentText("Modification canceled");
		    	alertNO.showAndWait();
		    }
			
			switchToClients(event);
	 }
	 @FXML
	    private TextField CINM;

	    @FXML
	    private TextField EmailM ;

	    @FXML
	    private TextField FullNameM ;

	    @FXML
	    private TextField NationalityM ;

	    @FXML
	    private TextField PhoneM ;

	    @FXML
	    private TextField SexeM ;

	    @FXML
	    private TextField idCM ;

	 
	public void ModifyClient(ActionEvent event) throws IOException  {
		 	String  fullName;
		 	 if(FullNameM.getText().isEmpty() ) {
		 		fullName="0";
			 }else {
				 fullName = FullNameM.getText();
	
			 }
		 	String  cin;
		 	 if(CINM.getText().isEmpty() ) {
		 		cin="0";
			 }else {
				 cin = CINM.getText();
	
			 }
		 	String  sexe;
		 	 if(SexeM.getText().isEmpty() ) {
		 		sexe="0";
			 }else {
				 sexe = SexeM.getText();
	
			 }
		 	String  nationality;
		 	 if(NationalityM.getText().isEmpty() ) {
		 		nationality="0";
			 }else {
				 nationality = NationalityM.getText();
	
			 }
		 	String  phone;
		 	 if(PhoneM.getText().isEmpty() ) {
		 		phone="0";
			 }else {
				 phone = PhoneM.getText();
	
			 }
		 	String  email;
		 	 if(EmailM.getText().isEmpty() ) {
		 		 	email="0";
				 }else {
					 email = EmailM.getText();
		
				 }
		 	 
		 	 
		 	int id=Integer.parseInt(idCM.getText());

		 	Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to save the changes?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
		    	clients Client = new clients(id,fullName,  cin,  sexe,  nationality,  phone,email) ;
				clients.ModifyClientM(Client);	
				Alert alertYES = new Alert(AlertType.INFORMATION);
				alertYES.setTitle("Information Dialog");
				alertYES.setHeaderText("");
				alertYES.setContentText("Modification applied");
				alertYES.showAndWait();
		    } else {	
		    	Alert alertNO = new Alert(AlertType.INFORMATION);
		    	alertNO.setTitle("Information Dialog");
		    	alertNO.setHeaderText("");
		    	alertNO.setContentText("Modification canceled");
		    	alertNO.showAndWait();
		    }	
			
			switchToClients(event);
	}
	 
	public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  postionDashboardSlider=0 ;
	  	  postionAddSlider=0 ;
	  	  postionModifySlider =0;
		 }
	public void switchToAddNewClient(ActionEvent event) throws IOException {
		 
		  translateAdd(event);
		 
		 }
	
	public void switchToModifyClient(ActionEvent event) throws IOException {
		  clients clientSelected = tableClients.getSelectionModel().getSelectedItem();
		  if(clientSelected==null ) {
				Alert alertNull = new Alert(AlertType.INFORMATION);
				alertNull.setTitle("Information Dialog");
				alertNull.setHeaderText("");
				alertNull.setContentText("Select a client");
				alertNull.showAndWait();
				switchToClients(event);
		}else {
		  CINM.setText(clientSelected.getCin());
		  EmailM.setText(clientSelected.getEmail());
		  FullNameM.setText(clientSelected.getFullName());
		  NationalityM.setText(clientSelected.getNationality());
		  PhoneM.setText(clientSelected.getPhone());
		  SexeM.setText(clientSelected.getSexe());
		  idCM.setText(Integer.toString(clientSelected.getID()));
		  translateModify(event);
		 
		 }
	}
	
	
	 public void switchToLogin(MouseEvent event) throws IOException {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Di you want to log out?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
	    	 root = FXMLLoader.load(getClass().getResource("../application/login.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
				
				
		    } 
			 
	     }
	public void switchToClients(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  postionDashboardSlider=0 ;
	  	  postionAddSlider=0 ;
	  	  postionModifySlider =0;
		 }
	
    @FXML
    private AnchorPane sliderAdd;

    @FXML
    private AnchorPane sliderDashboard;

    @FXML
    private AnchorPane sliderModify;
    
    static int postionAddSlider=0 ;
	 static int postionModifySlider =0;
	 static int postionDashboardSlider=0 ;
	 
 
    public void translateAdd(ActionEvent event) {
        Duration duration = Duration.millis(1000);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sliderAdd);
        transition.setDuration(duration);
        if(postionAddSlider==0){
        	transition.setByX(-285);
        	postionAddSlider=1;
        }else {
        	transition.setByX(285);
        	postionAddSlider=0;
        }
        transition.play();
        
	 }
    

    public void translateModify(ActionEvent event) {
        Duration duration = Duration.millis(1000);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sliderModify);
        transition.setDuration(duration);
        if(postionModifySlider==0){
        	transition.setByX(-285);
        	postionModifySlider=1;
        }else {
        	transition.setByX(285);
        	postionModifySlider=0;
        }
        transition.play();
        
	 }
    
    
    public void translateDashboard(ActionEvent event) {
        Duration duration = Duration.millis(1000);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sliderDashboard);
        transition.setDuration(duration);
        if(postionDashboardSlider==0){
        	transition.setByX(173);
        	postionDashboardSlider=1;
        }else {
        	transition.setByX(-173);
        	postionDashboardSlider=0;
        }
        transition.play();
        
	 }
    public void switchToHelpAndDoc(ActionEvent event) throws IOException {
  	  root = FXMLLoader.load(getClass().getResource("../application/helpANDdoc.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	  postionDashboardSlider=0 ;
  	  postionAddSlider=0 ;
  	  postionModifySlider =0;
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
  	postionDashboardSlider=0 ;
	  postionAddSlider=0 ;
	  postionModifySlider =0;
  	 }
  //switch to the list of activities already booked
  public void switchToBookingActivity(ActionEvent event) throws IOException {
  	 root = FXMLLoader.load(getClass().getResource("../application/bookingActivity.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	postionDashboardSlider=0 ;
	  postionAddSlider=0 ;
	  postionModifySlider =0;
  	 }
  public void switchToClient(ActionEvent event) throws IOException {
  	  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	postionDashboardSlider=0 ;
	  postionAddSlider=0 ;
	  postionModifySlider =0;
  	 }


  //rooms
  //Rooms
  public void switchToRooms(ActionEvent event) throws IOException {
  	  root = FXMLLoader.load(getClass().getResource("../application/rooms.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	postionDashboardSlider=0 ;
	  postionAddSlider=0 ;
	  postionModifySlider =0;
  	 
  	 }
  //switch to the list of rooms already booked
  public void switchToBookingRoom(ActionEvent event) throws IOException {
  	  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	postionDashboardSlider=0 ;
	  postionAddSlider=0 ;
	  postionModifySlider =0;
  	 }
 
  @FXML
  private TextField searchbycin;
  
  @FXML
  public void searchClient(ActionEvent event) throws IOException {

	  String CIN=  searchbycin.getText();
	  if(CIN=="") {
		    Alert alertNull = new Alert(AlertType.INFORMATION);
			alertNull.setTitle("Information Dialog");
			alertNull.setHeaderText("");
			alertNull.setContentText("Provide client's CIN");
			alertNull.showAndWait();
			switchToClients(event);

			
	  }else {
		    clients.SearchClient(CIN);
		    
			
 		 	if(clients.getClientsSearched().size()==0) {
 		 	  Alert alertNull = new Alert(AlertType.INFORMATION);
 				alertNull.setTitle("Information Dialog");
 				alertNull.setHeaderText("");
 				alertNull.setContentText("Client not found");
 				alertNull.showAndWait();
 				switchToClients(event);

 		 	}else {
 		 		tableClients.setItems(clients.getClientsSearched());
 				idClient.setCellValueFactory(new PropertyValueFactory<>("ID"));
 				FullNamem.setCellValueFactory(new PropertyValueFactory<>("fullName"));
 				CINm.setCellValueFactory(new PropertyValueFactory<>("cin"));
 				sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
 				nationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
 				phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
 				email.setCellValueFactory(new PropertyValueFactory<>("email"));
 		 	}
		    
	  }
	  
  }

}
