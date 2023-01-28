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
	public clients(String fullName, String cin) {
		super();
		this.ID.set(0);
		this.fullName.set(fullName);
		this.cin.set(cin);
		this.sexe.set("0");
		this.nationality.set("0");
		this.phone.set("0");
		this.email.set("0");
	}
	
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
	public String getCin() {
		return cin.get();
	}
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
	 
	 public static ObservableList<clients> getClientsSearched(){
		 return Clients;
	 }
	
}
