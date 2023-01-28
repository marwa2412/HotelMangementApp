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
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import models.activities;
import models.bookingActivities;
import models.bookingRooms;
import models.clients;
import models.hotelModel;
import models.rooms;

public class SceneControllerBookingActivities implements Initializable {
	
	 private hotelModel model= new hotelModel();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;

	    @FXML
	    private ComboBox<String> activityAdate;

	    @FXML
	    private ComboBox<String> activityAtype;

	    @FXML
	    private ComboBox<String> activityListMDate;

	    @FXML
	    private ComboBox<String> activityListMType;

	    @FXML
	    private TextField activityTypeDate;

	    @FXML
	    private ComboBox<String> clientAname;

	    @FXML
	    private ComboBox<String> clientListMCIN;

	    @FXML
	    private ComboBox<String> clientListMName;
	    
	    @FXML
	    private ComboBox<String> clientACIN;

	    @FXML
	    private TextField Cdate;

	    @FXML
	    private TextField Ctype;

	    @FXML
	    private TextField Ccin;

	    @FXML
	    private TextField Cname;
	
		@FXML
		private TextField currentDate;
		 
		@FXML
		private TextField currentTime;
	 
	 	@FXML
	    private TableColumn<bookingActivities, String> ActivityclmDATE = new TableColumn<bookingActivities, String>(); ;
	    @FXML
	    private TableColumn<bookingActivities, String> ActivityclmTYPE = new TableColumn<bookingActivities, String>(); ;
	    @FXML
	    private TableColumn<bookingActivities, String> Activityclm = new TableColumn<bookingActivities, String>(); ;
	    @FXML
	    private TableColumn<bookingActivities, String> clientclmNAME = new TableColumn<bookingActivities, String>(); ;
	    @FXML
	    private TableColumn<bookingActivities, String> clientclmCIN = new TableColumn<bookingActivities, String>(); ;

	    @FXML
	    private TableColumn<bookingActivities, Integer> Booknumclm = new TableColumn<bookingActivities, Integer>(); 

	    @FXML
	    private TableColumn<bookingActivities, String> clientclm = new TableColumn<bookingActivities, String>(); 

	    @FXML
	    private TableColumn<bookingActivities, String> dateclm= new TableColumn<bookingActivities, String>(); 

	    @FXML
	    private TableView<bookingActivities> tablebookingact  = new TableView<bookingActivities>(); 
	    	    
	    @FXML
	    private TableColumn<bookingActivities, String> statusclm= new TableColumn<bookingActivities, String>(); 
	    
	    @FXML
	    private TextField idBookingActivityAdd;

	    @FXML
	    private TextField idBookingActivityM;
	
	    @Override
	    /**
	     * This code initializes a JavaFX controller class, specifically the part that sets up the views and data for
	       the booking activities tab. It sets the items for several tables and combo boxes, sets the cell value factories 
	       for the table columns, and sets the text for the current date and time labels. It also creates a Timeline object
	       that updates the time label every second and creates an ObservableList of strings for the status combo box.
	     */
		public void initialize(URL arg0, ResourceBundle arg1) {	    	
		 tablebookingact.setItems(model.getBookingActivitiesList());
		 Booknumclm.setCellValueFactory(new PropertyValueFactory<>("ID"));
		 ActivityclmTYPE.setCellValueFactory(new PropertyValueFactory<>("activitytype"));
		 clientclmCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
		 ActivityclmDATE.setCellValueFactory(new PropertyValueFactory<>("activityDate"));
		 clientclmNAME.setCellValueFactory(new PropertyValueFactory<>("name"));
		 dateclm.setCellValueFactory(new PropertyValueFactory<>("now")); 
		 statusclm.setCellValueFactory(new PropertyValueFactory<>("status")); 
		 activityAdate.setItems(model.getActivities3());
		 activityAtype.setItems(model.getActivities2());
		 activityListMDate.setItems(model.getActivities3());
		 activityListMType.setItems(model.getActivities2());
		 clientAname.setItems(model.getClientsList3());
		 clientListMCIN.setItems(model.getClientsList2());
		 clientListMName.setItems(model.getClientsList3());
		 clientACIN.setItems(model.getClientsList2());
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
		 statues.add("confirmed");
		 statues.add("paid");
		 statues.add("canceled");
		 StatutUpdate.setItems(statues);
		}
	    
	    
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
	  * This code is a method that is used to save a new booking activity. It first checks if certain input fields
	    (activityAdate, activityAtype, clientAname, and clientACIN) are empty. If any of these fields are empty, 
	    it calls the "emptyFields()" method. Otherwise, it displays a confirmation dialog asking the user if they
	    want to add the activity. If the user confirms, it creates a new "bookingActivities" object with the specified 
	    activity type, date, client name, client identification number, the current date, and a "confirmed" status, 
	    and calls the "saveNewBookingActivityM" method to save this object. Finally, it switches to the booking activity view.
	  * @param event
	  * @throws IOException
	  */
	 @FXML
	 public void saveNewBookingActivity(ActionEvent event) throws IOException {
		 String activityList="";
		 int isEmpty=0;
		 if((String) activityAdate.getValue()==null ) {
				isEmpty=1;
		 }else {
			 activityList =  (String) activityAdate.getValue();

		 }
		 String activityList2="";
		 if((String) activityAtype.getValue()==null ) {
				isEmpty=1;
		 }else {
			 activityList2 =  (String) activityAtype.getValue();
		 }
		 String clientList="";
		 if((String) clientAname.getValue()==null ) {
				isEmpty=1;
		 }else {
			 clientList =  (String) clientAname.getValue();
		 }
		 String clientList2="";
		 if((String) clientACIN.getValue()==null ) {
				isEmpty=1;
		 }else {
			 clientList2 =  (String) clientACIN.getValue();
		 }
		 
		 String statusN="confirmed";
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			String now =dateFormat.format(date);
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
		    	 bookingActivities bookingActivitiesObj = new bookingActivities(activityList2, activityList,clientList2,clientList,now,statusN);   	 
				 bookingActivities.saveNewBookingActivityM(bookingActivitiesObj);
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
			 switchToBookingActivity(event);

			}
	 }
	 /**
	  * This code is a method that is used to modify an existing booking activity. It first checks if certain input fields
	    (activityListMDate, activityListMType, clientListMName, and clientListMCIN) are empty. If any of these fields are empty,
	    it sets the corresponding variables to the current value of the selected booking activity. Otherwise, it sets 
	    the variables to the values entered by the user.
		Then it takes the id of the selected bookingactivity and the status and the current date of the selected bookingactivity
		Then it displays a confirmation dialog asking the user if they want to save the changes. If the user confirms, it creates a
		new "bookingActivities" object with the specified id, activity type, date, client name, client identification number, 
		the current date, and status, and calls the "ModifyBookingActivityM" method to update this object. Finally, it switches
		to the bookingactivity view.
	  * @param event
	  * @throws IOException
	  */
	 @FXML
	public  void ModifyBookingActivity(ActionEvent event) throws IOException {
		 String activityList="";
		 if((String) activityListMDate.getValue()==null  ) {
			 activityList =  Cdate.getText() ;
		 }else {
			 activityList =  (String) activityListMDate.getValue();
		 }
		 String activityList2="";
		 if((String) activityListMType.getValue()==null) {
			 activityList2 = Ctype.getText() ;
			 }else {
			 activityList2 =  (String) activityListMType.getValue();
		 }
		 String clientList="";
		 if((String) clientListMName.getValue()==null ) {
			 clientList =  Cname.getText() ;
			 }else {
			 clientList =  (String) clientListMName.getValue();
		 }
		 String clientList2="";
		 if((String) clientListMCIN.getValue()==null ) {
			 clientList2 = Ccin.getText() ;
		 }else {
			 clientList2 =  (String) clientListMCIN.getValue();
		 }
	 		int	 id = Integer.parseInt(idBookingActivityM.getText());

	 	   	String statusM =  bookingActivityelected.getStatus();
		    
			String now = bookingActivityelected.getNow();
		
				
	
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to save the changes?");

		    ButtonType buttonTypeYes = new ButtonType("Yes");
		    ButtonType buttonTypeNo = new ButtonType("No");
		    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.get() == buttonTypeYes){
		    	 bookingActivities bookingActivitiesObj = new bookingActivities(id,activityList2, activityList,clientList2,clientList,now,statusM);
				 bookingActivities.ModifyBookingActivityM(bookingActivitiesObj);
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
		 switchToBookingActivity(event);
			
	 }
	 
	 /**
	  * This method is used to switch to the dashboard scene when the user clicks on a button or takes some other action that 
	    triggers the "switchToDashboard" method. The method first loads the FXML file for the dashboard scene, then sets the 
	    stage and scene to the new dashboard scene and shows it to the user.
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
	 /**
	  * The method "switchToAddNewBookingActivity" is used to switch to the add new booking activity screen. 
	    It does this by calling the method "translateAdd"
	  * @param event
	  * @throws IOException
	  */
	 public void switchToAddNewBookingActivity(ActionEvent event) throws IOException {
		  translateAdd(event);
		 }
	 
	 private static bookingActivities bookingActivityelected ;
	 /**
	  * This code is a Java method that is used to switch to a "modify booking activity" screen when a user selects
	    an item from a table of bookings. It first checks if the user has selected a booking by checking if the bookingActivityelected
	    variable is null. If it is null, an alert is displayed asking the user to select a booking and the method switches back to the
	    "booking activity" screen. If a booking is selected, the code sets the text of several text fields to the values of the selected 
	     booking's properties (such as the activity date, type, cin, name, and ID) and then calls a method called translateModify().
	  * @param event
	  * @throws IOException
	  */
	 public void switchToModifyBookingActivity(ActionEvent event) throws IOException {
		  bookingActivityelected = tablebookingact.getSelectionModel().getSelectedItem();
		 if(bookingActivityelected==null ) {
				Alert alertNull = new Alert(AlertType.INFORMATION);
				alertNull.setTitle("Information Dialog");
				alertNull.setHeaderText("");
				alertNull.setContentText("Select a booking");
				alertNull.showAndWait();
				switchToBookingActivity(event);
			}else {
		Cdate.setText(bookingActivityelected.getActivityDate());
		Ctype.setText(bookingActivityelected.getActivitytype());
		Ccin.setText(bookingActivityelected.getCin());
		Cname.setText(bookingActivityelected.getName());
		 idBookingActivityM.setText(Integer.toString(bookingActivityelected.getID()));
		  translateModify(event);	
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
	    	statutSlider.setVisible(false);

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
		    alert.setContentText("Dà you want to log out?");
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
     * This code handles the functionality for searching for a specific booking activity by type. It first checks if
       the user has provided an activity type in the text field, and if not, it displays an alert message asking the 
       user to provide one. If an activity type is provided, it checks if the list of booking activities is empty,
       and if it is, it displays an alert message stating that the activity was not found. If the list is not empty, 
       it populates the table with the data from the list of booking activities, sets the cell value factories 
       for each column, and sets the items for several combo boxes.
     * @param event
     * @throws IOException
     */
    @FXML
    public void searchBookingAct(ActionEvent event) throws IOException {
  	  String act=  activityTypeDate.getText();
  	  if(act=="") {
  		    Alert alertNull = new Alert(AlertType.INFORMATION);
  			alertNull.setTitle("Information Dialog");
  			alertNull.setHeaderText("");
  			alertNull.setContentText("Provide activity's type");
  			alertNull.showAndWait();
  			switchToBookingActivity(event);		
  	  }else {
		 	if(bookingActivities.getBookingAct().size()==0) {
   		 	  Alert alertNull = new Alert(AlertType.INFORMATION);
   				alertNull.setTitle("Information Dialog");
   				alertNull.setHeaderText("");
   				alertNull.setContentText("Activity not found");
   				alertNull.showAndWait();
   				switchToBookingActivity(event);
   		 	}else {
   		 	 tablebookingact.setItems(bookingActivities.getBookingAct());
	   		 Booknumclm.setCellValueFactory(new PropertyValueFactory<>("ID"));
	   		 Activityclm.setCellValueFactory(new PropertyValueFactory<>("activity"));
	   		 clientclm.setCellValueFactory(new PropertyValueFactory<>("client"));
	   		 dateclm.setCellValueFactory(new PropertyValueFactory<>("now")); 
	   		 statusclm.setCellValueFactory(new PropertyValueFactory<>("status")); 
	   		 activityAdate.setItems(model.getActivities3());
			 activityAtype.setItems(model.getActivities2());
			 activityListMDate.setItems(model.getActivities3());
			 activityListMType.setItems(model.getActivities2());
			 clientAname.setItems(model.getClientsList3());
			 clientListMCIN.setItems(model.getClientsList2());
			 clientListMName.setItems(model.getClientsList3());
			 clientACIN.setItems(model.getClientsList2());
   		 	}  
  	  }
    }
    
    @FXML
    private ComboBox<String> StatutUpdate;
    
    @FXML
    private AnchorPane statutSlider;
    
    private static   bookingActivities bookingActivityelectedStatus ;
    /**
     * This code is a  method that is used to upload the status of a selected booking from a table of bookings.
       It first checks if the user has selected a booking by checking if the bookingActivityelectedStatus variable is null.
       If it is null, an alert is displayed asking the user to select a booking and the method switches back to the
       "bookingactivity" screen. If a booking is selected, the code sets the visibility of the statutSlider to true,
        and sets the prompt text of the StatutUpdate to the current status of the selected booking.
     * @param event
     * @throws IOException
     */
    public void uploadStatut(ActionEvent event) throws IOException {
    	  bookingActivityelectedStatus = tablebookingact.getSelectionModel().getSelectedItem();
    	 if(bookingActivityelectedStatus==null ) {
 			Alert alertNull = new Alert(AlertType.INFORMATION);
 			alertNull.setTitle("Information Dialog");
 			alertNull.setHeaderText("");
 			alertNull.setContentText("Select a booking");
 			alertNull.showAndWait();
 			switchToBookingActivity(event);
 		}else {
	    String  currentStatus=bookingActivityelectedStatus.getStatus();
    	statutSlider.setVisible(true);
    	StatutUpdate.setPromptText(currentStatus);
 		}
    }  
    
    /**
     * This code is a  method that is used to change the status of a selected booking from a table of bookings.
       It uses the bookingActivityelectedStatus variable, which is set when a booking is selected, to get the ID, 
       activity type, activity date, client name, client ID, status and date of the selected booking. It then gets
       the new status of the booking from the StatutUpdate object.
       It then shows an alert asking the user to confirm the change. If the user confirms, it creates a new bookingActivities 
       object with the updated status and calls the changeStatutBookingActivity method of the bookingActivities class
       to save the changes to the database. It then shows an alert to inform the user that the changes have been saved. 
       If the user does not confirm, it shows an alert to inform the user that the modification has been canceled. 
       Finally, the method switches to the "booking activity" screen.
     * @param event
     * @throws IOException
     */
    public  void changeStatutBookingActivity(ActionEvent event) throws IOException { 	
    	int id = bookingActivityelectedStatus.getID() ;
		 String activityList2 = bookingActivityelectedStatus.getActivitytype() ;
		 String activityList = bookingActivityelectedStatus.getActivityDate() ;
		 String clientList2 = bookingActivityelectedStatus.getCin() ;
		 String clientList = bookingActivityelectedStatus.getName() ;
		 String now = bookingActivityelectedStatus.getNow();
		 String statut = (String)StatutUpdate.getValue();
		 	   Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("Confirmation Dialog");
			    alert.setHeaderText("");
			    alert.setContentText("Are you sure you want to save the changes?");
			    ButtonType buttonTypeYes = new ButtonType("Yes");
			    ButtonType buttonTypeNo = new ButtonType("No");
			    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
			    Optional<ButtonType> result = alert.showAndWait();
			    if (result.get() == buttonTypeYes){
			    	 bookingActivities bookingActivitiesObj = new bookingActivities(id,activityList2, activityList,clientList2,clientList,now,statut);
					 bookingActivities.changeStatutBookingActivity(bookingActivitiesObj);					
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
			    switchToBookingActivity(event);   
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
