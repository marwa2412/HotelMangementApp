package controllers;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	 /**
	  * This code initializes the tableClients by setting its items to the result of the getClientsList() 
	    method in the model. It also sets the cell value factories for each column in the table, so that it
	    knows which property of the client object to display in each column. The code also sets the text of 
	    the currentDate and currentTime labels to the current date and time respectively. It also creates
	    a Timeline clock that updates the currentTime label with the current time every second.
	    The clock is set to run indefinitely.
	  */
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
    private RadioButton sexeFemale;

    @FXML
    private RadioButton sexeMale;
	 
	 @FXML
	 private TextField idC;
	 
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
	 @FXML
	 /**
	  * This code is for a method that is used to save a new client in the system. It first checks if any of the text
	    fields (FullName, CIN, Sexe, Nationality, Phone, Email) are empty or if the Phone and Email fields contain valid input. 
	    If any of these checks return true, it calls an emptyFields() method and returns. If all the checks return false, 
	    it displays a confirmation dialog asking the user if they want to add the client. If the user clicks "Yes", 
	    the code creates a new clients object with the input from the text fields and calls the saveNewClientM method to save 
	    the client to the system. If the user clicks "No", it displays an information dialog saying the modification was canceled.
	    It then calls the switchToClients method to switch to the clients screen.
	  * @param event
	  * @throws IOException
	  */
	 void saveNewClient(ActionEvent event) throws IOException{
		 	int isEmpty =0 ;
		 	String  fullName="";
		 	 if(FullName.getText().isEmpty() ) {
		 		isEmpty=1 ;
			 }else {
				 fullName = FullName.getText();
	
			 }
		 	String  cin="";
		 	 if(CIN.getText().isEmpty() ) {
		 		isEmpty=1 ;
			 }else {
				 cin = CIN.getText();
	
			 }
		 	
		 	 String sexe = sexeMale.getText();
		 	 if(sexeFemale.isSelected()) {
		 		sexe = sexeFemale.getText();
		 	 }
		 	String  nationality="";
		 	 if(Nationality.getText().isEmpty() ) {
		 		isEmpty=1 ;
			 }else {
				 nationality = Nationality.getText();
	
			 }
		 	String  phone="";
		 	 if(Phone.getText().isEmpty() || isPhoneValid(Phone.getText()) ) {
		 		isEmpty=1 ;
			 }else {
				 phone = Phone.getText();
	
			 }
		 	String  email="";
		 	 if(Email.getText().isEmpty() || isEmailValid(Email.getText())==false) {
		 		isEmpty=1 ;
				 }else {
					 email = Email.getText();
		
				 }
		 	 
		 	if(isEmpty==1) {
				emptyFields();
			}
			else {
		 	Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation Dialog");
		    alert.setHeaderText("");
		    alert.setContentText("Are you sure you want to add the client?");

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
	    private RadioButton sexeFemaleM;

	    @FXML
	    private RadioButton sexeMaleM;
	    @FXML
	    private TextField idCM ;

	 /**
	  * This code is a method that handles the process of modifying a client's information. When this method is called,
	    it first checks if the client's full name, CIN, sexe, nationality, phone number, and email are not empty and that
	    the phone number and email are valid. If any of the fields are empty or the phone number and email are invalid,
	    it calls a method named "emptyFields()" which probably shows an error message. If all the fields are filled and
	    the phone number and email are valid, it prompts the user with a confirmation dialog asking if they want to save 
	    the changes. If the user confirms, it creates a new client object with the updated information and calls a method 
	    named "ModifyClientM()" passing the updated client object as a parameter. The method probably updates the client's 
	    information in the database. If the user cancels, it shows an information dialog saying that the modification was canceled.
	    Finally, it switches to the "clients" scene.
	  * @param event
	  * @throws IOException
	  */
	public void ModifyClient(ActionEvent event) throws IOException  {
			int isEmpty =0 ; 
		 	String  fullName="";
		 	 if(FullNameM.getText().isEmpty() ) {
		 		isEmpty=1 ;
			 }else {
				 fullName = FullNameM.getText();
	
			 }
		 	String  cin="";
		 	 if(CINM.getText().isEmpty() ) {
		 		isEmpty=1 ;
			 }else {
				 cin = CINM.getText();
	
			 }
		 	 String sexe = sexeMaleM.getText();
		 	 if(sexeFemaleM.isSelected()) {
		 		sexe = sexeFemaleM.getText();
		 	 }
		 	String  nationality="";
		 	 if(NationalityM.getText().isEmpty() ) {
		 		isEmpty=1 ;
			 }else {
				 nationality = NationalityM.getText();
	
			 }
		 	String  phone="";
		 	 if(PhoneM.getText().isEmpty() || isPhoneValid(PhoneM.getText())) {
		 		isEmpty=1 ;
			 }else {
				 phone = PhoneM.getText();
	
			 }
		 	String  email="";
		 	 if(EmailM.getText().isEmpty()  || isEmailValid(EmailM.getText())==false ) {
		 		isEmpty=1 ;
				 }else {
					 email = EmailM.getText();
		
				 }
		 	if(isEmpty==1) {
				emptyFields();
			}
			else {
		 	 
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
	 * This code is a  method for switching to an "add new client" screen. The method takes an ActionEvent as a parameter, 
	   The method calls the translateAdd method .
	 * @param event
	 * @throws IOException
	 */
	public void switchToAddNewClient(ActionEvent event) throws IOException {
		 
		  translateAdd(event);
		 
		 }
	/**
	 * This Java code appears to be a method for switching to a "modify client" screen and displaying
	   the details of the selected client in the table. The method takes an ActionEvent as a parameter, 
	   which is likely triggered by a button press or some other user interaction. The method begins by 
	   initializing a variable "clientSelected" to the client selected in the tableClients object. 
	   If the clientSelected variable is null (i.e. no client is selected), an alert is displayed telling 
	   the user to select a client. If clientSelected is not null, the method sets the text of several 
	   text fields to the properties of the selected client object. The method also calls translateModify
	   method with the event parameter. 
	 * @param event
	 * @throws IOException
	 */
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
		  
		  
		  if(clientSelected.getSexe().equals("Male")) {
			  sexeMaleM.setSelected(true);
		  }else {
			  sexeFemaleM.setSelected(true);
 		  }
		  idCM.setText(Integer.toString(clientSelected.getID()));
		  translateModify(event);
		 
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
		 * This code is similar is used to switch to a different scene within a JavaFX application. The method is called "switchToClient"
		   and it takes in a single parameter, an "ActionEvent" object, which represents an event that occurs when a user interacts with 
		   the application. The method starts by loading the FXML file "clients.fxml" using the FXMLLoader class. It then gets a reference 
		   to the current stage and sets the scene to the newly loaded FXML file. Finally, it shows the stage and resets some variables 
		   postionDashboardSlider, postionAddSlider, postionModifySlider.
		 * @param event
		 * @throws IOException
		 */
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
  private TextField searchbycin;
  
  /**
   * This code is a method for searching for a client by their CIN (Client Identification Number) 
     and displaying the results in a table. The method takes an ActionEvent as a parameter, which is likely triggered 
     by a button press or some other user interaction. The method begins by initializing a variable "CIN" to the value 
     entered in the "searchbycin" text field. If the CIN variable is empty, an alert is displayed telling the user to 
     provide a client's CIN. If CIN is not empty, the clients object's SearchClient method is called with the CIN variable
     as a parameter. If the search returns no results, another alert is displayed saying "Client not found." If there are results,
     the tableClients object's items are set to the results of the search, and the table columns are set to display specific 
     properties of the client objects. Finally, the switchToClients method is called with the event parameter.
   * @param event
   * @throws IOException
   */
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
  
  
  /**
   * This method, emptyFields(), creates an alert dialog box that displays a message to the user.
     The message informs the user to verify their inputs, specifically that text fields should be full 
     and that they should be in the correct format. 
   */
  public void emptyFields(){
  	Alert alertINFO = new Alert(AlertType.INFORMATION);
  	alertINFO.setTitle("Information Dialog");
  	alertINFO.setHeaderText("");
  	alertINFO.setContentText("Verify your inputs:\n \tText fields should be full \n \tText fields should be full respect their formats \n \tEmail format is not valid \n \tPhone number format is not valid ");
  	alertINFO.showAndWait();
  }
  /**
   * This is a Java method that validates an email address. The email address is passed as a 
     string parameter to the method. The method uses a regular expression pattern to check if
     the email address is valid.
   * @param email
   * @return
   */
  public static boolean isEmailValid(String email) {
      Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
      Matcher m = p.matcher(email.toUpperCase());
      return m.matches();
  }
  /**
   * This is a Java method that validates a phone number. The phone number is passed as a string parameter to the method.
     The method uses a regular expression pattern to check if the phone number matches the pattern "xxx-xxx-xxxx" where 
     'x' is a digit. The regular expression is compiled into a Pattern object using the Pattern.compile() method, 
      and a Matcher object is created by passing the phone number string to the matcher() method. The method then compares 
      the phone number with the regular expression pattern using the matches() method of the Matcher class. 
      If the phone number matches the pattern, the method returns true, otherwise it returns false.
   * @param phoneNumber
   * @return
   */
  public static boolean isPhoneValid(String phoneNumber) {
	 
	  Pattern pattern = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
	  Matcher matcher = pattern.matcher(phoneNumber);
	  boolean isvalid =true ;
	  if (!matcher.matches()) {
		  isvalid =false ;
	  } 
	  
	  return isvalid ;
  }

}
