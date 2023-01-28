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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.bookingActivities;
import models.bookingRooms;
import models.clients;
import models.hotelModel;
import models.rooms;

public class SceneControllerBookingRooms implements Initializable{
	private hotelModel model= new hotelModel();
	
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 static ObservableList<String> objectSelected ;
	 	
	 @FXML
	    private ComboBox<String> StatutUpdate;
	 
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;
	 
	 	@FXML
	    private TextField clientName;
	
	    @FXML
	    private TableView<bookingRooms> bookingTable = new TableView<bookingRooms>();
	    
		@FXML
	    private TableColumn<bookingRooms, Integer> numB = new TableColumn<bookingRooms, Integer>();

	    @FXML
	    private TableColumn<bookingRooms, String> checkInM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, String> checkOutM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, String> clientM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, String> dateM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, Integer> extraAdultM= new TableColumn<bookingRooms, Integer>();

	    @FXML
	    private TableColumn<bookingRooms, Integer> extraChildM= new TableColumn<bookingRooms, Integer>();

	    @FXML
	    private TableColumn<bookingRooms, Integer> priceM= new TableColumn<bookingRooms, Integer>();
	    
	    @FXML
	    private TableColumn<bookingRooms, Integer> timeM= new TableColumn<bookingRooms, Integer>();

	    @FXML
	    private TableColumn<bookingRooms, Integer> roomM= new TableColumn<bookingRooms, Integer>();

	    @FXML
	    private TableColumn<bookingRooms, String> statusM= new TableColumn<bookingRooms, String>();
	    
	    @FXML
	    private TableColumn<bookingRooms, String> observation= new TableColumn<bookingRooms, String>();
	    
	    
	    /**
	     * This code is initializing a JavaFX application for managing bookings. It sets the items of a table named "bookingTable" 
	       with the data from the "model" object's "getBookingRoomsList()" method. It sets the cell value factories for each column 
	       in the table with properties from the booking objects in the list. It also sets the date and time to the current date and time,
	       and creates a clock that updates the time every second. It also creates an observable list of possible statuses for a booking 
	       and sets it as the items for a ChoiceBox named "StatutUpdate".
	     */
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	
	    	bookingTable.setItems(model.getBookingRoomsList());
	    	numB.setCellValueFactory(new PropertyValueFactory<>("numBook"));
	    	checkInM.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
	    	checkOutM.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
			clientM.setCellValueFactory(new PropertyValueFactory<>("client"));
			dateM.setCellValueFactory(new PropertyValueFactory<>("dateBooking"));
			timeM.setCellValueFactory(new PropertyValueFactory<>("checkTime"));
			extraAdultM.setCellValueFactory(new PropertyValueFactory<>("extraAdult"));
			extraChildM.setCellValueFactory(new PropertyValueFactory<>("extraChild"));
			priceM.setCellValueFactory(new PropertyValueFactory<>("price"));
			roomM.setCellValueFactory(new PropertyValueFactory<>("room"));
			statusM.setCellValueFactory(new PropertyValueFactory<>("status"));
			observation.setCellValueFactory(new PropertyValueFactory<>("needs"));
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
			 statues.add("Out of date");		 
			 StatutUpdate.setItems(statues);
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
		 /**
		  * This is a  method called "switchToAddNewBooking" that is triggered by an ActionEvent. It is used to navigate 
		    to a different view or page within the application, specifically the "addBookingRoom.fxml" page. The method 
		    uses the FXMLLoader class to load the FXML file for the add new booking page and sets it as the root node. 
		    It then gets the stage and scene of the current window and sets the scene to the new root node. The stage is then shown,
		     this will display the add new booking page to the user. The method also set the value of postionDashboardSlider to 0.
		  * @param event
		  * @throws IOException
		  */
	    public void switchToAddNewBooking(ActionEvent event) throws IOException {
		  	  root = FXMLLoader.load(getClass().getResource("../application/addBookingRoom.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	postionDashboardSlider=0 ;
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
		   /**
		    * The method first creates an observableArrayList called "objectSelected" and then gets the selected item from the bookingTable,
		      which is of type "bookingRooms". If no item is selected, it shows an alert to the user telling them to select a booking and then
		      switches back to the booking room page.
			  If an item is selected, it gets the values of various properties of the selected booking room object such as price, status, 
			  checkInDate, checkTime, client, needs, extraAdult, extraChild, numBook and room and stores them in the "objectSelected" list. 
			  Then it uses the FXMLLoader class to load the FXML file for the modify booking room page and sets it as the root node.
			  It then gets the stage and scene of the current window and sets the scene to the new root node. The stage is then shown, 
			  this will display the modify booking room page to the user.
		    * @param event
		    * @throws IOException
		    */
		    public  void switchToModifyBookingRoom(ActionEvent event) throws IOException {
		    	 objectSelected	=FXCollections.observableArrayList();
		    	 bookingRooms bookingRoomSelected = bookingTable.getSelectionModel().getSelectedItem();
		    	 if(bookingRoomSelected==null ) {
		 			Alert alertNull = new Alert(AlertType.INFORMATION);
		 			alertNull.setTitle("Information Dialog");
		 			alertNull.setHeaderText("");
		 			alertNull.setContentText("Select a booking");
		 			alertNull.showAndWait();
		 			switchToBookingRoom(event);
		 		}else {
		    	 String stockage ;	 
			  	 stockage=Integer.toString(bookingRoomSelected.getPrice());
				 objectSelected.add(stockage);			
				 stockage=bookingRoomSelected.getStatus();
				 objectSelected.add(stockage);
				 stockage=bookingRoomSelected.getCheckInDate();
				 objectSelected.add(stockage);
				 stockage=Integer.toString(bookingRoomSelected.getCheckTime());
				 objectSelected.add(stockage);
				 stockage = bookingRoomSelected.getCheckOutDate();
				 objectSelected.add(stockage);
				 stockage=Integer.toString(bookingRoomSelected.getCheckTime());
				 objectSelected.add(stockage);
				 stockage = bookingRoomSelected.getClient();
				 objectSelected.add(stockage);
				 stockage = bookingRoomSelected.getNeeds();
				 objectSelected.add(stockage);
				 stockage = Integer.toString(bookingRoomSelected.getExtraAdult());
				 objectSelected.add(stockage);
				 stockage = Integer.toString(bookingRoomSelected.getExtraChild());
				 objectSelected.add(stockage);
				 stockage=Integer.toString(bookingRoomSelected.getNumBook());
				 objectSelected.add(stockage);
				 stockage = Integer.toString(bookingRoomSelected.getRoom());
				 objectSelected.add(stockage);
		    	 root = FXMLLoader.load(getClass().getResource("../application/modifyBookingRoom.fxml"));
			  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  	  scene = new Scene(root);
			  	  stage.setScene(scene);
			  	  stage.show();
		    }
		    }
		   /**
		    * This code is a static method called "getObjectSelected" which returns an ObservableList of Strings.
		    * @return
		    */
		    public static ObservableList<String> getObjectSelectd(){
				 return objectSelected;
			 }    
		    
		    @FXML
		    private AnchorPane statutSlider;
		    
		    private static  bookingRooms bookingRoomSelectedStatus ;
		    /**
		     * This code is a method called "uploadStatut" which is triggered by an ActionEvent. It first gets 
		       the selected item from a table (bookingTable) and assigns it to a variable (bookingRoomSelectedStatus).
		       It then checks if the selected item is null, and if it is, it shows an alert saying to select a booking 
		       and switches to another window (switchToBookingRoom). If the selected item is not null, it sets the visibility
		       of a slider (statutSlider) to true and sets the prompt text of an input field (StatutUpdate) to the current 
		       status of the selected booking.
		     * @param event
		     * @throws IOException
		     */
		    public void uploadStatut(ActionEvent event) throws IOException {
		    	bookingRoomSelectedStatus = bookingTable.getSelectionModel().getSelectedItem();
		    	 if(bookingRoomSelectedStatus==null ) {
		 			Alert alertNull = new Alert(AlertType.INFORMATION);
		 			alertNull.setTitle("Information Dialog");
		 			alertNull.setHeaderText("");
		 			alertNull.setContentText("Select a booking");
		 			alertNull.showAndWait();
		 			switchToBookingRoom(event);
		 		}else {
			    String  currentStatus=bookingRoomSelectedStatus.getStatus();
		    	statutSlider.setVisible(true);
		    	StatutUpdate.setPromptText(currentStatus);
		 		}	
		    }
		    /**
		     * This code defines a function changeStatutBookingRoom which is called when a button is pressed to change the status 
		       of a booking room. The function first retrieves the selected booking room's details such as the booking ID, price,
		       dates, client name, and needs. It also gets the new status selected by the user from a drop-down menu.
               Then, the function displays a confirmation dialog asking the user if they are sure they want to save the changes.
               If the user confirms, the function creates a bookingRooms object with the updated details and calls the changeStatutBookingM
               method to update the status in the database. If the new status is "cancelled" or "Out of date", the function also calls the 
               setRoomempty method to mark the room as available. Finally, the function displays an alert message to confirm the modification 
               was applied. If the user does not confirm, the function displays an alert message indicating that the modification was cancelled.
		     * @param event
		     * @throws IOException
		     */
		    public  void changeStatutBookingRoom(ActionEvent event) throws IOException {	    	
		    	 int id = bookingRoomSelectedStatus.getNumBook();
		    	 int price =bookingRoomSelectedStatus.getPrice();
				 String datein =bookingRoomSelectedStatus.getCheckInDate();
				 int outTime =bookingRoomSelectedStatus.getCheckTime();
				 String dateout = bookingRoomSelectedStatus.getCheckOutDate();
				 String now=bookingRoomSelectedStatus.getDateBooking();
				 String client = bookingRoomSelectedStatus.getClient();
				 String needs = bookingRoomSelectedStatus.getNeeds();
				 int adult = bookingRoomSelectedStatus.getExtraAdult();
				 int child = bookingRoomSelectedStatus.getExtraChild();
				 int  room = bookingRoomSelectedStatus.getRoom();    	 
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
				    	 bookingRooms bookingRoomObj = new bookingRooms(id,client,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
					     bookingRooms.changeStatutBookingM(bookingRoomObj);	
					     if(statut=="canceled" ||statut=="Out of date" ) {
					    	 
					    	 rooms.setRoomempty(room);
					     }
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
		   * This code snippet is a method called "SearchBookingRoom" that is called when a button is pressed. 
		     The method takes the text from a text field called "clientName" and assigns it to the variable "name".
			 If the variable "name" is empty, an alert dialog box is displayed with the message "Provide client's name"
			 and the user is switched to the "Booking Room" page. If the variable "name" is not empty, the method "SearchBookingRoom" 
			 is called on an object of the class "bookingRooms" with the "name" variable as the parameter.
             If the size of the list returned by the "SearchBookingRoom" method is 0, meaning no clients were found,
             an alert dialog box is displayed with the message "Client not found" and the user is switched to the "Booking Room" page.
             If clients were found, the table "bookingTable" is populated with the list of clients returned by the "SearchBookingRoom" 
             method and the values of certain properties of the table are set using the "PropertyValueFactory" class.
		   * @param event
		   * @throws IOException
		   */
		  @FXML
		    void SearchBookingRoom(ActionEvent event) throws IOException {
			  String name=  clientName.getText();
			  if(name=="") {
				    Alert alertNull = new Alert(AlertType.INFORMATION);
					alertNull.setTitle("Information Dialog");
					alertNull.setHeaderText("");
					alertNull.setContentText("Provide client's name");
					alertNull.showAndWait();
					switchToBookingRoom(event);					
			  }else {
				    bookingRooms.SearchBookingRoom(name);		
		 		 	if(bookingRooms.getBookingRoomSearched().size()==0) {
		 		 	  Alert alertNull = new Alert(AlertType.INFORMATION);
		 				alertNull.setTitle("Information Dialog");
		 				alertNull.setHeaderText("");
		 				alertNull.setContentText("Client not found");
		 				alertNull.showAndWait();
		 				switchToBookingRoom(event);
		 		 	}else {
		 		 		bookingTable.setItems(bookingRooms.getBookingRoomSearched());
		 		    	numB.setCellValueFactory(new PropertyValueFactory<>("numBook"));
		 		    	checkInM.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
		 		    	checkOutM.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
		 				clientM.setCellValueFactory(new PropertyValueFactory<>("client"));
		 				dateM.setCellValueFactory(new PropertyValueFactory<>("dateBooking"));
		 				timeM.setCellValueFactory(new PropertyValueFactory<>("checkTime"));
		 				extraAdultM.setCellValueFactory(new PropertyValueFactory<>("extraAdult"));
		 				extraChildM.setCellValueFactory(new PropertyValueFactory<>("extraChild"));
		 				priceM.setCellValueFactory(new PropertyValueFactory<>("price"));
		 				roomM.setCellValueFactory(new PropertyValueFactory<>("room"));
		 				statusM.setCellValueFactory(new PropertyValueFactory<>("status"));
		 				observation.setCellValueFactory(new PropertyValueFactory<>("needs"));
		 		 	} 
			  }
		    }
	    
}


