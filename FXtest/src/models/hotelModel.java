package models;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.*;
import javafx.collections.ObservableList;



public class hotelModel {
	
	
	 static int numActivities;
	 static int numCheckOUT;
	 static int numCheckIN;
	 ObservableList<rooms> Rooms;
	 ObservableList<bookingActivities> BookingActivities;
	 ObservableList<bookingRooms> BookingRooms ;
	 ObservableList<clients> Clients;
	 ObservableList<activities> Activities ;
	 ObservableList<String> Clients3;
	 ObservableList<String> Clients2;
	 ObservableList<String> Activities2 ;
	 ObservableList<String> Activities3 ;
	 ObservableList<String> Rooms2 ;

	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNow = new Date();
		String now =dateFormat.format(dateNow);

	 
	 /**
	  * This is a Java method that establishes a connection to a MySQL database named "hotel" on the localhost 
	    using the JDBC driver. The method takes no parameters and returns a Connection object. The method uses 
	    the user name "root" and password "3064" to connect to the database.
	  * @return
	  * @throws SQLException
	  */
	public static Connection connectionToDB() throws SQLException {
		 String userDB="root";
		 String passwordDB="3064";
		 Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=false",userDB,passwordDB);//Establishing Connection
		 return cnx;
	 }
	/**
	 * This code is a  constructor for the hotelModel class. It appears to be initializing several instance variables 
	  (Rooms, Clients, BookingRooms, and BookingActivities) by querying a database using JDBC and creating objects based 
	   on the results of those queries. Specifically, it is creating PreparedStatements for each query, executing those 
	   statements and iterating through the ResultSets to create objects and add them to the corresponding observableArrayList. 
	   The queries are selecting all data from tables named "rooms", "clients", "bookingrooms" and "bookingactivities" respectively. 
	   The objects being created are rooms, clients, bookingRooms, and bookingActivities.
	 */
	 public hotelModel() {		
		 try { 	
 			String query2 = "SELECT * FROM rooms";
 			PreparedStatement preparedStmt2 = connectionToDB().prepareStatement(query2);
 		 	ResultSet rs2 = preparedStmt2.executeQuery();
 		 	Rooms = FXCollections.observableArrayList();
 			while(rs2.next()) {
 				int ID=rs2.getInt("idRoom");
 				 int numRoom=rs2.getInt("numRoom");
 				 int numFloor=rs2.getInt("floor");
 				 String typeRoom=rs2.getString("typeRoom");
 				 String viewRoom=rs2.getString("view");
 				 String statueRoom=rs2.getString("statue");
 				 boolean cleaningRoom=rs2.getBoolean("cleaning");
 				 String techProbs=rs2.getString("techProb");
 				 int priceRoom=rs2.getInt("price");
 				 rooms Roomobj = new rooms(ID,numRoom, numFloor, typeRoom, viewRoom,statueRoom,cleaningRoom, techProbs, priceRoom) ;
 				 Rooms.add(Roomobj);

 			}
 			String query3 = "SELECT * FROM clients";
 			PreparedStatement preparedStmt3 = connectionToDB().prepareStatement(query3);
 		 	ResultSet rs3 = preparedStmt3.executeQuery();
 		 	Clients = FXCollections.observableArrayList();
 		 	while(rs3.next()) {
 				int ID=rs3.getInt("idClient");
 				 String cin=rs3.getString("cin");
 				 String fullName=rs3.getString("fullName");
 				 String sexe=rs3.getString("sexe");
 				 String nationality=rs3.getString("nationality");
 				 String email=rs3.getString("email");
 				 String phone=rs3.getString("phone");
 				 clients Client = new clients(ID,fullName,  cin,  sexe,  nationality,  phone,email) ;
 				Clients.add(Client);	

 			}
 			String query4 = "SELECT * FROM bookingrooms";
 			PreparedStatement preparedStmt4 = connectionToDB().prepareStatement(query4);
 		 	ResultSet rs4 = preparedStmt4.executeQuery();
 		 	BookingRooms = FXCollections.observableArrayList();
 			while(rs4.next()) {
 				int ID=rs4.getInt("idBooking");
				 int room=rs4.getInt("room");
				 String datein=rs4.getString("checkInDate");
				 String dateout=rs4.getString("checkOutDate");
				 int outTime=rs4.getInt("checkOutTime");
				 int adult=rs4.getInt("extraAdult");
				 int child=rs4.getInt("extraChild");
				 int price=rs4.getInt("price");
				 String statut=rs4.getString("status");
				 String now=rs4.getString("dateBooking");
				 String needs=rs4.getString("clientNeeds");
				 String IDclient=rs4.getString("clientId");
 				bookingRooms bookingRoomObj = new bookingRooms(ID,IDclient,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
 				BookingRooms.add(bookingRoomObj);	
 			}
 			String query5 = "SELECT * FROM bookingactivities";
 			PreparedStatement preparedStmt5 = connectionToDB().prepareStatement(query5);
 		    ResultSet rs5 = preparedStmt5.executeQuery();
 		    
 		   BookingActivities = FXCollections.observableArrayList();

 		  while(rs5.next()) {
 			  int ID=rs5.getInt("idActivity");
 			  String activityType= rs5.getString("ActivityType");
 			 String activityDate =rs5.getString("ActivityDate");
 			  String clientCIN = rs5.getString("clientCIN");	
 			 String clientName= rs5.getString("clientName");
 			  String now=rs5.getString("dateBookingActivity");
 			 String status= rs5.getString("status");
 				 bookingActivities bookingActivitiesObj = new bookingActivities(ID,activityType,activityDate, clientCIN,clientName,now,status);
 				BookingActivities.add(bookingActivitiesObj);


 		   }
 		    String query6 = "SELECT * FROM activities";
			PreparedStatement preparedStmt6 = connectionToDB().prepareStatement(query6);
		 	ResultSet rs6 = preparedStmt6.executeQuery();
		 	Activities = FXCollections.observableArrayList();
		 	while(rs6.next()) {
		 		  int ID=rs6.getInt("idActivity");
	 			  String type=rs6.getString("typeActivity");
	 			  String date=rs6.getString("dateActivity");
	 			  int time=rs6.getInt("timeActivity");
	 			  int persons=rs6.getInt("persons");
	 			  String spot=rs6.getString("spot");
	 			  int price=rs6.getInt("price");
	 			 int res=rs6.getInt("reserved");
			 	activities activityObj = new activities(ID,type,  date,  time,  persons,  spot, price,res);
			 	
			 	Activities.add(activityObj);


		 	}	
 		 String query7 = "SELECT dateActivity , typeActivity FROM activities WHERE reserved < persons";
			PreparedStatement preparedStmt7 = connectionToDB().prepareStatement(query7);
		    ResultSet rs7 = preparedStmt7.executeQuery();   
		    Activities2 = FXCollections.observableArrayList();
		    Activities3 = FXCollections.observableArrayList();
		    while(rs7.next()) {
	 			String activityObj2=rs7.getString("typeActivity");
	 			Activities2.add(activityObj2);
	 			String activityObj3=rs7.getString("dateActivity");
	 			Activities3.add(activityObj3);


	 		   }

		    String query8 = "SELECT cin ,  fullName FROM clients";
				PreparedStatement preparedStmt8 = connectionToDB().prepareStatement(query8);
			    ResultSet rs8 = preparedStmt8.executeQuery();   
			    Clients2 = FXCollections.observableArrayList();
			    Clients3 = FXCollections.observableArrayList();
			    while(rs8.next()) {
		 			  String clientcin=rs8.getString("cin");
		 			 Clients2.add(clientcin);	
		 			 String clientname=rs8.getString("fullName");
	 				Clients3.add(clientname);	


		 		   }
			    String query9 = "SELECT numRoom FROM rooms WHERE statue=?";
				PreparedStatement preparedStmt9 = connectionToDB().prepareStatement(query9);
				preparedStmt9.setString(1, "empty");
			    ResultSet rs9 = preparedStmt9.executeQuery();   
			    Rooms2 = FXCollections.observableArrayList();
			    while(rs9.next()) {
		 			  String room=rs9.getString("numRoom");
		 			Rooms2.add(room);

		 		   }
			    String query10 = "SELECT COUNT(*) AS numCheckIN FROM bookingrooms  WHERE checkInDate = ?  ";
				PreparedStatement preparedStmt10 = connectionToDB().prepareStatement(query10);
				preparedStmt10.setString (1, now);
			    ResultSet rs10 = preparedStmt10.executeQuery();   
			    while(rs10.next()) {
				     numCheckIN=rs10.getInt("numCheckIN");
		 		   }
	  
			    String query11 = "SELECT COUNT(*) AS numCheckOUT FROM bookingrooms  WHERE checkOutDate = ?  ";
				PreparedStatement preparedStmt11 = connectionToDB().prepareStatement(query11);
				preparedStmt11.setString (1, now);
			    ResultSet rs11 = preparedStmt11.executeQuery(); 
			    while(rs11.next()) {
		 		 numCheckOUT=rs11.getInt("numCheckOUT");
			    	}
		 		String query12 = "SELECT COUNT(*) AS numActivities FROM activities  WHERE dateActivity = ?  ";
				PreparedStatement preparedStmt12 = connectionToDB().prepareStatement(query12);
				preparedStmt12.setString (1, now);
			    ResultSet rs12 = preparedStmt12.executeQuery();  
			    while(rs12.next()) {
			    	numActivities=rs12.getInt("numActivities");
				    	}		 		
		 	  }catch(Exception e){
		 	    System.out.println("Error in connection"+ e);

		 	e.printStackTrace();
		 	}
		 
	 }
	 /**
	  * getter of CheckaIn number
	  * @return
	  */
	  public  int getnumCheckIN() {
		  return numCheckIN;
	  }
	  public  int getnumCheckOUT() {
		  return numCheckOUT;
	  }
	  public  int getNumActivities() {
		  return numActivities;
	  }
	  
	 
	 
	 
	 public ObservableList<rooms> getRoomsList(){
		 return Rooms;
	 }
	 /**
	  * he method "getBookingActivitiesList()" is a getter method that returns an ObservableList of type "bookingActivities". 
	  * @return
	  */
	 public ObservableList<bookingActivities> getBookingActivitiesList(){
		 return BookingActivities;
	 }
	 public ObservableList<bookingRooms> getBookingRoomsList(){
	
		 return BookingRooms;
	 }
	 public ObservableList<clients> getClientsList(){
		 return Clients;
	 }
	 public ObservableList<String> getClientsList2(){
		 return Clients2; //CIN
	 }
	 public ObservableList<String> getClientsList3(){
		 return Clients3;//NAME
	 }
	 public ObservableList<activities> getActivitiesList(){
		 return Activities;
	 }
	 public ObservableList<String> getActivities2(){
		 return Activities2; //TYPE
	 }
	 public ObservableList<String> getActivities3(){
		 return Activities3;//DATE
	 }
	 public ObservableList<String> getRooms2(){
		 return Rooms2;
	 }
}
