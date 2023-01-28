package models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class bookingActivities {
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleStringProperty activitytype = new SimpleStringProperty();
	private SimpleStringProperty activityDate = new SimpleStringProperty();
	private SimpleStringProperty cin = new SimpleStringProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty status = new SimpleStringProperty();
	private SimpleStringProperty now = new SimpleStringProperty();
	
	private static ObservableList<bookingActivities> activities;

	
	public bookingActivities(String activitytypeC,String activityDateC, String cinC,String nameC, String now,String status) {
		super();
		this.ID.set(0);
		this.activitytype.set(activitytypeC);
		this.cin.set(cinC);
		this.now.set(now);
		this.name.set(nameC);
		this.activityDate.set(activityDateC);
		this.status.set(status);
	}
	public bookingActivities(int ID,String activitytypeC,String activityDateC, String cinC,String nameC, String now,String status) {
		super();
		this.ID.set(ID);
		this.activitytype.set(activitytypeC);
		this.cin.set(cinC);
		this.now.set(now);
		this.name.set(nameC);
		this.activityDate.set(activityDateC);
		this.status.set(status);

	}
	//ID
	public int getID() {
		return ID.get();
	}
	public void setID(int ID) {
		this.ID.set(ID);
	}
	//TYPE
	public void setActivitytype(String type) {
		this.activitytype.set(type);
	}
	public String getActivitytype() {
		return activitytype.get();
	}
	//DATE
	public String getActivityDate() {
		return activityDate.get();
	}
	public void setActivityDate(String activityDate) {
		this.activityDate.set(activityDate);
	}
	//CIN
	public String getCin() {
		return cin.get();
	}
	public void setCin(String cin) {
		this.cin.set(cin);
	}
	//NAME
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	//STATUS
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	//NOW
	public String getNow() {
		return now.get();
	}
	public void setNow(String now) {
		this.now.set(now);
	}
	
	
	

	 public static void saveNewBookingActivityM(bookingActivities  bookingActivities) {		 
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO bookingactivities(ActivityType,clientCIN,dateBookingActivity,ActivityDate,clientName,status) " + " VALUES (?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
		
			    preparedStmt.setString (1, bookingActivities.getActivitytype());
			    preparedStmt.setString (2, bookingActivities.getCin());
			    preparedStmt.setString (3, bookingActivities.getNow());
			    preparedStmt.setString (4, bookingActivities.getActivityDate());
			    preparedStmt.setString (5, bookingActivities.getName());
			    preparedStmt.setString (6, bookingActivities.getStatus());
			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	 public static void ModifyBookingActivityM(bookingActivities  bookingActivities) {		 
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE bookingactivities SET"
			    		+ " ActivityType = ? , clientCIN = ? , dateBookingActivity = ? , ActivityDate = ?, clientName=?, status=?  WHERE idActivity=? ";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, bookingActivities.getActivitytype());
			    preparedStmt.setString (2, bookingActivities.getCin());
			    preparedStmt.setString (3, bookingActivities.getNow());
			    preparedStmt.setString (4, bookingActivities.getActivityDate());
			    preparedStmt.setString (5, bookingActivities.getName());
			    preparedStmt.setString (6, bookingActivities.getStatus());
			    preparedStmt.setInt (7, bookingActivities.getID());
			    
			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /*public static void SearchAct(String actType) {
		 try {	 
			 String query3 = "SELECT * FROM bookingactivities WHERE ActivityType=?";
	 			PreparedStatement preparedStmt3 = hotelModel.connectionToDB().prepareStatement(query3);
	 			preparedStmt3.setString (1, actType);
	 		 	ResultSet rs5 = preparedStmt3.executeQuery();
	 		 	activities = FXCollections.observableArrayList();
	 		 	
	 		 	while(rs5.next()) {
	 		 		int ID=rs5.getInt("idActivity");
	 	 			  String activityList= rs5.getString("ActivityType");
	 	 			 activityList+= " - ";
	 	 			 activityList+=rs5.getString("ActivityDate");
	 	 			  String clientList = rs5.getString("clientCIN");
	 	 			 clientList+= " - ";
	 	 			 clientList+= rs5.getString("clientName");
	 	 			  String now=rs5.getString("dateBookingActivity");
	 	 			String status=rs5.getString("status");
	 				 bookingActivities activ = new bookingActivities(ID,activitytypeC,activityDateC, cinC,nameC, now,status);
	 				activities.add(activ);	

	 			}
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }*/
	 
	 public static ObservableList<bookingActivities> getBookingAct(){
		 return activities;
	 }
	 
	 public static void changeStatutBookingActivity(bookingActivities  bookingActivities) {
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE  bookingactivities SET "
			    		+"status = ?  WHERE idActivity=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, bookingActivities.getStatus());
			    preparedStmt.setInt (2, bookingActivities.getID());

			  

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
