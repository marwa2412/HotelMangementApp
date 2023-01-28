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
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	
	    	System.out.println(model.getBookingActivitiesList().get(0).getActivitytype());
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
		 statues.add("Out of date");
		 
		 StatutUpdate.setItems(statues);


		}
	

	
	 
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
	 public void saveNewBookingActivity(ActionEvent event) throws IOException {
		 String activityList;
		 if((String) activityAdate.getValue()==null ) {
			 activityList="0";
		 }else {
			 activityList =  (String) activityAdate.getValue();

		 }
		 String activityList2;
		 if((String) activityAtype.getValue()==null ) {
			 activityList2="0";
		 }else {
			 activityList2 =  (String) activityAtype.getValue();

		 }
		 String clientList;
		 if((String) clientAname.getValue()==null ) {
			 clientList="0";
		 }else {
			 clientList =  (String) clientAname.getValue();

		 }
		 String clientList2;
		 if((String) clientACIN.getValue()==null ) {
			 clientList2="0";
		 }else {
			 clientList2 =  (String) clientACIN.getValue();

		 }
		 
		 String statusN="confirmed";
		
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			String now =dateFormat.format(date);
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
	 
	 @FXML
	public  void ModifyBookingActivity(ActionEvent event) throws IOException {
		 String activityList;
		 if((String) activityAdate.getValue()==null ) {
			 activityList="0";
		 }else {
			 activityList =  (String) activityAdate.getValue();

		 }
		 String activityList2;
		 if((String) activityAtype.getValue()==null ) {
			 activityList2="0";
		 }else {
			 activityList2 =  (String) activityAtype.getValue();

		 }
		 String clientList;
		 if((String) clientAname.getValue()==null ) {
			 clientList="0";
		 }else {
			 clientList =  (String) clientAname.getValue();

		 }
		 String clientList2;
		 if((String) clientACIN.getValue()==null ) {
			 clientList2="0";
		 }else {
			 clientList2 =  (String) clientACIN.getValue();

		 }
		 int id ;
	 	   	if(idBookingActivityM.getText().isEmpty() || !(isInt(idBookingActivityM.getText()))) {
	 	   		id=0;
	 		 }else {
	 			 id = Integer.parseInt(idBookingActivityM.getText());
	 		 }
	 	   	
	 	   	String statusM =  bookingActivityelected.getStatus();
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String now =dateFormat.format(date);
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
	 
	 
	 public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();

		 }
	 public void switchToAddNewBookingActivity(ActionEvent event) throws IOException {
		  translateAdd(event);
		 }
	 
	 private static bookingActivities bookingActivityelected ;
	 
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
	//switch to the list of activities already booked
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
    public void searchBookingAct(ActionEvent event) throws IOException {

  	  String act=  activityTypeDate.getText();
  	  if(act=="") {
  		    Alert alertNull = new Alert(AlertType.INFORMATION);
  			alertNull.setTitle("Information Dialog");
  			alertNull.setHeaderText("");
  			alertNull.setContentText("Provide activity's tyoe");
  			alertNull.showAndWait();
  			switchToBookingActivity(event);

  			
  	  }else {
  		    //bookingActivities.SearchAct(act);
  		    
  			
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

}
