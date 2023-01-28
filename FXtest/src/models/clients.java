package models;
import java.sql.PreparedStatement;

import javafx.beans.property.*;

public class clients {
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleStringProperty fullName = new SimpleStringProperty();
	private SimpleStringProperty cin = new SimpleStringProperty();
	private SimpleStringProperty sexe = new SimpleStringProperty();
	private SimpleStringProperty nationality = new SimpleStringProperty();
	private SimpleStringProperty phone = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	public clients(String fullName, String cin, String sexe, String nationality, String phone,String email) {
		super();
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
			    String query1 = "UPDATE Clients SET "
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
	
}
