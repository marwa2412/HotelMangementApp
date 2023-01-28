package controllers;

import java.io.IOException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.clients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.hotelModel;

public class SceneControllerDashboard implements Initializable{
	 private hotelModel model= new hotelModel();
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 @FXML
	 private TextField activitiesID;

	 @FXML
	 private TextField checkInID;

	 @FXML
	 private TextField checkOutID;
	 
	 @FXML
	 private TextField currentDate;
	 
	 @FXML
	 private TextField currentTime;

	 @FXML
	 private PieChart piechart;
	
	 
	 /**
	     * This is a method called "switchToDashboard" that is triggered by an ActionEvent. It is used to navigate
	       to a different view or page within the application, specifically the "dashboard.fxml" page. The method uses
	       the FXMLLoader class to load the FXML file for the dashboard page and sets it as the root node.
	       It then gets the stage and scene of the current window and sets the scene to the new root node. 
	       The stage is then shown, this will display the dashboard page to the user. 
	     * @param event
	     * @throws IOException
	     */
	public void switchToHelpAndDoc(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/helpANDdoc.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 /**
	  * This code appears to switch the current scene of the application to the "activity.fxml" scene when the method 
	    is called. It does this by loading the FXML file, setting the scene to the loaded root node, and then showing 
	    the stage.
	  * @param event
	  * @throws IOException
	  */
	public void switchToActivity(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../application/activity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	/**
	  * This code is creating a new scene by loading the FXML file "bookingActivity.fxml" and setting it as the current 
	    scene on the stage. The stage and scene are obtained by getting the source of the ActionEvent, which is likely 
	    a button press. 
	  * @param event
	  * @throws IOException
	  */
	public void switchToBookingActivity(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../application/bookingActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }

	
	 /**
	 * This code is similar is used to switch to a different scene within a JavaFX application. The method is called "switchToClient"
	   and it takes in a single parameter, an "ActionEvent" object, which represents an event that occurs when a user interacts with 
	   the application. The method starts by loading the FXML file "clients.fxml" using the FXMLLoader class. It then gets a reference 
	   to the current stage and sets the scene to the newly loaded FXML file. Finally, it shows the stage .
	 * @param event
	 * @throws IOException
	 */
	public void switchToClient(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }

	  /**
		 * This method is used to switch the current scene to the "rooms.fxml" scene. It does this by first loading the 
		   "rooms.fxml" file using the FXMLLoader class and then setting the loaded root element as the root node of a new Scene.
		   The stage variable is obtained by getting the source of the ActionEvent and using that to get the Scene's window, 
		   which is cast to a Stage. The new Scene is then set as the current scene of this stage, and the stage is shown to the user.
		 * @param event
		 * @throws IOException
		 */
	public void switchToRooms(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/rooms.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 
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
	 * This method is the initialize method of the controller class, which is called when the program starts. 
	   It sets the text of three labels (activitiesID, checkOutID, checkInID) to the values returned by 
	   the methods getNumActivities(), getnumCheckOUT(), and getnumCheckIN() of the model class respectively.
       It then calls three methods createChart(), createpieChart(), and createLineChart() to create the charts. 
       It also sets the text of two labels, currentDate and currentTime, to the current date and time respectively.
       A new clock is created using the Timeline class and is set to update every second and show current time. 
       The clock is set to run indefinitely using the setCycleCount() method and the play() method is called to start the clock.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		activitiesID.setText(Integer.toString(model.getNumActivities()));	
		checkOutID.setText(Integer.toString(model.getnumCheckOUT()));	
		checkInID.setText(Integer.toString(model.getnumCheckIN()));	
		createChart();
		createpieChart();
		createLineChart();
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
	    private BarChart<String, Number> barChart;
	    
	    @FXML
	    private CategoryAxis xAxis;
	    
	    @FXML
	    private NumberAxis yAxis;
	    /*
	     * This method creates a bar chart that represents the total number of clients per day.
	       The x-axis of the chart is labeled "Days" and the y-axis is labeled "Clients". The chart is given the title
	       "Total Client per Day" and the legend is not visible.
		   The method uses a SQL query to retrieve data from a database table called "bookingrooms", and groups the data
		   by the "checkInDate" column and orders by descending. The query limit the result to 5 rows.
		   The results are then used to create an ObservableList of XYChart.Data and a series is created with this data 
		   and added to the barChart.
		   The method also includes a catch block to handle any exceptions that may occur while executing
		   the query or creating the chart data.
	     */
	    public void createChart() {
		        yAxis.setLabel("Clients number");
		        barChart.setTitle("Booked Activities");
		        barChart.setLegendVisible(false);
	        try {
	            String query = "SELECT ActivityType, COUNT(*) FROM bookingactivities GROUP BY ActivityType ";
	            PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query);
	            ResultSet rs = preparedStmt.executeQuery();
	            ObservableList<XYChart.Data<String, Number>> chartData = FXCollections.observableArrayList();
	            while (rs.next()) {
	                chartData.add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
	            }
	            XYChart.Series<String, Number> series = new XYChart.Series<>("Total Client", chartData);
	            barChart.getData().add(series);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @FXML
	    private PieChart pieChart;
	    /**
	     * This method creates a pie chart that represents the state of rooms in a hotel. The chart is given the title "Rooms State". 
	       The method uses a SQL query to retrieve data from a database table called "rooms", and groups the data by the "statue" column.
	       The results are then used to create an ObservableList of PieChart.Data, which is then set as the data for the pieChart. 
	       The method also includes a catch block to handle any exceptions that may occur while executing the query or creating the chart data.
	     */
	    public void createpieChart() {
	    	  pieChart.setTitle("Rooms State");
	        try {
	            String query = "SELECT statue, COUNT(*) FROM rooms GROUP BY statue";
	            PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query);
	            ResultSet rs = preparedStmt.executeQuery();
	            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
	            while (rs.next()) {
	                chartData.add(new PieChart.Data(rs.getString(1), rs.getDouble(2)));
	            }
	            pieChart.setData(chartData);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } 
	    
	    @FXML
	    private LineChart<String, Number> lineChart;
	    /**
	     * This code creates a line chart that shows the total income per month. It sets the chart's title to "Incomes per Month" 
	       and turns off the legend. It then uses a prepared SQL statement to query the "bookingrooms" table in the database and 
	       retrieve the month and total price of each booking. The results are stored in an ObservableList, which is then used 
	       to create a new XYChart.Series. This series is then added to the lineChart's data to display the data on the chart. 
	       An exception is caught and printed in case of any errors during the execution.
	     */
	    public void createLineChart() {
	    	lineChart.setTitle("Incomes per Month");
	    	lineChart.setLegendVisible(false);
	    	try {
	            String query = "SELECT MONTH(checkInDate), SUM(price) FROM bookingrooms GROUP BY checkInDate ORDER BY checkInDate ";
	            PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query);
	            ResultSet rs = preparedStmt.executeQuery();
	            ObservableList<XYChart.Data<String, Number>> lineData = FXCollections.observableArrayList();
	            while (rs.next()) {
	            	lineData.add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
	            }
	            XYChart.Series<String, Number> series = new XYChart.Series<>("Incomes", lineData);
	            lineChart.getData().add(series);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    static int postionAbout=0 ;


	    @FXML
	    private AnchorPane today1;

	    @FXML
	    private AnchorPane today2;

	    @FXML
	    private AnchorPane today3;

	    
	    @FXML
	    private AnchorPane sliderAbout ;
	    /**
	     * This code handles a mouse event and triggers a transition animation for a node called "sliderAbout." 
	       When the mouse event is first triggered, the transition moves the "sliderAbout" node downward by 400 pixels, 
	       sets its opacity to 1, and sets a flag "postionAbout" to 1. When the mouse event is triggered again,
	       the transition moves the "sliderAbout" node upward by 400 pixels, sets the "postionAbout" flag back to 0, 
	       and sets the opacity of several other nodes (lineChart, pieChart, barChart, today1, today3, today2) to 1.
	     * @param event
	     */
	    public void translateAbout(MouseEvent event) {
	    	lineChart.setOpacity(0.43);
	    	pieChart.setOpacity(0.43);
	    	barChart.setOpacity(0.43);
	    	today1.setOpacity(0.43);
	    	today3.setOpacity(0.43);
	    	today2.setOpacity(0.43);
	        Duration duration = Duration.millis(1000);
	        TranslateTransition transition = new TranslateTransition();
	        transition.setNode(sliderAbout);
	        transition.setDuration(duration);
	        if(postionAbout==0){	
	        	transition.setByY(400);
	        	sliderAbout.setOpacity(1);
	        	postionAbout=1;
	        }else {
	        	transition.setByY(-400);
	        	postionAbout=0;
	        	lineChart.setOpacity(1);
		    	pieChart.setOpacity(1);
		    	barChart.setOpacity(1);
		    	today1.setOpacity(1);
		    	today3.setOpacity(1);
		    	today2.setOpacity(1);
	        }
	        transition.play();
	        
		 }
	}

