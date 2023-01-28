package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	 private TableColumn<activities, Integer> reservedActivity = new TableColumn<activities, Integer>();
	 
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
	 
	 
	 /**
	  * This is a Java method that is the initialize method of a controller class for a JavaFX application.
	    The initialize method is called automatically by the JavaFX framework when the associated FXML file is loaded.
        The method sets the data of a TableView (tableActivity) to the data of a model class (model.getActivitiesList()). 
        Then it sets the columns of the table to display the values of specific fields of the model class. It uses 
        the setCellValueFactory() method of the TableColumn objects to bind the table's columns to the appropriate 
        fields of the model class. 

        It also sets the current date and time and display it in the currentDate and currentTime text fields. 
        The current date is set using the SimpleDateFormat class, which formats the current date as "dd/MM/yyyy" 
        and assigns it to the currentDate text field. The current time is set using the Timeline class which is a
        JavaFX animation class that is used to update the currentTime text field every second. The clock is set
        to repeat indefinitely using the setCycleCount(Animation.INDEFINITE) method and starts playing by calling the play() method.
	  */
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
		reservedActivity.setCellValueFactory(new PropertyValueFactory<>("reserv"));

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
	 *This code is a JavaFX method that saves a new activity to a database. It first checks if the inputs for the activity's type,
	  date, time, number of persons, spot and price are not empty and in the correct format (e.g. time and price should be integers). 
	  If any of these inputs are empty or in the wrong format, it will show an error message. If all inputs are valid, it will show a 
	  confirmation dialog asking the user if they want to add the activity. If the user confirms, it will check if the activity already
	  exists in the database by comparing the type and date of the activity to existing activities in the database. If the activity already exists,
	  it will show an error message. If the activity does not exist in the database, it will create a new activity object, save it to the database, 
	  and show a success message. After that it will switch to the Activity scene. 
	 * @param event
	 * @throws IOException
	 */
	 @FXML
	 void saveNewActivity(ActionEvent event) throws IOException {
			 String  type =descriptionA.getText();
			 int isEmpty=0;
			 if(descriptionA.getText().isEmpty() ) {
				 isEmpty=1;
			 }else {
				 type = descriptionA.getText();
			 }
			
			String date= "";
			 if(dateA.getValue()==null ) {
				 isEmpty=1;
			 }else {
				 date =dateA.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 }
			 int  time =0;
			 if(timeA.getText().isEmpty()  || !(isInt(timeA.getText()))) {
				 isEmpty=1;
			 }else {
				 time =Integer.parseInt(timeA.getText());
			 }
			 int  persons= 0;
			 if(personsA.getText().isEmpty()  || !(isInt(personsA.getText()))) {
				 isEmpty=1;
			 }else {
				 persons =Integer.parseInt(personsA.getText());
			 }
		 	String  spot = spotA.getText();
		 	if(spotA.getText().isEmpty() ) {
		 		isEmpty=1;
			 }else {
				 spot = spotA.getText();
	
			 }
		 	int res=0;
		 	int  price = 0;
		 	 if(priceA.getText().isEmpty()  || !(isInt(priceA.getText()))) {
		 		isEmpty=1;
			 }else {
				 price = Integer.parseInt(priceA.getText());
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
		    	
		 
		   	 String query1 = "SELECT * FROM activities";
		   	 try { 
		   	 	 PreparedStatement preparedStmt1 = hotelModel.connectionToDB().prepareStatement(query1);
		   	 	 ResultSet rs1 = preparedStmt1.executeQuery();
		   	 	 int i= 0 ;
	   	 		 while(rs1.next()) {
	   	 		 String typeA = rs1.getString("typeActivity");
	   	 		 String dateA = rs1.getString("dateActivity");
	   	 		 	if(typeA.equals(type) && dateA.equals(date)) {
	   		 			i=1 ;
	   		 			JOptionPane.showMessageDialog(null,"This activity already exist!\n Try to change the type and/ or date");
	   		 			break ;
	   	 		 	}
	   	 	     }
	   		 	  if(i==0) {
	   		 		activities activityObj = new activities(type,  date,  time,  persons,  spot, price,res);
					activities.saveNewActivityM(activityObj);	
					Alert alertYES = new Alert(AlertType.INFORMATION);
					alertYES.setTitle("Information Dialog");
					alertYES.setHeaderText("");
					alertYES.setContentText("Activity added successfully");
					alertYES.showAndWait();
					switchToActivity(event);

	   		 	  }
		   	 	    
		   	 	  }catch(Exception e){
		   	 	    System.out.println("Error in connection");
		   	 	e.printStackTrace();
		   	 	}
		    	
		    } else {	
		    	Alert alertNO = new Alert(AlertType.INFORMATION);
		    	alertNO.setTitle("Information Dialog");
		    	alertNO.setHeaderText("");
		    	alertNO.setContentText("Modification canceled");
		    	alertNO.showAndWait();
				switchToActivity(event);

		    }	 	
			}
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
	    private TextField reservedM;

	    @FXML
	    private TextField spotAM;

	    @FXML
	    private TextField timeAM;
	   /**
	    * This code is a method for handling the modification of an activity in a GUI application. 
	      The method first checks if the number of reservations for the activity is greater than 0, 
	      and if it is, it displays an alert to the user indicating that the activity cannot be modified 
	      because it is already booked, and then switches to the activity view. If the number of reservations is 0, 
	      the method proceeds to check if any of the input fields for the activity's properties are empty or contain 
	      invalid values, and if any are, it calls a method called emptyFields(). If all input fields are filled with 
	      valid values, it then prompts the user to confirm the changes, and if the user confirms, it retrieves the
	      current activity's ID, creates a new activities object with the input values, and calls the ModifyActivityM 
	      method on the object, which presumably updates the activity in the database. Finally, it displays an alert to
	      the user indicating that the modification was applied.
	    * @param event
	    * @throws IOException
	    */
	    public void ModifyActivity(ActionEvent event) throws IOException {
	    	
	    	if(Integer.parseInt(reservedM.getText()) >0) {
	    		Alert alertINFO = new Alert(AlertType.INFORMATION);
		    	alertINFO.setTitle("Information Dialog");
		    	alertINFO.setHeaderText("");
		    	alertINFO.setContentText("You can't modify this activity. It is already booked.");
		    	alertINFO.showAndWait();
				switchToActivity(event);	
	    	}
	    	else {
	    	int isEmpty= 0;
		 String  type=descriptionAM.getText() ;
		 if(descriptionAM.getText().isEmpty() ) {
			 isEmpty=1;
		 }else {
			 type = descriptionAM.getText();
		 }
		 
		String  date ="";
		 if(dateAM.getValue()==null ) {
			 isEmpty=1;
		 }else {
			 date =dateAM.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		 }
		 int  time = 0;
		 if(timeAM.getText().isEmpty()  || !(isInt(timeAM.getText()))) {
			 isEmpty=1;
		 }else {
			 time =Integer.parseInt(timeAM.getText());
		 }
		 int  persons= 0;
		 if(personsAM.getText().isEmpty()  || !(isInt(personsAM.getText()))) {
			 isEmpty=1;
		 }else {
			 persons =Integer.parseInt(personsAM.getText());
		 }
	 	String  spot= spotAM.getText();
	 	if(spotAM.getText().isEmpty() ) {
			 isEmpty=1;
		 }else {
			 spot = spotAM.getText();
		 }
	 	int  price =0;
	 	 if(priceAM.getText().isEmpty()  || !(isInt(priceAM.getText()))) {
			 isEmpty=1;
		 }else {
			 price = Integer.parseInt(priceAM.getText());
		 }
	 	 int res=0;
	 	int id=Integer.parseInt(idActivityM.getText());
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
	      	 String query1 = "SELECT * FROM activities";
		   	 try { 
		   	 	 PreparedStatement preparedStmt1 = hotelModel.connectionToDB().prepareStatement(query1);
		   	 	 ResultSet rs1 = preparedStmt1.executeQuery();
		   	 	 int i= 0 ;
	   	 		 while(rs1.next()) {
	   	 		 String typeA = rs1.getString("typeActivity");
	   	 		 String dateA = rs1.getString("dateActivity");
	   	 		 	if(typeA.equals(type) && dateA.equals(date)) {
	   	 		 		i++;
	   	 		 		if(i==2) {
	   	 		 			JOptionPane.showMessageDialog(null,"This activity already exist!\n Try to change the type and/ or date");
	   	 		 			break ;
	   	 		 		}
	   	 		 	}
	   	 	     }
	   		 	  if(i==1) {
	   		    	activities activityObj = new activities(id,type,  date,  time,  persons,  spot, price,res);
	   				activities.ModifyActivityM(activityObj);				
	   				Alert alertYES = new Alert(AlertType.INFORMATION);
	   				alertYES.setTitle("Information Dialog");
	   				alertYES.setHeaderText("");
	   				alertYES.setContentText("Modification applied");
	   				alertYES.showAndWait();
					switchToActivity(event);

	   		 	  }
		   	 	    
		   	 	  }catch(Exception e){
		   	 	    System.out.println("Error in connection");
		   	 	e.printStackTrace();
		   	 	}
	    	
	    	
	    	
	    
	    } else {	
	    	Alert alertNO = new Alert(AlertType.INFORMATION);
	    	alertNO.setTitle("Information Dialog");
	    	alertNO.setHeaderText("");
	    	alertNO.setContentText("Modification canceled");
	    	alertNO.showAndWait();
	    	switchToActivity(event);
	    }
		
	}
	    	}
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
	  * This method is used to switch to the dashboard scene when the user clicks on a button or takes some other action that 
	    triggers the "switchToDashboard" method. The method first loads the FXML file for the dashboard scene, then sets the 
	    stage and scene to the new dashboard scene and shows it to the user. It also resets the position of the dashboard,
	    add and modify sliders variables to 0.
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
	  * This code is a method that is called when the user clicks on a button to switch to the "modify activity" view. 
	    It first checks if the user has selected an activity from the table. If not, it shows an alert message asking 
	    the user to select an activity, and then switches back to the "activity" view. If an activity is selected,
	    it sets the values of various input fields in the "modify activity" view to the values of the selected activity.
	    Finally, it calls the translateModify() method to switch to the "modify activity" view.
	  * @param event
	  * @throws IOException
	  */
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
		 dateAM.setValue(LocalDate.parse(activitySelected.getDate()));
		 descriptionAM.setText(activitySelected.getType());
		 idActivityM.setText(Integer.toString(activitySelected.getID()));
		 personsAM.setText(Integer.toString(activitySelected.getPersons()));
		 priceAM.setText(Integer.toString(activitySelected.getPrice()));
		 spotAM.setText(activitySelected.getSpot());
		 timeAM.setText(Integer.toString(activitySelected.getTime()));
		 reservedM.setText(Integer.toString(activitySelected.getReserv()));
		  translateModify(event);
		 }
	 }
	 /**
	  * This method, "switchToAddNewActivity", is used to switch to the "add new activity" screen 
	    when the corresponding button is clicked. The method uses the "translateAdd" method to animate 
	    the transition to the new screen.
	  * @param event
	  * @throws IOException
	  */
	 public void switchToAddNewActivity(ActionEvent event) throws IOException {
		  translateAdd(event);
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
	    
	    @FXML
	    private DatePicker searchByDate;
	    /**
	     * This code handles the event of searching for an activity based on a date. It first checks if the date input field is empty,
	       and if it is, it displays an alert message asking the user to provide a date. If the date input field is not empty, it formats
	       the date to "yyyy-MM-dd" and calls the SearchActivity method of the activities object with the formatted date as a parameter. 
	       Then it checks if the size of the list returned by the SearchActivity method is equal to 0, if it is, it displays an alert message 
	       that the activity is not found. If the list is not empty, it sets the items of the tableActivity to the list returned by the
	        SearchActivity method, and sets the cell value factories for each column of the table to the corresponding property of the Activity class.
	     * @param event
	     * @throws IOException
	     */
	    @FXML
	    public void searchActivity(ActionEvent event) throws IOException {
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
