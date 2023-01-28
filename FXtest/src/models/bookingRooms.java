package models;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.*;

public class bookingRooms {
	private SimpleStringProperty client = new SimpleStringProperty();
	private SimpleStringProperty room = new SimpleStringProperty();
	private SimpleIntegerProperty numBook = new SimpleIntegerProperty();

	private SimpleStringProperty checkInDate = new SimpleStringProperty();
	private SimpleStringProperty checkOutDate = new SimpleStringProperty();
	private SimpleStringProperty checkTime = new SimpleStringProperty();
	private SimpleStringProperty extraAdult = new SimpleStringProperty();
	private SimpleStringProperty extraChild = new SimpleStringProperty();
	private SimpleStringProperty price = new SimpleStringProperty();
	private SimpleStringProperty status = new SimpleStringProperty();
	private SimpleStringProperty needs = new SimpleStringProperty();
	private SimpleStringProperty dateBooking = new SimpleStringProperty();

	
	public bookingRooms(String client, String room, String checkInDate, String checkOutDate,
			String checkTime, String extraAdult, String extraChild, String price, String status, String now, String needs) {
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
	
	public bookingRooms(int id,String client, String room, String checkInDate, String checkOutDate,
			String checkTime, String extraAdult, String extraChild, String price, String status, String now, String needs) {
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
	public String getRoom() {
		return room.get();
	}
	public void setRoom(String room) {
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
	public String getCheckTime() {
		return checkTime.get();
	}
	public void setCheckTime(String checkTime) {
		this.checkTime.set(checkTime);
	}
	public String getExtraAdult() {
		return extraAdult.get();
	}
	public void setExtraAdult(String extraAdult) {
		this.extraAdult.set(extraAdult);
	}
	public String getExtraChild() {
		return extraChild.get();
	}
	public void setExtraChild(String extraChild) {
		this.extraChild.set(extraChild);
	}
	public String getPrice() {
		return price.get();
	}
	public void setPrice(String price) {
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
			    preparedStmt.setString (9, bookingRoomObj.getPrice());
			    preparedStmt.setString (8, bookingRoomObj.getStatus());
			    preparedStmt.setString (11,bookingRoomObj.getCheckInDate());
			    preparedStmt.setString (4, bookingRoomObj.getCheckTime());
			    preparedStmt.setString (3, bookingRoomObj.getDateBooking()); //the date of the current day
			    preparedStmt.setString (12, bookingRoomObj.getCheckOutDate());
			    preparedStmt.setString (5,  bookingRoomObj.getCheckTime());
			    preparedStmt.setString (2, bookingRoomObj.getClient());
			    preparedStmt.setString (7, bookingRoomObj.getExtraAdult());
			    preparedStmt.setString (6, bookingRoomObj.getExtraChild());
			    preparedStmt.setString (1, bookingRoomObj.getRoom());
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
			    String query1 = "UPDATE  bookingrooms SET"
			    		+ "room =?,clientId =?,"
			    		+ "dateBooking = ? ,checkInTime = ?,checkOutTime = ?"
			    		+ ",extraChild =? ,extraAdult = ?,Status =? ,"
			    		+ "price = ?,clientNeeds = ?,checkInDate = ?,checkOutDate = ? WHERE idBooking=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, bookingRoomObj.getRoom());
			    preparedStmt.setString (2, bookingRoomObj.getClient());
			    preparedStmt.setString (3, bookingRoomObj.getDateBooking()); //the date of the current day
			    preparedStmt.setString (4, bookingRoomObj.getCheckTime());
			    preparedStmt.setString (5,  bookingRoomObj.getCheckTime());
			    preparedStmt.setString (6, bookingRoomObj.getExtraChild());
			    preparedStmt.setString (7, bookingRoomObj.getExtraAdult());
			    preparedStmt.setString (8, bookingRoomObj.getStatus());
			    preparedStmt.setString (9, bookingRoomObj.getPrice());
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
}
