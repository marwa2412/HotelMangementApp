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

	/**
	 * This constructor is initializing the fields of an object of the class "bookingActivities". 
	  The constructor takes six parameters as inputs, activitytypeC, activityDateC, cinC, nameC, now and status.
      It initializes the object's ID field with a value of 0, activitytype field with activitytypeC, cin field with cinC,
      now field with now, name field with nameC, activityDate field with activityDateC and status field with status.
      The super() method call is used to call the parent class's constructor.
	 * @param activitytypeC
	 * @param activityDateC
	 * @param cinC
	 * @param nameC
	 * @param now
	 * @param status
	 */
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

	/**
	 * This is a constructor for a class called "bookingActivities". It initializes the class's 
	   properties with the values passed to the constructor as arguments. The properties being 
	   initialized include "ID", "activitytype", "cin", "now", "name", "activityDate", and "status". 
	   The "super()" method call is used to call the parent class's constructor.
	 * @param activitytypeC
	 * @param activityDateC
	 * @param cinC
	 * @param nameC
	 * @param now
	 * @param status
	 */
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
	
	public int getID() {
		return ID.get();
	}
	public void setID(int ID) {
		this.ID.set(ID);
	}
	/**
	 * setter of Activity's type
	 * @param type
	 */
	public void setActivitytype(String type) {
		this.activitytype.set(type);
	}
	/**
	 * getter of Activity's type
	 * @return
	 */
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
	
	
	
/**
 * This is a static method called "saveNewBookingActivityM" that takes an object of the class "bookingActivities" as a parameter.
   The method is used to save new booking activity data to a database.
   The method starts by creating a query to insert data into a table called "bookingactivities" with the following columns
   "ActivityType", "clientCIN", "dateBookingActivity", "ActivityDate", "clientName", "status".
   Then it creates a PreparedStatement object and sets the values of the columns in the query with the values of the properties
   of the "bookingActivities" object passed as a parameter.
   It then executes the query and closes the database connection and the statement.
   It also calls a statutIncrement method passing the object of bookingActivities as a parameter
   If there is an error in connecting to the database, it will catch the exception and print the error message.
 * @param bookingActivities
 */
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
			    statutIncrement(bookingActivities);
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /**
	  * This code is a  method that modifies a record in a database table called "bookingactivities"
	    based on the data provided in a "bookingActivities" object. The method first creates an SQL query
	    that updates the specified columns in the table where the idActivity matches the id of the "bookingActivities" object.
        Then, it uses a PreparedStatement to execute the query and updates the record in the table. If there is any error in the connection, 
        it will print the error message and the stack trace. Finally, it closes the connection and statement.
	  * @param bookingActivities
	  */
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
	 /**
	  * This code is a  method that searches for records in the "bookingactivities" table based on the activity
	    type provided as a parameter. The method first creates an SQL query that selects all columns from the table where
	    the "ActivityType" column matches the provided activity type. Then, it uses a PreparedStatement to execute the query 
	    and retrieves the result set. It creates an observable array list called "activities", it then iterates over the result 
	    set and for each record it creates a new "bookingActivities" object with the data from the result set, and adds the object
	    to the "activities" list. If there is any error in the connection, it will print the error message and the stack trace. 
	    Finally, it doesn't close the connection and statement.
	  * @param actType
	  */
	 public static void SearchAct(String actType) {
		 try {	 
			 String query3 = "SELECT * FROM bookingactivities WHERE ActivityType=?";
	 			PreparedStatement preparedStmt3 = hotelModel.connectionToDB().prepareStatement(query3);
	 			preparedStmt3.setString (1, actType);
	 		 	ResultSet rs5 = preparedStmt3.executeQuery();
	 		 	activities = FXCollections.observableArrayList();
	 		 	
	 		 	while(rs5.next()) {
	 		 		 int ID=rs5.getInt("idActivity");
	 	 			  String activityType= rs5.getString("ActivityType");
	 	 			 String activityDate =rs5.getString("ActivityDate");
	 	 			  String clientCIN = rs5.getString("clientCIN");	
	 	 			 String clientName= rs5.getString("clientName");
	 	 			  String now=rs5.getString("dateBookingActivity");
	 	 			 String status= rs5.getString("status");
	 				 bookingActivities activ = new bookingActivities(ID,activityType,activityDate, clientCIN,clientName, now,status);
	 				activities.add(activ);	

	 			}
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /**
	  * This code is a  method that returns an "ObservableList" of "bookingActivities" objects
	  * @return
	  */
	 public static ObservableList<bookingActivities> getBookingAct(){
		 return activities;
	 }
	 /**
	  * This code updates the status of a booking activity in the database using a PreparedStatement. 
	    The method takes in a bookingActivities object as a parameter and uses the idActivity attribute 
	    to target the specific row in the database to update. The query updates the status column in 
	    the bookingactivities table with the status attribute of the bookingActivities object.
        When the status of the booking is set as "canceled" the statutDecrement method is called.
        It is also important to note that the connection to the database is closed after the query
        is executed and the prepared statement is closed. The method also includes a try-catch block
        to handle any exceptions that may occur during the execution of the code.
	  * @param bookingActivities
	  */
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
			    if(bookingActivities.getStatus()=="canceled") {
			    	statutDecrement(bookingActivities);
			    }
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	
	 /**
	  * This method appears to increment the 'reserved' column of the 'activities' table in the database,
	    where the 'typeActivity' and 'dateActivity' columns match the values of the 'activitytype' and 'activityDate' 
	    properties of the passed in 'bookingActivities' object, respectively. This is likely used to keep track of 
	    how many spots for a certain activity type and date have been booked/reserved. The method also closes 
	    the database connection and the prepared statement after the update has been executed.
	  * @param bookingActivities
	  */
	 public static void statutIncrement(bookingActivities  bookingActivities) {
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE  activities SET "
			    		+"reserved = reserved+1  WHERE typeActivity=? AND dateActivity=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString(1, bookingActivities.getActivitytype());
			    preparedStmt.setString(2, bookingActivities.getActivityDate());
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
	  * this code is  is a method for decreasing the number of reserved spots for a specific activity based 
	    on the type of activity and the date of the activity. It starts by creating a prepared statement using
	    a SQL query that updates the "reserved" column in the "activities" table, where the "typeActivity" and 
	    "dateActivity" match the values passed in the "bookingActivities" object. The method then sets the values 
	    for the prepared statement's placeholders, executes the update, and closes the database connection and 
	    the prepared statement. It also prints a message to indicate that the update was successful.
	    If an exception is caught, it will print "Error in connection".
	  * @param bookingActivities
	  */
	 public static void statutDecrement(bookingActivities  bookingActivities) {
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE  activities SET "
			    		+"reserved = reserved-1  WHERE typeActivity=? AND dateActivity=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString(1, bookingActivities.getActivitytype());
			    preparedStmt.setString(2, bookingActivities.getActivityDate());
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
