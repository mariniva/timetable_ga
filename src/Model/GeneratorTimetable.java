package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GeneratorTimetable {
    private StringProperty style;
    private SimpleIntegerProperty shift;
    private SimpleIntegerProperty group;
    private StringProperty room;
    private StringProperty trainer;
    private StringProperty timeslot;
    private IntegerProperty generatorID;

    public GeneratorTimetable (int generatorID, String style, int shift, int group, String room, String trainer, String timeslot){
        this.style = new SimpleStringProperty(style);
        this.shift = new SimpleIntegerProperty(shift);
        this.group = new SimpleIntegerProperty(group);
        this.room = new SimpleStringProperty(room);
        this.trainer = new SimpleStringProperty(trainer);
        this.timeslot = new SimpleStringProperty(timeslot);
        this.generatorID = new SimpleIntegerProperty(generatorID);
    }
    public StringProperty classStyle(){
        return style;
    }
    public IntegerProperty classShift(){
        return shift;
    }
    public IntegerProperty classGroup(){
        return group;
    }
    public StringProperty classRoom(){
        return room;
    }
    public StringProperty classTrainer(){
        return trainer;
    }
    public StringProperty classTimeslot(){
        return timeslot;
    }
}
