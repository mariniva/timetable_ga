package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClassModel {
	 	private int classId;
	    private int groupId;
	    private int styleId;
	    private int trainerId;
	    private int timeslotId;
	    private int roomId;
		private IntegerProperty ID;
		private StringProperty day = new SimpleStringProperty("day");


//	    /**
//	     * Initialize new Class
//	     *
//	     * @param classId
//	     * @param groupId
//	     * @param moduleId
//	     */
	public ClassModel(int id){
		ID = new SimpleIntegerProperty(id);
	}

	public ClassModel(int classId, int groupId, int styleId){
	        this.classId = classId;
	        this.styleId = styleId;
	        this.groupId = groupId;
	    }
	    
//	    /**
//	     * Add professor to class
//	     *
//	     * @param professorId
//	     */
	    public void addTrainer(int trainerId){
	        this.trainerId = trainerId;
	    }


		public StringProperty classDay() {
			return day;
		}
	    /**
	     * Add timeslot to class
	     * 
	     * @param timeslotId
	     */
	    public void addTimeslot(int timeslotId){
	        this.timeslotId = timeslotId;
	    }    
	    
	    /**
	     * Add room to class
	     * 
	     * @param roomId
	     */
	    public void setRoomId(int roomId){
	        this.roomId = roomId;
	    }
	    
	    /**
	     * Get classId
	     * 
	     * @return classId
	     */
	    public int getClassId(){
	        return this.classId;
	    }
	    
	    /**
	     * Get groupId
	     * 
	     * @return groupId
	     */
	    public int getGroupId(){
	        return this.groupId;
	    }
	    
	    /**
	     * Get moduleId
	     * 
	     * @return moduleId
	     */
	    public int getStyleId(){
	        return this.styleId;
	    }

	public int getID() {
		return ID.get();
	}
	    /**
	     * Get professorId
	     * 
	     * @return professorId
	     */
	    public int getTrainerId(){
	        return this.trainerId;
	    }
	    
	    /**
	     * Get timeslotId
	     * 
	     * @return timeslotId
	     */
	    public int getTimeslotId(){
	        return this.timeslotId;
	    }
	    
	    /**
	     * Get roomId
	     * 
	     * @return roomId
	     */
	    public int getRoomId(){
	        return this.roomId;
	    }
}
