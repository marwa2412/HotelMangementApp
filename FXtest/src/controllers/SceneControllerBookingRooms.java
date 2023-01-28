package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.bookingRooms;
import models.hotelModel;

public class SceneControllerBookingRooms implements Initializable{
	private hotelModel model= new hotelModel();
	
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 

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
	    private TableColumn<bookingRooms, String> extraAdultM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, String> extraChildM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, String> priceM= new TableColumn<bookingRooms, String>();
	    
	    @FXML
	    private TableColumn<bookingRooms, String> timeM= new TableColumn<bookingRooms, String>();

	    @FXML
	    private TableColumn<bookingRooms, String> roomM= new TableColumn<bookingRooms, String>();

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
			
		}
	    
	    public void switchToDashboard(ActionEvent event) throws IOException {
			  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			 }
	 
	    public void switchToAddNewBooking(ActionEvent event) throws IOException {
		  	  root = FXMLLoader.load(getClass().getResource("../application/addBookingRoom.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	 }
		    public void switchToBookingRoom(ActionEvent event) throws IOException {
		  	  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	 }
		    public void switchToModifyBookingRoom(ActionEvent event) throws IOException {
		  	  root = FXMLLoader.load(getClass().getResource("../application/modifyBookingRoom.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	 }
	    
	 // function to add new booking for rooms
	    @FXML
	    private TextField Price;

	    @FXML
	    private ComboBox<?> Statut;

	    @FXML
	    private DatePicker checkInDate;

	    @FXML
	    private TextField checkInTime;

	    @FXML
	    private DatePicker checkOutDate;

	    @FXML
	    private TextField checkOutTime;

	    @FXML
	    private ChoiceBox<?> clientList;

	    @FXML
	    private TextArea clientNeeds;

	    @FXML
	    private TextField extraAdult;

	    @FXML
	    private TextField extraChild;

	    @FXML
	    private ChoiceBox<?> roomList;
	    
	    @FXML
	    private TextField idBooking;

	    @FXML
	    void saveNewBooking(ActionEvent event) throws IOException {

	   	 System.out.println("Logged succ to function");
	   	 String price = Price.getText();
	   	 String statut = (String) Statut.getValue();
	   	 String datein= checkInDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;
	   	 String dateout= checkOutDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;
	   	 String outTime = checkOutTime.getText();
	   	 String client = (String) clientList.getValue();
	   	 String needs = clientNeeds.getText();
	   	 String adult = extraAdult.getText();
	   	 String child = extraChild.getText();
	   	 String room = (String) roomList.getValue();
	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   		Date date = new Date();
   		String now =dateFormat.format(date);
        bookingRooms bookingRoomObj = new bookingRooms(client,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
        bookingRooms.saveNewBookingM(bookingRoomObj);
        switchToBookingRoom(event);

	    }
	    
	    // modify  booking Room
	    @FXML
	    private TextField adultR;

	    @FXML
	    private DatePicker checkInR;

	    @FXML
	    private DatePicker checkOutR;

	    @FXML
	    private TextField childR;

	    @FXML
	    private ChoiceBox<?> clientR;

	    @FXML
	    private TextField priceR;

	    @FXML
	    private ChoiceBox<?> roomR;

	    @FXML
	    private TextField timeInR;

	    @FXML
	    private TextField timeOutR;
	    
	    @FXML
	    private ComboBox<?> statutR;

	    @FXML
	    private TextArea needsR;
	    
	    



	    @FXML
	    void saveModifyBookingRoom(ActionEvent event) {
	   	 String adult = adultR.getText();
	   	 String statut= Statut.getPromptText();
	   	 String inDate= checkInR.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;
	   	 String outDate= checkOutR.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;
	   	 String child = childR.getText();
	   	 String client = (String) clientR.getValue();
	   	 String room = (String) roomR.getValue();
	   	 String price = priceR.getText();
	   	 String timeIn = timeInR.getText();
	   	 String needs = needsR.getText();
	   	 
	   	 
	   	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	   	Date date = new Date();
	   	String now =dateFormat.format(date);

	       bookingRooms bookingRoomObj = new bookingRooms(client,room,inDate,outDate,timeIn,adult,child,price,statut,now,needs);
	        
	       bookingRooms.ModifyBookingM(bookingRoomObj);


	    }
	    
	    
}


