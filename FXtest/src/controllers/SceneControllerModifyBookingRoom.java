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


public class SceneControllerModifyBookingRoom  implements Initializable {
	private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
    
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;
	 
    @FXML
    private TextField PriceM;

    @FXML
    private ComboBox<String> StatutM;

    @FXML
    private DatePicker checkInDateM;

    @FXML
    private TextField checkInTimeM;

    @FXML
    private DatePicker checkOutDateM;

    @FXML
    private TextField checkOutTimeM;

    @FXML
    private ChoiceBox<String> clientListM;

    @FXML
    private TextArea clientNeedsM;

    @FXML
    private TextField extraAdultMo;

    @FXML
    private TextField extraChildMo;

    @FXML
    private TextField idBookingM;
    @FXML
    private TextField currentRoom;
    @FXML
    private TextField currentClient;

    @FXML
    private ChoiceBox<String> roomListM;

    
    
    @FXML
    private TableView<bookingRooms> bookingTable = new TableView<bookingRooms>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//how to link with model
	   
		
	 	PriceM.setText(SceneControllerBookingRooms.getObjectSelectd().get(0));
		 StatutM.setPromptText(SceneControllerBookingRooms.getObjectSelectd().get(1));
		 checkInDateM.setPromptText(SceneControllerBookingRooms.getObjectSelectd().get(2));
		 checkInTimeM.setPromptText(SceneControllerBookingRooms.getObjectSelectd().get(3));
		 checkOutDateM.setPromptText(SceneControllerBookingRooms.getObjectSelectd().get(4));
		 checkOutTimeM.setText(SceneControllerBookingRooms.getObjectSelectd().get(5));
		 currentClient.setText(SceneControllerBookingRooms.getObjectSelectd().get(6));
		 clientNeedsM.setText(SceneControllerBookingRooms.getObjectSelectd().get(7));
		 extraAdultMo.setText(SceneControllerBookingRooms.getObjectSelectd().get(8));
		 extraChildMo.setText(SceneControllerBookingRooms.getObjectSelectd().get(9));
		 idBookingM.setText(SceneControllerBookingRooms.getObjectSelectd().get(10));
		 currentRoom.setText(SceneControllerBookingRooms.getObjectSelectd().get(11)); 
		 
		 roomListM.setItems(model.getRooms2());
		clientListM.setItems(model.getClientsList2());
		
		
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
	
	 public void switchToBookingRoom(ActionEvent event) throws IOException {
	  	  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	postionDashboardSlider=0 ;

	  	 }
	 

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
	   public void saveModifyBookingRoom(ActionEvent event) throws IOException {
	    	 int id ;
		 	   	if(idBookingM.getText().isEmpty() || !(isInt(idBookingM.getText()))) {
		 	   		id=0;
		 		 }else {
		 			 id = Integer.parseInt(idBookingM.getText());
		 		 }
	    	 int price ;
	 	   	if(PriceM.getText().isEmpty() || !(isInt(PriceM.getText()))) {
	 	   		price=0;
	 		 }else {
	 			 price = Integer.parseInt(PriceM.getText());
	 		 }
	 	    int outTime ;
	 	   	if(checkOutTimeM.getText().isEmpty() || !(isInt(checkOutTimeM.getText()))) {
	 	   		outTime=0;
	 		 }else {
	 			 outTime = Integer.parseInt(checkOutTimeM.getText());
	 		 }	   	
	 	    int adult ;
	 	   	if(extraAdultMo.getText().isEmpty() || !(isInt(extraAdultMo.getText()))) {
	 	   		adult=0;
	 		 }else {
	 			 adult = Integer.parseInt(extraAdultMo.getText());
	 		 }	   	
	 	   	int child ;
	 	   	if(extraChildMo.getText().isEmpty() || !(isInt(extraChildMo.getText()))) {
	 	   		child=0;
	 		 }else {
	 			 child = Integer.parseInt(extraChildMo.getText());

	 		 }	   	
	 	   	int room ;
	 	   	if(roomListM.getValue()==null || !(isInt((String)roomListM.getValue()))) {
	 	   		room=0;
	 		 }else {
	 			 room = Integer.parseInt(roomListM.getValue());

	 		 }
	 	   	 String statut;
	 	   	if((String) StatutM.getValue()==null ) {
	 	   		statut="0";
	 		 }else {
	 			 statut  = (String) StatutM.getValue();

	 		 }
	 	   	 String client;
	 	   	if((String) clientListM.getValue()==null) {
	 	   		client="0";
	 		 }else {
	 			 client = (String) clientListM.getValue();

	 		 }
	 	   	 String needs ;
	 	   	if(clientNeedsM.getText().isEmpty()) {
	 	   		needs="0";
	 		 }else {
	 			 needs = clientNeedsM.getText();
	 		 }
	 	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = new Date();
	    		String now =dateFormat.format(date);
	 	    String datein ;
	 	   	if( checkInDateM.getValue() ==null ) {
	 	   		datein=now;
	 		 }else {
	 			 datein = checkInDateM.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;

	 		 }
	 	    String dateout ;
	 	   	if( checkOutDateM.getValue()==null ) {
	 	   		dateout=now;
	 		 }else {
	 			 dateout = checkOutDateM.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;

	 		 }
	   	 
	 	   Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to save the changes?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
		    	 bookingRooms bookingRoomObj = new bookingRooms(id,client,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
			     bookingRooms.ModifyBookingM(bookingRoomObj);	
				
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
