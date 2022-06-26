package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupsModel {
	private int groupId;
	private int groupShift;
	private int groupSize;
	private int[] styleId;

	public GroupsModel(int groupId, int groupShift, int groupSize, int[] styleId) {
		this.groupId = groupId;
		this.groupShift = groupShift;
		this.groupSize = groupSize;
		this.styleId = styleId;
	}

	public int getGroupId() {
		return groupId;
	}
	
	public int getGroupShift() {
		return groupShift;
	}

	public int getGroupSize() {
		return groupSize;
	}

//	метод который добавляет id стиля в список, который должен быть добавлен в таблицу
//	public static void setStylesCheckBox(HashMap<String,Boolean> stylesMap){
//		stylesCheckBox.clear();
//		if(stylesMap.get("hiphop")){
//			stylesCheckBox.add("1");
//		}
//		if(stylesMap.get("breyk")){
//			stylesCheckBox.add("2");
//		}
//		if(stylesMap.get("stratching")){
//			stylesCheckBox.add("3");
//		}
//		if(stylesMap.get("fitness")){
//			stylesCheckBox.add("4");
//		}
//		if(stylesMap.get("actermaster")){
//			stylesCheckBox.add("5");
//		}
//		if(stylesMap.get("acrobatic")){
//			stylesCheckBox.add("6");
//		}
//		if(stylesMap.get("sovremen")){
//			stylesCheckBox.add("7");
//		}
//	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setGroupShift(int groupShift) {

		this.groupShift = groupShift;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int[] getStyleIds() {
		return styleId;
	}
}


//	public IntegerProperty getStyleIds() {
//		return this.styleId;
//	}



	   


