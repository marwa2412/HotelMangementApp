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
	public int getRoom() {
		return room.get();
	}
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
	 public static ObservableList<bookingRooms> getBookingRoomSearched(){
		 return BookingRoom;
	 }
	
}
