package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TrainersModel {
	private final IntegerProperty trainerId;
	private final StringProperty trainerName;

	public TrainersModel(int trainerId, String trainerName) {
		this.trainerId = new SimpleIntegerProperty(trainerId);
		this.trainerName = new SimpleStringProperty(trainerName) ;
	}

	public IntegerProperty getTrainerId() {
		return this.trainerId;
	}

	public StringProperty getTrainerName() {
		return this.trainerName;
	}
	    
	    @Override
	    public String toString(){
	        String str = "";
	        try {

	            str =  getTrainerId() + "_" + getTrainerName() + "_ ";
	        }catch (NullPointerException e){
	            str= "Empty";
	        }
	        return str;
	    }
}
