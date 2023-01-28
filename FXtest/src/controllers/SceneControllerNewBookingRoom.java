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
import models.rooms;

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
	 /**
	  * This code initializes a JavaFX controller class by setting the items of two ListViews (roomList and clientList)
	    to the lists returned by the getRooms2() and getClientsList2() methods of the model object. It also sets the text
	    of two Label objects (currentDate and currentTime) to the current date and time, respectively. The code also creates
	    a Timeline object that updates the currentTime Label every second to display the current time.
	  */
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
	
	/**
	 * This method appears to switch the current scene to a new FXML file called "bookingRoom.fxml" when a user triggers
	   the specified ActionEvent (e.g. clicks a button). It also sets the values of postionDashboardSlider, postionAddSlider,
	   and postionModifySlider to 0. The FXMLLoader is used to load the FXML file and set it as the root node of the scene. 
	   The scene is then set on the stage, and the stage is shown to the user. 
	   The method also set the value of postionDashboardSlider to 0.
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
	  	 }
	 
	 @FXML
	    private TextField Price;

	   

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
	  * This code is a Java method that handles the saving of a new booking for a room reservation system. 
	    It starts by initializing some variables such as "isEmpty", "price", "outTime", "adult", "child", "room",
	    "statut", "client", "needs", and "datein" and "dateout". Then it checks if the input fields are empty or 
	    if the input is not a valid integer for certain fields (Price, checkOutTime, extraAdult, extraChild, roomList).
	    If any of the input fields are empty or not a valid integer, the variable "isEmpty" is set to 1. Then it uses a 
	    date format to get the current date and sets it to the variable "now".
		Next, it checks if the value of "isEmpty" is 1, if true, it calls the method "emptyFields()". If "isEmpty" is not 1,
	    it prompts the user with a confirmation dialog asking if they want to add the activity. If the user selects "yes",
	    it creates a new bookingRooms object and calls the method "saveNewBookingM" to save the new booking to the database 
	    and set the room as occupied. If the user selects "no", it informs the user that the modification has been canceled. 
	    Finally, it calls the method "switchToBookingRoom" and passes the event as a parameter.
	  * @param event
	  * @throws IOException
	  */
	    @FXML
	  public  void saveNewBooking(ActionEvent event) throws IOException {
	    int isEmpty=0 ;
	   	 System.out.println("Logged succ to function");
	   	 int price =0;
	   	if(Price.getText().isEmpty() || !(isInt(Price.getText()))) {
	   		isEmpty=1 ;
		 }else {
			 price = Integer.parseInt(Price.getText());
		 }
	    int outTime =0;
	   	if(checkOutTime.getText().isEmpty() || !(isInt(checkOutTime.getText()))) {
	   		isEmpty=1 ;
		 }else {
			 outTime = Integer.parseInt(checkOutTime.getText());
		 }	   	
	    int adult = 0;
	   	if(extraAdult.getText().isEmpty() || !(isInt(extraAdult.getText()))) {
	   		isEmpty=1 ;
		 }else {
			 adult = Integer.parseInt(extraAdult.getText());
		 }	   	
	   	int child=0 ;
	   	if(extraChild.getText().isEmpty() || !(isInt(extraChild.getText()))) {
	   		isEmpty=1 ;
		 }else {
			 child = Integer.parseInt(extraChild.getText());

		 }	   	
	   	int room =0 ;
	   	if(roomList.getValue()==null || !(isInt((String)roomList.getValue()))) {
	   		isEmpty=1 ;
		 }else {
			 room = Integer.parseInt(roomList.getValue());

		 }
	   	 String statut="confirmed";
	   	
	   	 String client="";
	   	if((String) clientList.getValue()==null) {
	   		isEmpty=1 ;
		 }else { 
			 client = (String) clientList.getValue();

		 }
	   	 String needs="" ;
	   	if(clientNeeds.getText().isEmpty()) {
	   		isEmpty=1 ;
		 }else {
			 needs = clientNeeds.getText();
		 }
	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now =dateFormat.format(date);
	    String datein="" ;
	   	if( checkInDate.getValue() ==null ) {
	   		isEmpty=1 ;
		 }else {
			 datein = checkInDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;

		 }
	    String dateout="" ;
	   	if( checkOutDate.getValue()==null ) {
	   		isEmpty=1 ;
		 }else {
			 dateout = checkOutDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;

		 }
	   	
	   	
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
	    	bookingRooms bookingRoomObj = new bookingRooms(client,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
	        bookingRooms.saveNewBookingM(bookingRoomObj);	
			rooms.setRoomOccupied(room);
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
	    }
	    
	    /**
		  * This code is a Java method that is used to switch to a different scene within a JavaFX application. 
		    The method is called "switchToHelpAndDoc" and it takes in a single parameter, an "ActionEvent" object, 
		    which represents an event that occurs when a user interacts with the application.
		    The method starts by loading the FXML file "helpANDdoc.fxml" using the FXMLLoader class. It then gets a 
		    reference to the current stage and sets the scene to the newly loaded FXML file. Finally, it shows the stage
		    and resets  postionDashboardSlider.
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

	  	 }
	    
	    /**
		  * This code appears to switch the current scene of the application to the "activity.fxml" scene when the method 
		    is called. It does this by loading the FXML file, setting the scene to the loaded root node, and then showing 
		    the stage. It also resets  postionDashboardSlider to 0.
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
	  
	  /**
		  * This code is creating a new scene by loading the FXML file "bookingActivity.fxml" and setting it as the current 
		    scene on the stage. The stage and scene are obtained by getting the source of the ActionEvent, which is likely 
		    a button press. It is also setting the value of  "postionDashboardSlider" to 0. 
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

	  	 }
	  
	  /**
		 * This code is similar is used to switch to a different scene within a JavaFX application. The method is called "switchToClient"
		   and it takes in a single parameter, an "ActionEvent" object, which represents an event that occurs when a user interacts with 
		   the application. The method starts by loading the FXML file "clients.fxml" using the FXMLLoader class. It then gets a reference 
		   to the current stage and sets the scene to the newly loaded FXML file. Finally, it shows the stage and resets  
		   postionDashboardSlider.
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

	  	 }

	  /**
		 * This method is used to switch the current scene to the "rooms.fxml" scene. It does this by first loading the 
		   "rooms.fxml" file using the FXMLLoader class and then setting the loaded root element as the root node of a new Scene.
		   The stage variable is obtained by getting the source of the ActionEvent and using that to get the Scene's window, 
		   which is cast to a Stage. The new Scene is then set as the current scene of this stage, and the stage is shown to the user. 
		   Additionally, the method also resets the values of the postionDashboardSlider to 0.
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
	  	 
	  	 }
	  
	  /**
	     * This is a method called "switchToDashboard" that is triggered by an ActionEvent. It is used to navigate
	       to a different view or page within the application, specifically the "dashboard.fxml" page. The method uses
	       the FXMLLoader class to load the FXML file for the dashboard page and sets it as the root node.
	       It then gets the stage and scene of the current window and sets the scene to the new root node. 
	       The stage is then shown, this will display the dashboard page to the user. The method also set 
	       the value of postionDashboardSlider to 0.
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

	  	 }
	  @FXML
	    private AnchorPane sliderDashboard;
	  
		 static int postionDashboardSlider=0 ;
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
