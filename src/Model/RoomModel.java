package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomModel {
	private final IntegerProperty roomId;
	private final String roomNumber;
	private final IntegerProperty capacity;

	    public RoomModel(int roomId, String roomNumber, int capacity){
	    	this.roomId = new SimpleIntegerProperty(roomId);
			this.roomNumber = roomNumber;
			this.capacity = new SimpleIntegerProperty(capacity) ;
	    }

	    public int getRoomId() {
			return this.roomId.get();
		}

		public String getRoomNumber() {
			return this.roomNumber;
		}

		public int getRoomCapacity() {
			return this.capacity.get();
		}


//	public String getRoomNumber() {
//		return this.roomNumber.get();
//	}
//
//	public int getRoomCapacity() {
//		return this.capacity.get();
//	}

//	    @Override
//	    public String toString() {
//	        return getRoomNumber();
//	    }
	    
}
