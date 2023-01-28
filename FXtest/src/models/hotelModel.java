package models;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javafx.collections.*;
import javafx.collections.ObservableList;



public class hotelModel {
	
	
	
	 ObservableList<rooms> Rooms;
	 ObservableList<bookingActivities> BookingActivities;
	 ObservableList<bookingRooms> BookingRooms ;
	 ObservableList<clients> Clients;
	 ObservableList<activities> Activities ;
	 
	 
	 //connection to DB parametres
	public static Connection connectionToDB() throws SQLException {
		 String userDB="root";
		 String passwordDB="3064";
		 Connection cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=false",userDB,passwordDB);//Establishing Connection
		 return cnx;
	 }
	
	 
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
 				 System.out.print("query2");

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
				 System.out.print("query3");

 			}
 			String query4 = "SELECT * FROM bookingrooms";
 			PreparedStatement preparedStmt4 = connectionToDB().prepareStatement(query4);
 		 	ResultSet rs4 = preparedStmt4.executeQuery();
 		 	BookingRooms = FXCollections.observableArrayList();
 		 	System.out.print("\n Start \n");
 			while(rs4.next()) {
 				int ID=rs4.getInt("idBooking");
				 String room=rs4.getString("room");
				 String datein=rs4.getString("checkInDate");
				 String dateout=rs4.getString("checkOutDate");
				 String outTime=rs4.getString("checkOutTime");
				 String adult=rs4.getString("extraAdult");
				 String child=rs4.getString("extraChild");
				 String price=rs4.getString("price");
				 String statut=rs4.getString("status");
				 String now=rs4.getString("dateBooking");
				 String needs=rs4.getString("clientNeeds");
				 String IDclient=rs4.getString("clientId");
 				bookingRooms bookingRoomObj = new bookingRooms(ID,IDclient,room,datein,dateout,outTime,adult,child,price,statut,now,needs);
 				BookingRooms.add(bookingRoomObj);	
				 System.out.print("query4");

 	 			


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
	 			  System.out.println(ID);
	 			  System.out.println(type);
	 			  System.out.println(date);
	 			  System.out.println(time);
	 			  System.out.println(persons);
	 			  System.out.println(spot);
	 			  System.out.println(price);

			 	activities activityObj = new activities(ID,type,  date,  time,  persons,  spot, price);
	 			  System.out.println("passe");
	 			  System.out.println(activityObj);


			 	Activities.add(activityObj);
				 System.out.print(Activities);

				 System.out.print("query6");

		 	}
		 	String query5 = "SELECT * FROM bookingactivities";
 			PreparedStatement preparedStmt5 = connectionToDB().prepareStatement(query5);
 		    ResultSet rs5 = preparedStmt5.executeQuery();
 		    
 		   BookingActivities = FXCollections.observableArrayList();

 		  while(rs5.next()) {
 			  int ID=rs5.getInt("idActivity");
 			  String activityList=rs5.getString("Activity");
 			  String clientList=rs5.getString("clientId");
 			  String now=rs5.getString("dateBookingActivity");
 				 bookingActivities bookingActivitiesObj = new bookingActivities(ID,activityList, clientList,now);
 				BookingActivities.add(bookingActivitiesObj);
				 System.out.print("query5");

 		   }
		 	
		 	  }catch(Exception e){
		 	    System.out.println("Error in connection"+ e);

		 	e.printStackTrace();
		 	}
	 }
	 
	 
	 
	 
	 public ObservableList<rooms> getRoomsList(){
		 return Rooms;
	 }
	 public ObservableList<bookingActivities> getBookingActivitiesList(){
		 return BookingActivities;
	 }
	 public ObservableList<bookingRooms> getBookingRoomsList(){
	
		 return BookingRooms;
	 }
	 public ObservableList<clients> getClientsList(){
		 return Clients;
	 }
	 public ObservableList<activities> getActivitiesList(){
		 return Activities;
	 }
}
