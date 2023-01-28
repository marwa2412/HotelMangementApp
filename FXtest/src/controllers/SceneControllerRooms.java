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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.activities;
import models.bookingActivities;
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
	 
	 
	 /**
	  * This code is initializing various properties for a JavaFX application to manage a list of rooms in a hotel. 
		It sets the items for the table view (tableRoom) by calling the getRoomsList() method on an instance of a 
		model class (model). It sets the cell value factories for each column of the table view, which tells
	    the table view which properties of the rooms class to display in each column.
		It also sets the text for the current date and time labels, by getting the current date and time,
	    and formatting it in the desired format.
	    It also creates a clock that updates the current time label every second and creates two 
	    comboboxes( StatutUpdate and CleanigStatueM) by creating observable list with the values 
	    of statues and cleaning statues respectively.
	  */
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
		
		 ObservableList<String> statues = FXCollections.observableArrayList();
		 statues.add("empty");
		 statues.add("occupied");
		 statues.add("to fix");
		 StatutUpdate.setItems(statues);
		 
		 
		 ObservableList<String> cleningSt = FXCollections.observableArrayList();
		 cleningSt.add("Done");
		 cleningSt.add("Not yet");
		 CleanigStatueM.setItems(cleningSt);
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
	    private TextField TechPrbs;

	    @FXML
	    private TextField TypeRoom;
	    
	    @FXML
	    private TextField ViewRoom;
	    @FXML
	    private TextField roomNbr ;

	    
	    /**
	     * This is a Java method that checks if a given string (s) can be parsed as a valid integer.
	       The method uses a try-catch block to attempt to parse the string as an int using the Integer.parseInt() method. 
	       If the parse is successful, the method returns true, indicating that the string is a valid integer. 
	       If an exception is thrown, the method returns false, indicating that the string is not a valid integer.
	       This method can be used to validate user input or to ensure that certain data is in the correct format 
	       before processing it further.
	     * @param s
	     * @return
	     */
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
	   
	    /**
	     * This code defines a method called SaveNewRoom, which is associated with a button press event in a JavaFX application.
	      The method first initializes several variables, including numRoom, floor, priceRoom, and isEmpty. The code then checks
	      if the text in several text fields, such as NumRoom, Floor, PriceRoom, TechPrbs, TypeRoom, and ViewRoom, are empty or not,
	      and if they are not, it assigns the text field's text to the corresponding variable. If any of the text fields are empty,
	      the isEmpty variable is set to 1 and the method calls the emptyFields() method.
          If isEmpty is still 0, the code displays a confirmation dialog asking the user if they want to add the room,
          and if the user confirms, the code creates a new rooms object using the variables and calls the saveNewRoomM 
          method to save it. Then it will display an information dialog box to inform the user that the room has been added successfully. 
          If the user does not confirm, the code will display an information dialog box to inform the user that the modification has been canceled.
		  Finally, the method switches to the rooms page and the code ends.
	     * @param event
	     * @throws IOException
	     */
	 @FXML
	 void SaveNewRoom(ActionEvent event) throws IOException {
		 int numRoom =0;
		 int floor  = 0;
		 int priceRoom = 0;
		 int isEmpty= 0;
		 if(NumRoom.getText()=="" || !(isInt(NumRoom.getText()))) {
			 isEmpty=1;
		 }else {
			  numRoom = Integer.parseInt(NumRoom.getText());
		 }
		 if(Floor.getText()=="" || !(isInt(Floor.getText()))) {
			 isEmpty=1;
		 }else {
			  floor =  Integer.parseInt(Floor.getText());
		 }
		 if(PriceRoom.getText()=="" || !(isInt(PriceRoom.getText()))) {
			 isEmpty=1;
		 }else {
			  priceRoom =  Integer.parseInt(PriceRoom.getText());
		 }
		 String statueRoom="empty";
		
		 String techPrbs="" ;
		 if(TechPrbs.getText()=="" ) {
			 isEmpty=1;
		 }else {
			 techPrbs =  TechPrbs.getText();
		 }
		 String typeRoom="" ;
		 if(TypeRoom.getText()=="") {
			 isEmpty=1;
		 }else {
			 typeRoom =  TechPrbs.getText();
		 }
		 String viewRoom ="";
		 if(ViewRoom.getText()=="" ) {
			 isEmpty=1;
		 }else {
			 viewRoom =  ViewRoom.getText();
		 }
		 boolean CleanigStatue = true;
		 
		 if(isEmpty==1) {
				emptyFields();
			}
			else {
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
	 }
	 
	 @FXML
	    private ComboBox<String> CleanigStatueM;

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
	    
	    /**
	     * This method appears to handle the logic for modifying a room in a hotel management system, specifically
	       the updating of a room object's attributes based on user input in the application's GUI.
		   The method first initializes several variables to hold the user-inputted values for the room's attributes
		   such as room number, floor, price, etc.
		   It then checks if any of the input fields are empty or not integers for fields that need to be integers.
		   If any of these conditions are met, it calls a method called "emptyFields()" which I assume displays an error message to the user. 
		   If all the input fields have valid values, it prompts the user with a confirmation dialog asking if they are
		   sure they want to save the changes. If the user confirms, it creates a new room object with the input values,
		   and calls a method called "ModifyRoomM()" on this object, passing it as a parameter.
           Then it shows an alert to the user indicating that the modification has been applied successfully.
           If the user cancels the confirmation dialog, it shows an alert indicating that the modification has been canceled. 
           Finally, it calls a method "switchToRooms(event)" which I assume is responsible for switching back to the main
           room management view in the application.
	     * @param event
	     * @throws IOException
	     */
	 
	public  void ModifyRoom(ActionEvent event) throws IOException {
		 int numRoom=  0;
		 int floor=0;
		 int priceRoom = 0;
		 int isEmpty =0 ;
		 if(NumRoomM.getText()=="" || !(isInt(NumRoomM.getText()))) {
			 isEmpty=1 ;
		 }else {
			  numRoom = Integer.parseInt(NumRoomM.getText());
		 }
		 if(FloorM.getText()=="" || !(isInt(FloorM.getText()))) {
			 isEmpty=1 ;
		 }else {
			  floor =  Integer.parseInt(FloorM.getText());
		 }
		 if(PriceRoomM.getText()=="" || !(isInt(PriceRoomM.getText()))) {
			 isEmpty=1 ;
		 }else {
			  priceRoom =  Integer.parseInt(PriceRoomM.getText());
		 }
		 String  statueRoom =  StatueRoomM.getText();
		 
		 String techPrbs="" ;
		 if(TechPrbsM.getText()=="" ) {
			 isEmpty=1 ;
		 }else {
			 techPrbs =  TechPrbsM.getText();
		 }
		 String typeRoom="" ;
		 if(TypeRoomM.getText()=="") {
			 isEmpty=1 ;
		 }else {
			 typeRoom =  TechPrbsM.getText();
		 }
		 String viewRoom="" ;
		 if(ViewRoomM.getText()=="" ) {
			 isEmpty=1 ;
		 }else {
			 viewRoom = ViewRoomM.getText();
		 }
		 
		 
		 boolean CleanigStatue ;
		 if(CleanigStatueM.getValue()=="") {
			 CleanigStatue=true;
		 }else {
			 if(CleanigStatueM.getValue()=="Done") {
				 CleanigStatue =true ;
			 }else {
				 CleanigStatue =false ;
			 }
		 }
		 	int id=Integer.parseInt(idRoomM.getText());
		 	
		 	if(isEmpty==1) {
				emptyFields();
			}
			else {
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
	}
	
	 /**
	  * This method is used to switch to the dashboard scene when the user clicks on a button or takes some other action that 
	    triggers the "switchToDashboard" method. The method first loads the FXML file for the dashboard scene, then sets the 
	    stage and scene to the new dashboard scene and shows it to the user and resets some variables postionDashboardSlider, 
	    postionAddSlider, postionModifySlider.
	  * @param event
	  * @throws IOException
	  */
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
	 /**
	  * This method is used to switch to the "add new room" page in the application. It takes an ActionEvent as an input,
	    The method uses the translateAdd method to change the view to the add new room page. Additionally, 
	    this method does not return any value and throws an IOException if there is an input/output error.
	  * @param event
	  * @throws IOException
	  */
	 public void switchToAddNewRoom(ActionEvent event) throws IOException {
		  translateAdd(event);

		 }
	 /**
		 * This method is used to switch the current scene to the "rooms.fxml" scene. It does this by first loading the 
		   "rooms.fxml" file using the FXMLLoader class and then setting the loaded root element as the root node of a new Scene.
		   The stage variable is obtained by getting the source of the ActionEvent and using that to get the Scene's window, 
		   which is cast to a Stage. The new Scene is then set as the current scene of this stage, and the stage is shown to the user. 
		   Additionally, the method also resets the values of the postionDashboardSlider, postionAddSlider, and postionModifySlider 
		   variables to 0.
		 * @param event
		 * @throws IOException
		 */
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
	 
	 /**
	  * This code is handling the event of switching from the current view to a view for modifying a room.
	    When the event is triggered, it first checks if a room has been selected in the table of rooms. 
	    If no room has been selected, it will display an alert message asking the user to select a room. 
	    If a room has been selected, it will populate the fields in the modify room view with the information
	    of the selected room, and then call the translateModify function. The translateModify function is not 
	    defined in this code snippet and it's not clear what its purpose is, but it's likely that it is navigating 
	    to the modify room view.
	  * @param event
	  * @throws IOException
	  */
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
		 CleanigStatueM.setPromptText(Boolean.toString(roomSelected.isCleaningRoom()));
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
	 
	 /**
	  * This code is a Java method that is used to switch to a different scene within a JavaFX application. 
	    The method is called "switchToHelpAndDoc" and it takes in a single parameter, an "ActionEvent" object, 
	    which represents an event that occurs when a user interacts with the application.
	    The method starts by loading the FXML file "helpANDdoc.fxml" using the FXMLLoader class. It then gets a 
	    reference to the current stage and sets the scene to the newly loaded FXML file. Finally, it shows the stage
	    and resets some variables postionDashboardSlider, postionAddSlider, postionModifySlider.
	  * @param event
	  * @throws IOException
	  */
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
	 /**
	  * This code appears to switch the current scene of the application to the "activity.fxml" scene when the method 
	    is called. It does this by loading the FXML file, setting the scene to the loaded root node, and then showing 
	    the stage. It also resets the position variables for the dashboard, add, and modify sliders to 0.
	  * @param event
	  * @throws IOException
	  */
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
	
	 /**
	    * This code creates a confirmation dialog asking the user if they want to log out. If the user selects "Yes", 
	      the code proceeds to load the login.fxml file, set the scene to the login page, and display it on the stage.
	      If the user selects "No", the code does not perform any action and the user remains on the current page.
	    * @param event
	    * @throws IOException
	    */
	 public void switchToLogin(MouseEvent event) throws IOException {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Do you want to log out?");

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
	 /**
	  * This code is creating a new scene by loading the FXML file "bookingActivity.fxml" and setting it as the current 
	    scene on the stage. The stage and scene are obtained by getting the source of the ActionEvent, which is likely 
	    a button press. It is also setting the values of 3 variables "postionDashboardSlider", "postionAddSlider" and 
	    "postionModifySlider" to 0. 
	  * @param event
	  * @throws IOException
	  */
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
	
	 /**
	 * This code is similar is used to switch to a different scene within a JavaFX application. The method is called "switchToClient"
	   and it takes in a single parameter, an "ActionEvent" object, which represents an event that occurs when a user interacts with 
	   the application. The method starts by loading the FXML file "clients.fxml" using the FXMLLoader class. It then gets a reference 
	   to the current stage and sets the scene to the newly loaded FXML file. Finally, it shows the stage and resets some variables 
	   postionDashboardSlider, postionAddSlider, postionModifySlider.
	 * @param event
	 * @throws IOException
	 */
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

	/**
	 * This method appears to switch the current scene to a new FXML file called "bookingRoom.fxml" when a user triggers
	   the specified ActionEvent (e.g. clicks a button). It also sets the values of postionDashboardSlider, postionAddSlider,
	   and postionModifySlider to 0. The FXMLLoader is used to load the FXML file and set it as the root node of the scene. 
	   The scene is then set on the stage, and the stage is shown to the user. 
	 * @param event
	 * @throws IOException
	 */
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
		 
		 /**
			 * This method is used to slide the "sliderAdd" element horizontally by a distance of 285 pixels (when "postionAddSlider" is 0)
			   or -285 pixels (when "postionAddSlider" is 1) using a TranslateTransition animation. The duration of the animation is set to
			   1000 milliseconds.The "postionAddSlider" variable is used to track the current position of the slider and determine 
			   the direction of the animation.
			 * @param event
			 */
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
	    
	    
	    /**
	     * This function is called "translateModify" and it animates the movement of an element called "sliderModify" by sliding 
	       it horizontally by a certain distance (285 pixels) over a certain duration (1000 milliseconds). The distance and direction 
	       of the slide (left or right) is determined by the current position of the "sliderModify" element, which is stored in the 
	       variable "postionModifySlider". The function checks the value of this variable and if it's 0, the "sliderModify" element 
	       is moved to the left by -285 pixels, otherwise it is moved to the right by 285 pixels. The "TranslateTransition"
	       class is used to create the animation effect.
	     * @param event
	     */
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
	    
	    /**
	     * It  is used to animate the sliding in and out of a dashboard menu on the user interface. The function is triggered 
	     by an ActionEvent.The function sets up a TranslateTransition animation, which will move the "sliderDashboard" node
	     by a certain number of pixels along the x-axis. The distance and direction of the movement is determined by the value 
	     of the "postionDashboardSlider" variable, If the variable is 0, the animation will slide the dashboard in by moving 
	     it 173 pixels to the right. If the variable is 1, the animation will slide the dashboard out by moving it -173 pixels 
	     to the left. The transition animation will take 1 second to complete.
	     * @param event
	     */
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
	    
	    /**
	     * This code is a Java method that handles the event of searching for a room by its number. 
	       It first gets the room number from a text field (roomNbr.getText()). If the field is empty,
	       it displays an alert message asking the user to provide a room number, and then calls the switchToRooms(event) method.
	       If the field is not empty, it calls the SearchRoom(nbr) method on the "rooms" object, passing in the room number as a parameter. 
	       If the search returns no results, it displays an alert message saying the room was not found, and then calls the switchToRooms(event) method.
	       If the search returns results, it updates the items in the tableRoom with the search results, sets the cell value factories for each column,
	       and displays the search results.
	     * @param event
	     * @throws IOException
	     */
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
	    
	    
	    @FXML
	    private ComboBox<String> StatutUpdate;
	    
	    @FXML
	    private AnchorPane statutSlider;
	    
	    private static   rooms roomsSelectedStatus ;
	    /**
	     * This code is a Java method that handles the event of uploading a status update for a room reservation. 
	       It first checks if a room has been selected from a table (tableRoom.getSelectionModel().getSelectedItem()),
	       and if not, it displays an alert message asking the user to select a booking. If a room has been selected,
	       it sets the visibility of a status slider (statutSlider) to true and sets the prompt text of a StatutUpdate 
	       field to the current status of the room. The switchToRooms(event) method is called at the end.
	     * @param event
	     * @throws IOException
	     */
	    public void uploadStatut(ActionEvent event) throws IOException {
	    	roomsSelectedStatus = tableRoom.getSelectionModel().getSelectedItem();
	    	 if(roomsSelectedStatus==null ) {
	 			Alert alertNull = new Alert(AlertType.INFORMATION);
	 			alertNull.setTitle("Information Dialog");
	 			alertNull.setHeaderText("");
	 			alertNull.setContentText("Select a booking");
	 			alertNull.showAndWait();
	 			switchToRooms(event);
	 		}else {
		    String  currentStatus=roomsSelectedStatus.getStatueRoom();
	    	statutSlider.setVisible(true);
	    	StatutUpdate.setPromptText(currentStatus);
	 		}
	    }  
	    
	    /**
	     * This code is a Java method that handles the event of changing the status of a room. The method first gets
	       the selected room from a table and saves its current attributes (id, number, floor, type, view, cleaning status, 
	       technical problems, and price) into variables. Then it gets the new status from a selected value (StatutUpdate.getValue()), 
	       and displays a confirmation dialog to the user asking if they are sure they want to save the changes. If the user confirms, 
	       it creates a new room object with the updated status and calls the changeStatutRoom method passing the new room object as parameter.
	       Then it displays an alert message confirming that the modification has been applied. If the user cancels, it displays an alert message 
	       saying the modification has been canceled. Finally it calls the switchToRooms(event) method.
	     * @param event
	     * @throws IOException
	     */
	    public  void changeStatutRooms(ActionEvent event) throws IOException { 	
	    	int id = roomsSelectedStatus.getIdRoom() ;
			 int numRoom = roomsSelectedStatus.getNumRoom() ;
			 int numFloor = roomsSelectedStatus.getNumFloor() ;
			 String typeRoom = roomsSelectedStatus.getTypeRoom() ;
			 String viewRoom = roomsSelectedStatus.getViewRoom();
			 Boolean cleaningRoom = roomsSelectedStatus.isCleaningRoom();
			 String techProbs = roomsSelectedStatus.getTechProbs();
			 int priceRoom = roomsSelectedStatus.getPriceRoom();
			 
			 String statueRoom = (String)StatutUpdate.getValue();
			 	   Alert alert = new Alert(AlertType.CONFIRMATION);
				    alert.setTitle("Confirmation Dialog");
				    alert.setHeaderText("");
				    alert.setContentText("Are you sure you want to save the changes?");
				    ButtonType buttonTypeYes = new ButtonType("Yes");
				    ButtonType buttonTypeNo = new ButtonType("No");
				    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
				    Optional<ButtonType> result = alert.showAndWait();
				    if (result.get() == buttonTypeYes){
				    	 rooms room = new rooms(id,numRoom, numFloor,typeRoom,viewRoom,statueRoom,cleaningRoom,techProbs,priceRoom);
						 rooms.changeStatutRoom(room);					
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
				    switchToRooms(event);   
	    }
	    
	    /**
	     * This method, emptyFields(), creates an alert dialog box that displays a message to the user.
	       The message informs the user to verify their inputs, specifically that text fields should be full 
	       and that they should be in the correct format. 
	     */
	 
	    public void emptyFields(){
	    	Alert alertINFO = new Alert(AlertType.INFORMATION);
	    	alertINFO.setTitle("Information Dialog");
	    	alertINFO.setHeaderText("");
	    	alertINFO.setContentText("Verify your inputs:\n -Text fields should be full \n -Text fields should be full respect their formats ");
	    	alertINFO.showAndWait();
	    }
}
