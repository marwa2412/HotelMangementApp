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
	// Functions for switching between interfaces

	// help and documentation interface
	public void switchToHelpAndDoc(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/screen.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
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
		 }
	//switch to the list of activities already booked
	public void switchToBookingActivity(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../application/bookingActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }

	// new booking for an activity
	public void switchToAddNewBookingActivity(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/addBookingActivity.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }


	//clients
	//switch to the list of clients
	public void switchToClient(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/clients.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }


	//rooms
	//Rooms
	public void switchToRooms(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/rooms.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 
		 }
	//switch to the list of rooms already booked
	public void switchToBookingRoom(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/bookingRoom.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	//new booking for a room
	/*public void switchToAddNewBooking(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/addBookingRoom.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }*/
	//modify a previous booking for a room
	public void switchToModifyBookingRoom(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/modifyBookingRoom.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }


	//dashboard

	//switch to bashboard
	public void switchToDashboard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("../application/dashboard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
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

	    

	    public void createChart() {
	    	 xAxis.setLabel("Days");
		        yAxis.setLabel("Clients");
		        barChart.setTitle("Total Client per Day");
		        barChart.setLegendVisible(false);

	        try {
	            String query = "SELECT DAY(checkInDate), COUNT(clientId) FROM bookingrooms GROUP BY checkInDate ORDER BY checkInDate DESC LIMIT 5";
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


	    public void createpieChart() {

	    	  pieChart.setTitle("Total Client per Day");
	          pieChart.setLegendVisible(false);


	        try {
	            String query = "SELECT DAY(checkInDate), COUNT(clientId) FROM bookingrooms GROUP BY checkInDate ORDER BY checkInDate DESC LIMIT 3";
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
	    	
	
	}

