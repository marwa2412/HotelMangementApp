package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class activities {
	
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleStringProperty type = new SimpleStringProperty();
	private SimpleStringProperty date = new SimpleStringProperty();
	private SimpleIntegerProperty time = new SimpleIntegerProperty();
	private SimpleIntegerProperty persons = new SimpleIntegerProperty();
	private SimpleIntegerProperty price = new SimpleIntegerProperty();
	private SimpleStringProperty spot = new SimpleStringProperty();
	private SimpleIntegerProperty reserv = new SimpleIntegerProperty();
	private static ObservableList<activities> Activities;

	
		/**
		 * This code is defining a constructor for a class called "activities." The constructor initializes
		   the class's attributes, including "ID," "type," "date," "time," "persons," "spot," "price," and "reserv."
		   The constructor uses the "set" method to set the values of these attributes based on the parameters passed 
		   in to the constructor. The constructor also sets the ID attribute to 0.
		 * @param type
		 * @param date
		 * @param time
		 * @param persons
		 * @param spot
		 * @param price
		 * @param res
		 */
	public activities(String type, String date, int time, int persons, String spot,int price,int res) {
		super();
		this.ID.set(0);
		this.type.set(type);
		this.date.set(date);
		this.time.set(time);
		this.persons.set(persons);
		this.spot.set(spot);
		this.price.set(price);
		this.reserv.set(res);
	}
	/**
	 * This code is also defining a constructor for the "activities" class, but this constructor has an additional parameter,
	   "id." This constructor initializes the class's attributes, including "ID," "type," "date," "time," "persons," "spot," "price," 
	   and "reserv." However, this constructor sets the "ID" attribute to the value of the "id" parameter passed in, 
	   rather than always setting it to 0 as in the previous constructor. This constructor is used when you already
	   know the id of the activity.
	 * @param id
	 * @param type
	 * @param date
	 * @param time
	 * @param persons
	 * @param spot
	 * @param price
	 * @param res
	 */
	public activities(int id,String type, String date, int time, int persons, String spot,int price,int res) {
		super();
		this.ID.set(id);
		this.type.set(type);
		this.date.set(date);
		this.time.set(time);
		this.persons.set(persons);
		this.spot.set(spot);
		this.price.set(price);
		this.reserv.set(res);

	}
	/**
	 * getter of ID
	 * @return
	 */
	public int getID() {
		return ID.get();
	}
	/**
	 * setter of ID
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID.set(ID);
	}
	public int getReserv() {
		return reserv.get();
	}
	public void setReserv(int r) {
		this.reserv.set(r);
	}
	public String getType() {
		return type.get();
	}
	public void setType(String type) {
		this.type.set(type);
	}
	public String getDate() {
		return date.get();
	}
	public void setDate(String date) {
		this.date.set(date);
	}
	public int getTime() {
		return time.get();
	}
	public void setTime(int time) {
		this.time.set(time);
	}
	public int getPersons() {
		return persons.get();
	}
	public void setPersons(int persons) {
		this.persons.set(persons);
	}
	
	public String getSpot() {
		return spot.get();
	}
	public void setSpot(String spot) {
		this.spot.set(spot);
	}
	public int getPrice() {
		return price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	
	 /**
	  * This is a  method that saves a new activity object to a database. The method takes in an
	    "activityObj" object as an input parameter. The method first creates an SQL query that inserts
	    data into the "activities" table. Then, it creates a prepared statement using the connection
	    to the database and sets the various parameters of the activity object to the corresponding 
	    fields in the prepared statement. Finally, it executes the update and closes the connection 
	    and the prepared statement. If an exception occurs, the catch block will print out 
        "Error in connection" and the stack trace of the exception.
	  * @param activityObj
	  */
	 public static void saveNewActivityM(activities activityObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO activities (typeActivity,dateActivity,timeActivity,spot,persons,price,reserved) " + " VALUES (?,?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, activityObj.getType());
			    preparedStmt.setString (2, activityObj.getDate());
			    preparedStmt.setInt (3, activityObj.getTime());
			    preparedStmt.setString (4, activityObj.getSpot());
			    preparedStmt.setInt (5, activityObj.getPersons());
			    preparedStmt.setInt (6, activityObj.getPrice());
			    preparedStmt.setInt (7, activityObj.getReserv());
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
	  *This code is a  method that updates an activity in a database. The method takes in an object of type "activities" 
	  as a parameter. It then creates a SQL query that updates the specified activity's information in the database. 
	  The query uses a prepared statement to set values for the different attributes of the activity, such as type, date, 
	  time, spot, persons, price, and reserved. The method then executes the update query, closes the database connection 
	  and prepared statement, and prints a message indicating that the update was successful. If there is an error in the connection,
	  it will catch the exception, print a message and print the stack trace of the error.
	  * @param activityObj
	  */
	 
	 public static void ModifyActivityM(activities activityObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE activities SET typeActivity=?, dateActivity=?, timeActivity=?, spot=?, persons=?, price=?,reserved=? WHERE idActivity=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, activityObj.getType());
			    preparedStmt.setString (2, activityObj.getDate());
			    preparedStmt.setInt (3, activityObj.getTime());
			    preparedStmt.setString (4, activityObj.getSpot());
			    preparedStmt.setInt (5, activityObj.getPersons());
			    preparedStmt.setInt (6, activityObj.getPrice());
			    preparedStmt.setInt (7, activityObj.getReserv());
			    preparedStmt.setInt (8, activityObj.getID());
			   
			 
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
	  * This code is a  method that searches for activities in a database. The method takes in a string parameter
	    "DATE" representing the date of the activity that the user wants to search for. It then creates a SQL query 
	    that selects all the activities from the database where the date of the activity is the same as the date passed
	    as a parameter. The query uses a prepared statement to set the value of the date to search for. The method then
	    executes the query and retrieves the result set. It creates an observableArrayList of activities, iterates through
	    the result set, creating an object of type "activities" for each row in the result set and adding it to the observableArrayList.
	    Then it returns the list of activities. If there is an error in the connection, it will catch the exception, 
	    print a message and print the stack trace of the error.
	  * @param DATE
	  */
	 public static void SearchActivity(String DATE) {
		 try {	 
			
			 String query3 = "SELECT * FROM activities WHERE dateActivity=?";
	 			PreparedStatement preparedStmt3 = hotelModel.connectionToDB().prepareStatement(query3);
	 			preparedStmt3.setString (1, DATE);
	 		 	ResultSet rs3 = preparedStmt3.executeQuery();
	 		 	Activities = FXCollections.observableArrayList();
	 		 	
	 		 	while(rs3.next()) {
	 		 		 int ID=rs3.getInt("idActivity");
		 			  String type=rs3.getString("typeActivity");
		 			  String date=rs3.getString("dateActivity");
		 			  int time=rs3.getInt("timeActivity");
		 			  int persons=rs3.getInt("persons");
		 			  String spot=rs3.getString("spot");
		 			  int price=rs3.getInt("price");
		 			 int res=rs3.getInt("reserved");
				 	activities activityObj = new activities(ID,type,  date,  time,  persons,  spot, price,res);
				 	Activities.add(activityObj);	

	 			}
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /**
	  * This code is a Java method that returns an ObservableList of activities.
	    The method is named "getActivitiesSearched" and it does not take any parameters.
	    The method simply returns the ObservableList of activities that was created and populated 
	    in the previous method "SearchActivity" . it's used to return the searched activities 
	    from the database to the user interface.
	  * @return
	  */
	 public static ObservableList<activities> getActivitiesSearched(){
		 return Activities;
	 }
}
