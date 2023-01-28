package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class rooms {
	private SimpleIntegerProperty idRoom = new SimpleIntegerProperty() ;
	private SimpleIntegerProperty numRoom = new SimpleIntegerProperty() ;
	private SimpleIntegerProperty numFloor = new SimpleIntegerProperty();
	private SimpleStringProperty typeRoom = new SimpleStringProperty();
	private SimpleStringProperty viewRoom = new SimpleStringProperty();
	private SimpleStringProperty statueRoom = new SimpleStringProperty();
	private SimpleBooleanProperty cleaningRoom = new SimpleBooleanProperty();
	private SimpleStringProperty techProbs = new SimpleStringProperty();
	private SimpleIntegerProperty priceRoom = new SimpleIntegerProperty();
	private static ObservableList<rooms> Rooms;
	public rooms(int numRoom, int numFloor, String typeRoom, String viewRoom, String statueRoom, boolean cleaningRoom, String techProbs, int priceRoom) {
        super();
        this.idRoom.set(0);
        this.numRoom.set(numRoom);
        this.numFloor.set(numFloor);
        this.typeRoom.set(typeRoom);
        this.viewRoom.set(viewRoom);
        this.statueRoom.set(statueRoom);
        this.cleaningRoom.set(cleaningRoom);
        this.techProbs.set(techProbs);
        this.priceRoom.set(priceRoom);
    }
	/**
	 * This is a constructor for a class called "rooms". It appears to have several fields, such as ID, numRoom, numFloor,
	   typeRoom, viewRoom, statueRoom, cleaningRoom, techProbs, and priceRoom, which are being set using the set() method.
	 * @param ID
	 * @param numRoom
	 * @param numFloor
	 * @param typeRoom
	 * @param viewRoom
	 * @param statueRoom
	 * @param cleaningRoom
	 * @param techProbs
	 * @param priceRoom
	 */
	public rooms(int ID,int numRoom, int numFloor, String typeRoom, String viewRoom, String statueRoom, boolean cleaningRoom, String techProbs, int priceRoom) {
        super();
    	this.idRoom.set(ID);
        this.numRoom.set(numRoom);
        this.numFloor.set(numFloor);
        this.typeRoom.set(typeRoom);
        this.viewRoom.set(viewRoom);
        this.statueRoom.set(statueRoom);
        this.cleaningRoom.set(cleaningRoom);
        this.techProbs.set(techProbs);
        this.priceRoom.set(priceRoom);
    }
	
	
	public int getIdRoom() {
		return idRoom.get();
	}
	public void setIdRoom(int idRoom) {
		this.idRoom.set(idRoom);
	}
	public int getNumRoom() {
		return numRoom.get();
	}

	public void setNumRoom(int numRoom) {
		this.numRoom.set(numRoom);
	}

	public int getNumFloor() {
		return numFloor.get();
	}

	public void setNumFloor(int numFloor) {
		this.numFloor.set(numFloor);
	}

	public String getTypeRoom() {
		return typeRoom.get();
	}

	public void setTypeRoom(String typeRoom) {
		this.typeRoom.set(typeRoom);;
	}
	/**
	 * getter of view
	 * @return
	 */
	public String getViewRoom() {
		return viewRoom.get();
	}
	/**
	 * setter of view
	 * @param viewRoom
	 */
	public void setViewRoom(String viewRoom) {
		this.viewRoom.set(viewRoom);
	}

	public String getStatueRoom() {
		return statueRoom.get();
	}

	public void setStatueRoom(String statueRoom) {
		this.statueRoom.set(statueRoom);
	}

	public boolean isCleaningRoom() {
		return cleaningRoom.get();
	}

	public void setCleaningRoom(boolean cleaningRoom) {
		this.cleaningRoom.set(cleaningRoom);
	}

	public String getTechProbs() {
		return techProbs.get();
	}

	public void setTechProbs(String techProbs) {
		this.techProbs.set(techProbs);;
	}

	public int getPriceRoom() {
		return priceRoom.get();
	}

	public void setPriceRoom(int priceRoom) {
		this.priceRoom.set(priceRoom);
	}
	
	

	/**
	 * This is a static method called "saveNewRoomM" which takes in a "rooms" object as a parameter.
	  The method appears to be inserting a new row into a table called "rooms" in a database.
      The method uses a JDBC PreparedStatement to insert data into the table. The data being inserted is taken 
      from the fields of the "rooms" object passed as a parameter.
      It appears that the method is trying to connect to the database by calling a method "connectionToDB()" 
      and then creating a prepared statement with the SQL insert query.
      It then sets the values for each column in the table by getting the values from the fields of the "rooms" 
      object using the getter methods.It then execute the update query and close the connection to the database.
      If there is any exception, it prints the stack trace of the error and shows a message "Error in connection" 
	 * @param RoomObj
	 */
	 public static void saveNewRoomM(rooms RoomObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO rooms(numRoom,typeRoom,statue,cleaning,techProb,price,view,floor)" + " VALUES (?,?,?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setInt (1, RoomObj.getNumRoom());			  
			    preparedStmt.setString (2, RoomObj.getTypeRoom());
			    preparedStmt.setString (3, RoomObj.getStatueRoom());
			    preparedStmt.setBoolean (4, RoomObj.isCleaningRoom());
			    preparedStmt.setString (5, RoomObj.getTechProbs());
			    preparedStmt.setInt (6, RoomObj.getPriceRoom());
			    preparedStmt.setString (7, RoomObj.getViewRoom());
			    preparedStmt.setInt (8, RoomObj.getNumFloor());
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
	  * This code is a Java method that updates a record in a "rooms" table in a database. The method takes in a "RoomObj" 
	    object as a parameter. The method first creates an update query, which is stored in the "query1" variable. 
	    The query updates several columns in the "rooms" table, and uses the "idRoom" column as the identifier to determine 
	    which record should be updated. Next, the code creates a prepared statement using the connection to the database
	    and the update query. The prepared statement is then populated with data from the "RoomObj" object. 
	    The statement is then executed and the connection to the database is closed. If any errors occur while connecting 
	    to the database or executing the statement, they are caught and printed to the console.
	  * @param RoomObj
	  */
		 public static void ModifyRoomM(rooms RoomObj) {
			 try {	 
					System.out.println("Logged succ to try");
				    String query1 = "UPDATE rooms SET "
				    		+ "numRoom=?,typeRoom =?,"
				    		+ "statue =?,cleaning =?,techProb =?,"
				    		+ "price =?,view =?,floor =?   WHERE idRoom=?";
				    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
				    preparedStmt.setInt (1, RoomObj.getNumRoom());			  
				    preparedStmt.setString (2, RoomObj.getTypeRoom());
				    preparedStmt.setString (3, RoomObj.getStatueRoom());
				    preparedStmt.setBoolean (4, RoomObj.isCleaningRoom());
				    preparedStmt.setString (5, RoomObj.getTechProbs());
				    preparedStmt.setInt (6, RoomObj.getPriceRoom());
				    preparedStmt.setString (7, RoomObj.getViewRoom());
				    preparedStmt.setInt (8, RoomObj.getNumFloor());
				    preparedStmt.setInt (9, RoomObj.getIdRoom());
	
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
		  * This is a Java method called "SearchRoom" that takes in a single parameter, a string called "RoomNBR". 
		    The method uses a SQL query to search the "rooms" table in the database for a room with a matching room 
		    number to the one passed in as the parameter. If a matching room is found, the method creates a new "rooms" 
		    object with the data from the database and adds it to an observableArrayList called "Rooms". If there is an error 
		    with the database connection, it will print "Error in connection" and print the stack trace.
		  * @param RoomNBR
		  */
	 public static void SearchRoom(String RoomNBR) {
		 try {	 
			 String query3 = "SELECT * FROM rooms WHERE numRoom=?";
	 			PreparedStatement preparedStmt2 = hotelModel.connectionToDB().prepareStatement(query3);
	 			preparedStmt2.setString (1,RoomNBR);
	 		 	ResultSet rs2 = preparedStmt2.executeQuery();
	 		 	Rooms = FXCollections.observableArrayList();
	 		 	System.out.println("SELECT * FROM rooms WHERE numRoom=?");
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
	 				 rooms Room = new rooms(ID,numRoom, numFloor, typeRoom, viewRoom,statueRoom,cleaningRoom, techProbs, priceRoom) ;
	 				Rooms.add(Room);	

	 			}
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /**
	  * This is a  method called "getRoomsSearched" that returns an observableArrayList called "Rooms". 
	  * @return
	  */
	 public static ObservableList<rooms> getRoomsSearched(){
		 return Rooms;
	 }
	 
	 
	 /**
	  * This is a Java method called "changeStatutRoom" that takes in a single parameter, a "rooms" object called "RoomObj". 
	    The method updates the statue column of the rows in the "rooms" table where the id of the room is equal to the id of
	    the passed in room object. It does this by creating a SQL query that uses a prepared statement to update the table 
	    and set the statue column to the statue attribute of the passed in room object. The method then closes the database
	    connection and the prepared statement. If there is an error with the database connection, it will print "Error in connection" 
	    and print the stack trace.
	  * @param RoomObj
	  */
	 public static void changeStatutRoom(rooms RoomObj) {
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE rooms SET "
			    		+"statue = ?  WHERE idRoom=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, RoomObj.getStatueRoom());
			    preparedStmt.setInt (2, RoomObj.getIdRoom());

			  

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
	  * This is a Java method called "setRoomempty" that takes in a single parameter, an integer called "numR". 
	    The method updates the statue column of the rows in the "rooms" table where the numRoom of the room is 
	    equal to the passed in integer. It does this by creating a SQL query that uses a prepared statement
	    to update the table and set the statue column to "empty" where the numRoom is equal to the passed in integer. 
	    Then the method closes the database connection and the prepared statement. If there is an error with the 
	    database connection, it will print "Error in connection" and print the stack trace.
	  * @param numR
	  */
	 public static void setRoomempty(int numR) {
		 
		 try {	  
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE rooms SET "
			    		+"statue = ?  WHERE numRoom=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, "empty");
			    preparedStmt.setInt (2, numR);

			  

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
	  * This code is updating the status of a room in a database table called "rooms", using the numRoom column as a reference.
	    The status is being set to "occupied" when this method is called. It uses a prepared statement to prevent SQL injection attacks,
	    and the connection to the database is closed after the update is executed. The method also includes try-catch block to handle
	    any exceptions that may occur during the process.
	  * @param numR
	  */
	public static void setRoomOccupied(int numR) {
			 
			 try {	  
					System.out.println("Logged succ to try");
				    String query1 = "UPDATE rooms SET "
				    		+"statue = ?  WHERE numRoom=?";
				    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
				    preparedStmt.setString (1, "occupied");
				    preparedStmt.setInt (2, numR);
	
				  
	
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
