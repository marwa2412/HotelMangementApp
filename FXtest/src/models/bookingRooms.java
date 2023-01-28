package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class bookingRooms {
	private SimpleStringProperty client = new SimpleStringProperty();
	private SimpleIntegerProperty room = new SimpleIntegerProperty();
	private SimpleIntegerProperty numBook = new SimpleIntegerProperty();

	private SimpleStringProperty checkInDate = new SimpleStringProperty();
	private SimpleStringProperty checkOutDate = new SimpleStringProperty();
	private SimpleIntegerProperty checkTime = new SimpleIntegerProperty();
	private SimpleIntegerProperty extraAdult = new SimpleIntegerProperty();
	private SimpleIntegerProperty extraChild = new SimpleIntegerProperty();
	private SimpleIntegerProperty price = new SimpleIntegerProperty();
	private SimpleStringProperty status = new SimpleStringProperty();
	private SimpleStringProperty needs = new SimpleStringProperty();
	private SimpleStringProperty dateBooking = new SimpleStringProperty();

	private static ObservableList<bookingRooms> BookingRoom;
/**
 * This is a constructor for a class called "bookingRooms". It initializes all the class variables such 
  as client, room, checkInDate, checkOutDate, checkTime, extraAdult, extraChild, price, status, now, needs.
   The super() call invokes the constructor of the parent class. numBook is set to zero .
 * @param client
 * @param room
 * @param checkInDate
 * @param checkOutDate
 * @param checkTime
 * @param extraAdult
 * @param extraChild
 * @param price
 * @param status
 * @param now
 * @param needs
 */
	public bookingRooms(String client, int room, String checkInDate, String checkOutDate,
			int checkTime, int extraAdult, int extraChild, int price, String status, String now, String needs) {
		super();
		this.numBook.set(0);
		this.room.set(room);
		this.checkInDate.set(checkInDate);
		this.checkOutDate.set(checkOutDate);
		this.checkTime.set(checkTime);
		this.extraAdult.set(extraAdult);
		this.extraChild.set(extraChild);
		this.price.set(price);
		this.status.set(status);
		this.dateBooking.set(now);
		this.needs.set(needs);
		this.client.set(client);
	}
	/**
	 * This is a constructor for a class called "bookingRooms". It initializes all the class variables such 
	  as client, room, checkInDate, checkOutDate, checkTime, extraAdult, extraChild, price, status, now, needs.
	   The super() call invokes the constructor of the parent class.
	 * @param client
	 * @param room
	 * @param checkInDate
	 * @param checkOutDate
	 * @param checkTime
	 * @param extraAdult
	 * @param extraChild
	 * @param price
	 * @param status
	 * @param now
	 * @param needs
	 */
	public bookingRooms(int id ,String client, int room, String checkInDate, String checkOutDate,
			int checkTime, int extraAdult, int extraChild, int price, String status, String now, String needs) {
		super();
		this.numBook.set(id);
		this.room.set(room);
		this.checkInDate.set(checkInDate);
		this.checkOutDate.set(checkOutDate);
		this.checkTime.set(checkTime);
		this.extraAdult.set(extraAdult);
		this.extraChild.set(extraChild);
		this.price.set(price);
		this.status.set(status);
		this.dateBooking.set(now);
		this.needs.set(needs);
		this.client.set(client);
	}
	
	public int getNumBook() {
		return numBook.get();
	}
	public void setNumBook(int numBook) {
		this.numBook.set(numBook);
	}
	public String getClient() {
		return client.get();
	}
	public void setClient(String client) {
		this.client.set(client);
	}
	/**
	 * getter of room's number
	 * @return
	 */
	public int getRoom() {
		return room.get();
	}
	/**
	 * setter of room's number
	 * @param room
	 */
	public void setRoom(int room) {
		this.room.set(room);
	}
	public String getCheckInDate() {
		return checkInDate.get();
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate.set(checkInDate);
	}
	public String getCheckOutDate() {
		return checkOutDate.get();
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate.set(checkOutDate);
	}
	public int getCheckTime() {
		return checkTime.get();
	}
	public void setCheckTime(int checkTime) {
		this.checkTime.set(checkTime);
	}
	public int getExtraAdult() {
		return extraAdult.get();
	}
	public void setExtraAdult(int extraAdult) {
		this.extraAdult.set(extraAdult);
	}
	public int getExtraChild() {
		return extraChild.get();
	}
	public void setExtraChild(int extraChild) {
		this.extraChild.set(extraChild);
	}
	public int getPrice() {
		return price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	public String getDateBooking() {
		return dateBooking.get();
	}
	public void setDateBooking(String dateBooking) {
		this.dateBooking.set(dateBooking);
	}
	
	public String getNeeds() {
		return needs.get();
	}
	public void setNeeds(String needs) {
		this.needs.set(needs);
	}
	
	
	/**
	 * This is a method called "saveNewBookingM" that takes in a "bookingRooms" object as a parameter. 
	  It is used to save a new booking to a database. The method starts by creating a SQL query 
	  "INSERT INTO bookingrooms(...) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)" which is used to insert new 
	  data into the "bookingrooms" table of the database. The method then creates a prepared statement 
	  using the connectionToDB method, which is used to establish a connection to the database, and the SQL query.
	  It then sets the values for the prepared statement using the setter methods of the "bookingRoomObj" 
	  object passed as a parameter. The values being set are price, status, checkInDate, checkTime, dateBooking,
	  checkOutDate, client, extraAdult, extraChild, room, needs.
	  After setting the values, the method execute the prepared statement using the executeUpdate() method and closes
	  the connection to the database. If there is an exception, it will print an error message and the stack trace of the error.
	 * @param bookingRoomObj
	 */
	 public static void saveNewBookingM(bookingRooms bookingRoomObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO bookingrooms(room,clientId,dateBooking,checkInTime,checkOutTime,extraChild,extraAdult,Status,price,clientNeeds,checkInDate,checkOutDate) " + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setInt (9, bookingRoomObj.getPrice());
			    preparedStmt.setString (8, bookingRoomObj.getStatus());
			    preparedStmt.setString (11,bookingRoomObj.getCheckInDate());
			    preparedStmt.setInt (4, bookingRoomObj.getCheckTime());
			    preparedStmt.setString (3, bookingRoomObj.getDateBooking()); //the date of the current day
			    preparedStmt.setString (12, bookingRoomObj.getCheckOutDate());
			    preparedStmt.setInt (5,  bookingRoomObj.getCheckTime());
			    preparedStmt.setString (2, bookingRoomObj.getClient());
			    preparedStmt.setInt (7, bookingRoomObj.getExtraAdult());
			    preparedStmt.setInt (6, bookingRoomObj.getExtraChild());
			    preparedStmt.setInt (1, bookingRoomObj.getRoom());
			    preparedStmt.setString (10, bookingRoomObj.getNeeds());
			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	 /**
	  * This code is a  method that updates a booking in a database table called "bookingrooms".
	    It takes an object of the "bookingRooms" class as a parameter. The method uses a prepared statement 
	    to update the rows in the table with the values from the object passed as a parameter.
	     The method first creates a query string to update the table, and then uses the set methods 
	     of the prepared statement to set the values of the columns in the table to the corresponding values
	     from the object. The method then executes the update and closes the connection to the database. 
	     If there is an error in the connection, the method will print out an error message and the stack trace.
	  * @param bookingRoomObj
	  */
	 public static void ModifyBookingM(bookingRooms bookingRoomObj) {
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE  bookingrooms SET "
			    		+"room =?,clientId =?, dateBooking = ? ,checkInTime = ?,checkOutTime = ? ,extraChild =? ,extraAdult = ?,Status =? ,price = ?,clientNeeds = ?,checkInDate = ?,checkOutDate = ? WHERE idBooking=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setInt (1, bookingRoomObj.getRoom());
			    preparedStmt.setString (2, bookingRoomObj.getClient());
			    preparedStmt.setString (3, bookingRoomObj.getDateBooking()); //the date of the current day
			    preparedStmt.setInt(4, bookingRoomObj.getCheckTime());
			    preparedStmt.setInt (5,  bookingRoomObj.getCheckTime());
			    preparedStmt.setInt (6, bookingRoomObj.getExtraChild());
			    preparedStmt.setInt (7, bookingRoomObj.getExtraAdult());
			    preparedStmt.setString (8, bookingRoomObj.getStatus());
			    preparedStmt.setInt (9, bookingRoomObj.getPrice());
			    preparedStmt.setString (10, bookingRoomObj.getNeeds());
			    preparedStmt.setString (11,bookingRoomObj.getCheckInDate());
			    preparedStmt.setString (12, bookingRoomObj.getCheckOutDate());	
			    preparedStmt.setInt (13, bookingRoomObj.getNumBook());

			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	/**
	 * This is a method  that updates the status of a booking in a database table called "bookingrooms".
	   The method takes in an object of type "bookingRooms" as a parameter. The method uses a PreparedStatement 
	   to update the "Status" column in the "bookingrooms" table where the "idBooking" column matches the "numBook"
	   attribute of the "bookingRoomObj" object. The method also prints out messages to indicate if the update was
	   successful or if there was an error in the connection.
	 * @param bookingRoomObj
	 */
	 public static void changeStatutBookingM(bookingRooms bookingRoomObj) {
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE  bookingrooms SET "
			    		+"Status =?  WHERE idBooking=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, bookingRoomObj.getStatus());
			    preparedStmt.setInt (2, bookingRoomObj.getNumBook());

			  

			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	 /**
	  * This is a method that searches for a booking in a database table called "bookingrooms" based on the client ID.
	    The method takes in a String "name" as a parameter. The method uses a PreparedStatement to query the "bookingrooms" 
	    table and select all columns where the "clientId" column matches the value of the "name" parameter passed in.
	    The method then creates an observableArrayList called "BookingRoom" and iterates through the ResultSet of 
	    the query adding each row to the observableArrayList as a new "bookingRooms" object with the values from each column.
	    The method also prints out a message if there is an error in the connection.
	  * @param name
	  */
	 public static void SearchBookingRoom(String name) {
		 try {	 
			 String query3 = "SELECT * FROM bookingrooms WHERE clientId=?";
	 			PreparedStatement preparedStmt4 = hotelModel.connectionToDB().prepareStatement(query3);
	 			preparedStmt4.setString (1, name);
	 		 	ResultSet rs4 = preparedStmt4.executeQuery();
	 		 	BookingRoom = FXCollections.observableArrayList();
	 		 	
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
	 		
					 bookingRooms BookingRoom1 = new bookingRooms(ID,IDclient,room,datein,dateout,outTime,adult,child,price,statut,now,needs) ;
	 				BookingRoom.add(BookingRoom1);	

	 			}
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /**
	  * This is a method that returns an ObservableList of "bookingRooms" objects.
	  * @return
	  */
	 public static ObservableList<bookingRooms> getBookingRoomSearched(){
		 return BookingRoom;
	 }
	
}
