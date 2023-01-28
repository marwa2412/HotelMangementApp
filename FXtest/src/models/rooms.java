package models;

import java.sql.PreparedStatement;

import javafx.beans.property.*;

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
	
	public rooms(int numRoom, int numFloor, String typeRoom, String viewRoom, String statueRoom, boolean cleaningRoom, String techProbs, int priceRoom) {
        super();
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

	public String getViewRoom() {
		return viewRoom.get();
	}

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
	 
	 public static void ModifyRoomM(rooms RoomObj) {
		 try {	 
				System.out.println("Logged succ to try");
			    String query1 = "UPDATE rooms SET"
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
	
}
