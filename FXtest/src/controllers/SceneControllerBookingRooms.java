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
	    
	    public void switchToDashboard(ActionEvent event) throws IOException {
			  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  postionDashboardSlider=0 ;

			 }
	 
	    public void switchToAddNewBooking(ActionEvent event) throws IOException {
		  	  root = FXMLLoader.load(getClass().getResource("../application/addBookingRoom.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	postionDashboardSlider=0 ;

		  	 }
		    public void switchToBookingRoom(ActionEvent event) throws IOException {
		  	  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	postionDashboardSlider=0 ;

		  	 }
		    
		    

		   
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
		   
		    public static ObservableList<String> getObjectSelectd(){
				 return objectSelected;
			 }
		    
		    
		    @FXML
		    private AnchorPane statutSlider;
		    private static  bookingRooms bookingRoomSelectedStatus ;
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


