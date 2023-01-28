package models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class clients {
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleStringProperty fullName = new SimpleStringProperty();
	private SimpleStringProperty cin = new SimpleStringProperty();
	private SimpleStringProperty sexe = new SimpleStringProperty();
	private SimpleStringProperty nationality = new SimpleStringProperty();
	private SimpleStringProperty phone = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	private static ObservableList<clients> Clients;
	
	
	public clients(String fullName, String cin, String sexe, String nationality, String phone,String email) {
		super();
		this.ID.set(0);
		this.fullName.set(fullName);
		this.cin.set(cin);
		this.sexe.set(sexe);
		this.nationality.set(nationality);
		this.phone.set(phone);
		this.email.set(email);
	}
	
	/**
	 * This is a constructor method for a class called "clients." It initializes the ID, fullName, cin, sexe, 
	   nationality, phone, and email fields of an object of the class with the corresponding parameters passed to the constructor. 
	 * @param id
	 * @param fullName
	 * @param cin
	 * @param sexe
	 * @param nationality
	 * @param phone
	 * @param email
	 */
	public clients(int id,String fullName, String cin, String sexe, String nationality, String phone,String email) {
		super();
		this.ID.set(id);
		this.fullName.set(fullName);
		this.cin.set(cin);
		this.sexe.set(sexe);
		this.nationality.set(nationality);
		this.phone.set(phone);
		this.email.set(email);
	}
	public int getID() {
		return ID.get();
	}
	public void setID(int ID) {
		this.ID.set(ID);
	}
	public String getFullName() {
		return fullName.get();
	}
	public void setFullName(String fullName) {
		this.fullName.set(fullName);
	}
	/**
	 * getter of client's CIN
	 * @return
	 */
	public String getCin() {
		return cin.get();
	}
	/**
	 * setter of client's CIN
	 * @param cin
	 */
	public void setCin(String cin) {
		this.cin.set(cin);
	}
	public String getSexe() {
		return sexe.get();
	}
	public void setSexe(String sexe) {
		this.sexe.set(sexe);
	}
	public String getNationality() {
		return nationality.get();
	}
	public void setNationality(String nationality) {
		this.nationality.set(nationality);
	}
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	
	public String getEmail() {
		return email.get();
	}
	public void setEmaik(String email) {
		this.email.set(email);
	}
	/**
	 * This is a method called "saveNewClientM" that appears to take an object of the "clients" class as a parameter.
	   This method uses a prepared statement to insert a new client record into a database table named "clients".
       It calls the 'connectionToDB()' method to connect to the database, creates a prepared statement with a SQL
       query to insert the client information into the clients table. Then it set the values of the prepared statement 
       with the information from the 'ClientsObj' object passed as a parameter.It then calls the 'executeUpdate()' 
       method to execute the prepared statement, which will insert the new client record into the database.
       Finally, it closes the connection to the database and prepared statement. If there is any error in connecting 
       to the database the catch block will handle it and print the error message.
	 * @param ClientsObj
	 */
	 public static void saveNewClientM(clients ClientsObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO clients (fullName,cin,sexe,nationality,email,phone) " + " VALUES (?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, ClientsObj.getFullName());
			    preparedStmt.setString (2, ClientsObj.getCin());
			    preparedStmt.setString (3, ClientsObj.getSexe());
			    preparedStmt.setString (4, ClientsObj.getNationality());
			    preparedStmt.setString (5, ClientsObj.getEmail());
			    preparedStmt.setString (6, ClientsObj.getPhone());
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
	  * This method is called "ModifyClientM" and it appears to take an object of the "clients" class as a parameter.
		This method is similar to the saveNewClientM, but it updates an existing client record in the database table
	    named "clients". It also uses a prepared statement to update the client record.
        It calls the 'connectionToDB()' method to connect to the database, creates a prepared statement with a SQL query
        to update the client information in the clients table. Then it set the values of the prepared statement with 
        the information from the 'ClientsObj' object passed as a parameter. The final value to be set is the 'idClient' field, 
        which is used as the WHERE clause for the update statement.
		It then calls the 'executeUpdate()' method to execute the prepared statement, which will update the client record in 
		the database. Finally, it closes the connection to the database and prepared statement. 
		If there is any error in connecting to the database the catch block will handle it and print the error message.
	  * @param ClientsObj
	  */
	 public static void ModifyClientM(clients ClientsObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE clients SET "
			    		+ "fullName=?,cin=?,sexe=?,nationality=?,email=?,phone=?  WHERE idClient=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, ClientsObj.getFullName());
			    preparedStmt.setString (2, ClientsObj.getCin());
			    preparedStmt.setString (3, ClientsObj.getSexe());
			    preparedStmt.setString (4, ClientsObj.getNationality());
			    preparedStmt.setString (5, ClientsObj.getEmail());
			    preparedStmt.setString (6, ClientsObj.getPhone());
			    preparedStmt.setInt (7, ClientsObj.getID());
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
	  * This code  is a  method called "SearchClient" that takes in a parameter of type String called "CIN". 
	    The method uses a prepared statement to execute a SQL query that selects all columns from the "clients" table 
	    where the "cin" column is equal to the passed in "CIN" parameter. The results of the query are stored in a 
	    ResultSet object called "rs3". The method then creates an observable array list called "Clients" and loops 
	    through the ResultSet, creating new client objects with the retrieved information and adding them to the "Clients" list. 
	    If there is an exception, it will print "Error in connection" and the stack trace of the error.
	  * @param CIN
	  */
	 public static void SearchClient(String CIN) {
		 try {	 
			 String query3 = "SELECT * FROM clients WHERE cin=?";
	 			PreparedStatement preparedStmt3 = hotelModel.connectionToDB().prepareStatement(query3);
	 			preparedStmt3.setString (1, CIN);
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
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 /**
	  * This code  is a method called "getClientsSearched" that returns an ObservableList of "clients" objects. 
	  * @return
	  */
	 public static ObservableList<clients> getClientsSearched(){
		 return Clients;
	 }
	
}
