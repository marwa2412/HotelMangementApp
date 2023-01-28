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
	private static ObservableList<activities> Activities;

	public activities(String type, String date, int time, int persons, String spot,int price) {
		super();
		this.ID.set(0);
		this.type.set(type);
		this.date.set(date);
		this.time.set(time);
		this.persons.set(persons);
		this.spot.set(spot);
		this.price.set(price);
	}
	public activities(String type, String date) {
		super();
		this.ID.set(0);
		this.type.set(type);
		this.date.set(date);
		this.time.set(0);
		this.persons.set(0);
		this.spot.set("0");
		this.price.set(0);
	}
	
	public activities(int id,String type, String date, int time, int persons, String spot,int price) {
		super();
		this.ID.set(id);
		this.type.set(type);
		this.date.set(date);
		this.time.set(time);
		this.persons.set(persons);
		this.spot.set(spot);
		this.price.set(price);
	}
	public int getID() {
		return ID.get();
	}
	public void setID(int ID) {
		this.ID.set(ID);
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
	
	 
	 

	 public static void saveNewActivityM(activities activityObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO activities (typeActivity,dateActivity,timeActivity,spot,persons,price) " + " VALUES (?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, activityObj.getType());
			    preparedStmt.setString (2, activityObj.getDate());
			    preparedStmt.setInt (3, activityObj.getTime());
			    preparedStmt.setString (4, activityObj.getSpot());
			    preparedStmt.setInt (5, activityObj.getPersons());
			    preparedStmt.setInt (6, activityObj.getPrice());
			 
			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	 public static void ModifyActivityM(activities activityObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE activities SET typeActivity=?, dateActivity=?, timeActivity=?, spot=?, persons=?, price=? WHERE idActivity=?";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, activityObj.getType());
			    preparedStmt.setString (2, activityObj.getDate());
			    preparedStmt.setInt (3, activityObj.getTime());
			    preparedStmt.setString (4, activityObj.getSpot());
			    preparedStmt.setInt (5, activityObj.getPersons());
			    preparedStmt.setInt (6, activityObj.getPrice());
			    preparedStmt.setInt (7, activityObj.getID());

			 
			    preparedStmt.executeUpdate();
			    hotelModel.connectionToDB().close();
			    preparedStmt.close();		    
			    System.out.println("Connected Successfully");

				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	 
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
				 	activities activityObj = new activities(ID,type,  date,  time,  persons,  spot, price);
				 	Activities.add(activityObj);	

	 			}
	 		 
				}catch(Exception e){
				    System.out.println("Error in connection");
				e.printStackTrace();
				}
	 }
	 
	 public static ObservableList<activities> getActivitiesSearched(){
		 return Activities;
	 }
}
