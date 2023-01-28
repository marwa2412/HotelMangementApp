package controllers;

import java.io.IOException;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.activities;
import models.clients;
import models.hotelModel;

public class SceneControllerActivities implements Initializable {
	 private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;
	
	 
	 @FXML
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
	 @FXML
	    private DatePicker dateA;

	    @FXML
	    private TextField descriptionA;

	    @FXML
	    private TextField idRoom;

	    @FXML
	    private TextField personsA;

	    @FXML
	    private TextField priceA;

	    @FXML
	    private TextField spotA;

	    @FXML
	    private TextField timeA;
	    
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
	 void saveNewActivity(ActionEvent event) throws IOException {
			 String  type ;
			 if(descriptionA.getText().isEmpty() ) {
				 type="0";
			 }else {
				 type = descriptionA.getText();
	
			 }
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   		Date dateNow = new Date();
	   		String now =dateFormat.format(dateNow);
			String date;
			 if(dateA.getValue()==null ) {
				 date=now;
			 }else {
				 date =dateA.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			 }
			 int  time;
			 if(timeA.getText().isEmpty()  || !(isInt(timeA.getText()))) {
				 time=0;
			 }else {
				 time =Integer.parseInt(timeA.getText());

			 }
			 int  persons;
			 if(personsA.getText().isEmpty()  || !(isInt(personsA.getText()))) {
				 persons=0;
			 }else {
				 persons =Integer.parseInt(personsA.getText());

			 }
		 	String  spot;
		 	if(spotA.getText().isEmpty() ) {
		 		spot="0";
			 }else {
				 spot = spotA.getText();
	
			 }
		 	int  price;
		 	 if(priceA.getText().isEmpty()  || !(isInt(priceA.getText()))) {
		 		price=0;
			 }else {
				 price = Integer.parseInt(priceA.getText());

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
		    	activities activityObj = new activities(type,  date,  time,  persons,  spot, price);
				activities.saveNewActivityM(activityObj);	
				
				Alert alertYES = new Alert(AlertType.INFORMATION);
				alertYES.setTitle("Information Dialog");
				alertYES.setHeaderText("");
				alertYES.setContentText("Activity added successfully");
				alertYES.showAndWait();
		    } else {	
		    	Alert alertNO = new Alert(AlertType.INFORMATION);
		    	alertNO.setTitle("Information Dialog");
		    	alertNO.setHeaderText("");
		    	alertNO.setContentText("Modification canceled");
		    	alertNO.showAndWait();
		    }
		 	
			switchToActivity(event);

	 }
	 
	 
	 @FXML
	    private DatePicker dateAM;

	    @FXML
	    private TextField descriptionAM;

	    @FXML
	    private TextField idActivityM;

	    @FXML
	    private TextField personsAM;

	    @FXML
	    private TextField priceAM;

	    @FXML
	    private TextField spotAM;

	    @FXML
	    private TextField timeAM;
	 
	    public void ModifyActivity(ActionEvent event) throws IOException {
		 String  type ;
		 if(descriptionAM.getText().isEmpty() ) {
			 type="0";
		 }else {
			 type = descriptionAM.getText();

		 }
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   		Date dateNow = new Date();
   		String now =dateFormat.format(dateNow);
		String date;
		 if(dateAM.getValue()==null ) {
			 date=now;
		 }else {
			 date =dateAM.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		 }
		 int  time;
		 if(timeAM.getText().isEmpty()  || !(isInt(timeAM.getText()))) {
			 time=0;
		 }else {
			 time =Integer.parseInt(timeAM.getText());

		 }
		 int  persons;
		 if(personsAM.getText().isEmpty()  || !(isInt(personsAM.getText()))) {
			 persons=0;
		 }else {
			 persons =Integer.parseInt(personsAM.getText());

		 }
	 	String  spot;
	 	if(spotAM.getText().isEmpty() ) {
	 		spot="0";
		 }else {
			 spot = spotAM.getText();

		 }
	 	int  price;
	 	 if(priceAM.getText().isEmpty()  || !(isInt(priceAM.getText()))) {
	 		price=0;
		 }else {
			 price = Integer.parseInt(priceAM.getText());

		 }
	 	 
	 
	 	int id=Integer.parseInt(idActivityM.getText());
	 	
	 	
	 	
	 	Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Confirmation Dialog");
	    alert.setHeaderText("");
	    alert.setContentText("Are you sure you want to save the changes?");

	    ButtonType buttonTypeYes = new ButtonType("Yes");
	    ButtonType buttonTypeNo = new ButtonType("No");
	    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.get() == buttonTypeYes){
	    	activities activityObj = new activities(id,type,  date,  time,  persons,  spot, price);
			activities.ModifyActivityM(activityObj);	
			
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
		switchToActivity(event);

	 	
		
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
	 
	 public void switchToModifyActivity(ActionEvent event) throws IOException {
		 activities activitySelected = tableActivity.getSelectionModel().getSelectedItem();
		if(activitySelected==null ) {
			Alert alertNull = new Alert(AlertType.INFORMATION);
			alertNull.setTitle("Information Dialog");
			alertNull.setHeaderText("");
			alertNull.setContentText("Select an activity");
			alertNull.showAndWait();
			switchToActivity(event);
		}else {
		 dateAM.setPromptText(activitySelected.getDate());
		 descriptionAM.setText(activitySelected.getType());
		 idActivityM.setText(Integer.toString(activitySelected.getID()));
		 personsAM.setText(Integer.toString(activitySelected.getPersons()));
		 priceAM.setText(Integer.toString(activitySelected.getPrice()));
		 spotAM.setText(activitySelected.getSpot());
		 timeAM.setText(Integer.toString(activitySelected.getTime()));
		  translateModify(event);
	
		 }
		
		
	 }
	 
	 public void switchToAddNewActivity(ActionEvent event) throws IOException {
		  translateAdd(event);
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
	    private DatePicker searchByDate;
	    
	    @FXML
	    public void searchActivity(ActionEvent event) throws IOException {
	    	 System.out.println("hello2");
	   	  
	   	  if(searchByDate.getValue() ==null ) {
	   		    Alert alertNull = new Alert(AlertType.INFORMATION);
	   			alertNull.setTitle("Information Dialog");
	   			alertNull.setHeaderText("");
	   			alertNull.setContentText("Provide Activity's Date");
	   			alertNull.showAndWait();
	   			switchToActivity(event);

	   			
	   	  }else {
	   		String searchdate=  searchByDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;
	   		    activities.SearchActivity(searchdate);
	   		    
	   			
	    		 	if(activities.getActivitiesSearched().size()==0) {
	    		 	  Alert alertNull = new Alert(AlertType.INFORMATION);
	    				alertNull.setTitle("Information Dialog");
	    				alertNull.setHeaderText("");
	    				alertNull.setContentText("Activity not found");
	    				alertNull.showAndWait();
	    				switchToActivity(event);

	    		 	}else {
	    		 		tableActivity.setItems(activities.getActivitiesSearched());
	    				idActivity.setCellValueFactory(new PropertyValueFactory<>("ID"));
	    				personsActivity.setCellValueFactory(new PropertyValueFactory<>("persons"));
	    				priceActivity.setCellValueFactory(new PropertyValueFactory<>("price"));
	    				spotActivity.setCellValueFactory(new PropertyValueFactory<>("spot"));
	    				timeActivity.setCellValueFactory(new PropertyValueFactory<>("time"));
	    				typeActivity.setCellValueFactory(new PropertyValueFactory<>("type"));
	    				dateActivity.setCellValueFactory(new PropertyValueFactory<>("date"));
	    		 	}
	   		    
	   	  }

	    }
	   
}
