package controllers;

import java.io.IOException;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import models.rooms;

public class SceneControllerRooms implements Initializable{
	 private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;
	

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
	    private TextField ViewRoom;
	    @FXML
	    private TextField roomNbr ;

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
	 void SaveNewRoom(ActionEvent event) throws IOException {
		 int numRoom;
		 int floor;
		 int priceRoom;
		 if(NumRoom.getText().isEmpty() || !(isInt(NumRoom.getText()))) {
			 numRoom=0;
		 }else {
			  numRoom = Integer.parseInt(NumRoom.getText());
		 }
		 if(Floor.getText().isEmpty() || !(isInt(Floor.getText()))) {
			 floor=0;
		 }else {
			  floor =  Integer.parseInt(Floor.getText());
		 }
		 if(PriceRoom.getText().isEmpty() || !(isInt(PriceRoom.getText()))) {
			 priceRoom=0;
		 }else {
			  priceRoom =  Integer.parseInt(PriceRoom.getText());
		 }
		 String statueRoom;
		 if(StatueRoom.getText().isEmpty() || !(isInt(StatueRoom.getText()))) {
			 statueRoom="0";
		 }else {
			 statueRoom =  StatueRoom.getText();
		 }
		 String techPrbs = TechPrbs.getText();
		 if(TechPrbs.getText().isEmpty() || !(isInt(TechPrbs.getText()))) {
			 techPrbs="0";
		 }else {
			 techPrbs =  TechPrbs.getText();
		 }
		 String typeRoom ;
		 if(TypeRoom.getText().isEmpty() || !(isInt(TypeRoom.getText()))) {
			 typeRoom="0";
		 }else {
			 typeRoom =  TechPrbs.getText();
		 }
		 String viewRoom = ViewRoom.getText();
		 if(ViewRoom.getText().isEmpty() || !(isInt(ViewRoom.getText()))) {
			 viewRoom="0";
		 }else {
			 viewRoom =  ViewRoom.getText();
		 }
		 boolean CleanigStatue = true;
		 Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to add the activity?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
		    	rooms Roomobj = new rooms(numRoom, floor, typeRoom, viewRoom,statueRoom,CleanigStatue, techPrbs, priceRoom) ;
				 rooms.saveNewRoomM(Roomobj);	
				
				Alert alertYES = new Alert(AlertType.INFORMATION);
				alertYES.setTitle("Information Dialog");
				alertYES.setHeaderText("");
				alertYES.setContentText("Room added successfully");
				alertYES.showAndWait();
		    } else {	
		    	Alert alertNO = new Alert(AlertType.INFORMATION);
		    	alertNO.setTitle("Information Dialog");
		    	alertNO.setHeaderText("");
		    	alertNO.setContentText("Modification canceled");
		    	alertNO.showAndWait();
		    }
		 
		 switchToRooms(event);
		 }
	 
	 
	 @FXML
	    private TextField CleanigStatueM;

	    @FXML
	    private TextField FloorM;

	    @FXML
	    private TextField NumRoomM;

	    @FXML
	    private TextField PriceRoomM;

	    @FXML
	    private TextField StatueRoomM;

	    @FXML
	    private TextField TechPrbsM;

	    @FXML
	    private TextField TypeRoomM;
	    
	    @FXML
	    private TextField ViewRoomM;
	    
	    @FXML
	    private TextField idRoomM;
	    
	    
	 
	public  void ModifyRoom(ActionEvent event) throws IOException {
		 int numRoom;
		 int floor;
		 int priceRoom;
		 if(NumRoomM.getText().isEmpty() || !(isInt(NumRoomM.getText()))) {
			 numRoom=0;
		 }else {
			  numRoom = Integer.parseInt(NumRoomM.getText());
		 }
		 if(FloorM.getText().isEmpty() || !(isInt(FloorM.getText()))) {
			 floor=0;
		 }else {
			  floor =  Integer.parseInt(FloorM.getText());
		 }
		 if(PriceRoomM.getText().isEmpty() || !(isInt(PriceRoomM.getText()))) {
			 priceRoom=0;
		 }else {
			  priceRoom =  Integer.parseInt(PriceRoomM.getText());
		 }
		 String statueRoom;
		 if(StatueRoomM.getText().isEmpty() || !(isInt(StatueRoomM.getText()))) {
			 statueRoom="0";
		 }else {
			 statueRoom =  StatueRoomM.getText();
		 }
		 String techPrbs = TechPrbsM.getText();
		 if(TechPrbsM.getText().isEmpty() || !(isInt(TechPrbsM.getText()))) {
			 techPrbs="0";
		 }else {
			 techPrbs =  TechPrbsM.getText();
		 }
		 String typeRoom ;
		 if(TypeRoomM.getText().isEmpty() || !(isInt(TypeRoomM.getText()))) {
			 typeRoom="0";
		 }else {
			 typeRoom =  TechPrbsM.getText();
		 }
		 String viewRoom = ViewRoomM.getText();
		 if(ViewRoomM.getText().isEmpty() || !(isInt(ViewRoomM.getText()))) {
			 viewRoom="0";
		 }else {
			 viewRoom =  ViewRoomM.getText();
		 }
		 boolean CleanigStatue = true;
		 	int id=Integer.parseInt(idRoomM.getText());
		 	Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to save the changes?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
		    	 rooms Roomobj = new rooms(id,numRoom, floor, typeRoom, viewRoom,statueRoom,CleanigStatue, techPrbs, priceRoom) ;
				 rooms.ModifyRoomM(Roomobj);	
				
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
			
		 switchToRooms( event);
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
	 public void switchToAddNewRoom(ActionEvent event) throws IOException {
		  translateAdd(event);

		 }
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
	 public void switchToModifyRoom(ActionEvent event) throws IOException {
		 rooms roomSelected = tableRoom.getSelectionModel().getSelectedItem();
		 if(roomSelected==null ) {
				Alert alertNull = new Alert(AlertType.INFORMATION);
				alertNull.setTitle("Information Dialog");
				alertNull.setHeaderText("");
				alertNull.setContentText("Select a room");
				alertNull.showAndWait();
				switchToRooms(event);
			}else {
		 CleanigStatueM.setText(Boolean.toString(roomSelected.isCleaningRoom()));
		 FloorM.setText(Integer.toString(roomSelected.getNumFloor()));
		 NumRoomM.setText(Integer.toString(roomSelected.getNumRoom()));
		 PriceRoomM.setText(Integer.toString(roomSelected.getPriceRoom()));
		 StatueRoomM.setText(roomSelected.getStatueRoom());
		 TechPrbsM.setText(roomSelected.getTechProbs());
		 TypeRoomM.setText(roomSelected.getTypeRoom());
		 ViewRoomM.setText(roomSelected.getViewRoom());
		 idRoomM.setText(Integer.toString(roomSelected.getIdRoom()));
		  translateModify(event);
		 }
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
	    @FXML
	    public void searchRoom(ActionEvent event) throws IOException {
	  	  System.out.println("hello");
	  	  String nbr=  roomNbr.getText();
	  	  if(nbr=="") {
	  		    Alert alertNull = new Alert(AlertType.INFORMATION);
	  			alertNull.setTitle("Information Dialog");
	  			alertNull.setHeaderText("");
	  			alertNull.setContentText("Provide room's number");
	  			alertNull.showAndWait();
	  			switchToRooms(event);

	  			
	  	  }else {
	  		    rooms.SearchRoom(nbr);
	  		    
	  			
	   		 	if(rooms.getRoomsSearched().size()==0) { 
	   		 	  Alert alertNull = new Alert(AlertType.INFORMATION);
	   				alertNull.setTitle("Information Dialog");
	   				alertNull.setHeaderText("");
	   				alertNull.setContentText("Room not found");
	   				alertNull.showAndWait();
	   				switchToRooms(event);

	   		 	}else {
	   				tableRoom.setItems(rooms.getRoomsSearched());
	   			 	floorClm.setCellValueFactory(new PropertyValueFactory<>("numFloor"));
	   			 	priceClm.setCellValueFactory(new PropertyValueFactory<>("priceRoom"));
	   			 	roomClm.setCellValueFactory(new PropertyValueFactory<>("numRoom"));
	   			 	statueClm.setCellValueFactory(new PropertyValueFactory<>("statueRoom"));
	   			 	techClm.setCellValueFactory(new PropertyValueFactory<>("techProbs"));
	   			 	typeClm.setCellValueFactory(new PropertyValueFactory<>("typeRoom"));
	   			 	viewClm.setCellValueFactory(new PropertyValueFactory<>("viewRoom"));
	   			 	cleaningClm.setCellValueFactory(new PropertyValueFactory<>("cleaningRoom"));
	   		 	}
	  		    
	  	  }
	  	  
	    }
	    
	 
	
}
