package models;
import java.sql.PreparedStatement;

import javafx.beans.property.*;

public class bookingActivities {
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleStringProperty activity = new SimpleStringProperty();
	private SimpleStringProperty client = new SimpleStringProperty();
	private SimpleStringProperty now = new SimpleStringProperty();

	
	public bookingActivities(String activity, String client, String now) {
		super();
		this.ID.set(0);
		this.activity.set(activity);
		this.client.set(client);
		this.now.set(now);
	}
	public bookingActivities(int ID,String activity, String client, String now) {
		super();
		this.ID.set(ID);
		this.activity.set(activity);
		this.client.set(client);
		this.now.set(now);
	}
	public int getID() {
		return ID.get();
	}
	public void setID(int ID) {
		this.ID.set(ID);
	}
	public String getActivity() {
		return activity.get();
	}
	public void setActivity(String activity) {
		this.activity.set(activity);
	}
	public String getClient() {
		return client.get();
	}
	public void setClient(String client) {
		this.client.set(client);
	}
	public String getNow() {
		return now.get();
	}
	public void setNow(String now) {
		this.now.set(now);
	}
	
	
	

	 public static void saveNewBookingActivityM(bookingActivities  bookingActivities) {		 
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "INSERT INTO bookingactivities(Activity,clientId,dateBookingActivity) " + " VALUES (?,?,?)";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
		
			    preparedStmt.setString (1, bookingActivities.getActivity());
			    preparedStmt.setString (2, bookingActivities.getClient());
			    preparedStmt.setString (3, bookingActivities.getNow());

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
			    String query1 = "UPDATE bookingactivities"
			    		+ "SET clientId = ? " 		+ "SET Activity = ? " 	+ "SET dateBookingActivity = ? "
			    		+ "WHERE idActivity=? ";
			    PreparedStatement preparedStmt = hotelModel.connectionToDB().prepareStatement(query1);
			    preparedStmt.setString (1, bookingActivities.getClient());
			    preparedStmt.setString (2, bookingActivities.getActivity());
			    preparedStmt.setString (3, bookingActivities.getNow());
			    preparedStmt.setInt (4, bookingActivities.getID());
			    
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
