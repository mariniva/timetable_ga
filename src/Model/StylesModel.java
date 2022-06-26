package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StylesModel {
	private final IntegerProperty styleId;
	private final StringProperty styleCode;
	private final StringProperty styleName;
	private int[] trainersIds;

	public StylesModel(int styleId, String styleCode, String styleName, int[] trainersIds) {
		this.styleId = new SimpleIntegerProperty(styleId);
		this.styleCode = new SimpleStringProperty (styleCode);
		this.styleName = new SimpleStringProperty(styleName);
		this.trainersIds = trainersIds;
	}


	public IntegerProperty getStyleId() {
		return styleId;
	}

	public StringProperty getStyleCode() {
		return styleCode;
	}

	public StringProperty getStyleName() {
		return styleName;
	}

	
//	public int[] getTrainersIds() {
//		return trainersIds;
//	}

	public int getRandomTrainerId() {
		int trainersId = trainersIds[(int) (trainersIds.length * Math.random())];
		return trainersId;
	}

//    @Override
//    public String toString() {
//        return getStyleName() + " " + getStyleId();
//    }
    
}
