package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.activities;
import models.bookingRooms;
import models.hotelModel;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class SceneControllerNewBookingRoom  implements Initializable {
	private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		roomList.setItems(model.getRooms2());
		clientList.setItems(model.getClientsList2());
		
		
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

		// TODO Auto-generated method stub
		
	}
	
	 public void switchToBookingRoom(ActionEvent event) throws IOException {
	  	  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	postionDashboardSlider=0 ;
	  	 }
	 
	 @FXML
	    private TextField Price;

	    @FXML
	    private ComboBox<String> Statut;

	    @FXML
	    private DatePicker checkInDate;

	    @FXML
	    private TextField checkInTime;

	    @FXML
	    private DatePicker checkOutDate;

	    @FXML
	    private TextField checkOutTime;

	    @FXML
	    private ChoiceBox<String> clientList;

	    @FXML
	    private TextArea clientNeeds;

	    @FXML
	    private TextField extraAdult;

	    @FXML
	    private TextField extraChild;

	    @FXML
	    private TextField idBooking;

	    @FXML
	    private ChoiceBox<String> roomList;
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
	  public  void saveNewBooking(ActionEvent event) throws IOException {

	   	 System.out.println("Logged succ to function");
	   	 int price ;
	   	if(Price.getText().isEmpty() || !(isInt(Price.getText()))) {
	   		price=0;
		 }else {
			 price = Integer.parseInt(Price.getText());
		 }
	    int outTime ;
	   	if(checkOutTime.getText().isEmpty() || !(isInt(checkOutTime.getText()))) {
	   		outTime=0;
		 }else {
			 outTime = Integer.parseInt(checkOutTime.getText());
		 }	   	
	    int adult ;
	   	if(extraAdult.getText().isEmpty() || !(isInt(extraAdult.getText()))) {
	   		adult=0;
		 }else {
			 adult = Integer.parseInt(extraAdult.getText());
		 }	   	
	   	int child ;
	   	if(extraChild.getText().isEmpty() || !(isInt(extraChild.getText()))) {
	   		child=0;
		 }else {
			 child = Integer.parseInt(extraChild.getText());

		 }	   	
	   	int room ;
	   	if(roomList.getValue()==null || !(isInt((String)roomList.getValue()))) {
	   		room=0;
		 }else {
			 room = Integer.parseInt(roomList.getValue());

		 }
	   	 String statut;
	   	if((String) Statut.getValue()==null ) {
	   		statut="0";
		 }else {
			 statut  = (String) Statut.getValue();

		 }
	   	 String client;
	   	if((String) clientList.getValue()==null) {
	   		client="0";
		 }else { 
			 client = (String) clientList.getValue();

		 }
	   	 String needs ;
	   	if(clientNeeds.getText().isEmpty()) {
	   		needs="0";
		 }else {
			 needs = clientNeeds.getText();
		 }
	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now =dateFormat.format(date);
	    String datein ;
	   	if( checkInDate.getValue() ==null ) {
	   		datein=now;
		 }else {
			 datein = checkInDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;

		 }
	    String dateout ;
	   	if( checkOutDate.getValue()==null ) {
	   		dateout=now;
		 }else {
			 dateout = checkOutDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;

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
	    	bookingRooms bookingRoomObj = new bookingRooms(client,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
	        bookingRooms.saveNewBookingM(bookingRoomObj);	
			
			Alert alertYES = new Alert(AlertType.INFORMATION);
			alertYES.setTitle("Information Dialog");
			alertYES.setHeaderText("");
			alertYES.setContentText("Booking added successfully");
			alertYES.showAndWait();
	    } else {	
	    	Alert alertNO = new Alert(AlertType.INFORMATION);
	    	alertNO.setTitle("Information Dialog");
	    	alertNO.setHeaderText("");
	    	alertNO.setContentText("Modification canceled");
	    	alertNO.showAndWait();
	    }
	 	
     
     switchToBookingRoom(event);

	    }
	    public void switchToHelpAndDoc(ActionEvent event) throws IOException {
	  	  root = FXMLLoader.load(getClass().getResource("../application/helpANDdoc.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	postionDashboardSlider=0 ;

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
	  //switch to the list of activities already booked
	  public void switchToBookingActivity(ActionEvent event) throws IOException {
	  	 root = FXMLLoader.load(getClass().getResource("../application/bookingActivity.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	postionDashboardSlider=0 ;

	  	 }
	  public void switchToClient(ActionEvent event) throws IOException {
	  	  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	postionDashboardSlider=0 ;

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
	  	 
	  	 }
	  //switch to the list of rooms already booked
	  
	  public void switchToDashboard(ActionEvent event) throws IOException {
	  	  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	postionDashboardSlider=0 ;

	  	 }
	  @FXML
	    private AnchorPane sliderDashboard;
	  
		 static int postionDashboardSlider=0 ;

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

}
